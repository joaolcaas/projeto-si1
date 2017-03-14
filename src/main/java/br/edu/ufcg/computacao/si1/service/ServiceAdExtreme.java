package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.Usuarios.Usuario;
import br.edu.ufcg.computacao.si1.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

/**
 * Created by João Lucas on 13/03/2017.
 */
@Service
public class ServiceAdExtreme {

    private UsuarioService usuarioService;

    public ServiceAdExtreme(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void realizaDebito(Usuario usuario, Long valor){
        if (usuarioService.getById(usuario.getId()) != null){
            if(usuario.getSaldo() >= valor ){
                usuario.setSaldo(usuario.getSaldo() - valor);
                usuarioService.update(usuario);

            }
        }
    }

    public void realizaCredito(Usuario usuario, Long valor){
        if(usuarioService.getById(usuario.getId()) != null){
            usuario.setSaldo(usuario.getSaldo() + valor);
            usuarioService.update(usuario);
        }
    }

}
