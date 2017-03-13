package br.edu.ufcg.computacao.si1.model.Anuncio;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by João Lucas on 13/03/2017.
 */
@Entity
public class Movel extends Anuncio {

    public Movel() {
        super();
    }

    public Movel(String titulo, Date dataDeCriacao, double preco, String nota, String tipo) {
        super(titulo, dataDeCriacao, preco, nota, tipo);
    }
}
