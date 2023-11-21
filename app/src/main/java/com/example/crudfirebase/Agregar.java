package com.example.crudfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Agregar extends AppCompatActivity {

    EditText titulo, autor, genero, precio, cantidad;
    Button btnadd, btnatras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_agregar);

        titulo = findViewById(R.id.titulotxt);
        autor = findViewById(R.id.autortxt);
        genero = findViewById(R.id.generotxt);
        precio = findViewById(R.id.preciotxt);
        cantidad = findViewById(R.id.cantidadtxt);

        btnadd = findViewById(R.id.btn_agregar);
        btnatras = findViewById(R.id.btn_atras);

        btnadd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                insertarDatos();
            }
        });
        btnatras.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                finish();
            }
        });
    }
    private void insertarDatos() {
        Map<String,Object> map = new HashMap<>();

        map.put("Titulo", titulo.getText().toString());
        map.put("Autor", autor.getText().toString());
        map.put("Genero", genero.getText().toString());
        map.put("Precio", precio.getText().toString());
        map.put("Cantidad", cantidad.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Inventario").push()
                .setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused){
                        Toast.makeText(Agregar.this, "Insertado Correctamente", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e){
                        Toast.makeText(Agregar.this, "Error al Insertar", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}