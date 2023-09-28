package com.hiberus.F1testing;


public class Estrategia {

    public Combustible combustible;
    public float combustibleConsumidoPorKmRecorrido;
    public Neumaticos neumaticos;
    public float porcentajeDeVidaDeNeumaticosConsumidoPorKmRecorrido;
    public float kilometrosARecorrer;

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
