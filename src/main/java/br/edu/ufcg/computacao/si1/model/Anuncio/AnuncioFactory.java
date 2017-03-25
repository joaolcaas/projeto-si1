package br.edu.ufcg.computacao.si1.model.Anuncio;

import br.edu.ufcg.computacao.si1.model.Usuarios.Usuario;
import br.edu.ufcg.computacao.si1.model.form.AnuncioForm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by João Lucas on 13/03/2017.
 */

@Component
public class AnuncioFactory {

    public Anuncio create(AnuncioForm anuncioForm, Usuario criador){

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

        setUp(anuncioForm,anuncio, criador);

        return anuncio;
    }

    private void setUp(AnuncioForm anuncioForm, Anuncio anuncio, Usuario criador){
        anuncio.setTitulo(anuncioForm.getTitulo());
        anuncio.setPreco(anuncioForm.getPreco());
        anuncio.setTipo(anuncioForm.getTipo());
        anuncio.setCriador(criador);
    }


}
