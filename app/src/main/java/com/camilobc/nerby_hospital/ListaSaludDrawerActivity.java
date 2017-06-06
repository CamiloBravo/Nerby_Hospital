package com.camilobc.nerby_hospital;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaSaludDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent;
    String sangre, snombre, documento, scorreo, userid, eps;
    TextView textView, textView2, Ttitulo;
    ArrayList<DatosHospi> datoshospi;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ListView list;
    int img;
    String lat,longitud, data;
    Ubicacion ubicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_salud_drawer);

        Bundle extras=getIntent().getExtras();
        eps = extras.getString("eps");
        snombre= extras.getString("nombre");
        Toast.makeText(ListaSaludDrawerActivity.this, snombre,
                Toast.LENGTH_SHORT).show();

        database = FirebaseDatabase.getInstance();
        datoshospi = new ArrayList<DatosHospi>();

        myRef = database.getReference("Clinicas").child(eps);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                    datoshospi.add(userSnapshot.getValue(DatosHospi.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        list = (ListView) findViewById(R.id.list);
        Adapter adapter= new Adapter(this, datoshospi);
        list.setAdapter(adapter);
        if(eps.equals("Colsanitas")){
            eps="Sanitas";
        }

        Ttitulo=(TextView) findViewById(R.id.Ttitulo);
        Ttitulo.setText("Hospitales");
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                data= ((DatosHospi) parent.getItemAtPosition(position)).getNombre();
                //Toast.makeText(ListaSaludDrawerActivity.this,accidente,Toast.LENGTH_SHORT).show();
                database = FirebaseDatabase.getInstance();
                String accidente = "Accidentes";
                //Toast.makeText(ListaSaludDrawerActivity.this,eps,Toast.LENGTH_SHORT).show();
                myRef = database.getReference("Patologias").child(eps).child(accidente);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(data).exists()){
                            // ubicacion.add(dataSnapshot.child(data).getValue(Ubicacion.class));
                            ubicacion = dataSnapshot.child(data).getValue(Ubicacion.class);
                            lat = ubicacion.getLat();
                            longitud = ubicacion.getLongitud();
                            //Toast.makeText(ListaSaludDrawerActivity.this,lat,Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ListaSaludDrawerActivity.this, MapsActivity.class);
                            intent.putExtra("lat", lat);
                            intent.putExtra("longitud",longitud);
                            intent.putExtra("nombre", data);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

//                Intent intent=new Intent(ListActivity.this, HotelActivity.class);
//                startActivity(intent);

            }
        });

