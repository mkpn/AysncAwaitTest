package com.example.yoshidamakoto.aysncawaittest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener { onSerialButtonClick() }
    }

    fun onSerialButtonClick() = launch(UI) {
        val button = findViewById<Button>(R.id.button)
        button.isEnabled = false

        button.text = "ああああああああ"

        val ten = AsyncModel.returnTenAsync(button).await()
        val twenty = AsyncModel.returnTwentyAsync(button).await()
        val result = ten * twenty

        button.text = "いいいいいいいいい"

        Toast.makeText(this@MainActivity, "result = $result", Toast.LENGTH_SHORT).show()
        button.isEnabled = true
    }
}


object AsyncModel {
    fun returnTenAsync(button: Button) = async(CommonPool) {
        delay(1000)
        return@async 10
    }

    fun returnTwentyAsync(button: Button) = async(CommonPool) {
        delay(2000)
        return@async 20
    }
}
