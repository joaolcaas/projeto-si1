package br.edu.ufcg.computacao.si1.model.Usuarios;

import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.*;

@Entity(name = "Usuario")
@Table(name = "tb_usuario")

public abstract class Usuario extends org.springframework.security.core.userdetails.User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USUARIO_ID")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true)
    private String email;

    @Column
    private String senha;

    @Column
    private String role;

    @Column
    private Double saldo;

    @Column
    private Double gasto;

   /* @OneToMany
    private List<Anuncio> anuncios; cada usuario vai ter uma lista dos seus anuncios
    */
    public Usuario() {
        super("default", "default", AuthorityUtils.createAuthorityList("USER"));
    }

    public Usuario(String nome, String email, String senha, String role,Double saldo,Double gasto) {

        super(email, senha, AuthorityUtils.createAuthorityList(role));

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
        this.saldo = saldo;
        this.gasto = gasto;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getGasto() {
        return gasto;
    }

    public void setGasto(double gasto) {
        this.gasto = gasto;
    }
}
