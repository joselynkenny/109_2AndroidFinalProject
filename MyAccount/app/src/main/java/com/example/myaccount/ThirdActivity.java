package com.example.myaccount;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class ThirdActivity  extends AppCompatActivity {
   // private final LinkedList<String> mWordList = new LinkedList<>();
    public LinkedList<String> years = new LinkedList<>();
    //private final ArrayList<String> years = new ArrayList<String>();
    private RecyclerView mRecyclerView;
    private ListsAdapter mAdapter;
    private String year="",month="",day="",paid,totalmonth,income;
    private int adds=1,n=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent intent = getIntent();
        n=Integer.parseInt(intent.getStringExtra("n"));
        for (int i = 0; i < n; i++) {
            years.addLast(intent.getStringExtra(String.valueOf(i)));
        }
        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new ListsAdapter(this, years);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_third, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
