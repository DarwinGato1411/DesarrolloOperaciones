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
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author gato
 */
public class Rutas {

    private List<Ruta> listRoutes = new ArrayList<Ruta>();
    ServicioRutas routeService = new ServicioRutas();
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

        rutaSelected = listRoutes.isEmpty() ? null : listRoutes.get(0);
        listaRutaModel.addToSelection(rutaSelected);


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
        listaDetalleRutas = servicioDetalleRuta.findByIdRuta(rutaSelected);
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
    @NotifyChange({"listaDetalleFacturaModel", "buscarNombre", "listaRutas"})
    public void buscarRuta() {
        getBuscarRuta();
    }

    //usuarios
    @Command
    @NotifyChange({"listaRutas", "listaRutaModel"})
    public void agregarRuta() {
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/ruta.zul", null, null);
        window.doModal();
        getRutas();
    }

    @Command
    @NotifyChange({"listaRutas", "listaRutaModel"})
    public void modificarRuta(@BindingParam("valor") Ruta valor) {
        final HashMap<String, Ruta> map = new HashMap<String, Ruta>();
        map.put("valor", valor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/ruta.zul", null, map);
        window.doModal();
        getRutas();
    }

    @Command
    @NotifyChange({"listaRutas", "listaDetalleRutas", "listaRutaModel"})
    public void deleteRoute(@BindingParam("valor") final Ruta route) {
        System.out.println(route.getRutNombre());
        rutaSelected = route;
        consultaDetalleRutas();
        Messagebox.show("¿Esta seguro que desea eliminar este ruta?", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
            public void onEvent(Event evt) throws InterruptedException {
                if (evt.getName().equals("onOK")) {
                    if (!listaDetalleRutas.isEmpty()) {
                        Messagebox.show("Para eliminar esta ruta debe eliminar los puntos de control", "Atención", Messagebox.OK, Messagebox.ERROR);
                        listaDetalleRutas = null;
                        return;
                    }
                    servicioRutas.eliminar(route);
                    getRutas();
                    consultaDetalleRutas();
                }
            }
        });
    }

    @Command
    @NotifyChange({"listaDetalleRutas"})
    public void addCheckPoint() {
        if (rutaSelected == null) {
            Messagebox.show("Debe seleccionar una ruta", "Atención", Messagebox.OK, Messagebox.ERROR);
            return;
        }
        final HashMap<String, Ruta> map = new HashMap<String, Ruta>();
        map.put("valor1", rutaSelected);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/checkPoint.zul", null, map);
        window.doModal();
        consultaDetalleRutas();
    }

    @Command
    @NotifyChange({"listaDetalleRutas"})
    public void updateCheckPoint(@BindingParam("valor") DetalleRuta detailRoute) {
        final HashMap<String, DetalleRuta> map = new HashMap<String, DetalleRuta>();
        map.put("valor", detailRoute);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/nuevo/checkPoint.zul", null, map);
        window.doModal();
        consultaDetalleRutas();
    }

    @Command
    @NotifyChange({"listaDetalleRutas"})
    public void deleteCheckPoint(@BindingParam("valor") final DetalleRuta detailRoute) {
        Messagebox.show("¿Esta seguro que desea eliminar este punto de control?", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
            public void onEvent(Event evt) throws InterruptedException {
                if (evt.getName().equals("onOK")) {
                    servicioDetalleRuta.eliminar(detailRoute);
                }
            }
        });
        consultaDetalleRutas();
    }

    private void findAllRoutes() {
        listRoutes = routeService.findAllRoutes();
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
