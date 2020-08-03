/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constructor;

/**
 *
 * @author dany
 */
public class Constructor {
    public String nombre;
    public int precio;
    public int venta;
    public int tiempoConstruir;
    public String tipoNave;
    public int contadorTurno;
    public boolean ocupado=false;

    public Constructor(String nombre, int precio, int venta, int tiempoConstruir, String tipoNave) {
        this.nombre = nombre;
        this.precio = precio;
        this.venta = venta;
        this.tiempoConstruir = tiempoConstruir;
        this.tipoNave = tipoNave;
    }



    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getVenta() {
        return venta;
    }

    public void setVenta(int venta) {
        this.venta = venta;
    }

    public int getTiempoConstruir() {
        return tiempoConstruir;
    }

    public void setTiempoConstruir(int tiempoConstruir) {
        this.tiempoConstruir = tiempoConstruir;
    }

    public String getTipoNave() {
        return tipoNave;
    }

    public void setTipoNave(String tipoNave) {
        this.tipoNave = tipoNave;
    }

    @Override
    public String toString() {
        return "Constructor{" + "nombre=" + nombre + ", precio=" + precio + ", venta=" + venta + ", tiempoConstruir=" + tiempoConstruir + ", tipoNave=" + tipoNave + '}';
    }


    
}
