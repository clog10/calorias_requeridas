/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Loaeza
 */
@Entity
@Table(name = "INDICADORESSALUD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Indicadoressalud.findAll", query = "SELECT i FROM Indicadoressalud i"),
    @NamedQuery(name = "Indicadoressalud.findByIdindsalud", query = "SELECT i FROM Indicadoressalud i WHERE i.idindsalud = :idindsalud"),
    @NamedQuery(name = "Indicadoressalud.findByFecha", query = "SELECT i FROM Indicadoressalud i WHERE i.fecha = :fecha"),
    @NamedQuery(name = "Indicadoressalud.findByPeso", query = "SELECT i FROM Indicadoressalud i WHERE i.peso = :peso"),
    @NamedQuery(name = "Indicadoressalud.findByEstatura", query = "SELECT i FROM Indicadoressalud i WHERE i.estatura = :estatura"),
    @NamedQuery(name = "Indicadoressalud.findByCintura", query = "SELECT i FROM Indicadoressalud i WHERE i.cintura = :cintura"),
    @NamedQuery(name = "Indicadoressalud.findByCadera", query = "SELECT i FROM Indicadoressalud i WHERE i.cadera = :cadera")})
public class Indicadoressalud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDINDSALUD")
    private Integer idindsalud;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PESO")
    private Double peso;
    @Column(name = "ESTATURA")
    private Double estatura;
    @Column(name = "CINTURA")
    private Integer cintura;
    @Column(name = "CADERA")
    private Integer cadera;
    @JoinColumn(name = "TIPOACT", referencedColumnName = "IDTACT")
    @ManyToOne
    private Tipoactividad tipoact;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne
    private Usuario idusuario;

    public Indicadoressalud() {
    }

    public Indicadoressalud(Integer idindsalud) {
        this.idindsalud = idindsalud;
    }

    public Integer getIdindsalud() {
        return idindsalud;
    }

    public void setIdindsalud(Integer idindsalud) {
        this.idindsalud = idindsalud;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getEstatura() {
        return estatura;
    }

    public void setEstatura(Double estatura) {
        this.estatura = estatura;
    }

    public Integer getCintura() {
        return cintura;
    }

    public void setCintura(Integer cintura) {
        this.cintura = cintura;
    }

    public Integer getCadera() {
        return cadera;
    }

    public void setCadera(Integer cadera) {
        this.cadera = cadera;
    }

    public Tipoactividad getTipoact() {
        return tipoact;
    }

    public void setTipoact(Tipoactividad tipoact) {
        this.tipoact = tipoact;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idindsalud != null ? idindsalud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Indicadoressalud)) {
            return false;
        }
        Indicadoressalud other = (Indicadoressalud) object;
        if ((this.idindsalud == null && other.idindsalud != null) || (this.idindsalud != null && !this.idindsalud.equals(other.idindsalud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Indicadoressalud[ idindsalud=" + idindsalud + " ]";
    }
    
}
