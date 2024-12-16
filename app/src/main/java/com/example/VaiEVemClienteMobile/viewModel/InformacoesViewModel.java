package com.example.VaiEVemClienteMobile.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import modelDominio.Usuario;

public class InformacoesViewModel extends ViewModel {

    //informações necessárias para o socket
    private MutableLiveData<ObjectInputStream> mIn;
    private MutableLiveData<ObjectOutputStream> mOut;

    //informação referente ao usuário logado no aplicativo
    private MutableLiveData<Usuario> mUsuarioLogado;

    public void inicializaObjetosSocket(ObjectInputStream in, ObjectOutputStream out){

        //intanciando os MutableLiveData
        this.mIn = new MutableLiveData<>();
        this.mOut = new MutableLiveData<>();

        //inicializando os objetos
        this.mIn.postValue(in);
        this.mOut.postValue(out);
    }

    public void inicializaUsuarioLogado(Usuario usuarioLogado){

        //instanciando o MutableLiveData
        this.mUsuarioLogado = new MutableLiveData<>();

        //inicializando os objetos
        this.mUsuarioLogado.postValue(usuarioLogado);
    }

    public ObjectInputStream getInputStream(){
        return this.mIn.getValue();
    }

    public ObjectOutputStream getOutputStream(){
        return this.mOut.getValue();
    }

    public Usuario getUsuarioLogado (){
        return mUsuarioLogado.getValue();
    }
}
