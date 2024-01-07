package com.leoleo.recyclerviewjavasample;

import android.os.Bundle;
import android.os.SystemClock;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final MyListAdapter adapter = new MyListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnItemClickListener((item, position) -> {
            Snackbar.make(findViewById(R.id.update), item.toString(), Snackbar.LENGTH_LONG).show();
        });

        findViewById(R.id.update).setOnClickListener(v -> {
            List<Item> items = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                if (i % 2 == 0) {
                    items.add(new Item(i, "鉄人" + SystemClock.elapsedRealtime() + "号"));
                } else {
                    items.add(new Item(i, "鉄人" + i + "号"));
                }
            }
            adapter.submitList(items);
        });


        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            items.add(new Item(i, "鉄人" + i + "号"));
        }
        adapter.submitList(items);
    }
}