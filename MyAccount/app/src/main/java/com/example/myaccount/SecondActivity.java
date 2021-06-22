package com.example.myaccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {
    EditText edittext;
    private String memo ,amount,month_string,year_string,day_string;
    Switch switchs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        switchs = (Switch) findViewById(R.id.simpleSwitch);
        Button button = findViewById(R.id.btn_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittext = findViewById(R.id.numamount);
                amount = edittext.getText().toString();
                if (!switchs.isChecked()) {
                    amount = String.valueOf(Integer.parseInt(amount) * -1);
                }
                displayToast(amount);
                edittext = findViewById(R.id.nummemo);
                memo = edittext.getText().toString();
                if(((memo!=null)&&(amount!=null)&&(month_string!=null))) {
                    Intent intent = new Intent();
                    intent.putExtra("amount", amount);
                    setResult(RESULT_OK, intent);
                    intent.putExtra("memo", memo);
                    setResult(RESULT_OK, intent);
                    intent.putExtra("day", day_string);
                    setResult(RESULT_OK, intent);
                    intent.putExtra("month", month_string);
                    setResult(RESULT_OK, intent);
                    intent.putExtra("year", year_string);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else{
                        AlertDialogActivity alertDialogActivity=new AlertDialogActivity();
                        alertDialogActivity.show(getSupportFragmentManager(), "example dialog");
                }
            }
        });
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DateFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    public void processDatePickerResult(int year, int month, int day) {
        month_string = Integer.toString(month + 1);
        day_string = Integer.toString(day);
        year_string = Integer.toString(year);
        String dateMessage = (year_string+"/" + month_string + "/" + day_string );
        TextView editdate =findViewById(R.id.numdate);
        editdate.setText(dateMessage);
    }
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
