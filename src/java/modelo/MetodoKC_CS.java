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
public class MetodoKC_CS implements CaloriasRequeridas {

    @Override
    public double calculo_kc(Indicadoressalud is) {
        return is.getPeso() * 35 + 0.2;
    }

}
