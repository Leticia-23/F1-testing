package com.hiberus.f1testing;

import com.hiberus.f1testing.exceptions.CantidadNeumaticosNegativo;
import com.hiberus.f1testing.exceptions.CombustibleNegativoException;
import com.hiberus.f1testing.exceptions.PorcentajeVidaNeumaticoNoValido;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NeumaticosTest {

    private final MarcaNeumatico MARCAS_NEUMATICOS = MarcaNeumatico.PIRELLI;
    private final float PORCENTAJE_VIDA = 99.0f;
    private final int CANTIDAD_NEUMATICOS = 4;

    @BeforeAll
    static void beforeAll() {
        System.out.println("TESTS NEUMATICOS");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("FIN TESTS NEUMATICOS");
    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        System.out.println("Terminado el test: " + testInfo.getDisplayName());
    }


    @Test
    void noInstanciarNeumaticosSiCantidadNeumaticosNegativo() {

        // Given
        int cantidadNeumaticos = -1;

        // When & Then
        assertThrows(CantidadNeumaticosNegativo.class, () -> new Neumaticos(MARCAS_NEUMATICOS,PORCENTAJE_VIDA,cantidadNeumaticos));
    }

    @Test
    void noInstanciarNeumaticosSiPorcentajeVidaMenorACero() {

        // Given
        float porcentajeVida = -0.1f;

        // When & Then
        assertThrows(PorcentajeVidaNeumaticoNoValido.class, () -> new Neumaticos(MARCAS_NEUMATICOS,porcentajeVida,CANTIDAD_NEUMATICOS));
    }

    @Test
    void noInstanciarNeumaticosSiPorcentajeVidaMayorACien() {

        // Given
        float porcentajeVida = 100.1f;

        // When & Then
        assertThrows(PorcentajeVidaNeumaticoNoValido.class, () -> new Neumaticos(MARCAS_NEUMATICOS,porcentajeVida,CANTIDAD_NEUMATICOS));
    }


    @ParameterizedTest
    @CsvSource({
            "PIRELLI",
            "BRIDGESTONE"
    })
    void instanciarNeumaticosConMarcaNeumaticoYObtenerMarcaNeumatico(String tipo) throws CombustibleNegativoException, PorcentajeVidaNeumaticoNoValido, CantidadNeumaticosNegativo {

        // Given
        MarcaNeumatico marcaNeumatico = MarcaNeumatico.valueOf(tipo);

        // When
        Neumaticos neumaticos = new Neumaticos(marcaNeumatico, PORCENTAJE_VIDA, CANTIDAD_NEUMATICOS);

        // Then
        assertEquals(marcaNeumatico, neumaticos.getMarca());
    }

    @Test
    void instanciarNeumaticosConPorcentajeVidaYObtenerPorcentajeVida() throws PorcentajeVidaNeumaticoNoValido, CantidadNeumaticosNegativo {

        // Given & When
        Neumaticos neumaticos = new Neumaticos(MARCAS_NEUMATICOS, PORCENTAJE_VIDA, CANTIDAD_NEUMATICOS);

        // Then
        assertEquals(PORCENTAJE_VIDA, neumaticos.getPorcentajeVida());
    }

    @Test
    void instanciarNeumaticosConCantidadNeumaticosYObtenerCantidadNeumaticos() throws PorcentajeVidaNeumaticoNoValido, CantidadNeumaticosNegativo {

        // Given & When
        Neumaticos neumaticos = new Neumaticos(MARCAS_NEUMATICOS, PORCENTAJE_VIDA, CANTIDAD_NEUMATICOS);

        // Then
        assertEquals(CANTIDAD_NEUMATICOS, neumaticos.getCantidadNeumaticos());
    }
}
