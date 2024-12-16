package com.example.VaiEVemClienteMobile.controller;

import android.util.Log;

import com.example.VaiEVemClienteMobile.viewModel.InformacoesViewModel;

import java.io.IOException;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import modelDominio.Condutor;
import modelDominio.Passageiro;
import modelDominio.StatusPassageiro;
import modelDominio.Usuario;
import modelDominio.Viagem;


public class ConexaoController {

    private InformacoesViewModel informacoesViewModel;

    private Usuario usuarioLogado;
    public Usuario getUsuarioLogado(){
        return usuarioLogado;
    }
    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public ConexaoController(InformacoesViewModel informacoesViewModel) {
        this.informacoesViewModel = informacoesViewModel;
    }

    public boolean criaConexaoServidor(String ip, int porta) {
        boolean resultado;
        try {
            //criando a conexao com o servidor socket
            Socket socket = new Socket(ip, porta);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            //salvando as informações do socket no viewModel
            informacoesViewModel.inicializaObjetosSocket(in, out);
            resultado = true;
        } catch (IOException ioe) {
            Log.e("VaiEVem", "Erro: " + ioe.getMessage());
            resultado = false;
        }
        return resultado;
    }

    public Usuario efetuarLogin(Usuario user) {
        Usuario usuarioLogado;
        String mensagem;
        try {
            this.informacoesViewModel.getOutputStream().writeObject("UsuarioEfetuarLogin");
            mensagem = (String) informacoesViewModel.getInputStream().readObject();
            informacoesViewModel.getOutputStream().writeObject(user);
            usuarioLogado = (Usuario) informacoesViewModel.getInputStream().readObject();
        } catch (IOException ioe) {
                Log.e("VaiEVem", "Erro: " +ioe.getMessage());
                usuarioLogado = null;
        } catch (ClassNotFoundException classe) {
                Log.e("VaiEVem", "Erro: " +classe.getMessage());
                usuarioLogado = null;
        }
        return usuarioLogado;
    }



    public ArrayList<StatusPassageiro> marcaLista(){
        ArrayList<StatusPassageiro> listaMarcas;

        try{
            informacoesViewModel.getOutputStream().writeObject("MarcaLista");
            listaMarcas = (ArrayList<StatusPassageiro>) informacoesViewModel.getInputStream().readObject();
        } catch (IOException ioe) {
            Log.e("BikeShop", "Erro: " +ioe.getMessage());
            listaMarcas = null;
        } catch (ClassNotFoundException classe) {
            Log.e("BikeShop", "Erro: " +classe.getMessage());
            listaMarcas = null;
        }
        return listaMarcas;
    }

    public boolean marcaInserir(StatusPassageiro marca){
        boolean resultado;
        String mensagem;

        try {
            informacoesViewModel.getOutputStream().writeObject("MarcaInserir");
            mensagem = (String) informacoesViewModel.getInputStream().readObject();
            informacoesViewModel.getOutputStream().writeObject(marca);
            resultado = (Boolean) informacoesViewModel.getInputStream().readObject();
        } catch (IOException ioe) {
            Log.e("BikeShop", "Erro: " +ioe.getMessage());
            resultado = false;
        } catch (ClassNotFoundException classe) {
            Log.e("BikeShop", "Erro: " +classe.getMessage());
            resultado = false;
        }
        return resultado;
    }
    public boolean bikeInserir(Viagem bike){
        boolean resultado;
        String mensagem;

        try {
            informacoesViewModel.getOutputStream().writeObject("BikeInserir");
            mensagem = (String) informacoesViewModel.getInputStream().readObject();
            informacoesViewModel.getOutputStream().writeObject(bike);
            resultado = (Boolean) informacoesViewModel.getInputStream().readObject();
        } catch (IOException ioe) {
            Log.e("BikeShop", "Erro: " +ioe.getMessage());
            resultado = false;
        } catch (ClassNotFoundException classe) {
            Log.e("BikeShop", "Erro: " +classe.getMessage());
            resultado = false;
        }
        return resultado;
    }
}
