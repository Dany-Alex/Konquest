/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Planeta;

import Constructor.*;
import Guerrero.*;
import Nave.*;
import java.util.Random;

/**
 *
 * @author dany
 */
public class Planeta {

    Random random = new Random();
    public String nombre;
    public double porcentajeMuerte;
    public int cantidadDinero;
    public int cantidadConstructores = 0;
    public int cantidadNaves;
    public int cantidadGerreros;
    public int propietario;
    public Constructor[] arregloConstructor=new Constructor[60];
    public Nave[] arregloNave=new Nave[60];
    public Guerrero[] arregloGuerrero = new Guerrero[60];

    public Planeta(String nombre, int cantidadDinero, int cantidadConstructores, int cantidadNaves, int cantidadGerreros) {
        this.nombre = nombre;
        this.cantidadDinero = cantidadDinero;
        this.cantidadConstructores = cantidadConstructores;
        this.cantidadNaves = cantidadNaves;
        this.cantidadGerreros = cantidadGerreros;
        this.propietario = propietario;
    }




    public Constructor crearConstructor(int tipo, int pos) {
        Constructor actual = null;
        switch (tipo) {
            case 0:

                break;
            case 1:
                actual = new Obrero("Obrero-" + pos);

                break;
            case 2:
                actual = new MaestroObra("Maesto-" + pos);
                break;
            case 3:
                actual = new Arquitecto("Arquitecto-" + pos);
                break;
            case 4:
                actual = new Ingeniero("Ingeniero-" + pos);
                break;
            default:
                System.out.println(" ");
        }
        return actual;

    }

    public Guerrero crearGuerrero(int tipo, int pos) {
        Guerrero actual = null;
        switch (tipo) {
            case 1:
                actual = new Mole("Mole-" + pos);
                return actual;

            case 2:
                actual = new Nemo("Nemo-" + pos);
                return actual;
            case 3:
                actual = new Groot("Groot-" + pos);
                return actual;
            case 4:
                actual = new FusionGuy("Fusion Guy-" + pos);
                return actual;
            default:
                System.out.println(" ");
        }
        return actual;

    }

    public void aniadirGerrero(Guerrero guerrero) {


        arregloGuerrero[cantidadGerreros] = guerrero;

        cantidadGerreros++;

    }

    public void generearGerrero(int tipo, int minimo, int maximo) {
        int cantidadCrear = random.nextInt(maximo + 1 - minimo) + minimo;
        for (int i = 0; i < cantidadCrear; i++) {

            aniadirGerrero(crearGuerrero(tipo, i));
        }

    }

    public void aniadirConstructor(Constructor constructor) {

        if (this.cantidadDinero == 0) {
            System.out.println("El dinero no es suficiente para realizar esta compra");
        } else if (cantidadDinero < constructor.precio) {
            System.out.println("El dinero no es suficiente para realizar esta compra xd");
        } else {
            arregloConstructor[cantidadConstructores] = constructor;
            cantidadDinero -= constructor.precio;
            cantidadConstructores++;
        }
    }

    public Nave crearNave(int tipo, int pos) {
        Nave actual = null;
        switch (tipo) {

            case 1:
                actual = new NabooN1("Naboo N-1 #" + pos);

                break;
            case 2:
                actual = new XWing("X-Wing #" + pos);
                break;
            case 3:
                actual = new MillenialFalcon("Millenial Falcon #" + pos);
                break;
            case 4:
                actual = new StarDestroyer("Star Destroyer #" + pos);
                break;
            default:
                System.out.println(" ");
        }
        return actual;

    }

    public void aniadirNave(Nave nave) {


        arregloNave[cantidadNaves] = nave;

        cantidadNaves++;

    }

    public void generearConstructor() {
        arregloConstructor[cantidadConstructores] = crearConstructor(1, 0);
        cantidadConstructores++;
    }

    public void generearNave(int minimo, int maximo) {

        int cantidadCrear = random.nextInt(maximo + 1 - minimo) + minimo;
        for (int i = 0; i < cantidadCrear; i++) {

            aniadirNave(crearNave(1, (i + 1)));
        }

    }

    public void generarDinero(int minimo, int maximo) {

        cantidadDinero = random.nextInt(maximo + 1 - minimo) + minimo;
        setCantidadDinero(cantidadDinero);
    }

    public void letalidad() {

        double respuesta = 0;
        int temporal = (random.nextInt(9999 + 1 - 1000) + 1000);
        respuesta = temporal / 10000.0;
        setPorcentajeMuerte(respuesta);

    }

    public String imprimirIformacionPlaneta(Planeta planeta) {
        return ("Nombre: " + planeta.nombre + ", Cantidad de Dinero: " + planeta.cantidadDinero + ", Valor de Muerte: " + planeta.porcentajeMuerte + ", Cantidad de constructores: " + planeta.cantidadConstructores + ", Cantidad de Guerreros: " + planeta.cantidadGerreros + ", Cantidad de Naves: " + planeta.cantidadNaves + " " + " //" + arregloNave.length);

    }

    public void setCantidadDinero(int cantidadDinero) {
        this.cantidadDinero = cantidadDinero;
    }

    public void setPorcentajeMuerte(double porcentajeMuerte) {
        this.porcentajeMuerte = porcentajeMuerte;
    }

    public double getPorcentajeMuerte() {
        return porcentajeMuerte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadConstructores() {
        return cantidadConstructores;
    }

    public void setCantidadConstructores(int cantidadConstructores) {
        this.cantidadConstructores = cantidadConstructores;
    }

    public int getCantidadNaves() {
        return cantidadNaves;
    }

    public void setCantidadNaves(int cantidadNaves) {
        this.cantidadNaves = cantidadNaves;
    }

    public int getCantidadGerreros() {
        return cantidadGerreros;
    }

    public void setCantidadGerreros(int cantidadGerreros) {
        this.cantidadGerreros = cantidadGerreros;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int getPropietario() {
        return propietario;
    }

    public void setPropietario(int propietario) {
        this.propietario = propietario;
    }

    public Constructor[] getArregloConstructor() {
        return arregloConstructor;
    }

    public void setArregloConstructor(Constructor[] arregloConstructor) {
        this.arregloConstructor = arregloConstructor;
    }

    public Nave[] getArregloNave() {
        return arregloNave;
    }

    public void setArregloNave(Nave[] arregloNave) {
        this.arregloNave = arregloNave;
    }

    @Override
    public String toString() {
        return "Planeta{" + "random=" + random + ", nombre=" + nombre + ", porcentajeMuerte=" + porcentajeMuerte + ", cantidadDinero=" + cantidadDinero + ", cantidadConstructores=" + cantidadConstructores + ", cantidadNaves=" + cantidadNaves + ", cantidadGerreros=" + cantidadGerreros + ", propietario=" + propietario + " //" + arregloNave.length + '}';
    }

}
