package com.example.gribcatalog.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gribcatalog.R;

import java.util.ArrayList;
import java.util.List;

public class GribCatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grib_catalog);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new GribAdapter(this, loadData()));
    }

    private List<Grib> loadData() {
        List<Grib> list = new ArrayList<>();
        list.add(new Grib("Белый гриб", "Царский гриб", "Белый гриб — съедобный, имеет толстую ножку и коричневую шляпку.", R.drawable.grib1));
        list.add(new Grib("Мухомор", "Ядовитый гриб", "Мухомор красный — красивый, но ядовитый гриб с белыми пятнами.", R.drawable.grib2));
        list.add(new Grib("Лисичка", "Съедобный гриб", "Лисички имеют жёлтую окраску и приятный аромат, популярны для жарки.", R.drawable.grib3));
        return list;
    }
}
