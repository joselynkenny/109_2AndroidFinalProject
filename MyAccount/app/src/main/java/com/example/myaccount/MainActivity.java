package com.example.myaccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.LinkedList;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    private static int totalmonth,income,paid,adds=1,sticker;
    private String year="",month="",day="",text="",memo="";
    private static  LinkedList<String> years = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private ListsAdapter mAdapter;
    static final String mtotal = "Totalm";
    static final String mpaid = "paidm";
    static final String mincome = "mincome";
    static final String msticker = "msticker";
    Button btn_o;
    SharedPreferences sharedPreferences;
    private static final String sharedPrefFile = "sharedfile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = this.getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        totalmonth = sharedPreferences.getInt(mtotal,0);
        income = sharedPreferences.getInt(mincome,0);
        paid = sharedPreferences.getInt(mpaid,0);
        sticker = sharedPreferences.getInt(msticker,0);
        GifImageView img = (GifImageView) findViewById(R.id.gifkanahei);
        switch (sticker) {
            case 0:
                img.setImageResource(R.drawable.kanahei3);
                sticker = 0;
                break;
            case 1:
                img.setImageResource(R.drawable.kanahei4);
                sticker = 1;
                break;
            case 2:
                img.setImageResource(R.drawable.kanahei5);
                sticker = 2;
                break;
        }

        Calendar calender =Calendar.getInstance();
        String date = DateFormat.getDateInstance(DateFormat.FULL).format(calender.getTime());
        TextView textdate =findViewById(R.id.date);
        textdate.setText(date);
        TextView textdates = findViewById(R.id.numthis);
        textdates.setText(String.valueOf(totalmonth));
        TextView texts = findViewById(R.id.numincome);
        texts.setText(String.valueOf(income));
        TextView textss = findViewById(R.id.numpaid);
        textss.setText(String.valueOf(paid*-1));

        btn_o = findViewById(R.id.btn_others);
        btn_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, btn_o);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        SharedPreferences sharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        GifImageView img = (GifImageView) findViewById(R.id.gifkanahei);
                        switch (menuItem.getItemId()) {
                            case R.id.option1:
                                img.setImageResource(R.drawable.kanahei3);
                                sticker=0;
                                break;
                            case R.id.option2:
                                img.setImageResource(R.drawable.kanahei4);
                                sticker=1;
                                break;
                            case R.id.option3:
                                img.setImageResource(R.drawable.kanahei5);
                                sticker=2;
                                break;
                            default:
                                break;
                        }
                        editor.putInt(msticker, sticker);
                        editor.apply();
                        displayToast(menuItem.getTitle() + " clicked");
                        return true;
                    }


                });
                sticker = sharedPreferences.getInt(msticker,0);
                popupMenu.show();
            }
        });
    }
    public void onSecond(View view){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivityForResult(intent,1);
    }
    public void onThird(View view){
        Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
        intent.putExtra("n" ,String.valueOf(years.size()));
        startActivityForResult(intent,1);
        for (int i = 0; i < years.size(); i++) {
            displayToast(years.get(i)+"i="+i);
            intent.putExtra(String.valueOf(i) ,years.get(i));
        }
        startActivityForResult(intent,1);

    }
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1){
            if(resultCode==RESULT_OK) {
                String amount = data.getStringExtra("amount");
                totalmonth += Integer.parseInt(amount);
                TextView textdate = findViewById(R.id.numthis);
                textdate.setText(String.valueOf(totalmonth));
                year = data.getStringExtra("year");
                month = data.getStringExtra("month");
                day = data.getStringExtra("day");
                memo = data.getStringExtra("memo");
                if(Integer.parseInt(amount)>=0){
                    income+=Integer.parseInt(amount);
                    TextView texts = findViewById(R.id.numincome);
                    texts.setText(String.valueOf(income));
                    text=year+"/"+month+"/"+day+"->"+"INCOME= " +Integer.parseInt(amount)+"\n"
                         +memo;
                }
                else{
                    paid+=Integer.parseInt(amount);
                    TextView texts = findViewById(R.id.numpaid);
                    texts.setText(String.valueOf(paid*-1));
                    text=year+"/"+month+"/"+day+"->"+"   PAID= " +(Integer.parseInt(amount)*-1)+"\n"
                            +memo;
                }


            }
            displayToast(text);
            years.addLast(text);
        }
    }
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the scores.
        outState.putInt(mtotal, totalmonth);
        outState.putInt(mpaid, paid);
        outState.putInt(mincome, income);

    }*/
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(mtotal, totalmonth);
        editor.putInt(mpaid, paid);
        editor.putInt(mincome, income);
        editor.putInt(msticker, sticker);

        editor.apply();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(mtotal, totalmonth);
        editor.putInt(mpaid, paid);
        editor.putInt(mincome, income);
        editor.putInt(msticker, sticker);

        editor.apply();
    }

}