package com.secretproject.secretproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mBeerTextView;
    private TextView mBeerTextView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBeerTextView = (TextView)findViewById(R.id.textView);
        mBeerTextView2 = (TextView)findViewById(R.id.textView2);
    }

    public void onClick(View view) {
        mBeerTextView.setText("I");
        mBeerTextView2.setText("BEER");
    }
}
