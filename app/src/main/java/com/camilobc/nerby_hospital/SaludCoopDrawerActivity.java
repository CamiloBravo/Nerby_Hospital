package com.camilobc.nerby_hospital;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.net.Uri;

public class SaludCoopDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent;
    String sangre, snombre, documento, scorreo;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public SectionsPagerAdapter mSectionsPagerAdapter;
    public ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salud_coop_drawer);

//        telefono = "0345711516";
        Bundle extras=getIntent().getExtras();
        sangre = extras.getString("sangre");
        snombre = extras.getString("nombre");
        documento = extras.getString("documento");
        scorreo = extras.getString("correo");

//        prefs= getSharedPreferences("MisPreferencias",MODE_PRIVATE);
//        editor = prefs.edit();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////                Intent i = new Intent(Intent.ACTION_CALL);
////                i.setData(Uri.parse("tel:123456789"));
////                startActivity(i);
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0: HospSaludcoopFragment tab1 = new HospSaludcoopFragment();
                    return tab1;
                case 1: MapaSaludcoopFragment tab2 = new MapaSaludcoopFragment();
                    return tab2;
                default: return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Informacion";
                case 1:
                    return "Ubicacion";
            }
            return null;
        }
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
        getMenuInflater().inflate(R.menu.salud_coop_drawer, menu);
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
            intent = new Intent(SaludCoopDrawerActivity.this, LoginActivity.class);
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
            Intent intent = new Intent(SaludCoopDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);
        } else if (id == R.id.quemaduras) {
            Intent intent = new Intent(SaludCoopDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);

        } else if (id == R.id.infecciones) {
            Intent intent = new Intent(SaludCoopDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);

        } else if (id == R.id.alergias) {
            Intent intent = new Intent(SaludCoopDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);

        } else if (id == R.id.hemorragias) {
            Intent intent = new Intent(SaludCoopDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);

        } else if (id == R.id.cabeza) {
            Intent intent = new Intent(SaludCoopDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);

        } else if (id == R.id.cuerpo) {
            Intent intent = new Intent(SaludCoopDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);

        } else if (id == R.id.estomago) {
            Intent intent = new Intent(SaludCoopDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);

        } else if (id == R.id.oido) {
            Intent intent = new Intent(SaludCoopDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);

        } else if (id == R.id.vision) {
            Intent intent = new Intent(SaludCoopDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);

        } else if (id == R.id.piel) {
            Intent intent = new Intent(SaludCoopDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);

        } else if (id == R.id.miperfil) {
            Intent intent = new Intent(SaludCoopDrawerActivity.this, PerfilDrawerActivity.class);
            intent.putExtra("nombre", snombre);
            intent.putExtra("documento", documento);
            intent.putExtra("sangre", sangre);
            intent.putExtra("correo", scorreo);
            startActivity(intent);
            finish();

        } else if (id == R.id.cerrar) {
//            editor.putInt("login",-1);
//            editor.commit();
            intent = new Intent(SaludCoopDrawerActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
