package com.example.supletoriomovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask, AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://revistas.uteq.edu.ec/ws/getrevistas.php", datos, this, this);
        ws.execute("");

        ListView lstOpciones=(ListView)findViewById(R.id.lvDatosRevistas);
        lstOpciones.setOnItemClickListener(this);
        getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        getPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
    }


    ArrayList<Revistas> listaRevistas;
    @Override
    public void processFinish(String result) throws JSONException {

        Log.i("processFinish", result);
        JSONObject pais=  new JSONObject(result);


        listaRevistas=Revistas.JsonObjectsBuild(pais);
        AdaptadorRevistas adaptadorArticulos = new AdaptadorRevistas(this,listaRevistas);
        ListView listaOpciones =(ListView)findViewById(R.id.lvDatosRevistas);
        listaOpciones.setAdapter(adaptadorArticulos);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent (view.getContext(), activityArticles.class);
        intent.putExtra("volumen",listaRevistas.get(position).getVolumen());
        intent.putExtra("numero",listaRevistas.get(position).getNumero());

        startActivityForResult(intent, 0);

    }

    public void getPermission(String permission){
        if (Build.VERSION.SDK_INT >= 23) {
            if (!(checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED))
                ActivityCompat.requestPermissions(this, new String[]{permission}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]
            permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1)
        {
            Toast.makeText(this.getApplicationContext(),"OK", Toast.LENGTH_LONG).show();
        }
    }
}

