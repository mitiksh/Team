package com.example.team;

import android.text.BoringLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Adaptor extends RecyclerView.Adapter<Adaptor.AdaptorViewHolder> {

    private ArrayList<String> arrayTask;
    private ArrayList<String> status;

    public Adaptor(ArrayList<String> arrayTask,ArrayList<String> status) {

         this.arrayTask = arrayTask;
         this.status = status;

    }

    @NonNull
    @Override
    public AdaptorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_task,parent,false);
        return new AdaptorViewHolder(view);

    }



    public void onBindViewHolder(@NonNull AdaptorViewHolder holder, int position) {
            String checked = status.get(position);
            String string = arrayTask.get(position);
            System.out.println(checked + " " + checked.getClass().getSimpleName());
            /*holder.checkBox.setChecked(checked);*/
            if(checked.trim().equals("0")){
                holder.checkBox.setChecked(false);
            }
            else if(checked.trim().equals("1")){
                holder.checkBox.setChecked(true);
            }
            holder.textView.setText(string);
    }
    @Override
    public int getItemCount() {
        return arrayTask.size();
    }

    public class AdaptorViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CheckBox checkBox;
        public AdaptorViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.task);
            checkBox = itemView.findViewById(R.id.checkStatus);

        }

    }

}
