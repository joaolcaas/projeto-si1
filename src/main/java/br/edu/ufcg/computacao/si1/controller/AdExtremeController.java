package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.service.ServiceAdExtreme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by João Lucas on 13/03/2017.
 */
@Controller
public class AdExtremeController {

    @Autowired
    private ServiceAdExtreme serviceAdExtreme;

    //aqui iremos mexer com a venda e com os usuarios
    @RequestMapping(value = "/AdExtreme",method = RequestMethod.PUT)
    public ModelAndView fazerVenda(){
        ModelAndView model = new ModelAndView();
        return model;
    }
}
