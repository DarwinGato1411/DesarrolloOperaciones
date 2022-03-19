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
 * @author Omar
 */
@Entity
@Table(name = "track_points")
@NamedQueries({
    @NamedQuery(name = "TrackPoints.findAll", query = "SELECT t FROM TrackPoints t")})
public class TrackPoints implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_track")
    private Integer idTrack;
    @Column(name = "track_numero")
    private Integer trackNumero;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "track_latitud")
    private BigDecimal trackLatitud;
    @Column(name = "track_longitud")
    private BigDecimal trackLongitud;
    @Column(name = "track_velocidad")
    private BigDecimal trackVelocidad;
    @Column(name = "track_direccion")
    private BigDecimal trackDireccion;
    @Column(name = "track_fecha")
    @Temporal(TemporalType.DATE)
    private Date trackFecha;
    @Column(name = "track_hora")
    @Temporal(TemporalType.TIME)
    private Date trackHora;
    @Column(name = "track_fecha_completa")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trackFechaCompleta;
    @Column(name = "track_ruta")
    private String trackRuta;
    @Column(name = "track_unidad")
    private String trackUnidad;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    public TrackPoints() {

    }

    public TrackPoints(Integer trackNumero, BigDecimal trackLatitud, BigDecimal trackLongitud, BigDecimal trackVelocidad, BigDecimal trackDireccion, Date trackFecha, Date trackHora, Date trackFechaCompleta, String trackRuta, String trackUnidad) {
        this.trackNumero = trackNumero;
        this.trackLatitud = trackLatitud;
        this.trackLongitud = trackLongitud;
        this.trackVelocidad = trackVelocidad;
        this.trackDireccion = trackDireccion;
        this.trackFecha = trackFecha;
        this.trackHora = trackHora;
        this.trackFechaCompleta = trackFechaCompleta;
        this.trackRuta = trackRuta;
        this.trackUnidad = trackUnidad;
    }

    public TrackPoints(Integer idTrack) {
        this.idTrack = idTrack;
    }

    public Integer getIdTrack() {
        return idTrack;
    }

    public void setIdTrack(Integer idTrack) {
        this.idTrack = idTrack;
    }

    public Integer getTrackNumero() {
        return trackNumero;
    }

    public void setTrackNumero(Integer trackNumero) {
        this.trackNumero = trackNumero;
    }

    public BigDecimal getTrackLatitud() {
        return trackLatitud;
    }

    public void setTrackLatitud(BigDecimal trackLatitud) {
        this.trackLatitud = trackLatitud;
    }

    public BigDecimal getTrackLongitud() {
        return trackLongitud;
    }

    public void setTrackLongitud(BigDecimal trackLongitud) {
        this.trackLongitud = trackLongitud;
    }

    public BigDecimal getTrackVelocidad() {
        return trackVelocidad;
    }

    public void setTrackVelocidad(BigDecimal trackVelocidad) {
        this.trackVelocidad = trackVelocidad;
    }

    public BigDecimal getTrackDireccion() {
        return trackDireccion;
    }

    public void setTrackDireccion(BigDecimal trackDireccion) {
        this.trackDireccion = trackDireccion;
    }

    public Date getTrackFecha() {
        return trackFecha;
    }

    public void setTrackFecha(Date trackFecha) {
        this.trackFecha = trackFecha;
    }

    public Date getTrackHora() {
        return trackHora;
    }

    public void setTrackHora(Date trackHora) {
        this.trackHora = trackHora;
    }

    public Date getTrackFechaCompleta() {
        return trackFechaCompleta;
    }

    public void setTrackFechaCompleta(Date trackFechaCompleta) {
        this.trackFechaCompleta = trackFechaCompleta;
    }

    public String getTrackRuta() {
        return trackRuta;
    }

    public void setTrackRuta(String trackRuta) {
        this.trackRuta = trackRuta;
    }

    public String getTrackUnidad() {
        return trackUnidad;
    }

    public void setTrackUnidad(String trackUnidad) {
        this.trackUnidad = trackUnidad;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrack != null ? idTrack.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrackPoints)) {
            return false;
        }
        TrackPoints other = (TrackPoints) object;
        if ((this.idTrack == null && other.idTrack != null) || (this.idTrack != null && !this.idTrack.equals(other.idTrack))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.TrackPoints[ idTrack=" + idTrack + " ]";
    }

}
