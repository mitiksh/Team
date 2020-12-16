package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

public class create_project extends AppCompatActivity  {

    private Button button;
    private EditText editText;
    private ListView listView;
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);
        button = findViewById(R.id.addEditText);
        layout = findViewById(R.id.memberList);

    }

  public void CreateEditText(View view){
      addView();
  }

    private void addView() {
        final View cricketerView = getLayoutInflater().inflate(R.layout.custom_edittext,null,false);

        EditText editText = (EditText)cricketerView.findViewById(R.id.memberId);

        ImageButton imageClose = cricketerView.findViewById(R.id.delMember);


        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(cricketerView);
            }
        });

        layout.addView(cricketerView);
    }
    private void removeView(View view){
        layout.removeView(view);
    }
}