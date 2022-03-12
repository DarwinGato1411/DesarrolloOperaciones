/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Ruta;
import com.ec.servicio.ServicioRutas;

import com.ec.servicio.ServicioUsuario;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.activation.MimetypesFileTypeMap;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Fileupload;

/**
 *
 * @author daki
 */
public class Procesar {

    ServicioUsuario servicioUsuario = new ServicioUsuario();
    ServicioRutas servicioRutas = new ServicioRutas();
    private List<Ruta> listaRutas = new ArrayList<Ruta>();
    private Date fecha= new Date();

    private Ruta rutaSelected=null;
    public Procesar() {
        consultarRutasUsuario();
    }
        
    private void consultarRutasUsuario() {
        listaRutas = servicioRutas.findByNombre("");
    }

    public List<Ruta> getListaRutas() {
        return listaRutas;
    }

    public void setListaRutas(List<Ruta> listaRutas) {
        this.listaRutas = listaRutas;
    }

    public Ruta getRutaSelected() {
        return rutaSelected;
    }

    public void setRutaSelected(Ruta rutaSelected) {
        this.rutaSelected = rutaSelected;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Command
    @NotifyChange({"listaComprasSris", "inicio", "fin"})
    public void subirArchivo() {

        try {
            System.out.println("");

//            String folderDescargados = "/" + File.separator + "COMPRASDESCARGADASTXT"
//                    + File.separator + new Date().getYear()
//                    + File.separator + new Date().getMonth();
//
//            /*EN EL CASO DE NO EXISTIR LOS DIRECTORIOS LOS CREA*/
//            File folderGen = new File(folderDescargados);
//            if (!folderGen.exists()) {
//                folderGen.mkdirs();
//            }
            org.zkoss.util.media.Media media = Fileupload.get("Cargar su archivo excel", "Subir Archivo de rutas");

            if (media != null) {
                System.out.println("ARCHIVO CARGADO....");
                  Clients.showNotification("Informacion cargada", "info", null, "end_before", 1000, true);
            }

        } catch (Exception e) {
            System.out.println("ERROR al subir la imagen IOException " + e.getMessage());
        } 
    }
}
