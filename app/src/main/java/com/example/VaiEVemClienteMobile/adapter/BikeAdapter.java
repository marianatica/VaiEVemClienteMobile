package com.example.VaiEVemClienteMobile.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.VaiEVemClienteMobile.databinding.ItemListRowBinding;

import java.util.List;

import modelDominio.Viagem;

public class BikeAdapter extends RecyclerView.Adapter<BikeAdapter.MyViewHolder> {
    private List<Viagem> listaBikes;
    private BikeOnClickListener bikeOnClickListener;

    public BikeAdapter(List<Viagem> listaBikes, BikeOnClickListener bikeOnClickListener) {
        this.listaBikes = listaBikes;
        this.bikeOnClickListener = bikeOnClickListener;
    }

    @Override
    public BikeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemListRowBinding itemListRowBinding = ItemListRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(itemListRowBinding);
    }

    @Override
    public void onBindViewHolder(final BikeAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Viagem bike = listaBikes.get(position);
        holder.itemListRowBinding.tvBikeModelo.setText(bike.getModelo());
        holder.itemListRowBinding.tvBikeMarca.setText(bike.getMarca().getNomeMarca());
        /* CUIDADO: .setText() precisa sempre de String. Se for outro tipo de dado, deve ser feita a conversão com o String.valueOf() */

        // tratando o clique no item
        if (bikeOnClickListener != null) {
            holder.itemListRowBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bikeOnClickListener.onClickBike(holder.itemView, position, bike);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaBikes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public  ItemListRowBinding itemListRowBinding;
        public MyViewHolder(ItemListRowBinding itemListRowBinding) {
            super(itemListRowBinding.getRoot());
            this.itemListRowBinding = itemListRowBinding;
        }
    }

    public interface BikeOnClickListener {
        public void onClickBike(View view, int position, Viagem bike);
    }

}



