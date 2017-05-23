package com.camilobc.nerby_hospital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Map;

public class ListaSuraActivity extends AppCompatActivity {
    ListView listView;
    DatabaseReference myRef4;
    ArrayList<Infohosp> list;
    ArrayAdapter adapter;
    String clinica="ClinicaElRosario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sura);

        list = new ArrayList<Infohosp>();
        listView = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<Infohosp>(this, android.R.layout.simple_dropdown_item_1line, list);
        listView.setAdapter(adapter);
        myRef4 = FirebaseDatabase.getInstance().getReference("Sura");
        myRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(clinica).exists()){
                    list.add(dataSnapshot.child(clinica).getValue(Infohosp.class));
//                    nombre = info.get(0).getNombre();
//                    sangre = info.get(0).getSangre();
//                    correo = info.get(0).getCorreo();
//                    documento = info.get(0).getDocumento();
//                    tnombre_perfil.setText(nombre);
//                    tsangre_perfil.setText(sangre);
//                    tcorreo_perfil.setText(correo);
//                    tcedula_perfil.setText(documento);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        ValueEventListener listener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                map2list((Map) dataSnapshot.getValue());
//                //formats the datasnapshot entries to strings
//                adapter.notifyDataSetChanged();
//                //makes the ListView realtime
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println(databaseError.toException());
//
//            }
//
//        };



//        myRef4.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                String value = dataSnapshot.getValue(String.class);
//                list.add(value);
//                adapter.notifyDataSetChanged();
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
    }
//    public void map2list(Map<String, Long>map) {
//        list.clear();
//        for (Map.Entry<String, Long> entry : map.entrySet()) {
//
//            Long key = Long.parseLong(entry.getKey());
//            String d = DateFormat.getDateTimeInstance().format(key);
//            Long value = entry.getValue();
//            list.add(d + ": " + value);
//        }
//    }
}
