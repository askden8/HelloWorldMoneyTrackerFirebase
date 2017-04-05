package com.loftschool.moneytracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Copyright (c) 2017.
 * All Rights Reserved.
 * <p>
 * Author: Dmitry Gordeev <netimen@gmail.com>
 * Date:   05.04.17
 */

public class MainActivity extends AppCompatActivity {
    static class Item {
        String name;
        int price;

        Item(String name, int price) {
            this.name = name;
            this.price = price;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText price = (EditText) findViewById(R.id.price);
        final Button add = (Button) findViewById(R.id.add);
        final ListView items = (ListView) findViewById(R.id.items);
        final ItemsAdapter adapter = new ItemsAdapter();

        items.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add(new Item(name.getText().toString(), Integer.valueOf(price.getText().toString())));
            }
        });
    }

    private class ItemsAdapter extends ArrayAdapter<Item> {
        ItemsAdapter() {
            super(MainActivity.this, R.layout.item);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final View view = getLayoutInflater().inflate(R.layout.item, null);
            final Item item = getItem(position);
            ((TextView) view.findViewById(R.id.name)).setText(item.name);
            ((TextView) view.findViewById(R.id.price)).setText(String.valueOf(item.price));
            return view;
        }
    }
}
