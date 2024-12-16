package modelDominio;

import java.io.Serializable;

public class Condutor extends Usuario implements Serializable{

    private static final long serialVersionUID = 123L;


        public Condutor(int codUsuario, String nomeUsuario, String cpf, String nascimento, String endereco, String senha, String email, String fone) {
            super(codUsuario, nomeUsuario, cpf, nascimento, endereco, senha, email, fone);
        }

        public Condutor(String nomeUsuario, String cpf, String nascimento, String endereco, String senha, String email, String fone) {
            super(nomeUsuario, cpf, nascimento, endereco, senha, email, fone);
        }

        public Condutor(String email, String senha) {
            super(email, senha);
        }

        public Condutor(int codUsuario) {
            super(codUsuario);
        }

        @Override
        public String toString() {
            return super.toString() + "Condutor{" + '}';

        }

    }

