package com.camilobc.nerby_hospital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorActivity extends AppCompatActivity {

    TextView Tnombrepaciente, Tdocupaciente, Tcorreopaciente, Ttelefonopaciente, Tacudiente, Ttelefonoacudiente, Talergias, Tenfermedades, Tsangre;
    String userid, nombrepaciente, docupaciente, correopaciente, telefonopaciente, acudiente, telefonoacudiente, alergias, enfermedades, sangre;;
    EditText Einfodocu;
    Intent intent;
    Button verinfo;
    FirebaseDatabase database3;
    DatabaseReference myRef3;
    Usuarios usuarios;
    ArrayList<Usuarios> info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);





        Bundle extras = getIntent().getExtras();
        userid = extras.getString("user");

        Einfodocu = (EditText) findViewById(R.id.Einfopaciente);
        Tnombrepaciente = (TextView) findViewById(R.id.tnombrepaciente);
        Tacudiente = (TextView) findViewById(R.id.tacudiente);
        Tsangre = (TextView) findViewById(R.id.tsangre_perfil);
        Tcorreopaciente = (TextView) findViewById(R.id.tcorreo_perfil);
        verinfo = (Button) findViewById(R.id.binfopaciente);


//        verinfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


    }

    public void infopaciente(View view) {
        info = new ArrayList<Usuarios>();
        docupaciente = Einfodocu.getText().toString();
        database3 = FirebaseDatabase.getInstance();
        myRef3 = database3.getReference("Usuarios");
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(docupaciente).exists()){
                    info.add(dataSnapshot.child(docupaciente).getValue(Usuarios.class));
                    nombrepaciente = info.get(0).getNombre();
                    sangre = info.get(0).getSangre();
                    correopaciente = info.get(0).getCorreo();
                    acudiente = info.get(0).getAcudiente();
                    Tnombrepaciente.setText(nombrepaciente);
                    Tsangre.setText(sangre);
                    Tacudiente.setText(acudiente);
                    Tcorreopaciente.setText(correopaciente);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
