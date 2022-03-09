/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidad;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;

/**
 *
 * @author Darwin
 */
@Entity
@Table(name = "ruta")
@NamedQueries({
    @NamedQuery(name = "Ruta.findAll", query = "SELECT r FROM Ruta r")})
public class Ruta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ruta")
    private Integer idRuta;
    @Size(max = 100)
    @Column(name = "rut_nombre")
    private String rutNombre;
    @Size(max = 100)
    @Column(name = "rut_descripcion")
    private String rutDescripcion;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;
    @OneToMany(mappedBy = "idRuta")
    private Collection<DetalleRuta> detalleRutaCollection;

    public Ruta() {
    }

    public Ruta(Integer idRuta) {
        this.idRuta = idRuta;
    }

    public Integer getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
    }

    public String getRutNombre() {
        return rutNombre;
    }

    public void setRutNombre(String rutNombre) {
        this.rutNombre = rutNombre;
    }

    public String getRutDescripcion() {
        return rutDescripcion;
    }

    public void setRutDescripcion(String rutDescripcion) {
        this.rutDescripcion = rutDescripcion;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Collection<DetalleRuta> getDetalleRutaCollection() {
        return detalleRutaCollection;
    }

    public void setDetalleRutaCollection(Collection<DetalleRuta> detalleRutaCollection) {
        this.detalleRutaCollection = detalleRutaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRuta != null ? idRuta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ruta)) {
            return false;
        }
        Ruta other = (Ruta) object;
        if ((this.idRuta == null && other.idRuta != null) || (this.idRuta != null && !this.idRuta.equals(other.idRuta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.entidad.Ruta[ idRuta=" + idRuta + " ]";
    }
    
}
