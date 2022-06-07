/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author gato
 */
public class ServicioGeneral {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void procesarDatos(Date fecha, Integer idUsuario, Integer idRuta) {
        try {
            em = HelperPersistencia.getEMF();

            em.getTransaction().begin();
//           Query elimina= em.createNativeQuery("delete from model_ruta;");
//            int i=elimina.executeUpdate();
//            System.out.println("VALOR BORRA "+i);
            StoredProcedureQuery queryStore = em.createStoredProcedureQuery("procesardatosv2");
            queryStore.registerStoredProcedureParameter("fechaHoraParam", Date.class, ParameterMode.IN);
            queryStore.registerStoredProcedureParameter("idrutapar", Integer.class, ParameterMode.IN);
            queryStore.registerStoredProcedureParameter("idusuariopar", Integer.class, ParameterMode.IN);

            queryStore.setParameter("fechaHoraParam", fecha);
            queryStore.setParameter("idrutapar", idRuta);
            queryStore.setParameter("idusuariopar", idUsuario);
            queryStore.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("error iniciarProximoMes " + e.getMessage());
        } finally {
            em.close();
        }

    }
}
