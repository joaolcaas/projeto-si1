package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.anuncio.AnuncioFactory;
import br.edu.ufcg.computacao.si1.model.Notas;
import br.edu.ufcg.computacao.si1.model.usuarios.Usuario;
import br.edu.ufcg.computacao.si1.model.form.AnuncioForm;
import br.edu.ufcg.computacao.si1.repository.AnuncioRepository;
import br.edu.ufcg.computacao.si1.service.AnuncioServiceImpl;
import br.edu.ufcg.computacao.si1.service.UsuarioService;
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
public class CompanyAnuncioController {

    @Autowired
    private AnuncioServiceImpl anuncioService;

    @Autowired
    private AnuncioFactory anuncioFactory;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AnuncioRepository anuncioRep;

    @RequestMapping(value = "/company/cadastrar/anuncio", method = RequestMethod.GET)
    public ModelAndView getPageCadastarAnuncio(AnuncioForm anuncioForm){
        ModelAndView model = new ModelAndView();

        model.addObject("usuarioCompany",usuarioService.getUsuarioLogado());
        model.addObject("tiposCompany", anuncioForm.getTipos());
        model.setViewName("company/cadastrar_anuncio");

        return model;
    }

    @RequestMapping(value = "/company/listar/anuncios", method = RequestMethod.GET)
    public ModelAndView getPageListarAnuncios(){
        ModelAndView model = new ModelAndView();

        model.addObject("notas", Notas.notas);
        model.addObject("usuarioCompany",usuarioService.getUsuarioLogado());
        model.addObject("anuncios", anuncioRep.findAll());
        model.addObject("anunciosCompany",anuncioService.getByIdCriador(usuarioService.getUsuarioLogado().getId()));


        model.setViewName("company/listar_anuncios");

        return model;
    }

    @RequestMapping(value = "/company/cadastrar/anuncio", method = RequestMethod.POST)
    public ModelAndView cadastroAnuncio(@Valid AnuncioForm anuncioForm, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            return getPageCadastarAnuncio(anuncioForm);
        }

        Anuncio anuncio = anuncioFactory.create(anuncioForm,usuarioService.getUsuarioLogado());

        anuncioService.create(anuncio);

        attributes.addFlashAttribute("mensagem", "Anúncio cadastrado com sucesso!");
        return new ModelAndView("redirect:/company/cadastrar/anuncio");
    }

    @RequestMapping(value ="company/listar/avaliar/anuncio",method = RequestMethod.POST)
    public ModelAndView avaliarAnuncio(@RequestParam(value = "ANUNCIO_ID") long id, String nota){
        ModelAndView model = new ModelAndView();

        Anuncio anuncio = anuncioService.getById(id).get();

        anuncio.setNota(nota);
        model.setViewName("company/listar/anuncio");
        return model;
    }

    @RequestMapping(value = "company/listar/comprar/anuncio",method = RequestMethod.POST)
    public ModelAndView comprarAnuncio(@RequestParam(value = "ANUNCIO_ID") long id, RedirectAttributes attributes){
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

        return new ModelAndView("redirect:/company/listar/anuncios");

    }

    @RequestMapping(value = "company/listar/apagar/anuncio",method = RequestMethod.POST)
    public ModelAndView apagarAnuncio(@RequestParam(value = "ANUNCIO_ID") long id,RedirectAttributes attributes){
        ModelAndView model = new ModelAndView();

        anuncioService.delete(id);

        return new ModelAndView("redirect:/company/listar/anuncios");


    }


}
