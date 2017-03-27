package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.Anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.Anuncio.AnuncioFactory;
import br.edu.ufcg.computacao.si1.model.Notas;
import br.edu.ufcg.computacao.si1.model.Usuarios.Usuario;
import br.edu.ufcg.computacao.si1.model.form.AnuncioForm;
import br.edu.ufcg.computacao.si1.repository.AnuncioRepository;
import br.edu.ufcg.computacao.si1.service.AnuncioServiceImpl;
import br.edu.ufcg.computacao.si1.service.UsuarioService;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserAnuncioController {

    @Autowired
    private AnuncioServiceImpl anuncioService;

    @Autowired
    private AnuncioRepository anuncioRep;

    @Autowired
    private AnuncioFactory anuncioFactory;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/user/cadastrar/anuncio", method = RequestMethod.GET)
    public ModelAndView getPageCadastrarAnuncio(AnuncioForm anuncioForm){
        ModelAndView model = new ModelAndView();

        model.addObject("usuarioUser",usuarioService.getUsuarioLogado());
        model.addObject("tipos", anuncioForm.getTiposUser());
        model.setViewName("user/cadastrar_anuncio");

        return model;
    }

    @RequestMapping(value = "/user/listar/anuncios", method = RequestMethod.GET)
    public ModelAndView getPageListarAnuncios(){
        ModelAndView model = new ModelAndView();


        model.addObject("notas", Notas.notas);
        model.addObject("usuarioUser",usuarioService.getUsuarioLogado());
        model.addObject("anuncios", anuncioRep.findAll());
        model.addObject("anunciosUser",anuncioService.getByIdCriador(usuarioService.getUsuarioLogado().getId()));

        model.setViewName("user/listar_anuncios");

        return model;
    }


    @RequestMapping(value = "/user/cadastrar/anuncio", method = RequestMethod.POST)
    public ModelAndView cadastroAnuncio(@Valid AnuncioForm anuncioForm, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            return getPageCadastrarAnuncio(anuncioForm);
        }

        Anuncio anuncio = anuncioFactory.create(anuncioForm, usuarioService.getUsuarioLogado());

        anuncioService.create(anuncio);

        attributes.addFlashAttribute("mensagem", "Anúncio cadastrado com sucesso!");
        return new ModelAndView("redirect:/user/cadastrar/anuncio");
    }

    @RequestMapping(value ="user/listar/avaliar/anuncio",method = RequestMethod.POST)
    public ModelAndView avaliarAnuncio(Anuncio anuncio, @RequestParam(value= "NOTA") String nota){
        ModelAndView model = new ModelAndView();

        anuncio.setNota(nota);

        anuncioService.update(anuncio);

        return new ModelAndView("redirect:/user/listar/anuncios");
    }

    @RequestMapping(value = "user/listar/comprar/anuncio",method = RequestMethod.POST)
    public ModelAndView comprarAnuncio(@RequestParam(value = "ANUNCIO_ID") long id,RedirectAttributes attributes){
        ModelAndView model = new ModelAndView();

        Anuncio anuncio = anuncioService.getById(id).get();

        Usuario anunciante = anuncio.getCriador();

        Usuario usuarioLogado = usuarioService.getUsuarioLogado();

        anunciante.creditar(anuncio.getPreco());
        usuarioLogado.debitar(anuncio.getPreco());

        anunciante.addNotificacao("Seu produto: " + anuncio.getTitulo() + " foi vendido para " + usuarioLogado.getNome());

        anuncioService.delete(id);
        usuarioService.update(anunciante);
        usuarioService.update(usuarioLogado);

        return new ModelAndView("redirect:/user/listar/anuncios");

    }

}