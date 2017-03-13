package br.edu.ufcg.computacao.si1.model.Anuncio;

import java.util.Date;

/**
 * Created by João Lucas on 13/03/2017.
 */
public class Imovel extends Anuncio{

    public Imovel() {
        super();
    }

    public Imovel(String titulo, Date dataDeCriacao, double preco, String nota, String tipo) {
        super(titulo, dataDeCriacao, preco, nota, tipo);
    }
}
