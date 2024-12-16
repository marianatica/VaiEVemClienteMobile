package modelDominio;

import java.io.Serializable;

public class Admin extends Usuario implements Serializable{
    private static final long serialVersionUID = 123L;

    public Admin(int codUsuario, String nomeUsuario, String cpf, String nascimento, String endereco, String senha, String email, String fone) {
        super(codUsuario, nomeUsuario, cpf, nascimento, endereco, senha, email, fone);
    }

    public Admin(String nomeUsuario, String cpf, String nascimento, String endereco, String senha, String email, String fone) {
        super(nomeUsuario, cpf, nascimento, endereco, senha, email, fone);
    }

    public Admin(String email, String senha) {
        super(email, senha);
    }

    public Admin(int codUsuario) {
        super(codUsuario);
    }

    @Override
    public String toString() {
        return super.toString() + "Admin{" + '}';
    }


}