package com.example.VaiEVemClienteMobile.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.VaiEVemClienteMobile.adapter.BikeAdapter;
import com.example.VaiEVemClienteMobile.controller.ConexaoController;
import com.example.VaiEVemClienteMobile.databinding.FragmentVisualizacaoBikeBinding;
import com.example.VaiEVemClienteMobile.viewModel.InformacoesViewModel;

import java.util.ArrayList;

import modelDominio.Viagem;


public class VisualizacaoBikeFragment extends Fragment {
    FragmentVisualizacaoBikeBinding binding;
    BikeAdapter bikeAdapter;
    InformacoesViewModel informacoesViewModel;
    ArrayList<Viagem> listaBikes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_visualizacao_bike, container, false);
        binding = FragmentVisualizacaoBikeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //obtento a instancia do viewModel
        informacoesViewModel = new ViewModelProvider(getActivity()).get(InformacoesViewModel.class);
        //criando a thread para obter a lista de bikes
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //instanciando o controller para obtenção da lista
                ConexaoController conexaoController = new ConexaoController(informacoesViewModel);
                listaBikes = conexaoController.bikeLista();

                //verificando o recebimento de bikes
                if(listaBikes != null){
                    //sincronizando as threads para mostrar as bikes
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            atualizaListagem();
                        }
                    });
                }
            }
        });
        thread.start();
    }

    public void atualizaListagem() {
        bikeAdapter = new BikeAdapter(listaBikes, trataCliqueItem);
        binding.rvVisualizaBikes.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvVisualizaBikes.setItemAnimator(new DefaultItemAnimator());
        binding.rvVisualizaBikes.setAdapter(bikeAdapter);
    }

    BikeAdapter.BikeOnClickListener trataCliqueItem = new BikeAdapter.BikeOnClickListener() {
        @Override
        public void onClickBike(View view, int position, Viagem bike) {
            Toast.makeText(getContext(), "Modelo: " + bike.getModelo() +
                                              "Marca: " +bike.getMarca().getNomeMarca()+
                                              "Preço: " + bike.getPreco()+
                                              "Data: "+ bike.getDataLancamento(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}