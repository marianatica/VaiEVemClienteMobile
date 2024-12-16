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
import android.widget.Toast;

import com.example.VaiEVemClienteMobile.R;
import com.example.VaiEVemClienteMobile.controller.ConexaoController;
import com.example.VaiEVemClienteMobile.databinding.FragmentLoginBinding;
import com.example.VaiEVemClienteMobile.viewModel.InformacoesViewModel;

import modelDominio.Usuario;

public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    InformacoesViewModel informacoesViewModel;
    boolean resultado;
    Usuario usuarioLogado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Obtendo a instancia do viewModel
        informacoesViewModel = new ViewModelProvider(getActivity()).get(InformacoesViewModel.class);

        //criando a thread para se conectar com o servidor socket
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //instanciando o conecxao controller para criar a conexao
                ConexaoController conexaoController = new ConexaoController(informacoesViewModel);
                resultado = conexaoController.criaConexaoServidor("192.168.6.114",12345);
                //sincronizando as threads para mostrar o resultado na conexao
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //testando o resultado da conexao
                        if(resultado == true){
                            Toast.makeText(getContext(), "Conexão estabelecida com sucesso.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Erro: conexão não efetuada.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        thread.start();

        // programando o clique nos botões
        binding.bLoginEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // verificando se o usuário informou os dados
                if (!binding.etLoginUsuario.getText().toString().equals("")) {
                    if (!binding.etLoginSenha.getText().toString().equals("")) {
                        // obtendo as informações
                        String usuario = binding.etLoginUsuario.getText().toString();
                        String senha = binding.etLoginSenha.getText().toString();

                        //instanciando o objeto Usuario
                        usuarioLogado = new Usuario(usuario, senha);

                        //criando a thread para autenticar o usuario no servidor
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                //instanciando o conecxao controller para autenticar o usuário
                                ConexaoController conexaoController = new ConexaoController(informacoesViewModel);
                                usuarioLogado = conexaoController.efetuarLogin(usuarioLogado);
                                //sincronizando as thread para agir na tela
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //testando o resultado
                                        if(usuarioLogado != null){
                                            //salvando o usuario logado no sistema
                                            informacoesViewModel.inicializaUsuarioLogado(usuarioLogado);
                                            //chamando a proxima tela
                                            Navigation.findNavController(view).navigate(R.id.acao_LoginFragment_MenuFragment);
                                        } else {
                                            Toast.makeText(getContext(), "Erro: usuario e/ou senha inválidos.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });
                        thread.start();

                    } else {
                        binding.etLoginSenha.setError("Erro: informe a senha.");
                        binding.etLoginSenha.requestFocus();
                    }
                } else {
                    binding.etLoginUsuario.setError("Erro: informe o usuário.");
                    binding.etLoginUsuario.requestFocus();
                }
            }
        });

        binding.bLoginCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaCampos();
            }
        });
    }

    public void limpaCampos() {
        binding.etLoginUsuario.setText("");
        binding.etLoginSenha.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}