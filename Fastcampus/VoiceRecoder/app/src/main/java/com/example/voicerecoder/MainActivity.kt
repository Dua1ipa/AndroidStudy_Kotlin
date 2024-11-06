package com.example.voicerecoder

import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Color
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.voicerecoder.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineStart
import java.io.IOException

class MainActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_RECORD_AUDIO_CODE = 200
    }

    private enum class State {
        RELEASE, RECORDING, PLAYING
    }

    private var state: State = State.RELEASE
    private lateinit var binding: ActivityMainBinding
    private var recorder: MediaRecorder? = null
    private var fileName: String = ""
    private var player: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fileName = "${externalCacheDir?.absolutePath}/audiorecordtest.3gp"

        binding.playButton.setOnClickListener {  //재생 버튼 눌렀을 때
            when (state) {
                State.RELEASE -> {  //시작하면
                    onPlay(true)
                }

                else -> {

                }
            }
        }
        binding.recordButton.setOnClickListener {  //녹음 버튼 눌렀을 때
            when (state) {
                State.RELEASE -> {  //시작하면
                    record()
                }

                State.RECORDING -> {  //녹음 중이라면
                    onRecord(false)
                }

                else -> {

                }
            }

        }
        binding.stopButton.setOnClickListener {  //정지 버튼을 눌렀을 때
            when (state) {
                State.PLAYING -> {
                    onPlay(false)
                }

                else -> {

                }
            }
        }
    }

    private fun record() {
        when {
            //권한이 허용 되었는가?
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED -> {
                onRecord(true)
            }

            // 사용자에게 권한 요청 설명이 필요한가?
            ActivityCompat.shouldShowRequestPermissionRationale(
                this, android.Manifest.permission.RECORD_AUDIO
            ) -> {
                showPermissionRationalDialog()
            }

            // 권한이 없고 권한 요청 설명이 필요한 경우
            else -> {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.RECORD_AUDIO),
                    REQUEST_RECORD_AUDIO_CODE
                )
            }
        }
    }

    // 녹음 기능 함수 //
    private fun onRecord(start: Boolean) = if (start) startRecording() else stopRecording()

    // 재생 기능 함수 //
    private fun onPlay(start: Boolean) = if (start) startPlaying() else stopPlaying()

    // 녹음 시작 함수 //
    private fun startRecording() {
        state = State.RECORDING
        recorder = MediaRecorder()
            .apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)           //마이크 사용
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)   //녹음 파일 포맷
                setOutputFile(fileName)                                 //녹음 파일 이름
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)      //녹음 파일 인코더
                try {
                    prepare()
                } catch (e: IOException) {
                    Log.e("onRecord", "파일 저장 오류입니다.")
                }
                start()
            }
        binding.recordButton.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.pause)
        )
        binding.recordButton.imageTintList = ColorStateList.valueOf(Color.BLACK)
        binding.playButton.isEnabled = false
        binding.playButton.alpha = 0.3f
    }

    // 녹음 정지 함수 //
    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
        state = State.RELEASE

        binding.recordButton.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.circle)
        )

        binding.recordButton.imageTintList =
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.Red))
        binding.playButton.isEnabled = true
        binding.playButton.alpha = 1.0f
    }

    // 재생 기능 함수 //
    private fun startPlaying() {
        state = State.PLAYING

        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
            } catch (e: IOException) {
                Log.e("onPlay", "재생할 파일이 없습니다.")
            }
            start()
        }
        player?.setOnCompletionListener {
            stopPlaying()
        }
        binding.recordButton.isEnabled = false
        binding.recordButton.alpha = 0.3f
    }

    private fun stopPlaying() {
        state = State.RELEASE
        player?.release()
        player = null

        binding.recordButton.isEnabled = true
        binding.recordButton.alpha = 1.0f
    }

    // 권한 요청 설명 다이얼로그 함수 //
    private fun showPermissionRationalDialog() {
        AlertDialog.Builder(this)
            .setTitle("녹음 권한")
            .setPositiveButton("권한 허용하기") { _, _ ->
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.RECORD_AUDIO),
                    REQUEST_RECORD_AUDIO_CODE
                )
            }
            .setNegativeButton("권한 취소하기") { dialogInterface, _ ->
                dialogInterface.cancel()
            }.show()
    }

    // 권한 세팅 다이얼로그 함수 //
    private fun showPermissionSettingDialog() {
        AlertDialog.Builder(this)
            .setTitle("녹음 권한")
            .setPositiveButton("권한 변경하러 가기") { _, _ ->
                navigateToAppSetting()
            }
            .setNegativeButton("권한 취소하기") { dialogInterface, _ ->
                dialogInterface.cancel()
            }.show()
    }

    // 인텐드를 사용해서 세팅 창으로 보내기 함수 //
    private fun navigateToAppSetting() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", packageName, null)
        }
        startActivity(intent)
    }

    // 권한 받은 결과 함수 // 
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)

        //받아온 권한을 확인 하기
        val audioRecordPermissionGranted = requestCode == REQUEST_RECORD_AUDIO_CODE
                && grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED
        if (audioRecordPermissionGranted) {  //권한을 허용했다면
            onRecord(true)
        } else {  //권한을 허용하지 않았다면
            // 사용자에게 권한 요청 설명이 필요한가?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    android.Manifest.permission.RECORD_AUDIO
                )
            ) {
                showPermissionRationalDialog()
            } else {
                showPermissionSettingDialog()
            }
        }
    }

}