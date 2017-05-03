package com.camilobc.nerby_hospital;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Intent intent;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    EditText eCorreo, eContrasena;
    Button bIniciar, bRegistrar, bEmergencia;
    String sangre, nombre, documento, scorreo, scontrasena, sexo;
    //    Bitmap foto_perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);// QUITAR APPBAR
        setContentView(R.layout.activity_login);

        prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        editor = prefs.edit();

        sexo = prefs.getString("sexo", "nosexo");
        sangre = prefs.getString("sangre", "nosangre");
        nombre = prefs.getString("nombre", "nonombre");
        documento = prefs.getString("documento", "nodocumento");
        scorreo = prefs.getString("correo", "nocorreo");
        scontrasena = prefs.getString("pass", "nopass");

        if(prefs.getInt("login", -1) == 1) {
            intent = new Intent(LoginActivity.this, PerfilDrawerActivity.class);
//            intent.putExtra("sexo", sexo);
            intent.putExtra("sangre", sangre);
            intent.putExtra("nombre", nombre);
            intent.putExtra("documento", documento);
            intent.putExtra("correo", scorreo);
//            intent.putExtra("pass", scontrasena);

            startActivity(intent);
            finish();
        }

        eCorreo = (EditText) findViewById(R.id.edcorreo);
        eContrasena = (EditText) findViewById(R.id.econtrasena);
        bIniciar = (Button) findViewById(R.id.biniciar);
        bRegistrar = (Button) findViewById(R.id.bregistrese);
        bEmergencia = (Button) findViewById(R.id.bemergencia);

        bEmergencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this ,EmergenciaMapsActivity.class);
                startActivity(intent);
            }
        });

        bRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginActivity.this ,RegistroActivity.class);
                startActivityForResult(intent, 1234);
            }
        });
        bIniciar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(eCorreo.getText().toString().equals("") || eContrasena.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Llene los campos requeridos",Toast.LENGTH_SHORT).show();
                }else if(!(eCorreo.getText().toString().equals(scorreo) && eContrasena.getText().toString().equals(scontrasena))) {
                    Toast.makeText(getApplicationContext(), "Usuario invalido", Toast.LENGTH_SHORT).show();
                } else if(eCorreo.getText().toString().equals(scorreo) && eContrasena.getText().toString().equals(scontrasena)){

                    editor.putInt("login",1);
                    editor.commit();
                    intent = new Intent(LoginActivity.this, PerfilDrawerActivity.class);
                    intent.putExtra("sangre", sangre);
//                    intent.putExtra("sexo", sexo);
                    intent.putExtra("nombre", nombre);
                    intent.putExtra("documento", documento);
                    intent.putExtra("correo", scorreo);

                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "El usuario ingresado no existe", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode==RESULT_OK){

//            sexo = data.getExtras().getString("sexo");
            sangre = data.getExtras().getString("sangre");
            nombre = data.getExtras().getString("nombre");
            documento = data.getExtras().getString("documento");
            scorreo = data.getExtras().getString("correo");
            scontrasena = data.getExtras().getString("pass");

//            editor.putString("sexo", sexo);
            editor.putString("sangre", sangre);
            editor.putString("nombre", nombre);
            editor.putString("documento", documento);
            editor.putString("correo", scorreo);
            editor.putString("pass", scontrasena);
            editor.commit();
        }else{
            if(requestCode==1234 && resultCode==RESULT_CANCELED){
                Toast.makeText(this, "Error en Registro", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

