/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Ruta;
import com.ec.servicio.ServicioRutas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;

/**
 *
 * @author gato
 */
public class Rutas {

    ServicioRutas servicioRutas = new ServicioRutas();
    private List<Ruta> listaRutas = new ArrayList<Ruta>();
    private ListModelList<Ruta> listaRutaModel;
    private Set<Ruta> registrosSeleccionados = new HashSet<Ruta>();
    private String buscarRuta = "";

    //mailing
    public Rutas() {
        getRutas();

    }

    /*ADMINISTRAR USUARIO*/
    private void consultaRutas() {
        listaRutas = servicioRutas.findByNombre(buscarRuta);
    }

    private void getRutas() {
        consultaRutas();
        setListaRutaModel(new ListModelList<Ruta>(getListaRutas()));
        ((ListModelList<Ruta>) listaRutaModel).setMultiple(false);

    }

    public List<Ruta> getListaRutas() {
        return listaRutas;
    }

    public ListModelList<Ruta> getListaRutaModel() {
        return listaRutaModel;
    }

    public void setListaRutaModel(ListModelList<Ruta> listaRutaModel) {
        this.listaRutaModel = listaRutaModel;
    }

    public Set<Ruta> getRegistrosSeleccionados() {
        return registrosSeleccionados;
    }

    public void setRegistrosSeleccionados(Set<Ruta> registrosSeleccionados) {
        this.registrosSeleccionados = registrosSeleccionados;
    }

    public void setListaRutas(List<Ruta> listaRutas) {
        this.listaRutas = listaRutas;
    }

    public String getBuscarRuta() {
        return buscarRuta;
    }

    public void setBuscarRuta(String buscarRuta) {
        this.buscarRuta = buscarRuta;
    }

    @Command
    @NotifyChange({"listaDetalleFacturaModel", "buscarNombre"})
    public void buscarRuta() {
        getBuscarRuta();
    }

    //usuarios
    @Command
    @NotifyChange("listaUsuarios")
    public void agregarRuta() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/ruta.zul", null, null);
        window.doModal();
        consultaRutas();
    }

    @Command
    @NotifyChange("listaUsuarios")
    public void modificarRuta(@BindingParam("valor") Ruta valor) {
        final HashMap<String, Ruta> map = new HashMap<String, Ruta>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/ruta.zul", null, map);
        window.doModal();
        consultaRutas();
    }

}