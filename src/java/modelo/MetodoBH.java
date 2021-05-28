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
public class MetodoBH implements CaloriasRequeridas {

    @Override
    public double calculo_kc(Indicadoressalud is) {
        double bmr = is.getPeso() * 24;
        double calorias = 0;

        if (is.getIdusuario().getSexo() == 'M' && is.grasaCorporal() >= 10) {
            bmr = bmr * 0.9;
        }
        
        if (is.getIdusuario().getSexo() == 'H' && is.getTipoact().getIdtact() == 1) {
            calorias = bmr * 1;
        } else if (is.getIdusuario().getSexo() == 'H' && is.getTipoact().getIdtact() == 2) {
            calorias = bmr * (1.15);
        } else if (is.getIdusuario().getSexo() == 'H' && is.getTipoact().getIdtact() == 3) {
            calorias = bmr * (1.35);
        } else if (is.getIdusuario().getSexo() == 'H' && is.getTipoact().getIdtact() == 4) {
            calorias = bmr * (1.45);
        } else if (is.getIdusuario().getSexo() == 'M' && is.getTipoact().getIdtact() == 1) {
            calorias = bmr * 1;
        } else if (is.getIdusuario().getSexo() == 'M' && is.getTipoact().getIdtact() == 2) {
            calorias = bmr * (1.15);
        } else if (is.getIdusuario().getSexo() == 'M' && is.getTipoact().getIdtact() == 3) {
            calorias = bmr * (1.35);
        } else if (is.getIdusuario().getSexo() == 'M' && is.getTipoact().getIdtact() == 4) {
            calorias = bmr * (1.45);
        }
        
        return calorias;
    }

}
