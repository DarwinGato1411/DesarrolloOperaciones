/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.DetalleRutaProcesada;
import com.ec.entidad.Ruta;
import com.ec.entidad.RutaProcesada;
import com.ec.entidad.TrackPoints;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioDetalleRuta;
import com.ec.servicio.ServicioDetalleRutaProcesada;
import com.ec.servicio.ServicioGeneral;
import com.ec.servicio.ServicioRutas;
import com.ec.servicio.ServicioRutasProcesada;
import com.ec.servicio.ServicioTrackPoint;

import com.ec.servicio.ServicioUsuario;
import com.ec.untilitario.Reportes;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Fileupload;

/**
 *
 * @author daki
 */
public class Historial {

    ServicioUsuario servicioUsuario = new ServicioUsuario();
    ServicioRutasProcesada servicioRutas = new ServicioRutasProcesada();
    private List<RutaProcesada> listaRutas = new ArrayList<RutaProcesada>();
    private Date fecha = new Date();
    private Date fechaFin = new Date();

    UserCredential credential = new UserCredential();

    AMedia fileContent = null;

    public Historial() {
        Session sess = Sessions.getCurrent();
        credential = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());

    }

    private void consultarRutaProcesada() {
        listaRutas = servicioRutas.findBetweeFechaUsuario(fecha, fechaFin, credential.getUsuarioSistema());
    }

    @Command
    @NotifyChange({"listaRutas", "fecha", "fechaFin"})
    public void buscar() {
        consultarRutaProcesada();

    }

    @Command
    public void reporte(@BindingParam("valor") RutaProcesada valor) {
        AMedia reporte = Reportes.reportePuntoControlHistorial(valor.getIdRutaProcesada());
        final HashMap<String, AMedia> map = new HashMap<String, AMedia>();
//para pasar al visor
        map.put("pdf", reporte);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                    "/procesar/visorreporte.zul", null, map);
        window.doModal();
    }

    public List<RutaProcesada> getListaRutas() {
        return listaRutas;
    }

    public void setListaRutas(List<RutaProcesada> listaRutas) {
        this.listaRutas = listaRutas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

}
