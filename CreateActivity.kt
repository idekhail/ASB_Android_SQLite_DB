package com.idekhail.asb_sqlite_db1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)

        val create= findViewById<Button>(R.id.create)
        val cancel = findViewById<Button>(R.id.cancel)

        // Create User
        create.setOnClickListener {
            if(username.text.toString().isEmpty() || password.text.toString().isEmpty()){
                Toast.makeText(this, "fields cannot be empty", Toast.LENGTH_SHORT).show()
            }else{
                val user = UserOperation(this)
                user.createUser(username.text.toString().trim(),password.text.toString().trim())
                var i = Intent(this, LoginActivity::class.java)
                startActivity(i)
            }
        }

        cancel.setOnClickListener{
                var i = Intent(this, LoginActivity::class.java)
                startActivity(i)
        }
    }
}