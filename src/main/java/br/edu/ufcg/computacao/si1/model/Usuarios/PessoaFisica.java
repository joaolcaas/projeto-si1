package br.edu.ufcg.computacao.si1.model.Usuarios;

import javax.persistence.Entity;

/**
 * Created by João Lucas on 11/03/2017.
 */
@Entity
public class PessoaFisica extends Usuario {

    public PessoaFisica() {
    }

    public PessoaFisica(String nome, String email, String senha, Double saldo, Double gasto) {
        super(nome, email, senha, "USER", saldo,gasto);
    }
}
