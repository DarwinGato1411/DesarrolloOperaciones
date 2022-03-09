/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Size;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "detalle_ruta")
@NamedQueries({
    @NamedQuery(name = "DetalleRuta.findAll", query = "SELECT d FROM DetalleRuta d")})
public class DetalleRuta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_ruta")
    private Integer idDetalleRuta;
    @Size(max = 100)
    @Column(name = "detr_nombre")
    private String detrNombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "detr_longitud")
    private BigDecimal detrLongitud;
    @Column(name = "detr_latitud")
    private BigDecimal detrLatitud;
    @Column(name = "detr_inicio")
    private Boolean detrInicio;
    @Column(name = "detr_orden")
    private Integer detrOrden;
    @Column(name = "detr_fin")
    private Boolean detrFin;
    @JoinColumn(name = "id_ruta", referencedColumnName = "id_ruta")
    @ManyToOne
    private Ruta idRuta;

    public DetalleRuta() {
    }

    public DetalleRuta(Integer idDetalleRuta) {
        this.idDetalleRuta = idDetalleRuta;
    }

    public Integer getIdDetalleRuta() {
        return idDetalleRuta;
    }

    public void setIdDetalleRuta(Integer idDetalleRuta) {
        this.idDetalleRuta = idDetalleRuta;
    }

    public String getDetrNombre() {
        return detrNombre;
    }

    public void setDetrNombre(String detrNombre) {
        this.detrNombre = detrNombre;
    }

    public BigDecimal getDetrLongitud() {
        return detrLongitud;
    }

    public void setDetrLongitud(BigDecimal detrLongitud) {
        this.detrLongitud = detrLongitud;
    }

    public BigDecimal getDetrLatitud() {
        return detrLatitud;
    }

    public void setDetrLatitud(BigDecimal detrLatitud) {
        this.detrLatitud = detrLatitud;
    }

    public Boolean getDetrInicio() {
        return detrInicio;
    }

    public void setDetrInicio(Boolean detrInicio) {
        this.detrInicio = detrInicio;
    }

    public Integer getDetrOrden() {
        return detrOrden;
    }

    public void setDetrOrden(Integer detrOrden) {
        this.detrOrden = detrOrden;
    }

    public Boolean getDetrFin() {
        return detrFin;
    }

    public void setDetrFin(Boolean detrFin) {
        this.detrFin = detrFin;
    }

    public Ruta getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Ruta idRuta) {
        this.idRuta = idRuta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleRuta != null ? idDetalleRuta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleRuta)) {
            return false;
        }
        DetalleRuta other = (DetalleRuta) object;
        if ((this.idDetalleRuta == null && other.idDetalleRuta != null) || (this.idDetalleRuta != null && !this.idDetalleRuta.equals(other.idDetalleRuta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.DetalleRuta[ idDetalleRuta=" + idDetalleRuta + " ]";
    }
    
}
