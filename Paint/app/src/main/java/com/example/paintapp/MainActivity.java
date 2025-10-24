package com.example.paintapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {


    private DrawingView drawingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawingView = findViewById(R.id.drawing_view);


        ImageButton btnBlack = findViewById(R.id.btn_black);
        ImageButton btnRed = findViewById(R.id.btn_red);
        ImageButton btnBlue = findViewById(R.id.btn_blue);
        ImageButton btnGreen = findViewById(R.id.btn_green);
        ImageButton btnClear = findViewById(R.id.btn_clear);


        btnBlack.setOnClickListener(v -> drawingView.setPaintColor(0xFF000000));
        btnRed.setOnClickListener(v -> drawingView.setPaintColor(0xFFFF0000));
        btnBlue.setOnClickListener(v -> drawingView.setPaintColor(0xFF0000FF));
        btnGreen.setOnClickListener(v -> drawingView.setPaintColor(0xFF00AA00));
        btnClear.setOnClickListener(v -> drawingView.clear());
    }
}