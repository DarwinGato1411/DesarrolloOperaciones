/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.DetalleRuta;
import javax.persistence.EntityManager;

/**
 *
 * @author Omar
 */
public class ServiceCheckPoint {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void create(DetalleRuta checkPoint) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(checkPoint);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en el metodo crear de los puntos de control " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void delete(DetalleRuta checkPoint) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(checkPoint));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en el metodo delete de los puntos de control " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void update(DetalleRuta checkPoint) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(checkPoint);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en el metodo actualizar de los puntos de control " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
