package com.hiberus.F1testing;

import com.hiberus.F1testing.exceptions.CantidadNeumaticosNegativo;
import com.hiberus.F1testing.exceptions.CombustibleNegativoException;
import com.hiberus.F1testing.exceptions.PorcentajeVidaNeumaticoNoValido;
import com.hiberus.F1testing.exceptions.RecorridoKmNegativo;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class EstrategiaTest {

    private final Combustible COMBUSTIBLE = new Combustible(TipoCombustible.DIESEL, 145.0f);
    private final float COMBUSTIBLE_CONSUMIDO_KM = 0.33f;
    private final Neumaticos NEUMATICOS = new Neumaticos(MarcaNeumatico.BRIDGESTONE, 99.9f, 4);
    private final float PORCENTAJE_VIDA_NEUMATICOS_CONSUMIDO_KM = 0.1f;
    private final float KM_RECORRIDO = 330.0f;

    public EstrategiaTest() throws CombustibleNegativoException, PorcentajeVidaNeumaticoNoValido, CantidadNeumaticosNegativo {
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("TEST ESTRATEGIA");}

    @AfterAll
    static void afterAll() {
        System.out.println("FIN TEST ESTRATEGIA");
    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        System.out.println("Terminado el test: " + testInfo.getDisplayName());
    }

    @Test
    void estrategiaNoInstanciadaSiLitrosCombustibleConsumidoNegativo() {

        // Given
        int combustibleConsumidoPorKmRecorrido = -1;

        // When & Then
        assertThrows(CombustibleNegativoException.class, () -> new Estrategia(COMBUSTIBLE, combustibleConsumidoPorKmRecorrido, NEUMATICOS, PORCENTAJE_VIDA_NEUMATICOS_CONSUMIDO_KM, KM_RECORRIDO));
    }

    @Test
    void estrategiaNoInstanciadaSiPorcentajeVidaConsumidoMenorACero() {

        //Given
        float porcentajeVidaNeumaticosConsumidoKm = -0.1f;

        // When & Then
        assertThrows(PorcentajeVidaNeumaticoNoValido.class, () -> new Estrategia(COMBUSTIBLE, COMBUSTIBLE_CONSUMIDO_KM, NEUMATICOS, porcentajeVidaNeumaticosConsumidoKm, KM_RECORRIDO));
    }

    @Test
    void estrategiaNoInstanciadaSiPorcentajeVidaConsumidoMayorACien() {

        //Given
        float porcentajeVidaNeumaticosConsumidoKm = 100.1f;

        // When & Then
        assertThrows(PorcentajeVidaNeumaticoNoValido.class, () -> new Estrategia(COMBUSTIBLE, COMBUSTIBLE_CONSUMIDO_KM, NEUMATICOS, porcentajeVidaNeumaticosConsumidoKm, KM_RECORRIDO));
    }

    @Test
    void estrategiaNoInstanciadaSiRecorridoNegativo() {

        //Given
        float kilometrosRecorrido = -0.1f;

        // When & Then
        assertThrows(RecorridoKmNegativo.class, () -> new Estrategia(COMBUSTIBLE, COMBUSTIBLE_CONSUMIDO_KM, NEUMATICOS, PORCENTAJE_VIDA_NEUMATICOS_CONSUMIDO_KM, kilometrosRecorrido));
    }

    @Test
    void estrategiaViable() throws PorcentajeVidaNeumaticoNoValido, RecorridoKmNegativo, CombustibleNegativoException {

        // Given & Then
        Estrategia estrategia = new Estrategia(COMBUSTIBLE, COMBUSTIBLE_CONSUMIDO_KM, NEUMATICOS, PORCENTAJE_VIDA_NEUMATICOS_CONSUMIDO_KM, KM_RECORRIDO);

        // Then
        assertTrue(estrategia.esViable());
    }

    @Test
    void estrategiaNoViablePorSinCombustibleAntesFin() throws CombustibleNegativoException, PorcentajeVidaNeumaticoNoValido, RecorridoKmNegativo {

        // Given
        Combustible combustible = new Combustible(TipoCombustible.GASOLINA, 100.0f);

        // Then
        Estrategia estrategia = new Estrategia(combustible, COMBUSTIBLE_CONSUMIDO_KM, NEUMATICOS, PORCENTAJE_VIDA_NEUMATICOS_CONSUMIDO_KM, KM_RECORRIDO);

        // Then
        assertFalse(estrategia.esViable());
    }

    @Test
    void estrategiaNoViablePorNeumaticosDesgastadosAntesFin() throws PorcentajeVidaNeumaticoNoValido, CantidadNeumaticosNegativo, RecorridoKmNegativo, CombustibleNegativoException {

        // Given
        Neumaticos neumaticos = new Neumaticos(MarcaNeumatico.PIRELLI, 33.0f, 4);

        // Then
        Estrategia estrategia = new Estrategia(COMBUSTIBLE, COMBUSTIBLE_CONSUMIDO_KM, neumaticos, PORCENTAJE_VIDA_NEUMATICOS_CONSUMIDO_KM, KM_RECORRIDO);

        // Then
        assertFalse(estrategia.esViable());
    }

    @Test
    void estrategiaNoViablePorCantidadNeumaticosDistintaDeCuatro() throws PorcentajeVidaNeumaticoNoValido, CantidadNeumaticosNegativo, RecorridoKmNegativo, CombustibleNegativoException {

        // Given
        Neumaticos neumaticos = new Neumaticos(MarcaNeumatico.BRIDGESTONE, 100.0f, 5);

        // Then
        Estrategia estrategia = new Estrategia(COMBUSTIBLE, COMBUSTIBLE_CONSUMIDO_KM, neumaticos, PORCENTAJE_VIDA_NEUMATICOS_CONSUMIDO_KM, KM_RECORRIDO);

        // Then
        assertFalse(estrategia.esViable());
    }

}
