package com.example.team;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class task extends AppCompatActivity {

    private String project_id,project_name;
    private Integer user_id;
    private RecyclerView recyclerView;
    private ArrayList<String> name,details,status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        name = new ArrayList<>();
        details = new ArrayList<>();
        status = new ArrayList<>();
        recyclerView = findViewById(R.id.taskList);



        Intent intent = getIntent();
        project_id = intent.getStringExtra("id");
        project_name = intent.getStringExtra("name");
        user_id = intent.getIntExtra("userId", 0);
        System.out.println("\n userId - "+ user_id + "project_id - "+ project_id);
        StringRequest request = requestData();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

private StringRequest requestData(){
        return new StringRequest(Request.Method.POST, constants.taskList, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        name.add(jsonObject.getString("name"));
                        details.add(jsonObject.getString("details"));
                        status.add(jsonObject.getString("status"));

                    }
                    System.out.println("\n "+name+"\n"+status);
                    recycle(name,details,status);
                }
                catch (JSONException e){

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_id",user_id.toString());
                params.put("project_id", project_id);
                return params;

            }
        };
}
private void recycle(ArrayList<String> name,ArrayList<String> details,ArrayList<String> status){
    Adaptor adaptor = new Adaptor(name,status);
    recyclerView.setAdapter(adaptor);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));


}
}