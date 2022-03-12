/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.DetalleRuta;
import com.ec.entidad.Ruta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioDetalleRuta {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(DetalleRuta detalleRuta) {

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

    public void eliminar(DetalleRuta detalleRuta) {

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

    public void modificar(DetalleRuta detalleRuta) {

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

    public List<DetalleRuta> findByIdRuta(Ruta ruta) {

        List<DetalleRuta> listaDetalleRutas = new ArrayList<DetalleRuta>();
        try {

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT a FROM DetalleRuta a WHERE a.idRuta=:idRuta");
            query.setParameter("idRuta", ruta);
            listaDetalleRutas = (List<DetalleRuta>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en la consulta findByIdRuta " + e.getMessage());
        } finally {
            em.close();
        }

        return listaDetalleRutas;
    }

    public List<DetalleRuta> findByNombre(String ruta) {

        List<DetalleRuta> listaDetalleRutas = new ArrayList<DetalleRuta>();
        try {

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT a FROM DetalleRuta a WHERE a.detrNombre LIKE :idRuta");
            query.setParameter("idRuta", "%" + ruta + "%");
            listaDetalleRutas = (List<DetalleRuta>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en la consulta findByIdRuta " + e.getMessage());
        } finally {
            em.close();
        }

        return listaDetalleRutas;
    }

}
