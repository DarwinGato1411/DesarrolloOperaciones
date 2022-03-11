/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Usuario;
import com.ec.servicio.ServicioRutas;

import com.ec.servicio.ServicioUsuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;

/**
 *
 * @author daki
 */
public class Procesar {

    ServicioUsuario servicioUsuario = new ServicioUsuario();
    ServicioRutas servicioRutas = new ServicioRutas();
    private List<Rutas> listaRutas = new ArrayList<Rutas>();
//    
//    public Procesar() {
//        consultarRutasUsuario();
//    }
//        
//    private void consultarRutasUsuario() {
//        listaRutas = servicioRutas.findByUsuario(Usuario);
//    }
//
//    public List<Rutas> getListaRutas() {
//        return listaRutas;
//    }
//
//    public void setListaRutas(List<Rutas> listaRutas) {
//        this.listaRutas = listaRutas;
//    }

    
}
