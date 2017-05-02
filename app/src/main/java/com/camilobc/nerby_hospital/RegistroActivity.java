package com.camilobc.nerby_hospital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity {

    EditText eDocumento, eNombre, eTelefono, eCorreo, eAlergias, eEnfermedades, eAcudiente, eTelAcudiente, eContrasena, eR_contrasena;
    String  sangre2, sangre, EPS, documento, nombre, telefono, correo, sexo, alergias, enfermedades, acudiente, telacudiente, contrasena, r_contrasena;
    RadioButton masculino, femenino;
    Spinner ListaDesple, ListaDesple2;
    String[] items, items2;
    String[] opciones_sangre={"O+","A+"};

    FirebaseDatabase database;
    DatabaseReference myRef;
    Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        ListaDesple = (Spinner) findViewById(R.id.ListaDesple);
//        items = getResources().getStringArray(R.array.Tipo_de_Sangre);
//        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,items);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones_sangre);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ListaDesple.setAdapter(adaptador);

//        ListaDesple2 = (Spinner) findViewById(R.id.ListaDesple2);
//        items2 = getResources().getStringArray(R.array.EPS);
//        ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,items2);
//        adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        ListaDesple2.setAdapter(adaptador2);

//        sangre = ListaDesple.getSelectedItem().toString();
//        EPS = ListaDesple2.getSelectedItem().toString();

        sangre = ListaDesple.getItemAtPosition(ListaDesple.getSelectedItemPosition()).toString();


        eDocumento = (EditText) findViewById(R.id.eId);
        eNombre = (EditText) findViewById(R.id.enombre);
        eTelefono = (EditText) findViewById(R.id.etelefono);
        eCorreo = (EditText) findViewById(R.id.ecorreo);
        eAlergias = (EditText) findViewById(R.id.ealergias);
        eEnfermedades = (EditText) findViewById(R.id.eenfermedades);
        eAcudiente = (EditText) findViewById(R.id.enombre_acudiente);
        eTelAcudiente = (EditText) findViewById(R.id.etelefono_acudiente);
        eContrasena = (EditText) findViewById(R.id.econtrasenar);
        eR_contrasena = (EditText) findViewById(R.id.econtrasenarep);
        masculino = (RadioButton) findViewById(R.id.rmasculino);
        femenino = (RadioButton) findViewById(R.id.rfemenino);
    }

    public void onClick(View v){
        int idB = v.getId();
        database = FirebaseDatabase.getInstance();
        documento=eDocumento.getText().toString();
        nombre=eNombre.getText().toString();
        telefono=eTelefono.getText().toString();
        correo=eCorreo.getText().toString();
        alergias=eAlergias.getText().toString();
        enfermedades=eEnfermedades.getText().toString();
        acudiente=eAcudiente.getText().toString();
        telacudiente=eTelAcudiente.getText().toString();
//        contrasena=eContrasena.getText().toString();
//        r_contrasena=eR_contrasena.getText().toString();

        switch (idB){
            case R.id.benviar:
//                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);

//                startActivity(intent);
                if(masculino.isChecked()){
                        sexo="Masculino";
                    }else if(femenino.isChecked()){
                        sexo="Femenino";
                    }
//                if(!(eContrasena.getText().toString().equals(eR_contrasena.getText().toString()))){
//                    Toast.makeText(getApplicationContext(),"La contraseña no coincide",Toast.LENGTH_SHORT).show();
////                    setResult(RESULT_CANCELED, intent);
//                }

//                intent.putExtra("sexo", sexo);
//                    intent.putExtra("sangre", sangre);
//                    intent.putExtra("eps", EPS);
//                    intent.putExtra("nombre", eNombre.getText().toString());
//                    intent.putExtra("documento", eDocumento.getText().toString());
//                    intent.putExtra("correo", eCorreo.getText().toString());
//                    intent.putExtra("contrasena", eContrasena.getText().toString());

                Intent intent = new Intent();
                intent.putExtra("correo", eCorreo.getText().toString());
                intent.putExtra("nombre", eNombre.getText().toString());
                intent.putExtra("documento", eDocumento.getText().toString());
                intent.putExtra("sangre", sangre);
//                intent.putExtra("eps", EPS);
                intent.putExtra("sexo", sexo);
                intent.putExtra("pass", eContrasena.getText().toString());
                setResult(RESULT_OK, intent);
                finish();

                myRef = database.getReference("Usuarios").child(String.valueOf(documento));
                usuarios = new Usuarios(String.valueOf(documento),nombre,telefono,correo, sexo, sangre, alergias, enfermedades, acudiente, telacudiente);
                myRef.setValue(usuarios);
                break;
            case R.id.bcancelar:
                setResult(RESULT_CANCELED);
                finish();
                break;

        }
    }
}
