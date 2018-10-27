package com.ivanf.healthadviser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    private LayoutInflater inflater;
    private int layout;
    private List<Recipe> states;

    public RecipeAdapter(Context context, int resource, List<Recipe> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        ImageView imgView = (ImageView) view.findViewById(R.id.img);
        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView companyView = (TextView) view.findViewById(R.id.company);
        TextView useView = (TextView) view.findViewById(R.id.use);

        Recipe state = states.get(position);

        Picasso.get()
                .load(state.getImg())
                .into(imgView);

        nameView.setText(state.getName());
        companyView.setText(state.getCompany());
        useView.setText(state.getUse());

        return view;
    }
}