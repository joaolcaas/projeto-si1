package br.edu.ufcg.computacao.si1.model.Anuncio;

import br.edu.ufcg.computacao.si1.model.Usuarios.Usuario;
import com.sun.xml.internal.bind.v2.TODO;
import javax.persistence.Entity;
import java.util.Calendar;
import java.util.Date;

/**
 * TODO checar como essa classe será adicionada ao banco de dados
 *
 * Created by Pedro Falcão on 15/03/2017.
 */
@Entity
public class Servico extends Anuncio {
    //@Column(name = "data_agendada", nullable = false)
    private Calendar dataAgendada;

    public Servico() {
        super();
        this.dataAgendada = Calendar.getInstance();
    }

    public Servico(String titulo, Date dataDeCriacao, Double preco, String nota, String tipo, Date dataAgendada, Usuario criador) {
        super(titulo, dataDeCriacao, preco, nota, tipo, criador);
        this.dataAgendada = Calendar.getInstance();
        this.dataAgendada.setTime(dataAgendada);
    }
}