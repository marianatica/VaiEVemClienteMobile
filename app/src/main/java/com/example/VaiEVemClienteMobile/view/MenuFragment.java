package com.example.VaiEVemClienteMobile.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.VaiEVemClienteMobile.R;
import com.example.VaiEVemClienteMobile.databinding.FragmentMenuBinding;
import com.example.VaiEVemClienteMobile.viewModel.InformacoesViewModel;


public class MenuFragment extends Fragment {
    FragmentMenuBinding binding;
    InformacoesViewModel informacoesViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_menu, container, false);
        binding = FragmentMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //obtendo a instancia do ViewModel
        informacoesViewModel = new ViewModelProvider(getActivity()).get(InformacoesViewModel.class);
        //mostrando na tela o nome do usuario logado
        binding.tvMenuSaudacao.setText(informacoesViewModel.getUsuarioLogado().getNomeUsuario() + ", seja bem-vindo. Deseja: ");

        // programando o clique nos botões
        binding.bMenuCadastroMarca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.acao_MenuFragment_CadastroMarcaFragment);
            }
        });

        binding.bMenuCadastroBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.acao_MenuFragment_CadastroBikeFragment);
            }
        });

        binding.bMenuVisualizacaoBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.acao_MenuFragment_VisualizacaoBikeFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}