package com.example.shoppinglistprodeluxe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText itemEditText;
    private Button addButton;
    private Button infoButton;
    private ListView itemListView;
    private ArrayList<String> itemList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemEditText = findViewById(R.id.itemEditText);
        addButton = findViewById(R.id.addButton);
        itemListView = findViewById(R.id.itemListView);
        infoButton = findViewById(R.id.button2);

        itemList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        itemListView.setAdapter(adapter);


        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessageBox();
            }
        });
    }

    private void showMessageBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Informacja");
        builder.setMessage("Dodaj produkty do listy. Kliknij na produkt na liście aby go usunąć");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();



        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemToList();
            }
        });




        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                removeItem(position);
            }
        });
    }

    private void addItemToList() {
        String item = itemEditText.getText().toString();
        if (!item.isEmpty()) {
            itemList.add(item);
            adapter.notifyDataSetChanged();
            itemEditText.setText("");
        }
    }

    private void removeItem(int position) {
        itemList.remove(position);
        adapter.notifyDataSetChanged();
    }

}


