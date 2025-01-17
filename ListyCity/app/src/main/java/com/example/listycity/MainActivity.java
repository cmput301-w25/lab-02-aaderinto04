package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addCity;
    EditText entry;
    Button confirmButton;
    Button deleteCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cityList = findViewById(R.id.city_list);
        addCity = findViewById(R.id.addCity);
        EditText entry = findViewById(R.id.entry);
        confirmButton = findViewById(R.id.confirmButton);
        deleteCity = findViewById(R.id.deleteCity);

        dataList = new ArrayList<>();
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entry.setVisibility(View.VISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String item = entry.getText().toString();
                        dataList.add(item);
                        cityAdapter.notifyDataSetChanged();
                        entry.setText("");
                        entry.setVisibility(View.INVISIBLE);
                        confirmButton.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                deleteCity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dataList.remove(i);
                        cityAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}