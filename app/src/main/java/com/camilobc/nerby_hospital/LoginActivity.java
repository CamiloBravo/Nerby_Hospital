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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Intent intent;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    EditText eCorreo, eContrasena;
    Button bIniciar, bRegistrar, bEmergencia;
    String sangre, Correo2, nombre, documento, scorreo, scontrasena, sexo, correo2, nombre2, sangre2, documento2, userid;
    private FirebaseAuth mAuth2;
    //    Bitmap foto_perfil;
    FirebaseDatabase database3;
    DatabaseReference myRef3;
    Correo correoclass;
    ArrayList<Correo> info;

//    private String FIREBASE_URL="https://nerbyhospitalv1.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);// QUITAR APPBAR
        setContentView(R.layout.activity_login);

//        prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
//        editor = prefs.edit();
        database3 = FirebaseDatabase.getInstance();

        info = new ArrayList<Correo>();
        eCorreo = (EditText) findViewById(R.id.edcorreo);
        Correo2= eCorreo.getText().toString();
        myRef3 = database3.getReference("Datos");

//        correo2 = prefs.getString("correo", "nocorreo");
//        nombre2 = prefs.getString("nombre", "nonombre");
//        sangre2 = prefs.getString("sangre", "nosangre");
//        documento2 = prefs.getString("documento", "nodocumento");

//        if(prefs.getInt("login", -1) == 1) {
//            intent = new Intent(LoginActivity.this, PerfilDrawerActivity.class);
//            intent.putExtra("sangre", sangre2);
//            intent.putExtra("nombre", nombre2);
//            intent.putExtra("documento", documento2);
//            intent.putExtra("correo", correo2);
//
//            startActivity(intent);
//            finish();
//        }

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
                //                correo2 = eCorreo.getText().toString();
                mAuth2 = FirebaseAuth.getInstance();

                if(eCorreo.getText().toString().equals("") || eContrasena.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Llene los campos requeridos",Toast.LENGTH_SHORT).show();
                }else {
                    mAuth2.signInWithEmailAndPassword(eCorreo.getText().toString(), eContrasena.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "El usuario ingresado no existe",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                userid = mAuth2.getCurrentUser().getUid();

                                intent = new Intent(LoginActivity.this, PerfilDrawerActivity.class);
                                intent.putExtra("user", userid);
//                                intent.putExtra("sangre", sangre2);
//                                intent.putExtra("nombre", nombre2);
//                                intent.putExtra("documento", documento2);
//                                intent.putExtra("correo", correo2);

                                startActivity(intent);
                                finish();
                                Toast.makeText(LoginActivity.this, userid,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

//                editor.putInt("login",1);
//                editor.commit();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode==RESULT_OK){

        }else{
            if(requestCode==1234 && resultCode==RESULT_CANCELED){
                Toast.makeText(this, "Error en Registro", Toast.LENGTH_SHORT).show();
            }
        }
    }
}