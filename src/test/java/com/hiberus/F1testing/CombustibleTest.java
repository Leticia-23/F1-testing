package com.hiberus.F1testing;

import com.hiberus.F1testing.exceptions.CombustibleNegativoException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CombustibleTest {

    private final TipoCombustible TIPO_COMBUSTIBLE = TipoCombustible.GASOLINA;
    private final float LITROS_COMBUSTIBLE = 145.0f;

    @BeforeAll
    static void beforeAll() {
        System.out.println("TEST COMBUSTIBLE");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("FIN TEST COMBUSTIBLE");
    }


    @Test
    void combustibleNoInstanciadoSiLitrosCombustibleNegativo() {
        // Given
        int litrosCombustible = -1;
        TipoCombustible tipoCombustible = TipoCombustible.GASOLINA;

        // When & Then
        assertThrows(CombustibleNegativoException.class, () -> new Combustible(TIPO_COMBUSTIBLE,litrosCombustible));
    }

    @ParameterizedTest
    @CsvSource({
            "DIESEL",
            "GASOLINA"
    })
    void instanciarCombustibleConTipoCombustibleYObtienerTipoCombustible(String tipo) throws CombustibleNegativoException {

        // Given
        TipoCombustible tipoCombustible = TipoCombustible.valueOf(tipo);

        // When
        Combustible combustible = new Combustible(tipoCombustible, LITROS_COMBUSTIBLE);

        // Then
        assertEquals(tipoCombustible, combustible.getTipoCombustible());
    }

}
