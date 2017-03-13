package br.edu.ufcg.computacao.si1.model.Usuarios;

import javax.persistence.Entity;

/**
 * Created by João Lucas on 11/03/2017.
 */

@Entity
public class PessoaJuridica extends Usuario {

    public PessoaJuridica() {
    }

    public PessoaJuridica(String nome, String email, String senha, Long saldo) {
        super(nome, email, senha, "COMPANY", saldo);
    }
}
