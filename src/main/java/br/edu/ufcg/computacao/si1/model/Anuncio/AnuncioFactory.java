package br.edu.ufcg.computacao.si1.model.Anuncio;

import br.edu.ufcg.computacao.si1.model.form.AnuncioForm;

import java.util.Date;

/**
 * Created by João Lucas on 13/03/2017.
 */

public class AnuncioFactory {

    public Anuncio create(AnuncioForm anuncioForm){

        Anuncio anuncio = null;

        switch (anuncioForm.getTipo()){
            case "movel":
                anuncio = new Movel();
                break;
            case "imovel":
                anuncio = new Imovel();
                break;
            case "emprego":
                anuncio = new Emprego();
                break;
            case "servico":
                anuncio = new Servico();
                break;
        }

        setUp(anuncioForm, anuncio);

        return anuncio;
    }

    private void setUp(AnuncioForm anuncioForm, Anuncio anuncio){
        anuncio.setTitulo(anuncioForm.getTitulo());
        anuncio.setPreco(anuncioForm.getPreco());
        anuncio.setTipo(anuncioForm.getTipo());
    }


}
