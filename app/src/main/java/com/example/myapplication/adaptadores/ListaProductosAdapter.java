package com.example.myapplication.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entidades.Productos;
import com.example.myapplication.R;
import com.example.myapplication.VerActivity;

import java.util.ArrayList;

public class ListaProductosAdapter extends RecyclerView.Adapter<ListaProductosAdapter.NotaViewHolder> {
    ArrayList<Productos> listaNotas;
    public ListaProductosAdapter(ArrayList<Productos> listaNotas){
        this.listaNotas=listaNotas;
    }
    @NonNull
    @Override
    public NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_notas,null, false);
        return new NotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotaViewHolder holder, int position) {
        holder.viewTitulo.setText(listaNotas.get(position).getTitulo());
        holder.viewDescripcion.setText(listaNotas.get(position).getDescripcion());
        holder.viewPrecio.setText(listaNotas.get(position).getPrecio());
    }

    @Override
    public int getItemCount() {
        return listaNotas.size();
    }

    public class NotaViewHolder extends RecyclerView.ViewHolder {
        TextView viewTitulo, viewDescripcion, viewPrecio;
        public NotaViewHolder(@NonNull View itemView) {
            super(itemView);
            viewTitulo=itemView.findViewById(R.id.TextTitulo);
            viewDescripcion=itemView.findViewById(R.id.TextDescripcion);
            viewPrecio=itemView.findViewById(R.id.TextPrecio);
            viewDescripcion.setVisibility(View.INVISIBLE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "Detalles", Toast.LENGTH_SHORT).show();
                    Context context= view.getContext();
                    Intent intent=new Intent(context, VerActivity.class);
                    intent.putExtra("id", listaNotas.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
