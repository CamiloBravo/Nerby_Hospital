package com.camilobc.nerby_hospital;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MapasDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    String sangre, snombre, documento, scorreo, sexo, userid, eps;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Intent intent;
    FirebaseDatabase database3;
    DatabaseReference myRef3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapas_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();

//        sexo = extras.getString("sexo");
        eps = extras.getString("eps");
//        snombre = extras.getString("nombre");
//        documento = extras.getString("documento");
//        scorreo = extras.getString("correo");

//        prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
//        editor = prefs.edit();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, new Mapa1Fragment(), "Mapa1Fragment");
        transaction.commitAllowingStateLoss();
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
        getMenuInflater().inflate(R.menu.mapas_drawer, menu);
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
//            editor.putInt("login",-1);
//            editor.commit();
            intent = new Intent(MapasDrawerActivity.this, LoginActivity.class);
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
            Intent intent = new Intent(MapasDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);
        } else if (id == R.id.quemaduras) {
            Intent intent = new Intent(MapasDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);

        } else if (id == R.id.infecciones) {
            Intent intent = new Intent(MapasDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);

        } else if (id == R.id.alergias) {
            Intent intent = new Intent(MapasDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);

        } else if (id == R.id.hemorragias) {
            Intent intent = new Intent(MapasDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("sexo", sexo);
            startActivity(intent);

        } else if (id == R.id.cabeza) {
            Intent intent = new Intent(MapasDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);

        } else if (id == R.id.cuerpo) {
            Intent intent = new Intent(MapasDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);

        } else if (id == R.id.estomago) {
            Intent intent = new Intent(MapasDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);

        } else if (id == R.id.oido) {
            Intent intent = new Intent(MapasDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);

        } else if (id == R.id.vision) {
            Intent intent = new Intent(MapasDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);

        } else if (id == R.id.piel) {
            Intent intent = new Intent(MapasDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);

        } else if (id == R.id.miperfil) {
            Intent intent = new Intent(MapasDrawerActivity.this, PerfilDrawerActivity.class);
            intent.putExtra("user", userid);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);
            finish();

        } else if (id == R.id.cerrar) {
//            editor.putInt("login",-1);
//            editor.commit();
            intent = new Intent(MapasDrawerActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
