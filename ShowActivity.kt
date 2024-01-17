package com.idekhail.asb_sqlite_db1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        val logout = findViewById<Button>(R.id.logout)
        val show = findViewById<TextView>(R.id.show)

        val user = UserOperation(this)

        val data = user.readData()
        show.text = ""
        for (i in 0 until data.size) {
            show.append(
                data[i].id + " " + data[i].uname + " " + data[i].pword + "\n|\r "
            )
        }

        logout.setOnClickListener {
            var i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }



    }
}