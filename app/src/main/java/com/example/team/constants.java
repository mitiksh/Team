package com.example.team;

import com.android.volley.toolbox.StringRequest;

public class constants {
    public static final String  baseUrl = "http://trig-terrain.000webhostapp.com";
    public static final String registerUrl = baseUrl+"/v1/registerUser.php";
    public static final String loginUrl = baseUrl+"/v1/userLogin.php";
    public static final String projectList = baseUrl + "/v1/getProjects.php";
    public static final String taskList = baseUrl+ "/v1/getTask.php";
}
