/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "ruta_procesada")
@NamedQueries({
    @NamedQuery(name = "RutaProcesada.findAll", query = "SELECT r FROM RutaProcesada r")})
public class RutaProcesada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ruta_procesada")
    private Integer idRutaProcesada;
    @Size(max = 100)
    @Column(name = "rutp_nombre")
    private String rutpNombre;
    @Column(name = "rutp_fecha")
    @Temporal(TemporalType.DATE)
    private Date rutpFecha;
    @Column(name = "rutp_inicio")
    @Temporal(TemporalType.TIME)
    private Date rutpInicio;
  
    @Size(max = 150)
    @Column(name = "rutp_respnasable")
    private String rutpRespnasable;
    @Column(name = "id_ruta")
    private Integer idRuta;
 
    @OneToMany(mappedBy = "idRutaProcesada")
    private Collection<DetalleRutaProcesada> detalleRutaProcesadaCollection;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    public RutaProcesada() {
    }

    public RutaProcesada(Integer idRutaProcesada) {
        this.idRutaProcesada = idRutaProcesada;
    }

    public Integer getIdRutaProcesada() {
        return idRutaProcesada;
    }

    public void setIdRutaProcesada(Integer idRutaProcesada) {
        this.idRutaProcesada = idRutaProcesada;
    }

    public String getRutpNombre() {
        return rutpNombre;
    }

    public void setRutpNombre(String rutpNombre) {
        this.rutpNombre = rutpNombre;
    }

    public Date getRutpFecha() {
        return rutpFecha;
    }

    public void setRutpFecha(Date rutpFecha) {
        this.rutpFecha = rutpFecha;
    }

    public Date getRutpInicio() {
        return rutpInicio;
    }

    public void setRutpInicio(Date rutpInicio) {
        this.rutpInicio = rutpInicio;
    }

    public String getRutpRespnasable() {
        return rutpRespnasable;
    }

    public void setRutpRespnasable(String rutpRespnasable) {
        this.rutpRespnasable = rutpRespnasable;
    }

    public Collection<DetalleRutaProcesada> getDetalleRutaProcesadaCollection() {
        return detalleRutaProcesadaCollection;
    }

    public void setDetalleRutaProcesadaCollection(Collection<DetalleRutaProcesada> detalleRutaProcesadaCollection) {
        this.detalleRutaProcesadaCollection = detalleRutaProcesadaCollection;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRutaProcesada != null ? idRutaProcesada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RutaProcesada)) {
            return false;
        }
        RutaProcesada other = (RutaProcesada) object;
        if ((this.idRutaProcesada == null && other.idRutaProcesada != null) || (this.idRutaProcesada != null && !this.idRutaProcesada.equals(other.idRutaProcesada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.RutaProcesada[ idRutaProcesada=" + idRutaProcesada + " ]";
    }

}
