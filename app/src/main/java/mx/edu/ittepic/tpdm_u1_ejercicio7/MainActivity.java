package mx.edu.ittepic.tpdm_u1_ejercicio7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText n,d,e,s;
    Button limpia;
    final String nombreArchivo="info.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        n=findViewById(R.id.nombre);
        d=findViewById(R.id.domicilio);
        e=findViewById(R.id.edad);
        s=findViewById(R.id.sueldo);
        limpia=findViewById(R.id.limpiar);

        limpia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n.setText("");
                d.setText("");
                e.setText("");
                s.setText("");
            }
        });
    }
    public void guardarEnArchivo(View v){
        String data=n.getText().toString()+"&"+d.getText().toString()+"&"+e.getText().toString()+"&"+s.getText().toString();
        try{
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(nombreArchivo,MODE_PRIVATE));
            archivo.write(data);
            archivo.close();
            mensaje("Se guardo datos");
        }catch(Exception e){
            mensaje("Error "+e.getMessage());
        }
    }

    private void mensaje(String m){
        Toast.makeText(MainActivity.this,m,Toast.LENGTH_LONG).show();
    }

    public void abrirArchivo(View v){
        try{
            BufferedReader archivo=new BufferedReader(
                    new InputStreamReader(openFileInput(nombreArchivo)));
            String datos=archivo.readLine();
            archivo.close();
            String vector[]=datos.split("&");
            n.setText(vector[0]);
            d.setText(vector[1]);
            e.setText(vector[2]);
            s.setText(vector[3]);

        }catch(Exception e){
            mensaje("Error "+e.getMessage());
        }


    }
}
