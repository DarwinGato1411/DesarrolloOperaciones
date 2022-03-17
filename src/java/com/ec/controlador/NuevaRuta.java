/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Ruta;
import com.ec.entidad.Usuario;
import com.ec.servicio.ServicioRutas;
import com.ec.servicio.ServicioUsuario;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class NuevaRuta {

    ServicioRutas servicioRutas = new ServicioRutas();
    private Ruta entidad = new Ruta();

    private String accion = "create";

    ServicioUsuario servicioUsuario = new ServicioUsuario();
    private List<Usuario> listaUsuario = new ArrayList<Usuario>();
    private Usuario usuarioSelected;
    @Wire
    Window windowRuta;

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("valor") Ruta valor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        listaUsuario = servicioUsuario.findUsuarioControl();
        if (valor != null) {
            this.entidad = valor;
            usuarioSelected = valor.getIdUsuario();
            accion = "update";
        } else {
            this.entidad = new Ruta();
            usuarioSelected = listaUsuario.isEmpty() ? null : listaUsuario.get(0);
            this.entidad.setIdUsuario(usuarioSelected);
            accion = "create";
        }
    }

    @Command
    public void guardar() {
        /*getCliNombre es el nombre comercial*/
        if (entidad.getIdUsuario() != null
                && entidad.getRutNombre() != null
                && entidad.getRutDescripcion() != null) {
            entidad.setIdUsuario(usuarioSelected);
            if (accion.equals("create")) {
                servicioRutas.crear(entidad);
                windowRuta.detach();
            } else {
                servicioRutas.modificar(entidad);
//                Messagebox.show("Guardado con exito");
                windowRuta.detach();
            }

        } else {

            Clients.showNotification("Verifique la informacion requerida",
                    Clients.NOTIFICATION_TYPE_ERROR, null, "end_center", 3000, true);
        }
    }

    public Ruta getEntidad() {
        return entidad;
    }

    public void setEntidad(Ruta entidad) {
        this.entidad = entidad;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public Usuario getUsuarioSelected() {
        return usuarioSelected;
    }

    public void setUsuarioSelected(Usuario usuarioSelected) {
        this.usuarioSelected = usuarioSelected;
    }

}
