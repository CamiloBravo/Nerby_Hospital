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

import com.firebase.ui.auth.AuthUI;
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
    String sangre, Correo2, nombre, documento, scorreo, scontrasena, sexo, correo2, nombre2, sangre2, documento2, userid, userid2;
    private FirebaseAuth mAuth2;
    //    Bitmap foto_perfil;
    FirebaseDatabase database3;
    DatabaseReference myRef3;
    Correo correoclass;
    ArrayList<Correo> info;
    private static final int RC_SIGN_IN=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);// QUITAR APPBAR
        setContentView(R.layout.activity_login);

        prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        editor = prefs.edit();
        database3 = FirebaseDatabase.getInstance();

        info = new ArrayList<Correo>();

        correo2 = prefs.getString("correo", "nocorreo");
        nombre2 = prefs.getString("nombre", "nonombre");
        sangre2 = prefs.getString("sangre", "nosangre");
        documento2 = prefs.getString("documento", "nodocumento");

        if(prefs.getInt("login", -1) == 1) {
            mAuth2 = FirebaseAuth.getInstance();
            intent = new Intent(LoginActivity.this, PerfilDrawerActivity.class);
            userid = mAuth2.getCurrentUser().getUid();
            intent.putExtra("user", userid);
            startActivity(intent);
            finish();
        }

        mAuth2 = FirebaseAuth.getInstance();
        if (mAuth2.getCurrentUser()!=null){
            // usuario logueado
        } else{
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setProviders(
                    AuthUI.FACEBOOK_PROVIDER,
                    AuthUI.GOOGLE_PROVIDER).build(),RC_SIGN_IN);
        }


        eContrasena = (EditText) findViewById(R.id.econtrasena);
        bIniciar = (Button) findViewById(R.id.biniciar);
        bRegistrar = (Button) findViewById(R.id.bregistrese);
        bEmergencia = (Button) findViewById(R.id.bemergencia);
        eCorreo = (EditText) findViewById(R.id.edcorreo);

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
                myRef3 = database3.getReference("Datos");
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
                                if (userid.equals("auOvjKwIrqQ9Wtazh7I6wK2m0wt1")){
                                    intent = new Intent(LoginActivity.this, DoctorActivity.class);
                                    intent.putExtra("user", userid);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    editor.putInt("login",1);
                                    editor.commit();
                                    intent = new Intent(LoginActivity.this, PerfilDrawerActivity.class);
                                    intent.putExtra("user", userid);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(LoginActivity.this, userid,
                                            Toast.LENGTH_SHORT).show();

                                    myRef3.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.child(userid).exists()) {
                                                info.add(dataSnapshot.child(userid).getValue(Correo.class));
                                                nombre2 = info.get(0).getNombre();
                                                sangre2 = info.get(0).getSangre();
                                                correo2 = info.get(0).getCorreo();
                                                documento2 = info.get(0).getDocumento();
                                                editor.putString("sangre", sangre2);
                                                editor.putString("nombre", nombre2);
                                                editor.putString("documento", documento2);
                                                editor.putString("correo", correo2);
                                                editor.commit();

                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                            Toast.makeText(LoginActivity.this, "El userid no existe",
                                                    Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                }
                            }
                        }
                    });

                }
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