package com.daftmobile.a4bhomework3

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.R.attr.data



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EMAIL_RETRIEVER = EmailRetriever.Impl(applicationContext)
        sendMailButton.setOnClickListener(this::pickMail)
    }

    companion object {
        lateinit var EMAIL_RETRIEVER: EmailRetriever
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            val mail = EMAIL_RETRIEVER.retrieve(data!!.data!!)
            //Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            sendMail(mail!!)
        }
    }

    private fun pickMail(view: View) {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Email.CONTENT_URI)
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, 101)
        }
    }

    private fun sendMail(mail: String) {
        val sendIntent = Intent(Intent.ACTION_SENDTO)
        sendIntent.data = Uri.parse("mailto:$mail")
        sendIntent.putExtra("subject", "Wiadomość z pracy domowej")
        sendIntent.putExtra("body", "Treść wiadomości z pracy domowej")
        startActivity(sendIntent)
    }


}
