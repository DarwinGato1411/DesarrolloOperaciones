/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.TrackPoints;
import com.ec.entidad.Usuario;
import java.text.ParseException;
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
public class ServicioTrackPoint {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(TrackPoints trackPoints) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(trackPoints);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar trackPoints " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void eliminar(TrackPoints trackPoints) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(trackPoints));
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en eliminar  trackPoints " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public void modificar(TrackPoints trackPoints) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(trackPoints);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("Error en insertar trackPoints " + e.getMessage());
        } finally {
            em.close();
        }

    }

    public List<TrackPoints> findByNombre(String nombre) {

        List<TrackPoints> listaTrackPointss = new ArrayList<TrackPoints>();
        try {
            System.out.println("Entra a consultar trackPointss");
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT a FROM TrackPoints a where a.rutNombre LIKE :rutNombre");
            query.setParameter("rutNombre", "%" + nombre + "%");
            listaTrackPointss = (List<TrackPoints>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en la consulta findByNombre " + e.getMessage());
        } finally {
            em.close();
        }

        return listaTrackPointss;
    }

    public List<TrackPoints> findByUsuario(Usuario usuario) {

        List<TrackPoints> listaTrackPointss = new ArrayList<TrackPoints>();
        try {

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT a FROM TrackPoints a WHERE a.idUsuario=:usuario");
            query.setParameter("usuario", usuario);
            listaTrackPointss = (List<TrackPoints>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en la consulta findByUsuario " + e.getMessage());
        } finally {
            em.close();
        }

        return listaTrackPointss;
    }
    
    public List<TrackPoints> findByFecha(Date fecha) {

        List<TrackPoints> listaTrackPointss = new ArrayList<TrackPoints>();
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
            Query query = em.createQuery("SELECT u FROM TrackPoints u WHERE  u.trackFechaCompleta BETWEEN :inicio and :fin");
            query.setParameter("inicio", paramInicio);
            query.setParameter("fin", paramFin);
            listaTrackPointss = (List<TrackPoints>) query.getResultList();
            System.out.println("RUTAS " + listaTrackPointss.size());
            em.getTransaction().commit();
        } catch (ParseException e) {
            System.out.println("Error en lsa consulta TrackPointss  findByFecha  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaTrackPointss;
    }
}
