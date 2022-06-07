/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidad.RutaProcesada;
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
public class ServicioRutasProcesada {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(RutaProcesada ruta) {

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

    public void eliminar(RutaProcesada ruta) {

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

    public void modificar(RutaProcesada ruta) {

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

    public List<RutaProcesada> findByNombre(String nombre) {

        List<RutaProcesada> listaRutaProcesadas = new ArrayList<RutaProcesada>();
        try {
            System.out.println("Entra a consultar rutas");
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT a FROM RutaProcesada a where a.rutNombre LIKE :rutNombre");
            query.setParameter("rutNombre", "%" + nombre + "%");
            listaRutaProcesadas = (List<RutaProcesada>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en la consulta findByNombre " + e.getMessage());
        } finally {
            em.close();
        }

        return listaRutaProcesadas;
    }

    public List<RutaProcesada> findByUsuario(Usuario usuario) {

        List<RutaProcesada> listaRutaProcesadas = new ArrayList<RutaProcesada>();
        try {

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT a FROM RutaProcesada a WHERE a.idUsuario=:usuario");
            query.setParameter("usuario", usuario);
            listaRutaProcesadas = (List<RutaProcesada>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en la consulta findByUsuario " + e.getMessage());
        } finally {
            em.close();
        }

        return listaRutaProcesadas;
    }

    public List<RutaProcesada> findByFecha(Date fecha) {

        List<RutaProcesada> listaRutaProcesadas = new ArrayList<RutaProcesada>();
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
            Query query = em.createQuery("SELECT u FROM RutaProcesada u WHERE  u. BETWEEN :inicio and :fin");
            query.setParameter("inicio", paramInicio);
            query.setParameter("fin", paramFin);
            listaRutaProcesadas = (List<RutaProcesada>) query.getResultList();
            System.out.println("RUTAS " + listaRutaProcesadas.size());
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta RutaProcesadas  findByFecha  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaRutaProcesadas;
    }

    public List<RutaProcesada> findAllRoutes() {
        List<RutaProcesada> listRoutes = new ArrayList<RutaProcesada>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT r FROM RutaProcesada r");
            listRoutes = (List<RutaProcesada>) query.getResultList();
            System.out.println("RUTAS " + listRoutes.size());
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en el metodo findAllRoutes al consultar todas las rutas  " + e.getMessage());
        } finally {
            em.close();
        }
        return listRoutes;
    }

    /*BUSCAR POS USUARIO Y FECHA*/
    public List<RutaProcesada> findBetweeFechaUsuario(Date fechaInicio, Date fechaFIn, Usuario idUsuario) {

        List<RutaProcesada> listaRutaProcesadas = new ArrayList<RutaProcesada>();
        try {
            String pattern = "yyyy MMMMM dd  HH:mm:ss";
            SimpleDateFormat simpleDateFormat
                        = new SimpleDateFormat(pattern);
            //Connection connection = em.unwrap(Connection.class);
            Date inicio = fechaInicio;
            inicio.setHours(0);
            inicio.setMinutes(0);
            String inicioText = simpleDateFormat.format(inicio);

            Date fin = fechaFIn;
            fin.setHours(11);
            fin.setMinutes(59);
            String finText = simpleDateFormat.format(fin);

            Date paramInicio = simpleDateFormat.parse(inicioText);
            Date paramFin = simpleDateFormat.parse(finText);

            System.out.println("inicio " + paramInicio);
            System.out.println("fin " + paramFin);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT u FROM RutaProcesada u WHERE  u.rutpFecha BETWEEN :inicio and :fin AND u.idUsuario=:idUsuario");
            query.setParameter("inicio", paramInicio);
            query.setParameter("fin", paramFin);
            query.setParameter("idUsuario", idUsuario);
            listaRutaProcesadas = (List<RutaProcesada>) query.getResultList();
            System.out.println("RUTAS " + listaRutaProcesadas.size());
            em.getTransaction().commit();
        } catch (ParseException e) {
            System.out.println("Error en lsa consulta RutaProcesadas  findByFecha  " + e.getMessage());
        } finally {
            em.close();
        }

        return listaRutaProcesadas;
    }
}
