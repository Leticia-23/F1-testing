package com.hiberus.f1testing;

import com.hiberus.f1testing.exceptions.CombustibleNegativoException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CombustibleTest {
    private final TipoCombustible TIPO_COMBUSTIBLE = TipoCombustible.GASOLINA;
    private final float LITROS_COMBUSTIBLE = 145.0f;

    @BeforeAll
    static void beforeAll() {
        System.out.println("TESTS COMBUSTIBLE");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("FIN TESTS COMBUSTIBLE");
    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        System.out.println("Terminado el test: " + testInfo.getDisplayName());
    }

    @Test
    void noInstanciarCombustibleSiLitrosCombustibleNegativo() {

        // Given
        int litrosCombustible = -1;

        // When & Then
        assertThrows(CombustibleNegativoException.class, () -> new Combustible(TIPO_COMBUSTIBLE,litrosCombustible));
    }

    @ParameterizedTest
    @CsvSource({
            "DIESEL",
            "GASOLINA"
    })
    void instanciarCombustibleConTipoCombustibleYObtenerTipoCombustible(String tipo) throws CombustibleNegativoException {

        // Given
        TipoCombustible tipoCombustible = TipoCombustible.valueOf(tipo);

        // When
        Combustible combustible = new Combustible(tipoCombustible, LITROS_COMBUSTIBLE);

        // Then
        assertEquals(tipoCombustible, combustible.getTipoCombustible());
    }

    @Test
    void instanciarCombustibleConLitrosCombustibleYObtenerLitrosCombustible() throws CombustibleNegativoException {

        //Given & When
        Combustible combustible = new Combustible(TIPO_COMBUSTIBLE, LITROS_COMBUSTIBLE);

        //Then
        assertEquals(LITROS_COMBUSTIBLE, combustible.getLitrosCombustible());
    }
}
