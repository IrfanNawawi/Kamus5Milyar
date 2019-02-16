package com.example.irfannawawi.kamus5milyar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    public static final String DATA_KATA = "data_kata";
    public static final String DATA_ARTI = "data_arti";
    private TextView kata, arti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        kata = findViewById(R.id.tv_kata);
        arti = findViewById(R.id.tv_arti);
        kata.setText(getIntent().getStringExtra(DATA_KATA));
        arti.setText(getIntent().getStringExtra(DATA_ARTI));
    }
}
