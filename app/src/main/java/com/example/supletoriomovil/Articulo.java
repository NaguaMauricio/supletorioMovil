package com.example.supletoriomovil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Articulo {

    private String titulo;
    private String fecha_Publicacion;
    private String url_Pdf;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha_Publicacion() {
        return fecha_Publicacion;
    }

    public void setFecha_Publicacion(String fecha_Publicacion) {
        this.fecha_Publicacion = fecha_Publicacion;
    }

    public String getUrl_Pdf() {
        return url_Pdf;
    }

    public void setUrl_Pdf(String url_Pdf) {
        url_Pdf = url_Pdf;
    }

    public Articulo(JSONObject a) throws JSONException {
        titulo = a.getString("title").toString();
        fecha_Publicacion = a.getString("date_published").toString();;
        url_Pdf = a.getString("pdf").toString() ;
    }


    public static ArrayList<Articulo> JsonObjectsBuild(JSONObject datos) throws JSONException {
        ArrayList<Articulo> articulos = new ArrayList<>();

        JSONArray d= datos.getJSONArray("articles");
        for (int i = 0; i < d.length(); i++) {
            articulos.add(new Articulo(d.getJSONObject(i)));
        }
        return articulos;
    }
}
