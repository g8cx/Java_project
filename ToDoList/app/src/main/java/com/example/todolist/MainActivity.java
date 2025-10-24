package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText vvodZadachi;
    Button knopkaDobavit;
    LinearLayout spisokLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(16,16,16,16);

        // Поле ввода
        vvodZadachi = new EditText(this);
        vvodZadachi.setHint("Новая задача");
        mainLayout.addView(vvodZadachi,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));

        // Кнопка добавления
        knopkaDobavit = new Button(this);
        knopkaDobavit.setText("Добавить");
        mainLayout.addView(knopkaDobavit,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));

        //для списка задач
        ScrollView scrollView = new ScrollView(this);
        spisokLayout = new LinearLayout(this);
        spisokLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(spisokLayout,
                new ScrollView.LayoutParams(
                        ScrollView.LayoutParams.MATCH_PARENT,
                        ScrollView.LayoutParams.WRAP_CONTENT));
        mainLayout.addView(scrollView,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));

        setContentView(mainLayout);

        // Загружаем задачи
        obnovitSpisok();

        knopkaDobavit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zadacha = vvodZadachi.getText().toString();
                if (!zadacha.isEmpty()) {
                    dobavitZadachu(zadacha);
                    vvodZadachi.setText("");
                    obnovitSpisok();
                }
            }
        });
    }

    void dobavitZadachu(String text) {
        String filename = System.currentTimeMillis() + ".txt";
        File file = new File(getFilesDir(), filename);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void obnovitSpisok() {
        spisokLayout.removeAllViews();
        File dir = getFilesDir();
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            String zadacha = readFile(file);
            if (zadacha == null) continue;

            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.HORIZONTAL);

            TextView textView = new TextView(this);
            textView.setText(zadacha);
            textView.setLayoutParams(new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            Button deleteButton = new Button(this);
            deleteButton.setText("Удалить");
            deleteButton.setOnClickListener(v -> {
                file.delete();
                obnovitSpisok();
            });

            itemLayout.addView(textView);
            itemLayout.addView(deleteButton);
            spisokLayout.addView(itemLayout);
        }
    }

    String readFile(File file) {
        try {
            byte[] bytes = new byte[(int) file.length()];
            java.io.FileInputStream fis = new java.io.FileInputStream(file);
            fis.read(bytes);
            fis.close();
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
