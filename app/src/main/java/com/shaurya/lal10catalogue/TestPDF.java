package com.shaurya.lal10catalogue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hendrix.pdfmyxml.PdfDocument;
import com.hendrix.pdfmyxml.viewRenderer.AbstractViewRenderer;

import java.io.File;

public class TestPDF extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_pdf);

        AbstractViewRenderer page = new AbstractViewRenderer(TestPDF.this, R.layout.activity_test_pdf) {
            private String _text="Hello";

           /* public void setText(String text) {
                _text = text;
            }*/

            @Override
            protected void initView(View view) {
                TextView tv_hello = (TextView) view.findViewById(R.id.tv_hello);
                tv_hello.setText(_text);
                Log.v("Text->",_text);
            }
        };

        // you can reuse the bitmap if you want
        //page.setReuseBitmap(true);


        PdfDocument doc = new PdfDocument(TestPDF.this);

// add as many pages as you have
        doc.addPage(page);

        doc.setRenderWidth(2115);
        doc.setRenderHeight(1500);
        doc.setOrientation(PdfDocument.A4_MODE.LANDSCAPE);
        doc.setProgressTitle(R.string.gen_please_wait);
        doc.setProgressMessage(R.string.gen_pdf_file);
        doc.setFileName("test");
        doc.setSaveDirectory(TestPDF.this.getExternalFilesDir(null));
        doc.setInflateOnMainThread(false);
        doc.setListener(new PdfDocument.Callback() {
            @Override
            public void onComplete(File file) {
                Log.i(PdfDocument.TAG_PDF_MY_XML, "Complete");
            }

            @Override
            public void onError(Exception e) {
                Log.i(PdfDocument.TAG_PDF_MY_XML, "Error");
            }
        });

        doc.createPdf(TestPDF.this);
    }
}
