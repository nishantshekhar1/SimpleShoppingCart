package com.techobbyist.signuplogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
public class BuyerActivity extends AppCompatActivity {


    private ArrayList<String> arrayList;
    ListView listView;
    private DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer);
        arrayList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list);
        db = new DbHelper(this);
        showList();


    }

    public  void showList()
    {
        arrayList = db.getAllCategorys();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,arrayList);
        listView.setAdapter(adapter);
    }
}
