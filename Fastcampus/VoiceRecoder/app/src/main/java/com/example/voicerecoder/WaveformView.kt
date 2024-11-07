package com.example.voicerecoder

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class WaveformView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val ampList = mutableListOf<Float>()   //진폭값을 저장
    private val rectList = mutableListOf<RectF>()  //좌표값을 저장
    private val rectWidth = 10f
    private var tick = 0

    private val redPaint = Paint().apply {         //파형을 그릴 색 설정 (빨간색)
        color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (rectF in rectList) {
            canvas?.drawRect(rectF, redPaint)
        }
    }

    fun addAmplitude(maxAmplitude: Float) {

        ampList.add(maxAmplitude)
        rectList.clear()

        val maxRect = (this.width / rectWidth).toInt()

        val amps = ampList.takeLast(maxRect)

        for ((i, amp) in amps.withIndex()) {
            val rectF = RectF()
            rectF.top = 0f
            rectF.bottom = amp
            rectF.left = i * rectWidth
            rectF.right = rectF.left + rectWidth

            rectList.add(rectF)
        }


    }

    fun replayAmplitude(duration: Int) {
        rectList.clear()

        val maxRect = (this.width / rectWidth).toInt()
        val amps = ampList.take(tick).takeLast(maxRect)

        for ((i, amp) in amps.withIndex()) {
            val rectF = RectF()
            rectF.top = 0f
            rectF.bottom = amp
            rectF.left = i * rectWidth
            rectF.right = rectF.left + rectWidth

            rectList.add(rectF)
        }
        tick++
        invalidate()  //뷰를 다시 그리기
    }

    fun clearData(){
        ampList.clear()
    }

    fun clearWave(){
        rectList.clear()
        tick = 0
        invalidate()
    }

}
