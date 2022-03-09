/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "detalle_ruta_procesada")
@NamedQueries({
    @NamedQuery(name = "DetalleRutaProcesada.findAll", query = "SELECT d FROM DetalleRutaProcesada d")})
public class DetalleRutaProcesada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_det_rut_procesada")
    private Integer idDetRutProcesada;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "detrp_longitud")
    private BigDecimal detrpLongitud;
    @Column(name = "detrp_lalitud")
    private BigDecimal detrpLalitud;
    @Column(name = "detrp_orden")
    private Integer detrpOrden;
    @Column(name = "detrp_hora")
    @Temporal(TemporalType.TIME)
    private Date detrpHora;
    @JoinColumn(name = "id_ruta_procesada", referencedColumnName = "id_ruta_procesada")
    @ManyToOne
    private RutaProcesada idRutaProcesada;

    public DetalleRutaProcesada() {
    }

    public DetalleRutaProcesada(Integer idDetRutProcesada) {
        this.idDetRutProcesada = idDetRutProcesada;
    }

    public Integer getIdDetRutProcesada() {
        return idDetRutProcesada;
    }

    public void setIdDetRutProcesada(Integer idDetRutProcesada) {
        this.idDetRutProcesada = idDetRutProcesada;
    }

    public BigDecimal getDetrpLongitud() {
        return detrpLongitud;
    }

    public void setDetrpLongitud(BigDecimal detrpLongitud) {
        this.detrpLongitud = detrpLongitud;
    }

    public BigDecimal getDetrpLalitud() {
        return detrpLalitud;
    }

    public void setDetrpLalitud(BigDecimal detrpLalitud) {
        this.detrpLalitud = detrpLalitud;
    }

    public Integer getDetrpOrden() {
        return detrpOrden;
    }

    public void setDetrpOrden(Integer detrpOrden) {
        this.detrpOrden = detrpOrden;
    }

    public Date getDetrpHora() {
        return detrpHora;
    }

    public void setDetrpHora(Date detrpHora) {
        this.detrpHora = detrpHora;
    }

    public RutaProcesada getIdRutaProcesada() {
        return idRutaProcesada;
    }

    public void setIdRutaProcesada(RutaProcesada idRutaProcesada) {
        this.idRutaProcesada = idRutaProcesada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetRutProcesada != null ? idDetRutProcesada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleRutaProcesada)) {
            return false;
        }
        DetalleRutaProcesada other = (DetalleRutaProcesada) object;
        if ((this.idDetRutProcesada == null && other.idDetRutProcesada != null) || (this.idDetRutProcesada != null && !this.idDetRutProcesada.equals(other.idDetRutProcesada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.DetalleRutaProcesada[ idDetRutProcesada=" + idDetRutProcesada + " ]";
    }
    
}
