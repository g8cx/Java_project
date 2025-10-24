package com.example.qrscanpro100;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

public class MainActivity extends AppCompatActivity {

    CompoundBarcodeView kamera;
    TextView tekst;
    Button knopka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //
        LinearLayout korobka = new LinearLayout(this);
        korobka.setOrientation(LinearLayout.VERTICAL);
        korobka.setPadding(20, 20, 20, 20);

        tekst = new TextView(this);
        tekst.setText("Наведи камеру на QR-код");
        korobka.addView(tekst);

        knopka = new Button(this);
        knopka.setText("Начать");
        korobka.addView(knopka);

        kamera = new CompoundBarcodeView(this);
        korobka.addView(kamera);

        setContentView(korobka);

        // проверяем камеру
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }

        // при нажатии запуск
        knopka.setOnClickListener(v -> {
            kamera.resume();
            Toast.makeText(MainActivity.this, "Сканирование началось", Toast.LENGTH_SHORT).show();
        });

        // QR читаем
        kamera.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                if (result != null && result.getText() != null) {
                    String text = result.getText();
                    tekst.setText("Найдено: " + text);
                    Toast.makeText(MainActivity.this, "QR: " + text, Toast.LENGTH_SHORT).show();

                    // если ссылка открыть
                    if (text.startsWith("http://") || text.startsWith("https://")) {
                        try {
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(text));
                            startActivity(i);
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Не удалось открыть", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void possibleResultPoints(java.util.List<com.google.zxing.ResultPoint> resultPoints) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        kamera.resume(); //  включаем
    }

    @Override
    protected void onPause() {
        super.onPause();
        kamera.pause(); // выключаем
    }
}
