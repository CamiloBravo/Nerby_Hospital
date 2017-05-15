package com.camilobc.nerby_hospital;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PerfilDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent;
    TextView tnombre_perfil, tcorreo_perfil, tsangre_perfil, tcedula_perfil;
    String sangre, nombre, documento, correo, Ssangre, Snombre, Sdocumento, Scorreo, Scontrasena;
    Spinner ListaSalud;
    RadioButton rMas, rFem;
    String[] items;
    Button binfohosp;
    ImageView iusuario;
    //    Bitmap imageBitmap;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListaSalud = (Spinner) findViewById(R.id.ListaEPS);
        items = getResources().getStringArray(R.array.EPS);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,items);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ListaSalud.setAdapter(adaptador);

        Bundle extras = getIntent().getExtras();

        sangre = extras.getString("sangre");
        nombre = extras.getString("nombre");
        documento = extras.getString("documento");
        correo = extras.getString("correo");

        prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        editor = prefs.edit();

        Ssangre = prefs.getString("sangre", "nosangre");
        Snombre = prefs.getString("nombre", "nonombre");
        Sdocumento = prefs.getString("documento", "nodocumento");
        Scorreo = prefs.getString("correo", "nocorreo");
        Scontrasena = prefs.getString("pass", "nopass");


        binfohosp = (Button) findViewById(R.id.binfohospi);
        tnombre_perfil = (TextView) findViewById(R.id.tnombre_perfil);
        tsangre_perfil = (TextView) findViewById(R.id.tsangre_perfil);
        tcorreo_perfil = (TextView) findViewById(R.id.tcorreo_perfil);
        tcedula_perfil = (TextView) findViewById(R.id.tcedula_perfil);
        tnombre_perfil.setText(nombre);
        tsangre_perfil.setText(sangre);
        tcorreo_perfil.setText(correo);
        tcedula_perfil.setText(documento);
//        database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("Usuarios");
//        myRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                if (dataSnapshot.child(scorreo).exists()){
//                    usuarios = dataSnapshot.child(documento).getValue(Usuarios.class);
//                                        tnombre_perfil.setText(usuarios.getNombre());
//                                        tsangre_perfil.setText(usuarios.getTelefono());
//                                        tcorreo_perfil.setText(usuarios.getCorreo());
//
//                }
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        binfohosp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilDrawerActivity.this, ListaSaludDrawerActivity.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("documento", documento);
                intent.putExtra("sangre", sangre);
                intent.putExtra("correo", correo);
                startActivity(intent);
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.perfil_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cerrar_sesion) {
            editor.putInt("login",-1);
            editor.commit();
            intent = new Intent(PerfilDrawerActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.accidente) {
            Intent intent = new Intent(PerfilDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", nombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", correo);
            startActivity(intent);
        } else if (id == R.id.quemaduras) {
            Intent intent = new Intent(PerfilDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", nombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", correo);
            startActivity(intent);

        } else if (id == R.id.infecciones) {
            Intent intent = new Intent(PerfilDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", nombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", correo);
            startActivity(intent);

        } else if (id == R.id.alergias) {
            Intent intent = new Intent(PerfilDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", nombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", correo);
            startActivity(intent);

        } else if (id == R.id.hemorragias) {
            Intent intent = new Intent(PerfilDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", nombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", correo);
            startActivity(intent);

        } else if (id == R.id.cabeza) {
            Intent intent = new Intent(PerfilDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", nombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", correo);
            startActivity(intent);

        } else if (id == R.id.cuerpo) {
            Intent intent = new Intent(PerfilDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre",nombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", correo);
            startActivity(intent);

        } else if (id == R.id.estomago) {
            Intent intent = new Intent(PerfilDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", nombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", correo);
            startActivity(intent);

        } else if (id == R.id.oido) {
            Intent intent = new Intent(PerfilDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", nombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", correo);
            startActivity(intent);

        } else if (id == R.id.vision) {
            Intent intent = new Intent(PerfilDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre",nombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", correo);
            startActivity(intent);

        } else if (id == R.id.piel) {
            Intent intent = new Intent(PerfilDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", nombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", correo);
            startActivity(intent);

//        } else if (id == R.id.cambiar_datos) {
//            Intent intent = new Intent(PerfilDrawerActivity.this, Registro2Activity.class);
//            intent.putExtra("nombre", snombre);
//            intent.putExtra("documento", documento);
//            intent.putExtra("sangre", sangre);
//            intent.putExtra("sexo", sexo);
//            startActivity(intent);
//            finish();

        } else if (id == R.id.cerrar) {
            editor.putInt("login",-1);
            editor.commit();
            intent = new Intent(PerfilDrawerActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            FirebaseAuth.getInstance().signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}