package com.example.eliseo.usandointent;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {
    TextView tabla;
    Button btnVolver;
    String cadena = "";
    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        tabla = (TextView) findViewById(R.id.tabla);
        btnVolver = (Button) findViewById(R.id.btnVolver);

        ///////agregando el tipo de fuente
        Typeface TF = Typeface.createFromAsset(getAssets(), "font/AarvarkCafe.ttf");
        tabla.setTypeface(TF);

        Bundle datos = this.getIntent().getExtras();

        n = datos.getInt("valor");
        for (int i = 1; i < 11; i++) {
            cadena += "" + n + " X " + i + " = " + (n * i);
            cadena += "\n";
        }
        tabla.setText(cadena);
    }

    public void btnVolverClic(View v) {
        finish();

    }
}
