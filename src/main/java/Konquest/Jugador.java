/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Konquest;

import Constructor.Constructor;
import Nave.Nave;
import Planeta.Planeta;

/**
 *
 * @author dany
 */


public class Jugador {
  public String nombre;  
  public int turno;
  public int id;
  public Planeta[] planeta;

  

    public Jugador(int id, String nombre, int turno, Planeta[] planeta) {
        this.nombre = nombre;
        this.turno = turno;
        this.id = id;
        this.planeta = planeta;
    }

 

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Planeta[] getPlaneta() {
        return planeta;
    }

    public void setPlaneta(Planeta[] planeta) {
        this.planeta = planeta;
    }

 
    
    

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", turno=" + turno + ", id=" + id + ", planeta=" + planeta[0] + '}';
    }
  
  
}
