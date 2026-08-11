package com.astrea.core.naves;

import com.astrea.core.base.NaveEspacial;
import com.astrea.core.exceptions.AstreaException;
import com.astrea.core.exceptions.CombustibleInsuficienteException;

public class NaveCarga extends NaveEspacial {
    private double cargaActual;
    private double cargaMaxima;

    public NaveCarga(String matricula, String modelo, double combustibleInicial, double capacidadCombustible, double cargaMaxima) throws AstreaException {
        super(matricula, modelo, combustibleInicial, capacidadCombustible);
        if (cargaMaxima < 0) {
            throw new AstreaException("La carga máxima no puede ser negativa.");
        }
        this.cargaMaxima = cargaMaxima;
        this.cargaActual = 0.0;
    }

    public void cargar(double cantidad) throws AstreaException {
        if (cantidad < 0) {
            throw new AstreaException("La cantidad a cargar no puede ser negativa.");
        }
        if (cargaActual + cantidad > cargaMaxima) {
            throw new AstreaException("La carga excede la capacidad máxima de la nave.");
        }
        cargaActual += cantidad;
    }

    public double getCargaActual() {
        return cargaActual;
    }

    public double getCargaMaxima() {
        return cargaMaxima;
    }

    @Override
    public void viajar(double distanciaAniosLuz) throws CombustibleInsuficienteException {
        boolean cargaPesada = cargaActual > cargaMaxima * 0.5;
        double factor = cargaPesada ? 3.0 : 1.5;
        double consumo = factor * distanciaAniosLuz;

        if (consumo > combustible) {
            throw new CombustibleInsuficienteException(
                    "Combustible insuficiente para el viaje: se requieren " + consumo + " y solo hay " + combustible + ".");
        }
        combustible -= consumo;
    }
}