/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Ruta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioRutas {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Ruta ruta) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(ruta);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar ruta " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(Ruta ruta) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(ruta));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en eliminar  ruta " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(Ruta ruta) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(ruta);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar ruta " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<Ruta> findByNombre(String nombre) {

        List<Ruta> listaRutas = new ArrayList<Ruta>();
        try {
            System.out.println("Entra a consultar rutas");
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT a FROM Ruta a where a.rutNombre LIKE :rutNombre");
            query.setParameter("rutNombre", "%" + nombre + "%");
            listaRutas = (List<Ruta>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en la consulta findByNombre " + e.getMessage());
        } finally {
            em.close();
        }

        return listaRutas;
    }
}
