package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebPagesController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getPageIndex(){
        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getPageLogin(){
        ModelAndView model = new ModelAndView();
        model.setViewName("login");

        return model;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView getPageIndexUser(){
        ModelAndView model = new ModelAndView();

        model.addObject("notificacoes",usuarioService.getUsuarioLogado().getNotificacoes());

        model.addObject("usuarioLogado",usuarioService.getUsuarioLogado());
        model.setViewName("user/index");

        return model;
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public ModelAndView getPageIndexCompany(){
        ModelAndView model = new ModelAndView();
        model.addObject("notificacoes",usuarioService.getUsuarioLogado().getNotificacoes());
        model.addObject("usuarioLogado",usuarioService.getUsuarioLogado());
        model.setViewName("company/index");

        return model;
    }
}
