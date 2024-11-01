package com.example.phoneverify.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import androidx.core.view.children
import com.example.phoneverify.databinding.WidgetShuffleNumberKeyboardBinding
import kotlin.random.Random

class ShuffleNumberKeyboard @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    GridLayout(context, attrs, defStyleAttr), View.OnClickListener {
        
    private var _binding: WidgetShuffleNumberKeyboardBinding? = null
    private val binding get() = _binding!!

    private var listener: keyPadListener? = null

    init {
        _binding =
            WidgetShuffleNumberKeyboardBinding.inflate(LayoutInflater.from(context), this, true)
        binding.view = this
        binding.clickListener = this
        shuffle()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        _binding = null
    }

    private fun shuffle() {
        val keyNumberArray = ArrayList<String>()
        for (i in 1..9) {
            keyNumberArray.add(i.toString())
        }
        binding.gridLayout.children.forEach { view ->
            if (view is TextView && view.tag != null) {
                val randIndex = Random.nextInt(keyNumberArray.size)
                view.text = keyNumberArray[randIndex]
                keyNumberArray.removeAt(randIndex)
            }
        }
    }

    fun setKeyPadListener(listener: keyPadListener) {
        this.listener = listener
    }

    fun onClickDelete() {
        listener?.onClickDelete()
    }

    fun onClickDone() {
        listener?.onClickDone()
    }

    interface keyPadListener {
        fun onClickNum(num: String)
        fun onClickDelete()
        fun onClickDone()
    }

    override fun onClick(p0: View?) {
        if (p0 is TextView && p0.tag != null) {
            listener?.onClickNum(p0.toString())
        }
    }
}