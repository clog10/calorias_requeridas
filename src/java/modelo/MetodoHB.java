/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Carlos Loaeza
 */
public class MetodoHB implements CaloriasRequeridas {

    @Override
    public double calculo_kc(Indicadoressalud is) {
        double calorias = 0; 
        if (is.getIdusuario().getSexo() == 'M' && is.getTipoact().getIdtact() == 1) {
            calorias = ((655 + (9.6 * is.getPeso())) + (1.8 * is.getEstatura()) - (4.7 * is.getEdad())) * 1.2; 
        } else if (is.getIdusuario().getSexo() == 'M' && is.getTipoact().getIdtact() == 2) {
            calorias = ((655 + (9.6 * is.getPeso())) + (1.8 * is.getEstatura()) - (4.7 * is.getEdad())) * 1.375; 
        } else if (is.getIdusuario().getSexo() == 'M' && is.getTipoact().getIdtact() == 3) {
            calorias = ((655 + (9.6 * is.getPeso())) + (1.8 * is.getEstatura()) - (4.7 * is.getEdad())) * 1.55; 
        } else if (is.getIdusuario().getSexo() == 'M' && is.getTipoact().getIdtact() == 4) {
            calorias = ((655 + (9.6 * is.getPeso())) + (1.8 * is.getEstatura()) - (4.7 * is.getEdad())) * 1.725; 
        } else if (is.getIdusuario().getSexo() == 'H' && is.getTipoact().getIdtact() == 1) {
            calorias = ((66 + (13.7 * is.getPeso())) + (5 * is.getEstatura()) - (6.8 * is.getEdad())) * 1.2; 
        } else if (is.getIdusuario().getSexo() == 'H' && is.getTipoact().getIdtact() == 2) {
            calorias = ((66 + (13.7 * is.getPeso())) + (5 * is.getEstatura()) - (6.8 * is.getEdad())) * 1.375; 
        } else if (is.getIdusuario().getSexo() == 'H' && is.getTipoact().getIdtact() == 3) {
            calorias = ((66 + (13.7 * is.getPeso())) + (5 * is.getEstatura()) - (6.8 * is.getEdad())) * 1.55; 
        } else if (is.getIdusuario().getSexo() == 'H' && is.getTipoact().getIdtact() == 4) {
            calorias = ((66 + (13.7 * is.getPeso())) + (5 * is.getEstatura()) - (6.8 * is.getEdad())) * 1.725; 
        }
        return calorias;
    }

}
