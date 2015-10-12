package com.example.eliseo.usandointent;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etNumero;
    Button btnMostrar;
    TextView texto;


    private int progressStatus = 0;

    private ProgressBar progressBar;


    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumero = (EditText) findViewById(R.id.etNumero);
        btnMostrar = (Button) findViewById(R.id.btnMostrar);
        texto = (TextView) findViewById(R.id.texto);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);


    }

    public void btnMostrarClic(View v) {
        if (etNumero.getText().toString().equals("")) {
            Toast.makeText(getBaseContext(), "Ingrese un numero", Toast.LENGTH_SHORT).show();
            etNumero.requestFocus();
        } else {
            progressStatus = 0;
            new Thread(new Runnable() {
                public void run() {
                    while (progressStatus < 100) {
                        progressStatus += 1;
                        // Update the progress bar and display the
                        //current value in the text view
                        handler.post(new Runnable() {
                            public void run() {
                                progressBar.setProgress(progressStatus);
                                //texto.setText(progressStatus + "/" + progressBar.getMax());
                                texto.setText(progressStatus + "%");
                                if (progressStatus == 100) {

                                    llamada();
                                }
                            }
                        });
                        try {
                            // Sleep for 200 milliseconds.
                            //Just to display the progress slowly
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();


        }

    }

    public void llamada() {
        Intent intent = new Intent(this, ResultadoActivity.class);
        intent.putExtra("valor", Integer.valueOf(etNumero.getText().toString()));
        startActivity(intent);

        etNumero.setText("");
        etNumero.requestFocus();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
