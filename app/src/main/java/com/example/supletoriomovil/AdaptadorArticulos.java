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

public class AdaptadorArticulos extends ArrayAdapter<Articulo> {

    public AdaptadorArticulos(Context context, ArrayList<Articulo> datos) {
        super(context, R.layout.ly_item, datos);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.ly_item, null);


        ImageView imgPDF= (ImageView)item.findViewById(R.id.imgPdf);
        TextView lblTitulo = (TextView)item.findViewById(R.id.txtTitulo);
        lblTitulo.setText("TÍTULO:"+getItem(position).getTitulo());
        TextView lblFechaPublicacion = (TextView)item.findViewById(R.id.txtFechaPublicacion);
        lblFechaPublicacion.setText("Fecha Pub:"+getItem(position).getFecha_Publicacion());

        //imgPDF.setTag(getItem(position).getUrl_Pdf());

        Glide.with(this.getContext()).load("https://www.spanish.cl/rules/articulos-definidos-indefinidos.gif").into(imgPDF);

        return(item);

    }



}
