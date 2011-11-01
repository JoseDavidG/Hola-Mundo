/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.ArrayList;

public class Jugador {
    
    public String nombre;
    public char sexo;
    public int puntos;
    public String foto;
    //public Cartas cartas[];
    public ArrayList<Cartas> cartas;
    public ArrayList<CartasEsp> cartasesp;
    
    public Jugador(String n, char s,String f)
    {
        nombre=n;
        sexo=s;
        puntos=0;
        foto=f;
        cartas= new ArrayList<Cartas>();
        cartasesp =new ArrayList<CartasEsp>();
    }
    
    
}
