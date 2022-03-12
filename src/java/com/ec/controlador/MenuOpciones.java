/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menuitem;

/**
 *
 * @author gato
 */
public class MenuOpciones extends SelectorComposer<Component> {

    @Wire("#btnProcesar")
    Button btnProcesar;
    @Wire("#btnAdministrar")
    Button btnAdministrar;

    UserCredential credential = new UserCredential();
    private String acceso = "";

    public MenuOpciones() {
        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        credential = cre;
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        if (credential.getUsuarioSistema() != null) {

            if (credential.getUsuarioSistema().getUsuNivel() == 1) {
            } else {
                btnAdministrar.setVisible(Boolean.FALSE);
            }
        }
    }

    @Listen("onClick = #btnAdministrar")
    public void buttonConsultar() {
        Executions.sendRedirect("/administrar/administrar.zul");
    }

    @Listen("onClick = #btnProcesar")
    public void buttonProcesar() {
        Executions.sendRedirect("/procesar/procesar.zul");
    }

    @Command
    public void nuevoCliente() {

        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/cliente.zul", null, null);
        window.doModal();

    }

    public UserCredential getCredential() {
        return credential;
    }

    public void setCredential(UserCredential credential) {
        this.credential = credential;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }
}
