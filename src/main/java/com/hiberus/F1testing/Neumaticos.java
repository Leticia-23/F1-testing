package com.hiberus.F1testing;

import com.hiberus.F1testing.exceptions.CantidadNeumaticosNegativo;
import com.hiberus.F1testing.exceptions.PorcentajeVidaNeumaticoNoValido;

public class Neumaticos {
    private final MarcasNeumatico marca;
    private float porcentajeDeVida;

    private final int cantidadNeumaticos;

    public Neumaticos(MarcasNeumatico marca, float porcentajeDeVida, int cantidadNeumaticos) throws CantidadNeumaticosNegativo, PorcentajeVidaNeumaticoNoValido {

        if (cantidadNeumaticos < 0) {
            throw new CantidadNeumaticosNegativo();
        }

        if (porcentajeDeVida < 0 || porcentajeDeVida > 100 ) {
            throw new PorcentajeVidaNeumaticoNoValido();
        }

        this.marca = marca;
        this.porcentajeDeVida = porcentajeDeVida;
        this.cantidadNeumaticos = cantidadNeumaticos;
    }
}
