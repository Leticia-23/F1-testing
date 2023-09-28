package com.hiberus.F1testing;

import com.hiberus.F1testing.exceptions.CombustibleNegativoException;

public class Combustible {

    private final TipoCombustible tipoCombustible;

    private final float litrosCombustible;

    public Combustible(TipoCombustible tipoCombustible, float litrosCombustible) throws CombustibleNegativoException {

        if (litrosCombustible < 0) {
            throw new CombustibleNegativoException();
        }

        this.litrosCombustible = litrosCombustible;
        this.tipoCombustible = tipoCombustible;
    }

    public TipoCombustible getTipoCombustible() {
        return tipoCombustible;
    }
}
