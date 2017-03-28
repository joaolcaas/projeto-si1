package br.edu.ufcg.computacao.si1.model.anuncio;


import br.edu.ufcg.computacao.si1.model.usuarios.Usuario;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by João Lucas on 13/03/2017.
 */
@Entity
public class Imovel extends Anuncio{

    public Imovel() {
        super();
    }

    public Imovel(String titulo, Date dataDeCriacao, Double preco, String nota, String tipo, Usuario criador) {
        super(titulo, dataDeCriacao, preco, nota, tipo, criador);
    }
}
