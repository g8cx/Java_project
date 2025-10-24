package com.example.truegame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textVopros;
    Button buttonPravda, buttonDeistvie;
    Random random = new Random();

    String[] pravdaVoprosy = {
            "Ты когда-нибудь бухал?",
            "Ты мылся?",
            "У тебя есть секрет, который никто не знает?"
    };

    String[] deistvieZadaniya = {
            "Съешь ежа",
            "Сделай 300 отжиманий",
            "Позвони хоть кому то"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 50, 50, 50);

        textVopros = new TextView(this);
        textVopros.setText("Нажми кнопку для задания");
        textVopros.setTextSize(18);
        layout.addView(textVopros);

        buttonPravda = new Button(this);
        buttonPravda.setText("Правда");
        layout.addView(buttonPravda);

        buttonDeistvie = new Button(this);
        buttonDeistvie.setText("Действие");
        layout.addView(buttonDeistvie);

        setContentView(layout);

        buttonPravda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = random.nextInt(pravdaVoprosy.length);
                textVopros.setText(pravdaVoprosy[index]);
            }
        });

        buttonDeistvie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = random.nextInt(deistvieZadaniya.length);
                textVopros.setText(deistvieZadaniya[index]);
            }
        });
    }
}
