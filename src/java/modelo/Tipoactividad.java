/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos Loaeza
 */
@Entity
@Table(name = "TIPOACTIVIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoactividad.findAll", query = "SELECT t FROM Tipoactividad t"),
    @NamedQuery(name = "Tipoactividad.findByIdtact", query = "SELECT t FROM Tipoactividad t WHERE t.idtact = :idtact"),
    @NamedQuery(name = "Tipoactividad.findByDescripcion", query = "SELECT t FROM Tipoactividad t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tipoactividad.findByActividades", query = "SELECT t FROM Tipoactividad t WHERE t.actividades = :actividades")})
public class Tipoactividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTACT")
    private Integer idtact;
    @Size(max = 30)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 100)
    @Column(name = "ACTIVIDADES")
    private String actividades;
    @OneToMany(mappedBy = "tipoact")
    private List<Indicadoressalud> indicadoressaludList;
    @OneToMany(mappedBy = "tipoact")
    private List<Usuario> usuarioList;

    public Tipoactividad() {
    }

    public Tipoactividad(Integer idtact) {
        this.idtact = idtact;
    }

    public Integer getIdtact() {
        return idtact;
    }

    public void setIdtact(Integer idtact) {
        this.idtact = idtact;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    @XmlTransient
    public List<Indicadoressalud> getIndicadoressaludList() {
        return indicadoressaludList;
    }

    public void setIndicadoressaludList(List<Indicadoressalud> indicadoressaludList) {
        this.indicadoressaludList = indicadoressaludList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtact != null ? idtact.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoactividad)) {
            return false;
        }
        Tipoactividad other = (Tipoactividad) object;
        if ((this.idtact == null && other.idtact != null) || (this.idtact != null && !this.idtact.equals(other.idtact))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tipoactividad[ idtact=" + idtact + " ]";
    }
    
}
