package com.cas.myapplication.resources;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.cas.myapplication.R;
import com.cas.myapplication.users.Director;

import java.util.List;

public class AdapatadorDirector extends ArrayAdapter<Director>  {
    private Context context;
    private List<Director>objects;


    public AdapatadorDirector(@NonNull Context context, int resource, @NonNull List<Director> objects) {
        super(context, resource, objects);
        this.objects= objects;
        this.context= context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) this.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_profesor, parent,false);
        }

        Director itemDirector =getItem(position);




        return super.getView(position, convertView, parent);
    }
}
