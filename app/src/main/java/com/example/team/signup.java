package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity implements View.OnClickListener{
    private TextInputEditText username,email,password;
    private Button signup;
    private TextView login;
    private ProgressBar progressBar;
    private String str_fullname,str_username,str_email,str_password;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        email = (TextInputEditText) findViewById(R.id.email);
        password = (TextInputEditText) findViewById(R.id.password);
        username = (TextInputEditText) findViewById(R.id.username);
        signup = (Button) findViewById(R.id.buttonSignUp);
        progressBar = (ProgressBar) findViewById(R.id.progress);




        email.setOnClickListener(this);
        password.setOnClickListener(this);
        signup.setOnClickListener(this);
        progressBar.setOnClickListener(this);


    }

    public void register(){
        if(
            username.getText().toString().trim().equals("")||
            email.getText().toString().trim().equals("") ||
            password.getText().toString().trim().equals("")
        ){
            Toast.makeText(this,"check details",Toast.LENGTH_SHORT).show();
        }
        else{
                str_username = username.getText().toString().trim();
                str_email = email.getText().toString().trim();
                str_password = password.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, constants.registerUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response);
                        if(jsonObject.getString("error") == "false"){
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(signup.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();
                    System.out.println(error.getMessage());
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("username", str_username);
                    params.put("email" , str_email);
                    params.put("password" , str_password);
                    return params;

                }
            };

            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);

        }
    }

    @Override
    public void onClick(View v) {
       switch(v.getId()){
           case R.id.buttonSignUp:
               register();
               break;
           case R.id.loginText:
               Intent intent = new Intent(this, MainActivity.class);
               startActivity(intent);
        }
    }
}