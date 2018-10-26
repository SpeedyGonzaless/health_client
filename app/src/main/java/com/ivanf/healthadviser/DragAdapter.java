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

public class DragAdapter extends ArrayAdapter<Drag> {

    private LayoutInflater inflater;
    private int layout;
    private List<Drag> states;

    public DragAdapter(Context context, int resource, List<Drag> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        ImageView flagView = (ImageView) view.findViewById(R.id.flag);
        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView capitalView = (TextView) view.findViewById(R.id.capital);

        Drag state = states.get(position);


        //flagView.setImageResource(state.getFlagResource());
        Picasso.get()
                .load(state.getFlagResource())
                .into(flagView);
        nameView.setText(state.getName());
        capitalView.setText(state.getCapital());

        return view;
    }
}