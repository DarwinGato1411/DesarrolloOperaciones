/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.DetalleRuta;
import com.ec.entidad.Ruta;
import com.ec.entidad.Usuario;
import com.ec.servicio.ServicioDetalleRuta;
import com.ec.servicio.ServicioRutas;

import java.util.ArrayList;
import java.util.Date;
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
    ServicioDetalleRuta servicioDetalleRuta = new ServicioDetalleRuta();
    private List<Ruta> listaRutas = new ArrayList<Ruta>();
    private ListModelList<Ruta> listaRutaModel;
    private Set<Ruta> registrosSeleccionados = new HashSet<Ruta>();
    private String buscarRuta = "";
    private Usuario usuario;
    private Date fecha = new Date();
    private Ruta rutaSelected;
    
    
    private List<DetalleRuta> listaDetalleRutas;
    
    //mailing
    public Rutas() {
        consultaUsuarios();
        getRutas();
       
        
    }
    
    @Command
    @NotifyChange({"listaDetalleRutas"})
    public void seleccionarRuta() {

        registrosSeleccionados = ((ListModelList<Ruta>) getListaRutaModel()).getSelection();

        for (Ruta ruta : registrosSeleccionados) {
            rutaSelected = ruta;         

        }
       consultaDetalleRutas();
    }

    /*ADMINISTRAR USUARIO*/
    private void consultaRutas() {
        listaRutas = servicioRutas.findByNombre(buscarRuta);
    }
    private void consultaDetalleRutas() {
         listaDetalleRutas= servicioDetalleRuta.findByIdRuta(rutaSelected);
    }
    private void consultaUsuarios() {
        listaRutas = servicioRutas.findByUsuario(usuario);
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    @Command
    @NotifyChange({"listaRutas", "usuario"})
    public void buscarRutasFecha() {
        consultaUsuarios();
    }

    public List<DetalleRuta> getListaDetalleRutas() {
        return listaDetalleRutas;
    }

    public void setListaDetalleRutas(List<DetalleRuta> listaDetalleRutas) {
        this.listaDetalleRutas = listaDetalleRutas;
    }
    
    
}
