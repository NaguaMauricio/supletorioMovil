package com.example.supletoriomovil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Revistas {

    private String tittle;
    private String imgPortada;
    private String volumen;
    private String numero;
    private String año;





    public Revistas(JSONObject a) throws JSONException {
        tittle = a.getString("title").toString();
        imgPortada = a.getString("portada").toString();
        volumen = a.getString("volume").toString();
        numero = a.getString("number").toString();
        año = a.getString("year").toString();
    }


    public static ArrayList<Revistas> JsonObjectsBuild(JSONObject datos) throws JSONException {
        ArrayList<Revistas> revistas = new ArrayList<>();
        JSONArray d= datos.getJSONArray("issues");
        for (int i = 0; i < d.length(); i++) {
            revistas.add(new Revistas(d.getJSONObject(i)));
        }


        return revistas;
    }




    public String getImgPortada() {
        return imgPortada;
    }

    public void setImgPortada(String imgPortada) {
        this.imgPortada = imgPortada;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
}
