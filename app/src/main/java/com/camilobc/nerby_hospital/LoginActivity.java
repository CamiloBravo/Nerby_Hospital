package com.camilobc.nerby_hospital;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Intent intent;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    EditText eCorreo, eContrasena;
    Button bIniciar, bRegistrar, bEmergencia;
    String sangre, nombre, documento, scorreo, scontrasena, sexo, correo2, nombre2;
    private FirebaseAuth mAuth;
    //    Bitmap foto_perfil;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Usuarios usuarios;
    ArrayList<Usuarios> info;

//    private String FIREBASE_URL="https://nerbyhospitalv1.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);// QUITAR APPBAR
        setContentView(R.layout.activity_login);

        prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        editor = prefs.edit();

        info = new ArrayList<Usuarios>();
        documento = "65978";



//        sexo = prefs.getString("sexo", "nosexo");
//        sangre = prefs.getString("sangre", "nosangre");
//        nombre = prefs.getString("nombre", "nonombre");
//        documento = prefs.getString("documento", "nodocumento");
//        scorreo = prefs.getString("correo", "nocorreo");
//        scontrasena = prefs.getString("pass", "nopass");




//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//                if (firebaseUser != null) {
//
//                    String userEmail = firebaseUser.getEmail();
//
//                    editor.putString("correo", userEmail);
//                    editor.commit();
//                }
//            }
//        };

        scorreo = prefs.getString("correo", "nocorreo");
        nombre = prefs.getString("nombre", "nonombre");


//            editor.putString("sangre", sangre);
//            editor.putString("nombre", nombre);
//            editor.putString("documento", documento);
//            editor.putString("correo", scorreo);
//            editor.putString("pass", scontrasena);
//            editor.commit();

        mAuth = FirebaseAuth.getInstance();

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
                database = FirebaseDatabase.getInstance();
//                correo2 = eCorreo.getText().toString();
                mAuth.signInWithEmailAndPassword (eCorreo.getText().toString(), eContrasena.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Usuario Invalido",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            intent = new Intent(LoginActivity.this, PerfilDrawerActivity.class);
                            intent.putExtra("sangre", sangre);
//                    intent.putExtra("sexo", sexo);
                            intent.putExtra("nombre", nombre);
                            intent.putExtra("documento", documento);
                            intent.putExtra("correo", scorreo);

                            startActivity(intent);
                            finish();
                            Toast.makeText(LoginActivity.this, "Bienvenido",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });

                myRef = database.getReference("Usuarios");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(documento).exists()){
                            info.add(dataSnapshot.child(documento).getValue(Usuarios.class));
                            correo2 = info.get(0).getCorreo();
                            editor.putString("correo", correo2);
                            nombre2 = info.get(0).getNombre();
                            editor.putString("nombre", nombre2);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                editor.putInt("login",1);
                editor.commit();


//                if(eCorreo.getText().toString().equals("") || eContrasena.getText().toString().equals("")){
//                    Toast.makeText(getApplicationContext(),"Llene los campos requeridos",Toast.LENGTH_SHORT).show();
//                }else if(!(eCorreo.getText().toString().equals(scorreo) && eContrasena.getText().toString().equals(scontrasena))) {
//                    Toast.makeText(getApplicationContext(), "Usuario invalido", Toast.LENGTH_SHORT).show();
//                } else if(eCorreo.getText().toString().equals(scorreo) && eContrasena.getText().toString().equals(scontrasena)){
//
//                    editor.putInt("login",1);
//                    editor.commit();
//                    intent = new Intent(LoginActivity.this, PerfilDrawerActivity.class);
//                    intent.putExtra("sangre", sangre);
////                    intent.putExtra("sexo", sexo);
//                    intent.putExtra("nombre", nombre);
//                    intent.putExtra("documento", documento);
//                    intent.putExtra("correo", scorreo);
//
//                    startActivity(intent);
//                    finish();
//                }
//                else
//                {
//                    Toast.makeText(LoginActivity.this, "El usuario ingresado no existe", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode==RESULT_OK){

//            sangre = data.getExtras().getString("sangre");
//            nombre = data.getExtras().getString("nombre");
//            documento = data.getExtras().getString("documento");
//            scorreo = data.getExtras().getString("correo");
//            scontrasena = data.getExtras().getString("pass");
//
//            editor.putString("sangre", sangre);
//            editor.putString("nombre", nombre);
//            editor.putString("documento", documento);
//            editor.putString("correo", scorreo);
//            editor.putString("pass", scontrasena);
//            editor.commit();
        }else{
            if(requestCode==1234 && resultCode==RESULT_CANCELED){
                Toast.makeText(this, "Error en Registro", Toast.LENGTH_SHORT).show();
            }
        }
    }
}