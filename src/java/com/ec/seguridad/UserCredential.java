/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.seguridad;


import com.ec.entidad.Usuario;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserCredential implements Serializable {

    private static final long serialVersionUID = 1L;
    String account;
    String name;
    String nombreUsuario;
    private Integer nivelUsuario;
    private Usuario usuarioSistema;
 
    Set<String> roles = new HashSet<String>();

    public UserCredential(Usuario usuario, String account, String name, Integer nivelUsuario, String nombreUsuario) {
        this.usuarioSistema = usuario;
        this.name = name;
        this.account = account;
        this.nivelUsuario = nivelUsuario;
        this.nombreUsuario = nombreUsuario;
    }

   

    public UserCredential() {
        this.account = "anonymous";
        this.name = "Anonymous";
        roles.add("anonymous");
    }

    public boolean isAnonymous() {
        return hasRole("anonymous") || "anonymous".equals(account);
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasRole(String role) {
        return roles.contains(role);
    }

    public void addRole(String role) {
        roles.add(role);
    }

    public Integer getNivelUsuario() {
        return nivelUsuario;
    }

    public void setNivelUsuario(Integer nivelUsuario) {
        this.nivelUsuario = nivelUsuario;
    }

    public Usuario getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(Usuario usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

}
