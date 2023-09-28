package com.hiberus.F1testing;


import com.hiberus.F1testing.exceptions.CombustibleNegativoException;
import com.hiberus.F1testing.exceptions.PorcentajeVidaNeumaticoNoValido;
import com.hiberus.F1testing.exceptions.RecorridoKmNegativo;

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
        return !sinCombustibleAntesFinRecorrido(combustible, combustibleConsumidoPorKmRecorrido, kilometrosRecorrido) && !neumaticosDesgastadosAntesFinRecorrido(neumaticos, porcentajeDeVidaDeNeumaticosConsumidoPorKmRecorrido, kilometrosRecorrido) && (neumaticos.getCantidadNeumaticos() == 4);
    }

    private boolean sinCombustibleAntesFinRecorrido(Combustible combustible, float combustibleConsumidoPorKmRecorrido, float kilometrosARecorrer) {

        float combustibleFinal = combustible.getLitrosCombustible() - kilometrosARecorrer * combustibleConsumidoPorKmRecorrido;

        return combustibleFinal <= 0;

    }

    private boolean neumaticosDesgastadosAntesFinRecorrido(Neumaticos neumaticos, float porcentajeDeVidaDeNeumaticosConsumidoPorKmRecorrido, float kilometrosARecorrer) {

        float porcentajeFinal = neumaticos.getPorcentajeVida() - kilometrosARecorrer * porcentajeDeVidaDeNeumaticosConsumidoPorKmRecorrido;

        return porcentajeFinal <= 0;
    }
}
