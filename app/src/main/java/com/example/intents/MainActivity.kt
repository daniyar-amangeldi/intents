package com.example.intents

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        println("Incoming Intent Data: ${intent.data}")

        val intentData = intent.data
        val coordinates = intent.data?.lastPathSegment ?: return

        findViewById<Button>(R.id.dial_btn).setOnClickListener {
            /**
             * Action dial
             */

            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("770112312345")
            }

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "No Activity Found", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.open_web_btn).setOnClickListener {
            /**
             * Action view
             */

            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://google.com")
            }

            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "No Activity Found", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.send_email_btn).setOnClickListener {
            /**
             * Action sendto
             */

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:example@example.com")
                putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
                putExtra(Intent.EXTRA_TEXT, "Body of the email")
            }

            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "No Activity Found", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.take_picture_btn).setOnClickListener {
            /**
             * Action MediaStore.ACTION_IMAGE_CAPTURE
             */

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "No Activity Found", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.send_info_btn).setOnClickListener {
            /**
             * Action send
             */

            val url = "https://2gis.kz/almaty/geo/123123141324123"

            val intentText = buildString {
                append("Hello, I am testing Intent.ACTION_SEND")
                append("\n")
                append(url)
            }

            val intent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, intentText)
                type = "text/plain"
            }

            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "No Activity Found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
    }
}