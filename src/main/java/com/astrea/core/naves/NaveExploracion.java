package com.astrea.core.naves;
import com.astrea.core.base.NaveEspacial;
import com.astrea.core.interfaces.Propulsable;
import com.astrea.core.exceptions.AstreaException;
import com.astrea.core.exceptions.CombustibleInsuficienteException;
import com.astrea.core.exceptions.FallaSistemasException;

public class NaveExploracion extends NaveEspacial implements Propulsable {
    private double integridadEscudo;
    private boolean hiperviajeListo;

    public NaveExploracion(String matricula, String modelo, double combustibleInicial, double capacidadCombustible) throws AstreaException {
        super(matricula, modelo, combustibleInicial, capacidadCombustible);
        this.integridadEscudo = 0.0;
        this.hiperviajeListo = false;
    }

    public double getIntegridadEscudo() {
        return integridadEscudo;
    }

    public boolean isHiperviajeListo() {
        return hiperviajeListo;
    }

    @Override
    public void viajar(double distanciaAniosLuz) throws CombustibleInsuficienteException {
        double consumo = 0.8 * distanciaAniosLuz;
        if (consumo > combustible) {
            throw new CombustibleInsuficienteException(
                    "Combustible insuficiente para el viaje: se requieren " + consumo + " y solo hay " + combustible + ".");
        }
        combustible -= consumo;
    }

    @Override
    public void activarHiperviaje(double factorWarp) throws FallaSistemasException, CombustibleInsuficienteException {
        double consumo = capacidadCombustible / 6.0;
        if (consumo > combustible) {
            throw new CombustibleInsuficienteException(
                    "Combustible insuficiente para el hiperviaje: se requieren " + consumo + " y solo hay " + combustible + ".");
        }
        combustible -= consumo;

        if (factorWarp > 9.0 && Math.random() < 0.30) {
            hiperviajeListo = false;
            throw new FallaSistemasException("Falla de sistemas durante el hiperviaje.");
        }

        hiperviajeListo = true;
    }
}