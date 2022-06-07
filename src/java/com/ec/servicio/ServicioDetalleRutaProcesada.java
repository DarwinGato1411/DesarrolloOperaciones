/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.DetalleRutaProcesada;
import com.ec.entidad.Ruta;
import com.ec.entidad.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioDetalleRutaProcesada {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(DetalleRutaProcesada detalleRuta) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(detalleRuta);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar detalleRuta " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(DetalleRutaProcesada detalleRuta) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(detalleRuta));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en eliminar  detalleRuta " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(DetalleRutaProcesada detalleRuta) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(detalleRuta);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar detalleRuta " + e.getMessage());
        } finally {
            em.close();
        }

    }

//    public List<DetalleRutaProcesada> findByIdRuta(Ruta ruta) {
//
//        List<DetalleRutaProcesada> listaDetalleRutaProcesadas = new ArrayList<DetalleRutaProcesada>();
//        try {
//
//            em = HelperPersistencia.getEMF();
//            em.getTransaction().begin();
//            Query query = em.createQuery("SELECT a FROM DetalleRutaProcesada a WHERE a.idDetRutProcesada:idRuta");
//            query.setParameter("idRuta", ruta);
//            listaDetalleRutaProcesadas = (List<DetalleRutaProcesada>) query.getResultList();
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            System.out.println("Error en la consulta findByIdRuta " + e.getMessage());
//        } finally {
//            em.close();
//        }
//
//        return listaDetalleRutaProcesadas;
//    }

    public List<DetalleRutaProcesada> findByPuntoControlUsuarioFechas(String puntoControl, Usuario usuario, Date inicio, Date fin,Integer idRuta) {

        List<DetalleRutaProcesada> listaDetalleRutaProcesadas = new ArrayList<DetalleRutaProcesada>();
        try {

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT a FROM DetalleRutaProcesada a WHERE a.puntoControl like :puntoControl and a.idRutaProcesada.idUsuario=:idUsuario AND a.idRutaProcesada.rutpFecha BETWEEN :inicio and :fin AND a.idRuta=:idRuta ORDER BY a.idRutaProcesada ASC");
            query.setParameter("puntoControl", "%" + puntoControl + "%");
            query.setParameter("idUsuario", usuario);
            query.setParameter("inicio", inicio);
            query.setParameter("fin", fin);
            query.setParameter("idRuta", idRuta);
            listaDetalleRutaProcesadas = (List<DetalleRutaProcesada>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en la consulta findByIdRuta " + e.getMessage());
        } finally {
            em.close();
        }

        return listaDetalleRutaProcesadas;
    }

}
