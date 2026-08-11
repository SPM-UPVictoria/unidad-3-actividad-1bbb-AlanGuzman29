package com.astrea.core.base;

import com.astrea.core.exceptions.AstreaException;
import com.astrea.core.exceptions.CombustibleInsuficienteException;

public abstract class NaveEspacial {
    protected String matricula;
    protected String modelo;
    protected double combustible;
    protected double capacidadCombustible;

    public NaveEspacial(String matricula, String modelo, double combustibleInicial, double capacidadCombustible) throws AstreaException {
        if (capacidadCombustible < 0) {
            throw new AstreaException("La capacidad de combustible no puede ser negativa.");
        }
        if (combustibleInicial < 0) {
            throw new AstreaException("El combustible inicial no puede ser negativo.");
        }
        if (combustibleInicial > capacidadCombustible) {
            throw new AstreaException("El combustible inicial no puede exceder la capacidad de combustible.");
        }

        this.matricula = matricula;
        this.modelo = modelo;
        this.combustible = combustibleInicial;
        this.capacidadCombustible = capacidadCombustible;
    }

    public void repostarCombustible(double cantidad) throws AstreaException {
        if (cantidad < 0) {
            throw new AstreaException("La cantidad a repostar no puede ser negativa.");
        }
        if (combustible + cantidad > capacidadCombustible) {
            throw new AstreaException("El repostaje excede la capacidad de combustible de la nave.");
        }
        combustible += cantidad;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public double getCombustible() {
        return combustible;
    }

    public double getCapacidadCombustible() {
        return capacidadCombustible;
    }

    public abstract void viajar(double distanciaAniosLuz) throws CombustibleInsuficienteException, AstreaException;
}