//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String data= ((Lista_entrada) parent.getItemAtPosition(position)).getNombre();
//                Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();
//
//                if (position == 0) {
//                    Intent intent=new Intent(ListaSaludDrawerActivity.this, SaludCoopDrawerActivity.class);
//                    intent.putExtra("nombre", snombre);
//                    intent.putExtra("documento", documento);
//                    intent.putExtra("sangre", sangre);
//                    intent.putExtra("correo", scorreo);
//                    startActivity(intent);
//                }
//                if (position == 1) {
//                    Intent intent=new Intent(ListaSaludDrawerActivity.this, Leon13DrawerActivity.class);
//                    intent.putExtra("nombre", snombre);
//                    intent.putExtra("documento", documento);
//                    intent.putExtra("sangre", sangre);
//                    intent.putExtra("correo", scorreo);
//                    startActivity(intent);
//                }
//                if (position == 2) {
//                    Intent intent=new Intent(ListaSaludDrawerActivity.this, SanvicenteDrawerActivity.class);
//                    intent.putExtra("nombre", snombre);
//                    intent.putExtra("documento", documento);
//                    intent.putExtra("sangre", sangre);
//                    intent.putExtra("correo", scorreo);
//                    startActivity(intent);
//                }
////                Intent intent=new Intent(ListActivity.this, HotelActivity.class);
////                startActivity(intent);
//
//            }
//        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header= navigationView.getHeaderView(0);
        textView=(TextView) header.findViewById(R.id.texview2);
        textView2=(TextView) header.findViewById(R.id.textView);

        textView.setText(snombre);
        textView2.setText(eps);

    }

    class Adapter extends ArrayAdapter<DatosHospi> {

        public Adapter(@NonNull Context context, ArrayList<DatosHospi> datoshospi) { //recibe contecto y arreglo
            super(context, R.layout.list_item, datoshospi); //Retorna el array con la info
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            DatosHospi datoshospi = getItem(position);

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.list_item, null);

//            if (datoshospi.getNombre().equals("Clinica el rosario")){
//                ImageView img = (ImageView) findViewById(R.id.imagHosp);
//                img.set;
//            }


            TextView nombre = (TextView) item.findViewById(R.id.Nombre);
            nombre.setText(datoshospi.getNombre());

            TextView telefono = (TextView) item.findViewById(R.id.Tel);
            telefono.setText(datoshospi.getTelefono());

            TextView direccion = (TextView) item.findViewById(R.id.Direc);
            direccion.setText(datoshospi.getDireccion());

            return item;
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
        getMenuInflater().inflate(R.menu.lista_salud_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cerrar) {
            LoginManager.getInstance().logOut();
            FirebaseAuth.getInstance().signOut();
            intent = new Intent(ListaSaludDrawerActivity.this, LoginActivity.class);
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
        if(eps.equals("Colsanitas")){
            eps="Sanitas";
        }

        if (id == R.id.accidente) {
            String accidente = "Accidentes";
            Intent intent = new Intent(ListaSaludDrawerActivity.this, Lista_accidentes.class);
            intent.putExtra("eps", eps);
            intent.putExtra("patologia", accidente);
            startActivity(intent);
        } else if (id == R.id.quemaduras) {
            String accidente = "Quemaduras";
            Intent intent = new Intent(ListaSaludDrawerActivity.this, Lista_accidentes.class);
            intent.putExtra("eps", eps);
            intent.putExtra("patologia", accidente);
            startActivity(intent);

        } else if (id == R.id.infecciones) {
            String accidente = "Infecciones";
            Intent intent = new Intent(ListaSaludDrawerActivity.this, Lista_accidentes.class);
            intent.putExtra("eps", eps);
            intent.putExtra("patologia", accidente);
            startActivity(intent);

        } else if (id == R.id.alergias) {
            String accidente = "Alergias Agudas";
            Intent intent = new Intent(ListaSaludDrawerActivity.this, Lista_accidentes.class);
            intent.putExtra("eps", eps);
            intent.putExtra("patologia", accidente);
            startActivity(intent);

        } else if (id == R.id.hemorragias) {
            String accidente = "Hemorragias";
            Intent intent = new Intent(ListaSaludDrawerActivity.this, Lista_accidentes.class);
            intent.putExtra("eps", eps);
            intent.putExtra("patologia", accidente);
            startActivity(intent);

        } else if (id == R.id.cabeza) {
            String accidente = "Dolor de Cabeza";
            Intent intent = new Intent(ListaSaludDrawerActivity.this, Lista_accidentes.class);
            intent.putExtra("eps", eps);
            intent.putExtra("patologia", accidente);
            startActivity(intent);

        } else if (id == R.id.cuerpo) {
            String accidente = "Dolor de las Articulaciones";
            Intent intent = new Intent(ListaSaludDrawerActivity.this, Lista_accidentes.class);
            intent.putExtra("eps", eps);
            intent.putExtra("patologia", accidente);
            startActivity(intent);

        } else if (id == R.id.estomago) {
            String accidente = "Dolor de Estómago";
            Intent intent = new Intent(ListaSaludDrawerActivity.this, Lista_accidentes.class);
            intent.putExtra("eps", eps);
            intent.putExtra("patologia", accidente);
            startActivity(intent);

        } else if (id == R.id.vision) {
            String accidente = "Visión Borrosa";
            Intent intent = new Intent(ListaSaludDrawerActivity.this, Lista_accidentes.class);
            intent.putExtra("eps", eps);
            intent.putExtra("patologia", accidente);
            startActivity(intent);

        } else if (id == R.id.piel) {
            String accidente = "Irritaciones en la piel";
            Intent intent = new Intent(ListaSaludDrawerActivity.this, Lista_accidentes.class);
            intent.putExtra("eps", eps);
            intent.putExtra("patologia", accidente);
            startActivity(intent);

        } else if (id == R.id.miperfil) {
            Intent intent = new Intent(ListaSaludDrawerActivity.this, PerfilActivity.class);
            intent.putExtra("user", userid);
            startActivity(intent);
            finish();

        } else if (id == R.id.cerrar) {
            LoginManager.getInstance().logOut();
            FirebaseAuth.getInstance().signOut();
            intent = new Intent(ListaSaludDrawerActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
