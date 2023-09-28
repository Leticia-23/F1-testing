package com.hiberus.F1testing;

import com.hiberus.F1testing.exceptions.CantidadNeumaticosNegativo;
import com.hiberus.F1testing.exceptions.CombustibleNegativoException;
import com.hiberus.F1testing.exceptions.PorcentajeVidaNeumaticoNoValido;
import com.hiberus.F1testing.exceptions.RecorridoKmNegativo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EstrategiaTest {

    private final Combustible COMBUSTIBLE = new Combustible(TipoCombustible.DIESEL, 145.0f);
    private final float COMBUSTIBLE_CONSUMIDO_KM = 0.3f;
    private final Neumaticos NEUMATICOS = new Neumaticos(MarcaNeumatico.BRIDGESTONE, 99.9f, 4);
    private final float PORCENTAJE_VIDA_NEUMATICOS_CONSUMIDO_KM = 0.2f;
    private final float KM_RECORRIDO = 4000.0f;

    public EstrategiaTest() throws CombustibleNegativoException, PorcentajeVidaNeumaticoNoValido, CantidadNeumaticosNegativo {
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("TEST NEUMATICOS");}

    @Test
    void EstrategiaViable() throws PorcentajeVidaNeumaticoNoValido, RecorridoKmNegativo, CombustibleNegativoException {
        // Given & Then
        Estrategia estrategia = new Estrategia(COMBUSTIBLE, COMBUSTIBLE_CONSUMIDO_KM, NEUMATICOS, PORCENTAJE_VIDA_NEUMATICOS_CONSUMIDO_KM, KM_RECORRIDO);

        // Then
        assertTrue(estrategia.esViable());
    }

    @Test
    void EstrategiaNoViablePorSinCombustibleAntesFin() throws CombustibleNegativoException, PorcentajeVidaNeumaticoNoValido, RecorridoKmNegativo {

        // Given
        Combustible combustible = new Combustible(TipoCombustible.GASOLINA, 100.0f);

        // Then
        Estrategia estrategia = new Estrategia(combustible, COMBUSTIBLE_CONSUMIDO_KM, NEUMATICOS, PORCENTAJE_VIDA_NEUMATICOS_CONSUMIDO_KM, KM_RECORRIDO);

        // Then
        assertFalse(estrategia.esViable());
    }

    @Test
    void EstrategiaNoViablePorNeumaticosDesgastadosAntesFin() throws PorcentajeVidaNeumaticoNoValido, CantidadNeumaticosNegativo, RecorridoKmNegativo, CombustibleNegativoException {
        // Given
        Neumaticos neumaticos = new Neumaticos(MarcaNeumatico.PIRELLI, 10.0f, 4);

        // Then
        Estrategia estrategia = new Estrategia(COMBUSTIBLE, COMBUSTIBLE_CONSUMIDO_KM, NEUMATICOS, PORCENTAJE_VIDA_NEUMATICOS_CONSUMIDO_KM, KM_RECORRIDO);

        // Then
        assertFalse(estrategia.esViable());
    }

    @Test
    void EstrategiaNoViablePorCantidadNeumaticosDistintaDeCuatro() throws PorcentajeVidaNeumaticoNoValido, CantidadNeumaticosNegativo, RecorridoKmNegativo, CombustibleNegativoException {
        // Given
        Neumaticos neumaticos = new Neumaticos(MarcaNeumatico.BRIDGESTONE, 100.0f, 5);

        // Then
        Estrategia estrategia = new Estrategia(COMBUSTIBLE, COMBUSTIBLE_CONSUMIDO_KM, NEUMATICOS, PORCENTAJE_VIDA_NEUMATICOS_CONSUMIDO_KM, KM_RECORRIDO);

        // Then
        assertFalse(estrategia.esViable());
    }

}
