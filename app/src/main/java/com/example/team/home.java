package com.example.team;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class home extends AppCompatActivity {
    private ListView listView;
    private TextView textView;
    private String username, email, id,p_name,p_details;
    private Integer userId;
    private RequestQueue requestQueue;
    private ArrayList<String> ArrayName =new ArrayList<>();
    private ArrayList<String> ArrayId = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textView = (TextView) findViewById(R.id.message);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        email = intent.getStringExtra("email");
        userId = intent.getIntExtra("id",0);
        textView.setText("welcome "+ username + " ID: "+ userId + " email:" + email);

        listView = findViewById(R.id.list);

        StringRequest request = getStringRequest();
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);



    }

    public void listData(final ArrayList<String> Arrayid, final ArrayList<String> Arrayname) {

        ArrayId = Arrayid;
        ArrayName = Arrayname;
        ArrayAdapter arrayAdapter =  new ArrayAdapter(this, R.layout.listview,ArrayName);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),task.class);
                intent.putExtra("id", Arrayid.get(position));
                intent.putExtra("name", Arrayname.get(position));
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
    }

    private StringRequest getStringRequest()  {
        return new StringRequest(Request.Method.POST, constants.projectList, new Response.Listener<String>() {
                @Override
                public void onResponse(String response)  {
                    try {
                        JSONArray jsonArray= new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String str = jsonObject.getString("name");
                            String id = jsonObject.getString("id");

                            ArrayName.add(str);
                            ArrayId.add(id);
                        }
                        listData(ArrayId,ArrayName);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(home.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("user_id", userId.toString());
                    return params;
                }
            };

    }

    public void createProject(View view) {
        Intent intent = new Intent(getApplicationContext(),create_project.class);
        intent.putExtra("userId",userId.toString());
        startActivity(intent);
    }
}