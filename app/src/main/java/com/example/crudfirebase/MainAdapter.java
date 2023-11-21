package com.example.crudfirebase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myViewHolder> {
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options){

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainModel model) {

        holder.titulo.setText("Título: "+model.getTitulo());
        holder.autor.setText("Autor: "+model.getAutor());
        holder.genero.setText("Género: "+model.getGenero());
        holder.precio.setText("Precio: $"+model.getPrecio());
        holder.cantidad.setText("Cantidad: "+model.getCantidad());

        holder.editar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final DialogPlus dialogPlus = DialogPlus.newDialog(v.getContext())
                        .setContentHolder(new ViewHolder(R.layout.ventana_emergente))
                        .setExpanded(true, 1750)
                        .create();
                View view = dialogPlus.getHolderView();

                EditText titulo = view.findViewById(R.id.titulotxt);
                EditText autor = view.findViewById(R.id.autortxt);
                EditText genero = view.findViewById(R.id.generotxt);
                EditText precio = view.findViewById(R.id.preciotxt);
                EditText cantidad = view.findViewById(R.id.cantidadtxt);

                Button actualizar = view.findViewById(R.id.btn_actualizar);

                titulo.setText(model.getTitulo());
                autor.setText(model.getAutor());
                genero.setText(model.getGenero());
                precio.setText(model.getPrecio());
                cantidad.setText(model.getCantidad());

                dialogPlus.show();

                actualizar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("Titulo", titulo.getText().toString());
                        map.put("Autor", autor.getText().toString());
                        map.put("Genero", genero.getText().toString());
                        map.put("Precio", precio.getText().toString());
                        map.put("Cantidad", cantidad.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Inventario")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>(){
                                    @Override
                                    public void onSuccess(Void unused){
                                        Toast.makeText(holder.titulo.getContext(),"Actualizacion Correcta",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }//
                                }).addOnFailureListener(new OnFailureListener(){
                                    @Override
                                    public void onFailure(@NonNull Exception e){
                                        Toast.makeText(holder.titulo.getContext(), "Error en la actualizacion", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });


            }
        });
        holder.eliminar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.titulo.getContext());
                builder.setTitle("Estas seguro de Eliminar");
                builder.setMessage("Eliminado");

                builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Inventario")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        Toast.makeText(holder.titulo.getContext(),"Cancelar",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, autor, genero, precio, cantidad;
        Button editar, eliminar;

        public myViewHolder(@NonNull View itemView){
            super(itemView);

            titulo = itemView.findViewById(R.id.titulotxt);
            autor = itemView.findViewById(R.id.autortxt);
            genero = itemView.findViewById(R.id.generotxt);
            precio = itemView.findViewById(R.id.preciotxt);
            cantidad = itemView.findViewById(R.id.cantidadtxt);

            editar = itemView.findViewById(R.id.btn_edit);
            eliminar = itemView.findViewById(R.id.btn_eliminar);
        }

    }

}
