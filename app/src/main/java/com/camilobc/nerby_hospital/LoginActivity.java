package com.camilobc.nerby_hospital;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    Intent intent;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    EditText eCorreo, eContrasena;
    Button bIniciar, bRegistrar, bEmergencia;
    String sangre, Correo2, nombre, documento, scorreo, scontrasena, sexo, correo2, nombre2, sangre2, documento2, userid, userid2;
    private FirebaseAuth mAuth;
    //    Bitmap foto_perfil;
    FirebaseDatabase database3;
    DatabaseReference myRef3;
    Correo correoclass;
    ArrayList<Correo> info;
    private static final String TAG = "LoginActivity";
    private FirebaseAuth.AuthStateListener mAuthListener;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private static final int RC_SIGN_IN = 1;
    private GoogleApiClient mGoogleApiClient;
    String optLog="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);// QUITAR APPBAR
        setContentView(R.layout.activity_login);

       /* prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        editor = prefs.edit();*/
        database3 = FirebaseDatabase.getInstance();

        info = new ArrayList<Correo>();

        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext()).enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                Toast.makeText(LoginActivity.this , "Error", Toast.LENGTH_SHORT).show();
            }
        })
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                myRef3 = database3.getReference("Datos");
                mAuth = FirebaseAuth.getInstance();
                if (user != null) {  //cuando ya hay unn usuario loggeado
                    userid = mAuth.getCurrentUser().getUid();
                    myRef3.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (userid.equals("auOvjKwIrqQ9Wtazh7I6wK2m0wt1")){
                                intent = new Intent(LoginActivity.this, DoctorActivity.class);
                                intent.putExtra("user", userid);
                                startActivity(intent);
                                finish();
                            }
                            else if (dataSnapshot.child(userid).exists()) { //cuando se ha creado en la base de datos entra acá
                                info.add(dataSnapshot.child(userid).getValue(Correo.class));
                                nombre2 = info.get(0).getNombre();
                                sangre2 = info.get(0).getSangre();
                                correo2 = info.get(0).getCorreo();
                                documento2 = info.get(0).getDocumento();
                                /*editor.putString("sangre", sangre2);
                                editor.putString("nombre", nombre2);
                                editor.putString("documento", documento2);
                                editor.putString("correo", correo2);
                                editor.commit();*/
                                intent = new Intent(LoginActivity.this, PerfilActivity.class);;
                                startActivity(intent);//lo envia al perfil
                                finish();

                            }else{
                                intent = new Intent(LoginActivity.this, RegistroActivity.class);
                                optLog="2"; //si el usuario está loggeado pero por ejemplo canceló el proceso de llenar el resto de info
                                intent.putExtra("optLog", optLog);
                                startActivity(intent);
                                //finish();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(LoginActivity.this, "El userid no existe",
                                    Toast.LENGTH_SHORT).show();

                        }
                    });

                    // User is signed in
                    // goNextActivity();
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
       /* correo2 = prefs.getString("correo", "nocorreo");
        nombre2 = prefs.getString("nombre", "nonombre");
        sangre2 = prefs.getString("sangre", "nosangre");
        documento2 = prefs.getString("documento", "nodocumento");

        if(prefs.getInt("login", -1) == 1) {
            mAuth = FirebaseAuth.getInstance();
            intent = new Intent(LoginActivity.this, PerfilDrawerActivity.class);
            userid = mAuth.getCurrentUser().getUid();
            intent.putExtra("user", userid);
            startActivity(intent);
            finish();
        }*/
        eContrasena = (EditText) findViewById(R.id.econtrasena);
        bIniciar = (Button) findViewById(R.id.biniciar);
        bRegistrar = (Button) findViewById(R.id.bregistrese);
        bEmergencia = (Button) findViewById(R.id.bemergencia);
        eCorreo = (EditText) findViewById(R.id.edcorreo);

        if(!(AccessToken.getCurrentAccessToken()==null)){
            //goNextActivity();  //si hay alguien loggeado al darle atrás lo manda a otra actividad

        }

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email"));
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                optLog = "2";
                handleFacebookAccessToken(loginResult.getAccessToken());
                //goNextActivity();


            }


            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),"loginCancelado",Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Error en Login", Toast.LENGTH_SHORT).show();
            }
        });

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
                intent.putExtra("optLog", optLog);
                startActivity(intent);
            }
        });

        bIniciar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                myRef3 = database3.getReference("Datos");
                mAuth = FirebaseAuth.getInstance();
                if(eCorreo.getText().toString().equals("") || eContrasena.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Llene los campos requeridos",Toast.LENGTH_SHORT).show();
                }else {
                    mAuth.signInWithEmailAndPassword(eCorreo.getText().toString(), eContrasena.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "El usuario ingresado no existe",
                                        Toast.LENGTH_SHORT).show();
                            } else {

                                userid = mAuth.getCurrentUser().getUid();
                                if (userid.equals("auOvjKwIrqQ9Wtazh7I6wK2m0wt1")){
                                    intent = new Intent(LoginActivity.this, DoctorActivity.class);
                                    intent.putExtra("user", userid);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    //  editor.putInt("login",1);
                                    //  editor.commit();
                                    intent = new Intent(LoginActivity.this, PerfilActivity.class);
                                    //intent.putExtra("user", userid);
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
                                              /*  editor.putString("sangre", sangre2);
                                                editor.putString("nombre", nombre2);
                                                editor.putString("documento", documento2);
                                                editor.putString("correo", correo2);
                                                editor.commit();*/

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
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    private void signIn() {
        optLog="1";
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void handleFacebookAccessToken(AccessToken accessToken) {
        myRef3 = database3.getReference("Datos");
        mAuth = FirebaseAuth.getInstance();
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Error en login", Toast.LENGTH_SHORT).show();
                }else {
                    userid = mAuth.getCurrentUser().getUid();
                    //intent = new Intent(LoginActivity.this, RegistroActivity.class);
                    // intent.putExtra("user", userid);
                    // startActivity(intent);
                    // finish();
                    // Toast.makeText(LoginActivity.this, userid,
                    //         Toast.LENGTH_SHORT).show();

                    myRef3.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(userid).exists()) { //si el usuario está en la base de datos con toda la info entra acá
                                info.add(dataSnapshot.child(userid).getValue(Correo.class));
                                nombre2 = info.get(0).getNombre();
                                sangre2 = info.get(0).getSangre();
                                correo2 = info.get(0).getCorreo();
                                documento2 = info.get(0).getDocumento();
                                /*editor.putString("sangre", sangre2);
                                editor.putString("nombre", nombre2);
                                editor.putString("documento", documento2);
                                editor.putString("correo", correo2);
                                editor.commit();*/
                                intent = new Intent(LoginActivity.this, PerfilActivity.class);
                                //intent.putExtra("optLog", optLog);
                                startActivity(intent);
                                finish();

                            }else{
                                intent = new Intent(LoginActivity.this, RegistroActivity.class);
                                intent.putExtra("optLog", optLog);//cuando se loggea pero es la primera vez que entra lo mandaaregistro
                                startActivity(intent);
                                //finish();
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
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(optLog.equals("1")){
            if (requestCode == RC_SIGN_IN) {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                if (result.isSuccess()) {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = result.getSignInAccount();
                    firebaseAuthWithGoogle(account);
                } else {
                    // Google Sign In failed, update UI appropriately
                    // ...
                }
            }
        }else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
        myRef3 = database3.getReference("Datos");
        mAuth = FirebaseAuth.getInstance();
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            userid = mAuth.getCurrentUser().getUid();
                            myRef3.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    if (dataSnapshot.child(userid).exists()) {//si el usuario loggeado está ya en la base de datos con toda la info
                                        info.add(dataSnapshot.child(userid).getValue(Correo.class));
                                        nombre2 = info.get(0).getNombre();
                                        sangre2 = info.get(0).getSangre();
                                        correo2 = info.get(0).getCorreo();
                                        documento2 = info.get(0).getDocumento();
                                       /* editor.putString("sangre", sangre2);
                                        editor.putString("nombre", nombre2);
                                        editor.putString("documento", documento2);
                                        editor.putString("correo", correo2);
                                        editor.commit();*/
                                        intent = new Intent(LoginActivity.this, PerfilActivity.class);
                                        //intent.putExtra("optLog", optLog);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        intent = new Intent(LoginActivity.this, RegistroActivity.class);
                                        intent.putExtra("optLog", optLog);//si se loggea con google pero es la primera vez que entra
                                        startActivity(intent);
                                        //finish();
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
                });
    }
}