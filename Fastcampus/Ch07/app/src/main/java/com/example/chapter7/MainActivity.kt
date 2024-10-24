package com.example.chapter7

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), WordAdapter.ItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var wordAdapter: WordAdapter
    private var selectedWord: Word? = null
    private val updateAddWordResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val isUpdated = result.data?.getBooleanExtra("isUpdated", false) ?: false

            if (result.resultCode == RESULT_OK && isUpdated) {
                updateAddWord()
            }
        }

    private val updateEditWordResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val editWord = result.data?.getParcelableExtra<Word>("editWord") ?: null

            if (result.resultCode == RESULT_OK && editWord != null) {
                updateEditWord(editWord)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        binding.addButton.setOnClickListener {
            Intent(this, AddActivity::class.java).let { updateAddWordResult.launch(it) }
        }
        binding.deleteImageView.setOnClickListener {
            delete()
        }
        binding.editImageView.setOnClickListener {
            edit()
        }
    }

    private fun initRecyclerView() {
        wordAdapter = WordAdapter(mutableListOf(), this)
        binding.wordRecyclerView.apply {
            adapter = wordAdapter
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            val dividerItemDecoration = androidx.recyclerview.widget.DividerItemDecoration(
                applicationContext,
                LinearLayoutManager.VERTICAL
            )
            addItemDecoration(dividerItemDecoration)
        }

        Thread {
            val list = AppDataBase.getInstance(this)?.wordDao()?.getAll() ?: emptyList()
            Thread.sleep(1000)  //1초 대기
            wordAdapter.list.addAll(list)
            runOnUiThread() {  //화면에 다시 로드
                wordAdapter.notifyDataSetChanged()  //부하가 많이 걸림
            }
        }.start()
    }

    override fun onClick(word: Word) {
        selectedWord = word
        binding.textTextView.text = word.text
        binding.meanTextView.text = word.mean
    }

    private fun updateAddWord() {
        Thread {
            AppDataBase.getInstance(this)?.wordDao()?.getLatestWord()?.let { word ->
                wordAdapter.list.add(0, word)
                runOnUiThread { wordAdapter.notifyDataSetChanged() }
            }
        }.start()
    }

    private fun delete() {
        if (selectedWord == null) return
        Thread {
            selectedWord?.let { word ->
                AppDataBase.getInstance(this)?.wordDao()?.delete(word)
                runOnUiThread {
                    wordAdapter.list.remove(word)
                    wordAdapter.notifyDataSetChanged()
                    binding.textTextView.text = ""
                    binding.meanTextView.text = ""
                    Toast.makeText(this, "삭제 완료", Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }

    private fun updateEditWord(word: Word) {
        var index = wordAdapter.list.indexOfFirst { it.id == word.id }
        wordAdapter.list.set(index, word)
        runOnUiThread {
            selectedWord = word
            wordAdapter.notifyDataSetChanged()
            binding.textTextView.text = word.text
            binding.meanTextView.text = word.mean
        }
    }

    private fun edit() {
        if (selectedWord == null) return
        val intent = Intent(this, AddActivity::class.java).putExtra("originalWord", selectedWord)
        updateEditWordResult.launch(intent)
    }

}