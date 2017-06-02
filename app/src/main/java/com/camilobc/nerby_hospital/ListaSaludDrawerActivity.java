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
//    SharedPreferences prefs;
//    SharedPreferences.Editor editor;

//    private Lista_entrada[] datos=new Lista_entrada[]{
//            new Lista_entrada(R.drawable.saludcoop, "Saludcoop", "Clinica", "calle 7 #12-A"),
//            new Lista_entrada(R.drawable.leon13, "Clinica Leon XIII", "Clinica", "calle 9 #13-B"),
//            new Lista_entrada(R.drawable.hospsanvicente, "San Vicente Fundación", "Hospital Universitario", "calle 8 #11-A")
//    };
    ArrayList<DatosHospi> datoshospi;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_salud_drawer);

        Bundle extras=getIntent().getExtras();
        eps = extras.getString("eps");

//        prefs= getSharedPreferences("MisPreferencias",MODE_PRIVATE);
//        editor = prefs.edit();

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
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()){
//                    lista_entrada.add(userSnapshot.getValue(Lista_entrada.class));
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        list = (ListView) findViewById(R.id.list);
        Adapter adapter= new Adapter(this, datoshospi);
        list.setAdapter(adapter);

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
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_parques, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id=item.getItemId();
//        switch (id)
//        {
//            case R.id.cerrar: //cerrar es nombre de menu.xml
//                editor.putInt("login",-1); //aqui coloca el -1, para que quede sin login
//                editor.commit();
//                intent =new Intent(ListActivity.this, LoginActivity.class); //ojo a lo que antepone el this!!!
//                startActivity(intent);
//                finish();
//                break;
//            case R.id.Principal: //este tambien esta en menu.xml
//                intent =new Intent(ListActivity.this, DrawerMainActivity.class); //ojo a lo que antepone el this!!!
//                intent.putExtra("username", username);
//                intent.putExtra("correo", correo);
//                startActivity(intent);
//                finish();
//                break;
//            case R.id.miPerfil: //este tambien esta en menu.xml
//                intent =new Intent(ListActivity.this, DrawerPerfilActivity.class); //ojo a lo que antepone el this!!!
//                intent.putExtra("username", username);
//                intent.putExtra("correo", correo);
//                startActivity(intent);
//                finish();
//                break;
//            case R.id.sitios: //cerrar es nombre de menu.xml
//                intent =new Intent(ListActivity.this, DrawerSitiosActivity.class); //ojo a lo que antepone el this!!!
//                intent.putExtra("username", username);
//                intent.putExtra("correo", correo);
//                startActivity(intent);
//                finish();
//                break;
//            case R.id.bares: //cerrar es nombre de menu.xml
//                intent =new Intent(ListActivity.this, DrawerBarActivity.class); //ojo a lo que antepone el this!!!
//                intent.putExtra("username", username);
//                intent.putExtra("correo", correo);
//                startActivity(intent);
//                finish();
//                break;
//            case R.id.hotel: //cerrar es nombre de menu.xml
//                intent =new Intent(ListActivity.this, DrawerHotelActivity.class); //ojo a lo que antepone el this!!!
//                intent.putExtra("username", username);
//                intent.putExtra("correo", correo);
//                startActivity(intent);
//                finish();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    class Adapter extends ArrayAdapter<DatosHospi> {

        public Adapter(@NonNull Context context, ArrayList<DatosHospi> datoshospi) { //recibe contecto y arreglo
            super(context, R.layout.list_item, datoshospi); //Retorna el array con la info
        }



        //ctrl + O y buscar getview
//        @NonNull
//        @Override
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            View item = inflater.inflate(R.layout.list_item, null);
//
//            TextView nombre=(TextView) item.findViewById(R.id.Nombre);
//            nombre.setText(datos[position].getNombre());
//
//            TextView descrip=(TextView) item.findViewById(R.id.Descrip);
//            descrip.setText(datos[position].getDescrip());
//
//            TextView direc=(TextView) item.findViewById(R.id.Direcc);
//            direc.setText(datos[position].getDirec());
//
//            ImageView imagen= (ImageView) item.findViewById(R.id.iFoto);
//            imagen.setImageResource(datos[position].getIdImagen());
//
//            return item;
//            //return super.getView(position, convertView, parent);
//        }
        public View getView(int position, View convertView, ViewGroup parent) {

            DatosHospi datoshospi = getItem(position);

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.list_item, null);

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
//            editor.putInt("login",-1);
//            editor.commit();
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

        if (id == R.id.accidente) {
            Intent intent = new Intent(ListaSaludDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("user", userid);
            startActivity(intent);
        } else if (id == R.id.quemaduras) {
            Intent intent = new Intent(ListaSaludDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("user", userid);
            startActivity(intent);

        } else if (id == R.id.infecciones) {
            Intent intent = new Intent(ListaSaludDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("user", userid);
            startActivity(intent);

        } else if (id == R.id.alergias) {
            Intent intent = new Intent(ListaSaludDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("user", userid);
            startActivity(intent);

        } else if (id == R.id.hemorragias) {
            Intent intent = new Intent(ListaSaludDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("user", userid);
            startActivity(intent);

        } else if (id == R.id.cabeza) {
            Intent intent = new Intent(ListaSaludDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("user", userid);
            startActivity(intent);

        } else if (id == R.id.cuerpo) {
            Intent intent = new Intent(ListaSaludDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("user", userid);
            startActivity(intent);

        } else if (id == R.id.estomago) {
            Intent intent = new Intent(ListaSaludDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("user", userid);
            startActivity(intent);

        } else if (id == R.id.oido) {
            Intent intent = new Intent(ListaSaludDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("user", userid);
            startActivity(intent);

        } else if (id == R.id.vision) {
            Intent intent = new Intent(ListaSaludDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("user", userid);
            startActivity(intent);

        } else if (id == R.id.piel) {
            Intent intent = new Intent(ListaSaludDrawerActivity.this, MapasDrawerActivity.class);
            intent.putExtra("user", userid);
            startActivity(intent);

        } else if (id == R.id.miperfil) {
            Intent intent = new Intent(ListaSaludDrawerActivity.this, PerfilDrawerActivity.class);
            intent.putExtra("user", userid);
            startActivity(intent);
            finish();

        } else if (id == R.id.cerrar) {
//            editor.putInt("login",-1);
//            editor.commit();
            intent = new Intent(ListaSaludDrawerActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
