/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nave;

import Guerrero.Guerrero;
import Planeta.*;
import Konquest.Partida;
/**
 *
 * @author dany
 */
public class Nave {
    public String nombre;
    public int espacioDisponible;
    public int precio;
    public double velocidad;
    public int contadorTurno;
    public boolean ocupado=false;
    public String nombrePlanetaOrigen;
    public String nombrePlanetaDestino;
    public Guerrero[] gerreros = new Guerrero[espacioDisponible];
    

    public Nave(String nombre, int espacioDisponible, int precio, double velocidad) {
        this.nombre = nombre;
        this.espacioDisponible = espacioDisponible;
        this.precio = precio;
        this.velocidad = velocidad;
    }

    public void setContadorTurno(int contadorTurno) {
        this.contadorTurno = contadorTurno;
    }

    public int getContadorTurno() {
        return contadorTurno;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public String getNombrePlanetaOrigen() {
        return nombrePlanetaOrigen;
    }

    public void setNombrePlanetaOrigen(String nombrePlanetaOrigen) {
        this.nombrePlanetaOrigen = nombrePlanetaOrigen;
    }

    public String getNombrePlanetaDestino() {
        return nombrePlanetaDestino;
    }

    public void setNombrePlanetaDestino(String nombrePlanetaDestino) {
        this.nombrePlanetaDestino = nombrePlanetaDestino;
    }
    

    @Override
    public String toString() {
        return "Nave{" + "nombre=" + nombre + ", espacioDisponible=" + espacioDisponible + ", precio=" + precio + ", velocidad=" + velocidad + ", contadorTurno=" + contadorTurno + ", ocupado=" + ocupado + ", gerreros a bordo=" + gerreros.length +", origen: "+nombrePlanetaOrigen+", destino"+nombrePlanetaDestino+ '}';
    }


}
