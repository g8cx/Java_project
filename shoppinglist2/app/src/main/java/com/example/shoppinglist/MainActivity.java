package com.example.shoppinglist;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Объявление переменных
    private EditText editTextItem;
    private Button buttonAdd;
    private ListView listViewItems;
    private ArrayList<String> shoppingList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ИНИЦИАЛИЗАЦИЯ ЭЛЕМЕНТОВ
        editTextItem = findViewById(R.id.editTextItem);
        buttonAdd = findViewById(R.id.buttonAdd);
        listViewItems = findViewById(R.id.listViewItems);

        // СОЗДАЕМ СПИСОК ПОКУПОК
        shoppingList = new ArrayList<>();

        // СОЗДАЕМ АДАПТЕР ДЛЯ ListView
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                shoppingList
        );

        // УСТАНАВЛИВАЕМ АДАПТЕР ДЛЯ ListView
        listViewItems.setAdapter(adapter);

        // ОБРАБОТЧИК КНОПКИ "ДОБАВИТЬ"
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemToList();
            }
        });

        // ОБРАБОТЧИК ДОЛГОГО НАЖАТИЯ ДЛЯ УДАЛЕНИЯ
        listViewItems.setOnItemLongClickListener((parent, view, position, id) -> {
            removeItemFromList(position);
            return true;
        });
    }

    // МЕТОД ДЛЯ ДОБАВЛЕНИЯ ТОВАРА В СПИСОК
    private void addItemToList() {
        String item = editTextItem.getText().toString().trim();

        if (!item.isEmpty()) {
            shoppingList.add(item);
            adapter.notifyDataSetChanged(); // Обновляем список
            editTextItem.setText(""); // Очищаем поле ввода
        } else {
            Toast.makeText(this, "Введите название товара", Toast.LENGTH_SHORT).show();
        }
    }

    // МЕТОД ДЛЯ УДАЛЕНИЯ ТОВАРА ИЗ СПИСКА
    private void removeItemFromList(int position) {
        String removedItem = shoppingList.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Удалено: " + removedItem, Toast.LENGTH_SHORT).show();
    }
}