package com.hiberus.f1testing;

import com.hiberus.f1testing.exceptions.CantidadNeumaticosNegativo;
import com.hiberus.f1testing.exceptions.CombustibleNegativoException;
import com.hiberus.f1testing.exceptions.PorcentajeVidaNeumaticoNoValido;
import com.hiberus.f1testing.exceptions.RecorridoKmNegativo;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class EstrategiaTest {

    private final float LITROS_COMBUSTIBLE_INICIAL = 145.0f;
    private final Combustible COMBUSTIBLE = new Combustible(TipoCombustible.DIESEL, LITROS_COMBUSTIBLE_INICIAL);
    private final float COMBUSTIBLE_CONSUMIDO_KM = 0.33f;

    private final float PORCENTAJE_VIDA_INICIAL = 99.9f;

    private final int  CANTIDAD_NEUMATICOS = 4;
    private final Neumaticos NEUMATICOS = new Neumaticos(MarcaNeumatico.BRIDGESTONE, PORCENTAJE_VIDA_INICIAL, CANTIDAD_NEUMATICOS);
    private final float PORCENTAJE_VIDA_NEUMATICOS_CONSUMIDO_KM = 0.1f;
    private final float KM_RECORRIDO = 330.0f;

    public EstrategiaTest() throws CombustibleNegativoException, PorcentajeVidaNeumaticoNoValido, CantidadNeumaticosNegativo {
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("TESTS ESTRATEGIA");}

    @AfterAll
    static void afterAll() {
        System.out.println("FIN TESTS ESTRATEGIA");
    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        System.out.println("Terminado el test: " + testInfo.getDisplayName());
    }

    @Test
    void noInstanciarEstrategiaSiLitrosCombustibleConsumidoNegativo() {

        // Given
        int combustibleConsumidoPorKmRecorrido = -1;

        // When & Then
        assertThrows(CombustibleNegativoException.class, () -> new Estrategia(COMBUSTIBLE, combustibleConsumidoPorKmRecorrido, NEUMATICOS, PORCENTAJE_VIDA_NEUMATICOS_CONSUMIDO_KM, KM_RECORRIDO));
    }

    @Test
    void noInstanciarEstrategiaSiPorcentajeVidaConsumidoMenorACero() {

        //Given
        float porcentajeVidaNeumaticosConsumidoKm = -0.1f;

        // When & Then
        assertThrows(PorcentajeVidaNeumaticoNoValido.class, () -> new Estrategia(COMBUSTIBLE, COMBUSTIBLE_CONSUMIDO_KM, NEUMATICOS, porcentajeVidaNeumaticosConsumidoKm, KM_RECORRIDO));
    }

    @Test
    void noInstanciarEstrategiaSiPorcentajeVidaConsumidoMayorACien() {

        //Given
        float porcentajeVidaNeumaticosConsumidoKm = 100.1f;

        // When & Then
        assertThrows(PorcentajeVidaNeumaticoNoValido.class, () -> new Estrategia(COMBUSTIBLE, COMBUSTIBLE_CONSUMIDO_KM, NEUMATICOS, porcentajeVidaNeumaticosConsumidoKm, KM_RECORRIDO));
    }

    @Test
    void noInstanciarEstrategiaSiRecorridoNegativo() {

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
        Combustible combustible = new Combustible(TipoCombustible.GASOLINA, 108.9f);

        // Then
        Estrategia estrategia = new Estrategia(combustible, COMBUSTIBLE_CONSUMIDO_KM, NEUMATICOS, PORCENTAJE_VIDA_NEUMATICOS_CONSUMIDO_KM, KM_RECORRIDO);

        // Then
        assertFalse(estrategia.esViable());
    }

    @Test
    void estrategiaNoViablePorNeumaticosDesgastadosAntesFin() throws PorcentajeVidaNeumaticoNoValido, CantidadNeumaticosNegativo, RecorridoKmNegativo, CombustibleNegativoException {

        // Given
        Neumaticos neumaticos = new Neumaticos(MarcaNeumatico.PIRELLI, 33.0f, CANTIDAD_NEUMATICOS);

        // Then
        Estrategia estrategia = new Estrategia(COMBUSTIBLE, COMBUSTIBLE_CONSUMIDO_KM, neumaticos, PORCENTAJE_VIDA_NEUMATICOS_CONSUMIDO_KM, KM_RECORRIDO);

        // Then
        assertFalse(estrategia.esViable());
    }

    @Test
    void estrategiaNoViablePorCantidadNeumaticosDistintaDeCuatro() throws PorcentajeVidaNeumaticoNoValido, CantidadNeumaticosNegativo, RecorridoKmNegativo, CombustibleNegativoException {

        // Given
        Neumaticos neumaticos = new Neumaticos(MarcaNeumatico.BRIDGESTONE, PORCENTAJE_VIDA_INICIAL, 5);

        // Then
        Estrategia estrategia = new Estrategia(COMBUSTIBLE, COMBUSTIBLE_CONSUMIDO_KM, neumaticos, PORCENTAJE_VIDA_NEUMATICOS_CONSUMIDO_KM, KM_RECORRIDO);

        // Then
        assertFalse(estrategia.esViable());
    }

}
