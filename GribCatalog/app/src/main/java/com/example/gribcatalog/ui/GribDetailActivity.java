package com.example.gribcatalog.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gribcatalog.R;

public class GribDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grib_detail);

        ImageView imageView = findViewById(R.id.imageView);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvDesc = findViewById(R.id.tvDesc);

        tvName.setText(getIntent().getStringExtra("name"));
        tvDesc.setText(getIntent().getStringExtra("desc"));
        imageView.setImageResource(getIntent().getIntExtra("img", 0));
    }
}
