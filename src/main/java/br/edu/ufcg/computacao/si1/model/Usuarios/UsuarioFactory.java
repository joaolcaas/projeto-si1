package br.edu.ufcg.computacao.si1.model.Usuarios;

import br.edu.ufcg.computacao.si1.model.form.UsuarioForm;
import org.springframework.stereotype.Component;

/**
 * Created by João Lucas on 13/03/2017.
 */
@Component
public class UsuarioFactory {

    public Usuario create(UsuarioForm usuarioForm){
        Usuario usuario = null;

        switch (usuarioForm.getRole()){
            case 1:
                usuario = createUser(usuarioForm);
                break;
            case 2:
                usuario = createCompany(usuarioForm);
                break;
        }
        return usuario;
    }

    private Usuario createUser(UsuarioForm usuarioForm){

        return new PessoaFisica(usuarioForm.getNome(), usuarioForm.getEmail(), usuarioForm.getSenha(), 0.0, 0.0);
    }

    private Usuario createCompany(UsuarioForm usuarioForm){

        return new PessoaJuridica(usuarioForm.getNome(), usuarioForm.getEmail(), usuarioForm.getSenha(), 0.0, 0.0);
    }

}
