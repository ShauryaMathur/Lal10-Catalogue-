package com.shaurya.lal10catalogue;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmailPopup extends Activity {

    private EditText mEditTextMessage;
    private Button sendbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_popup);

        mEditTextMessage=findViewById(R.id.message);
        sendbtn=findViewById(R.id.sendbtn);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
                finish();
            }
        });
    }

    private void sendMail() {

        String recipient= getString(R.string.recipientemail);
        String[] recipients = recipient.split(",");

        String message = mEditTextMessage.getText().toString();

        String subject=getString(R.string.subject);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an E-mail client"));
    }
}
