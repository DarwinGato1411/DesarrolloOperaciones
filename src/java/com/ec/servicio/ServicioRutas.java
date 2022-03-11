/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.Ruta;
import com.ec.entidad.Usuario;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public List<Ruta> findByUsuario(Usuario usuario) {

        List<Ruta> listaRutas = new ArrayList<Ruta>();
        try {

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT a FROM Ruta a WHERE a.idUsuario=:usuario");
            query.setParameter("usuario", usuario);
            listaRutas = (List<Ruta>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en la consulta findByUsuario " + e.getMessage());
        } finally {
            em.close();
        }

        return listaRutas;
    }
    
    public List<Ruta> findByFecha(Date fecha) {

        List<Ruta> listaRutas = new ArrayList<Ruta>();
        try {
            String pattern = "yyyy MMMMM dd  HH:mm:ss";
            SimpleDateFormat simpleDateFormat
                    = new SimpleDateFormat(pattern);
            //Connection connection = em.unwrap(Connection.class);
            Date inicio = fecha;
            inicio.setHours(0);
            inicio.setMinutes(0);
            String inicioText = simpleDateFormat.format(inicio);

            Date fin = fecha;
            fin.setHours(11);
            fin.setMinutes(59);
            String finText = simpleDateFormat.format(fin);

            Date paramInicio = simpleDateFormat.parse(inicioText);
            Date paramFin = simpleDateFormat.parse(finText);

            System.out.println("inicio " + paramInicio);
            System.out.println("fin " + paramFin);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM Ruta u WHERE  u. BETWEEN :inicio and :fin");
            query.setParameter("inicio", paramInicio);
            query.setParameter("fin", paramFin);
            listaRutas = (List<Ruta>) query.getResultList();
            System.out.println("RUTAS " + listaRutas.size());
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta Rutas  findByFecha  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaRutas;
    }
}
