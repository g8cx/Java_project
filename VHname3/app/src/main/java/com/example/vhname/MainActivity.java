package com.example.vhname;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private String[] names = {"Гром", "Клинок", "Тень", "Свет", "Дух", "Сталь", "Меч", "Щит"};
    private String[] adjectives = {"Грозный", "Могучий", "Свирепый", "Древний", "Бессмертный"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createSimpleUI();
    }

    private void createSimpleUI() {
        // Главный контейнер
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(android.view.Gravity.CENTER);
        layout.setBackgroundColor(0xFF1a1a1a);
        layout.setPadding(50, 50, 50, 50);

        // Текст результата
        resultText = new TextView(this);
        resultText.setText("Нажми кнопку");
        resultText.setTextSize(26);
        resultText.setTextColor(0xFFffd700);
        resultText.setTypeface(null, android.graphics.Typeface.BOLD);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        textParams.bottomMargin = 120;
        resultText.setLayoutParams(textParams);

        // Кнопка
        Button button = new Button(this);
        button.setText("Сгенерировать");
        button.setTextSize(18);
        button.setBackgroundColor(0xFF8b0000);
        button.setTextColor(0xFFFFFFFF);
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(400, 180);
        button.setLayoutParams(btnParams);

        button.setOnClickListener(v -> {
            Random rand = new Random();
            String name = names[rand.nextInt(names.length)];
            String adj = adjectives[rand.nextInt(adjectives.length)];
            int num = rand.nextInt(1000);
            resultText.setText(adj + name + num);
        });

        layout.addView(resultText);
        layout.addView(button);
        setContentView(layout);
    }
}