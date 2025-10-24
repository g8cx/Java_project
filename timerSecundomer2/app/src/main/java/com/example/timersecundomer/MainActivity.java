package com.example.timersecundomer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText inputSeconds;
    private Button startButton, stopButton, resetButton, modeButton;

    private CountDownTimer timer;
    private long timeMillis = 0;
    private boolean isRunning = false;
    private boolean isTimerMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        inputSeconds = findViewById(R.id.inputSeconds);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        resetButton = findViewById(R.id.resetButton);
        modeButton = findViewById(R.id.modeButton);

        updateDisplay();

        startButton.setOnClickListener(v -> start());
        stopButton.setOnClickListener(v -> stop());
        resetButton.setOnClickListener(v -> reset());
        modeButton.setOnClickListener(v -> toggleMode());
    }

    private void start() {
        if (isRunning) return;
        isRunning = true;

        if (isTimerMode) {
            String input = inputSeconds.getText().toString();
            long seconds = 0;
            if (!input.isEmpty()) {
                seconds = Long.parseLong(input);
            }

            if (seconds <= 0) {
                textView.setText("Введите секунды!");
                isRunning = false;
                return;
            }

            timeMillis = seconds * 1000;
            timer = new CountDownTimer(timeMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeMillis = millisUntilFinished;
                    updateDisplay();
                }

                @Override
                public void onFinish() {
                    textView.setText("Время вышло!");
                    isRunning = false;
                }
            }.start();
        } else {
            timer = new CountDownTimer(Long.MAX_VALUE, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeMillis += 1000;
                    updateDisplay();
                }

                @Override
                public void onFinish() {
                }
            }.start();
        }
    }

    private void stop() {
        if (timer != null) {
            timer.cancel();
        }
        isRunning = false;
    }

    private void reset() {
        stop();
        timeMillis = 0;
        updateDisplay();
    }

    private void toggleMode() {
        isTimerMode = !isTimerMode;
        reset();
        inputSeconds.setEnabled(isTimerMode);
        modeButton.setText(isTimerMode ? "Режим: Таймер" : "Режим: Секундомер");
    }

    private void updateDisplay() {
        long seconds = (timeMillis / 1000) % 60;
        long minutes = (timeMillis / 1000) / 60;
        String time = String.format("%02d:%02d", minutes, seconds);
        textView.setText(time);
    }
}
