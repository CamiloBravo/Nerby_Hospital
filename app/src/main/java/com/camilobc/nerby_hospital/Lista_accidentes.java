package com.camilobc.nerby_hospital;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class Lista_accidentes extends AppCompatActivity {
    Intent intent;
    ArrayList<DatosHospi> datoshospi;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ListView list;
    String eps, accidente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_accidentes);

        Bundle extras = getIntent().getExtras();
        eps = extras.getString("eps");
        accidente = extras.getString("patologia");

        database = FirebaseDatabase.getInstance();
        datoshospi = new ArrayList<DatosHospi>();

        myRef = database.getReference("Patologias").child(eps).child(accidente);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    datoshospi.add(userSnapshot.getValue(DatosHospi.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        list = (ListView) findViewById(R.id.list);
        Adapter adapter = new Adapter(this, datoshospi);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data= ((DatosHospi) parent.getItemAtPosition(position)).getNombre();
                Toast.makeText(Lista_accidentes.this,data,Toast.LENGTH_SHORT).show();

//                Intent intent=new Intent(ListActivity.this, HotelActivity.class);
//                startActivity(intent);

            }
        });
    }

    class Adapter extends ArrayAdapter<DatosHospi> {

        public Adapter(@NonNull Context context, ArrayList<DatosHospi> datoshospi) { //recibe contecto y arreglo
            super(context, R.layout.list_item, datoshospi); //Retorna el array con la info
        }

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cerrar_s) {
//            editor.putInt("login",-1);
//            editor.commit();
            LoginManager.getInstance().logOut();
            FirebaseAuth.getInstance().signOut();
            intent = new Intent(Lista_accidentes.this, LoginActivity.class);
            startActivity(intent);

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}