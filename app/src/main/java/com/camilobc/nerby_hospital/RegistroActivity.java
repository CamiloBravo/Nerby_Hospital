package com.camilobc.nerby_hospital;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegistroActivity extends AppCompatActivity {

    EditText eDocumento, eNombre, eTelefono, eCorreo, eAlergias, eEnfermedades, eAcudiente, eTelAcudiente, eContrasena, eR_contrasena;
    String  sangre, documento, nombre, telefono, correo, sexo, alergias, enfermedades, acudiente, tel_acudiente, contra, userid;
    RadioButton masculino, femenino;
    Spinner ListaDesple, ListaDesple2;
    Button benviar, bcancelar;
    String[] items, items2;
    String[] opciones_sangre={"O+","A+"};

    FirebaseDatabase database, database3;
    DatabaseReference myRef, myRef3;
    Usuarios usuarios, usuarios2;
    Correo correoclass;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        ListaDesple = (Spinner) findViewById(R.id.ListaDesple);
        items = getResources().getStringArray(R.array.Tipo_de_Sangre);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,items);
//        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones_sangre);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ListaDesple.setAdapter(adaptador);

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
        benviar = (Button) findViewById(R.id.benviar);
        bcancelar  = (Button) findViewById(R.id.bcancelar);

        benviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent();

                database3 = FirebaseDatabase.getInstance();
                mAuth = FirebaseAuth.getInstance();
                correo = eCorreo.getText().toString();
                database = FirebaseDatabase.getInstance();
                documento = eDocumento.getText().toString();
                nombre = eNombre.getText().toString();
                telefono = eTelefono.getText().toString();

                alergias = eAlergias.getText().toString();
                enfermedades = eEnfermedades.getText().toString();
                acudiente = eAcudiente.getText().toString();
                tel_acudiente = eTelAcudiente.getText().toString();
                contra = eContrasena.getText().toString();

                mAuth.createUserWithEmailAndPassword(correo, contra)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegistroActivity.this, R.string.auth_failed,
                                            Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    userid = mAuth.getCurrentUser().getUid();
                                    myRef = database.getReference("Usuarios").child(String.valueOf(documento));
                                    usuarios = new Usuarios(String.valueOf(correo), nombre, telefono, documento, sexo, sangre, alergias, enfermedades, acudiente, tel_acudiente);
                                    myRef.setValue(usuarios);
                                    myRef3 = database3.getReference("Datos").child(String.valueOf(userid));
                                    correoclass = new Correo(String.valueOf(correo), nombre, telefono, documento, sexo, sangre, alergias, enfermedades, acudiente, tel_acudiente);
                                    myRef3.setValue(correoclass);

                                    Toast.makeText(RegistroActivity.this, "Proceso exitoso",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

//                if(eNombre.getText().toString().equals("") || eDocumento.getText().toString().equals("") ||
//                        eTelefono.getText().toString().equals("") || eContrasena.getText().toString().equals("") ||
//                        eR_contrasena.getText().toString().equals("") || eCorreo.getText().toString().equals("") ||
//                        eEnfermedades.getText().toString().equals("") || eAcudiente.getText().toString().equals("") ||
//                        sangre.equals("") ||
//                        eAlergias.getText().toString().equals("") || eTelAcudiente.getText().toString().equals("") )
//                {
//                    Toast.makeText(getApplicationContext(),"Llene todos los campos",Toast.LENGTH_SHORT).show();
//                    setResult(RESULT_CANCELED, intent);
//                } else if(!(eContrasena.getText().toString().equals(eR_contrasena.getText().toString()))){
//                    Toast.makeText(getApplicationContext(),"La contraseña no coincide",Toast.LENGTH_SHORT).show();
//                    setResult(RESULT_CANCELED, intent);
//                }else {
//                    if(masculino.isChecked()){
//                        sexo="Masculino";
//                    }else if(femenino.isChecked()){
//                        sexo="Femenino";
//                    }
//
//                    intent.putExtra("sangre", sangre);
//                    intent.putExtra("nombre", eNombre.getText().toString());
//                    intent.putExtra("documento", eDocumento.getText().toString());
//                    intent.putExtra("correo", eCorreo.getText().toString());
//                    intent.putExtra("pass", eContrasena.getText().toString());
//
//                    setResult(RESULT_OK, intent);
//                    finish();
//                }

                setResult(RESULT_OK, intent);
                finish();
            }
        });
        bcancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }
}
