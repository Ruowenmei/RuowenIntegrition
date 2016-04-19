package com.ruowen.listviewdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ruowen.listviewdemo.R;

public class TestActivity extends AppCompatActivity {

    private TextView tvTest;

    private String source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        source = intent.getStringExtra("source");

        setContentView(R.layout.activity_test);

        tvTest = (TextView)findViewById(R.id.tv_test);
        tvTest.setText(source);
    }
}
