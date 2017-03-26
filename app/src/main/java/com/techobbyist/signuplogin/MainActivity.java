package com.techobbyist.signuplogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnLogout;
    private Session session;
    private EditText editNumber;
    private Button OkButton;
    private Button AddButton;
    private DbHelper db;
            LinearLayout layout1;
            LinearLayout.LayoutParams params;
    private int count =-1;
    List<EditText> allEds = new ArrayList<EditText>();
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Session(this);
        if(!session.loggedin()){
            logout();
        }
        db = new DbHelper(this);
        btnLogout = (Button)findViewById(R.id.btnLogout);
        editNumber=(EditText)findViewById(R.id.editBox);
        OkButton=(Button)findViewById(R.id.buttonOK);
        AddButton=(Button)findViewById(R.id.btnAdd);
        layout1 = (LinearLayout) findViewById(R.id.LLayout);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        OkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEditboxes();
            }
        });

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToDb();
            }
        });
    }

    private void logout(){
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }

    private void createEditboxes(){
        count = Integer.parseInt(editNumber.getText().toString());
        //EditText myEditText = new EditText(this);
        //myEditText.setLayoutParams(mRparams);

        for(int i =1; i<=count; i++)
        {
            createEditText(i);
        }
    }

    public void createEditText(int i) {
        editText = new EditText(this);
        allEds.add(editText);
        editText.setId(i);
        layout1.addView(editText,params);

    }

    public void addToDb() {
          // Log.d("db","1");
            for (int i = 0; i < count; i++) {
                //Log.d("YO",allEds.get(i).getText().toString());
                String name = allEds.get(i).getText().toString();
                db.addItem(name);

            }

    }

}
