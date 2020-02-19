package com.example.supletoriomovil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdaptadorRevistas extends ArrayAdapter<Revistas> {

    public AdaptadorRevistas(Context context, ArrayList<Revistas> datos) {
        super(context, R.layout.ly_revistas, datos);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.ly_revistas, null);


        ImageView imgRevista= (ImageView)item.findViewById(R.id.imgRevista);
        TextView tittleRevista = (TextView)item.findViewById(R.id.txtPais);
        TextView volumenRevista = (TextView)item.findViewById(R.id.txtVolumen);
        TextView numeroRevista = (TextView)item.findViewById(R.id.txtNumero);
        TextView añoRevista = (TextView)item.findViewById(R.id.txtAño);

        tittleRevista.setText(getItem(position).getTittle());
        volumenRevista.setText("Vol."+getItem(position).getVolumen());
        numeroRevista.setText("N."+getItem(position).getNumero());
        añoRevista.setText(getItem(position).getAño());


        Glide.with(this.getContext()).load(getItem(position).getImgPortada()).into(imgRevista);
        return(item);

    }

}
