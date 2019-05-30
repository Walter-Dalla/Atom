package br.com.cotil.aton.usuario;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.cotil.aton.empresa.EmpresaModel;

@Entity
@Table(name = "USUARIO")
@EntityListeners(AuditingEntityListener.class)
public class UsuarioModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne
  @JoinColumn(name = "ID_EMPRESA")
  private EmpresaModel empresa;

  @Column(name = "NOME_USUARIO")
  private String nome;

  @Column(name = "EMAIL_PRIMARIO")
  private String email;

  @Column(name = "TELEFONE")
  private String telefone;

  @Column(name = "CARGO")
  private String cargo;

  @Column(name = "CPF")
  private String cpf;

  @Column(name = "PERFIL_ORDEM")
  private Integer perfilOrdem;

  @CreatedDate
  @Column(name = "DATA_CRIACAO")
  Date dataCriacao;

  @LastModifiedDate
  @Column(name = "DATA_ATUALIZACAO")
  Date dataUltimaAtualizacao;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public EmpresaModel getEmpresa() {
    return empresa;
  }

  public void setEmpresa(EmpresaModel empresa) {
    this.empresa = empresa;
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

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getCargo() {
    return cargo;
  }

  public void setCargo(String cargo) {
    this.cargo = cargo;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public Integer getPerfilOrdem() {
    return perfilOrdem;
  }

  public void setPerfilOrdem(Integer perfilOrdem) {
    this.perfilOrdem = perfilOrdem;
  }

  public Date getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Date dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public Date getDataUltimaAtualizacao() {
    return dataUltimaAtualizacao;
  }

  public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
    this.dataUltimaAtualizacao = dataUltimaAtualizacao;
  } 
}
