package com.example.team;

import android.content.Context;
import android.os.AsyncTask;

public class backgroudWorker extends AsyncTask<String, Void, Void> {
    Context context;

    public backgroudWorker(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... strings) {
        String type = strings[0];
        String login_url = "https://10.0.2.2/";
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(){
        super.onPostExecute();
    }

    @Override
    protected void onProgressUpdate(Void aVoid){

        super.onProgressUpdate(aVoid);
    }



}
