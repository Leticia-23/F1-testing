package com.hiberus.f1testing;

import com.hiberus.f1testing.exceptions.CombustibleNegativoException;
import com.hiberus.f1testing.exceptions.PorcentajeVidaNeumaticoNoValido;
import com.hiberus.f1testing.exceptions.RecorridoKmNegativo;

public class Estrategia {

    private final Combustible combustible;
    private final float combustibleConsumidoPorKmRecorrido;
    private final Neumaticos neumaticos;
    private final float porcentajeDeVidaDeNeumaticosConsumidoPorKmRecorrido;
    private final float kilometrosRecorrido;

    public Estrategia(Combustible combustible, float combustibleConsumidoPorKmRecorrido, Neumaticos neumaticos, float porcentajeDeVidaDeNeumaticosConsumidoPorKmRecorrido, float kilometrosRecorrido) throws CombustibleNegativoException, PorcentajeVidaNeumaticoNoValido, RecorridoKmNegativo {

        if (combustibleConsumidoPorKmRecorrido < 0) {
            throw  new CombustibleNegativoException();
        }

        if (porcentajeDeVidaDeNeumaticosConsumidoPorKmRecorrido < 0 || porcentajeDeVidaDeNeumaticosConsumidoPorKmRecorrido > 100 ) {
            throw new PorcentajeVidaNeumaticoNoValido();
        }

        if (kilometrosRecorrido < 0 ) {
            throw new RecorridoKmNegativo();
        }

        this.combustible = combustible;
        this.combustibleConsumidoPorKmRecorrido = combustibleConsumidoPorKmRecorrido;
        this.neumaticos = neumaticos;
        this.porcentajeDeVidaDeNeumaticosConsumidoPorKmRecorrido = porcentajeDeVidaDeNeumaticosConsumidoPorKmRecorrido;
        this.kilometrosRecorrido = kilometrosRecorrido;
    }

    public boolean esViable() {
       float combustibleFinal = combustibleFinalRecorrido();
       float desgasteNeumaticosFinal = desgasteNeumaticosFinalRecorrido();
       return (combustibleFinal > 0) && (desgasteNeumaticosFinal > 0) && (neumaticos.getCantidadNeumaticos() == 4);
    }

    private float combustibleFinalRecorrido() {
        return combustible.getLitrosCombustible() - kilometrosRecorrido * combustibleConsumidoPorKmRecorrido;
    }

    private float desgasteNeumaticosFinalRecorrido() {
        return neumaticos.getPorcentajeVida() - kilometrosRecorrido * porcentajeDeVidaDeNeumaticosConsumidoPorKmRecorrido;
    }
}
