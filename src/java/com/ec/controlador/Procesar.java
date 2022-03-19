/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidad.Ruta;
import com.ec.entidad.TrackPoints;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioDetalleRuta;
import com.ec.servicio.ServicioRutas;
import com.ec.servicio.ServicioTrackPoint;

import com.ec.servicio.ServicioUsuario;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Fileupload;

/**
 *
 * @author daki
 */
public class Procesar {

    ServicioUsuario servicioUsuario = new ServicioUsuario();
    ServicioRutas servicioRutas = new ServicioRutas();
    ServicioDetalleRuta servicioDetalleRuta = new ServicioDetalleRuta();
    private List<Ruta> listaRutas = new ArrayList<Ruta>();
    private Date fecha = new Date();

    ServicioTrackPoint servicioTrackPoint = new ServicioTrackPoint();

    private Ruta rutaSelected = null;
    UserCredential credential = new UserCredential();

    public Procesar() {
        Session sess = Sessions.getCurrent();
        credential = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
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
            org.zkoss.util.media.Media media = Fileupload.get("Cargar su archivo excel", "Subir Archivo de rutas");
            if (media == null || !media.getFormat().equals("xls")) {
                Clients.showNotification("!Verificar que el archivo Excel sea de tipo XLS¡", "warning", null, "end_before", 10000, true);
                return;
            }
            HSSFWorkbook wb = new HSSFWorkbook(media.getStreamData());
            HSSFSheet sheet = wb.getSheetAt(0);

            int rows = sheet.getLastRowNum();
            String header = sheet.getRow(0).getCell(0).toString();
            String[] device = header.replaceAll(" ", "").toUpperCase().split("FROM")[0].split(":");//[1].split("-");
            TrackPoints trackPoints;
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfHour = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat sdfTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i = 2; i < rows; ++i) {
                HSSFRow row = sheet.getRow(i);
                int number = (int) Double.parseDouble(row.getCell(0).toString());
                String[] timestamp = row.getCell(1).toString().split(" ");
                System.out.println(row.getCell(2).toString().split(";")[0]);
                String[] coordinated = row.getCell(2).toString().replaceAll("[)\"]", "").split(",")[1].split("/");
                double lng = Double.parseDouble(coordinated[0]);
                double lat = Double.parseDouble(coordinated[1]);
                int speed = (int) Double.parseDouble(row.getCell(3).toString());
                int direction = (int) Double.parseDouble(row.getCell(4).toString());
                trackPoints = new TrackPoints(number,
                        BigDecimal.valueOf(lat),
                        BigDecimal.valueOf(lng),
                        BigDecimal.valueOf(speed),
                        BigDecimal.valueOf(direction),
                        sdfDate.parse(timestamp[0]),
                        sdfHour.parse(timestamp[1]),
                        sdfTimeStamp.parse(timestamp[0] + " " + timestamp[1]),
                        header, device[1]);
                trackPoints.setIdUsuario(credential.getUsuarioSistema());
                servicioTrackPoint.crear(trackPoints);
            }
            System.out.println("ARCHIVO CARGADO....");
            Clients.showNotification("Informacion cargada", "info", null, "end_before", 1000, true);
        } catch (Exception e) {
            System.out.println("ERROR al subir la imagen IOException " + e.getMessage());
        }
    }
}
