package com.hiberus.F1testing;

import com.hiberus.F1testing.exceptions.CantidadNeumaticosNegativo;
import com.hiberus.F1testing.exceptions.PorcentajeVidaNeumaticoNoValido;

public class Neumaticos {
    private final MarcaNeumatico marca;
    private float porcentajeVida;

    private final int cantidadNeumaticos;

    public Neumaticos(MarcaNeumatico marca, float porcentajeVida, int cantidadNeumaticos) throws CantidadNeumaticosNegativo, PorcentajeVidaNeumaticoNoValido {

        if (cantidadNeumaticos < 0) {
            throw new CantidadNeumaticosNegativo();
        }

        if (porcentajeVida < 0 || porcentajeVida > 100 ) {
            throw new PorcentajeVidaNeumaticoNoValido();
        }

        this.marca = marca;
        this.porcentajeVida = porcentajeVida;
        this.cantidadNeumaticos = cantidadNeumaticos;
    }

    public MarcaNeumatico getMarca() {
        return marca;
    }

    public float getPorcentajeVida() {
        return porcentajeVida;
    }

    public int getCantidadNeumaticos() {
        return cantidadNeumaticos;
    }
}
