/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.untilitario;

import com.ec.servicio.HelperPersistencia;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Executions;

/**
 *
 * @author Darwin
 */
public class Reportes {

    static Connection con = null;
    static AMedia fileContent = null;

    public static AMedia reportePuntoControl(Integer idRuta, Integer idUsuario, Date fecha) {
        EntityManager emf = HelperPersistencia.getEMF();

        try {

            emf.getTransaction().begin();
            /*CONEXION A LA BASE DE DATOS*/
            con = emf.unwrap(Connection.class);

            //  con = emf.unwrap(Connection.class);
            String reportFile = Executions.getCurrent().getDesktop().getWebApp()
                        .getRealPath("/reportes");
            String reportPath = "";
//                con = ConexionReportes.Conexion.conexion();

            /*ES EL PATH DONDE SE ENCUENTRA EL REPORTE EN MI CASO*/
            reportPath = reportFile + File.separator + "puntocontrol.jasper";

            /*PARAMETROS DEL REPORTE*/
            Map<String, Object> parametros = new HashMap<String, Object>();

            //  parametros.put("codUsuario", String.valueOf(credentialLog.getAdUsuario().getCodigoUsuario()));
            parametros.put("idRuta", idRuta);
            parametros.put("idUsuario", idUsuario);
            parametros.put("fecha", fecha);

            if (con != null) {
                System.out.println("Conexión Realizada Correctamente");
            }

            FileInputStream is = null;
            is = new FileInputStream(reportPath);
            /*COMPILAS EL ARCHIVO.JASPER*/
            byte[] buf = JasperRunManager.runReportToPdf(is, parametros, con);
            /*EN MI CASO LO PRESENTO EN UNA VENTANA EMERGENTE  PERO LO ANTERIOR SERIA TODO*/
            InputStream mediais = new ByteArrayInputStream(buf);

            AMedia amedia = new AMedia("Reporte", "pdf", "application/pdf", mediais);
            fileContent = amedia;

        } catch (FileNotFoundException e) {
            System.out.println("Error FileNotFoundException en generar el reporte " + e.getMessage());
        } catch (JRException e) {
            System.out.println("Error JRException en generar el reporte " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (emf != null) {
                emf.close();
                System.out.println("cerro entity");
            }
        }
        return fileContent;

    }

    public static AMedia reportePuntoControlHistorial(Integer idRuta) {
        EntityManager emf = HelperPersistencia.getEMF();

        try {

            emf.getTransaction().begin();
            /*CONEXION A LA BASE DE DATOS*/
            con = emf.unwrap(Connection.class);

            //  con = emf.unwrap(Connection.class);
            String reportFile = Executions.getCurrent().getDesktop().getWebApp()
                        .getRealPath("/reportes");
            String reportPath = "";
//                con = ConexionReportes.Conexion.conexion();

            /*ES EL PATH DONDE SE ENCUENTRA EL REPORTE EN MI CASO*/
            reportPath = reportFile + File.separator + "puntocontrolhistorial.jasper";

            /*PARAMETROS DEL REPORTE*/
            Map<String, Object> parametros = new HashMap<String, Object>();

            //  parametros.put("codUsuario", String.valueOf(credentialLog.getAdUsuario().getCodigoUsuario()));
            parametros.put("idRuta", idRuta);

            if (con != null) {
                System.out.println("Conexión Realizada Correctamente");
            }

            FileInputStream is = null;
            is = new FileInputStream(reportPath);
            /*COMPILAS EL ARCHIVO.JASPER*/
            byte[] buf = JasperRunManager.runReportToPdf(is, parametros, con);
            /*EN MI CASO LO PRESENTO EN UNA VENTANA EMERGENTE  PERO LO ANTERIOR SERIA TODO*/
            InputStream mediais = new ByteArrayInputStream(buf);

            AMedia amedia = new AMedia("Reporte", "pdf", "application/pdf", mediais);
            fileContent = amedia;

        } catch (FileNotFoundException e) {
            System.out.println("Error FileNotFoundException en generar el reporte " + e.getMessage());
        } catch (JRException e) {
            System.out.println("Error JRException en generar el reporte " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (emf != null) {
                emf.close();
                System.out.println("cerro entity");
            }
        }
        return fileContent;

    }
}
