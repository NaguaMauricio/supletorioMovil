package com.example.supletoriomovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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

public class activityArticles extends AppCompatActivity implements Asynchtask, AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        ListView lstOpciones=(ListView)findViewById(R.id.lvListaArticulos);
        lstOpciones.setOnItemClickListener(this);
        getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        getPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://revistas.uteq.edu.ec/ws/getarticles.php?volumen="+
                getIntent().getStringExtra("volumen")+
                "&num="+getIntent().getStringExtra("numero"), datos, this, this);
        ws.execute("");
    }

    @Override
    public void processFinish(String result) throws JSONException {

        Log.i("processFinish", result);
        ArrayList<Articulo> listaArticulo;
        JSONObject articulos=  new JSONObject(result);

        listaArticulo=Articulo.JsonObjectsBuild(articulos);

        AdaptadorArticulos adaptadorArticulos = new AdaptadorArticulos(this,listaArticulo);
        ListView listaArticulos =(ListView)findViewById(R.id.lvListaArticulos);
        listaArticulos.setAdapter(adaptadorArticulos);
    }

    long enq;
    DownloadManager downloadManager;
    Context contexto;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this,((Articulo)parent.getItemAtPosition(position)).getUrl_Pdf(),
        //      Toast.LENGTH_LONG).show();

        contexto=this.getApplicationContext();
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(((Articulo)parent.getItemAtPosition(position)).getUrl_Pdf()));
        request.setDescription("PDF Paper");
        request.setTitle("Pdf Artclee");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "filedownload.pdf");
        DownloadManager manager = (DownloadManager) this.getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
        try {
            enq = manager.enqueue(request);
        } catch (Exception e) {
            Toast.makeText(this.getApplicationContext(),
                    e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
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
