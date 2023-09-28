package com.hiberus.F1testing;


public class Estrategia {

    private final Combustible combustible;
    private final float combustibleConsumidoPorKmRecorrido;
    private final Neumaticos neumaticos;
    private final float porcentajeDeVidaDeNeumaticosConsumidoPorKmRecorrido;
    private final float kilometrosARecorrer;

    public Estrategia(Combustible combustible, float combustibleConsumidoPorKmRecorrido, Neumaticos neumaticos, float porcentajeDeVidaDeNeumaticosConsumidoPorKmRecorrido, float kilometrosARecorrer) {
        this.combustible = combustible;
        this.combustibleConsumidoPorKmRecorrido = combustibleConsumidoPorKmRecorrido;
        this.neumaticos = neumaticos;
        this.porcentajeDeVidaDeNeumaticosConsumidoPorKmRecorrido = porcentajeDeVidaDeNeumaticosConsumidoPorKmRecorrido;
        this.kilometrosARecorrer = kilometrosARecorrer;
    }

    public boolean esViable() {

        boolean estrategiaViable = true;

        return estrategiaViable;
    }


    private boolean sinCombustibleAntesFinRecorrido(Combustible combustible, float combustibleConsumidoPorKmRecorrido, float kilometrosARecorrer) {

        // TODO: teniendo combustible (l);  recorrido (km); y combustible consumido por km. Calcular combustible al final de recorrido. Si <=0 entonces return true sino false



        return false;
    }

    private boolean neumaticosDesgastadosAntesFinRecorrido(Neumaticos neumaticos, float porcentajeDeVidaDeNeumaticosConsumidoPorKmRecorrido, float kilometrosARecorrer) {

        // TODO: teniendo porcentaje vida consumido por km; porcentaje inicial neumáticos; y recorrido (km). Calcular porcentaje al final de recorrido, si <=0 entonces return true sino false

        return false;
    }
}
