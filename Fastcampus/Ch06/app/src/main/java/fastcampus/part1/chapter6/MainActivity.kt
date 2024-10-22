package fastcampus.part1.chapter6

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import fastcampus.part1.chapter6.databinding.ActivityMainBinding
import fastcampus.part1.chapter6.databinding.DialogCountdownSettingBinding
import java.util.Timer
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var countdownSecond = 10
    private var currentCountdownDeciSecond = countdownSecond * 10
    private var currentDeciSecond = 0
    private var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.countTextView.setOnClickListener {
            showCountdownSettingDialog()
        }

        binding.playButton.setOnClickListener {
            start()
            binding.playButton.isVisible = false
            binding.stopButton.isVisible = false
            binding.pauseButton.isVisible = true
            binding.lapButton.isVisible = true
        }

        binding.pauseButton.setOnClickListener {
            pause()
            binding.playButton.isVisible = true
            binding.stopButton.isVisible = true
            binding.pauseButton.isVisible = false
            binding.lapButton.isVisible = false
        }

        binding.stopButton.setOnClickListener {
            showAlertDialog()
        }

        binding.lapButton.setOnClickListener {
            lap()
            binding.playButton.isVisible = false
            binding.stopButton.isVisible = false
            binding.pauseButton.isVisible = true
            binding.lapButton.isVisible = true
        }
        initCountdownViews()
    }

    private fun initCountdownViews() {
        binding.countTextView.text = String.format("%02d", countdownSecond)
        binding.progressBar.progress = 100
        binding.lapButton.isVisible = false
        binding.stopButton.isVisible = false
    }

    private fun start() {
        timer = timer(initialDelay = 0, period = 100) {
            if (currentCountdownDeciSecond == 0) {
                currentDeciSecond += 1
                val min = currentDeciSecond.div(10) / 60
                val sec = currentDeciSecond.div(10) % 60
                val deciSec = currentDeciSecond % 10
                runOnUiThread {
                    binding.timeTextView.text = String.format("%02d:%02d", min, sec)
                    binding.tickTexkView.text = deciSec.toString()

                    binding.countdownGroup.isVisible = false
                }
            } else {
                currentCountdownDeciSecond -= 1
                val sec = currentCountdownDeciSecond / 10
                val progress = (currentCountdownDeciSecond / (countdownSecond * 10f)) * 100

                binding.root.post {
                    binding.countTextView.text = String.format("%02d", sec)
                    binding.progressBar.progress = progress.toInt()
                }
            }
            if (currentDeciSecond == 0 && currentCountdownDeciSecond < 31 && currentCountdownDeciSecond % 10 == 0){
                val toneType = if (currentCountdownDeciSecond == 0) ToneGenerator.TONE_CDMA_HIGH_L else ToneGenerator.TONE_CDMA_ANSWER
                ToneGenerator(AudioManager.STREAM_ALARM, ToneGenerator.MAX_VOLUME)
                    .startTone(toneType, 100)
            }
        }
    }

    private fun pause() {
        timer?.cancel()
        timer = null
    }

    private fun stop() {
        binding.playButton.isVisible = true
        binding.stopButton.isVisible = false
        binding.pauseButton.isVisible = false
        binding.lapButton.isVisible = true

        currentDeciSecond = 0
        binding.timeTextView.text = "00:00"
        binding.tickTexkView.text = "0"

        binding.countdownGroup.isVisible = true
        initCountdownViews()
        binding.linearLayout.removeAllViews()
    }

    private fun lap() {
        if (currentDeciSecond == 0) return
        val container = binding.linearLayout
        TextView(this).apply {
            textSize = 20f
            gravity = Gravity.CENTER
            val min = currentDeciSecond.div(10) / 60
            val sec = currentDeciSecond.div(10) % 60
            val deciSec = currentDeciSecond % 10
            text = "${container.childCount.inc()}. " + String.format(
                "%02d:%02d %01d",
                min,
                sec,
                deciSec
            )
            setPadding(30)
        }.let { lapTextView ->
            container.addView(lapTextView, 0)
        }
    }

    private fun showCountdownSettingDialog() {
        AlertDialog.Builder(this).apply {
            val dialogBinding = DialogCountdownSettingBinding.inflate(layoutInflater)
            with(dialogBinding.countdownSecondPicker) {
                maxValue = 20
                minValue = 0
                value = countdownSecond
            }
            setTitle("카운트다운 설정")
            setView(dialogBinding.root)
            setPositiveButton("확인") { _, _ ->
                countdownSecond = dialogBinding.countdownSecondPicker.value
                currentCountdownDeciSecond = countdownSecond * 10
                binding.countTextView.text = String.format("%02d", countdownSecond)
            }
            setNegativeButton("취소", null)
        }.show()
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(this).apply {
            setMessage("종료하시겠습니까?")
            setPositiveButton("네") { _, _ ->
                stop()
            }
            setNegativeButton("아니요", null)
        }.show()
    }

}