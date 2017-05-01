package com.camilobc.nerby_hospital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity {

    EditText eDocumento, eNombre, eTelefono, eCorreo;
    String documento, nombre, telefono, correo;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        eDocumento = (EditText) findViewById(R.id.eId);
        eNombre = (EditText) findViewById(R.id.enombre);
        eTelefono = (EditText) findViewById(R.id.etelefono);
        eCorreo = (EditText) findViewById(R.id.ecorreo);
    }

    public void onClick(View v){
        int idB = v.getId();
        database = FirebaseDatabase.getInstance();
        documento=eDocumento.getText().toString();
        nombre=eNombre.getText().toString();
        telefono=eTelefono.getText().toString();
        correo=eCorreo.getText().toString();
        switch (idB){
            case R.id.benviar:
                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(intent);

                myRef = database.getReference("Usuarios").child(String.valueOf(documento));
                usuarios = new Usuarios(String.valueOf(documento),nombre,telefono,correo);
                myRef.setValue(usuarios);
                clear();
                break;
            case R.id.bcancelar:
                break;

        }
    }

    private void clear(){
        eDocumento.setText("");
        eNombre.setText("");
        eTelefono.setText("");
        eCorreo.setText("");
    }
}
