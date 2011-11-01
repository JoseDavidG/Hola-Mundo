/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author DAVID
 */
public class CartaGenericas {
    
    protected String sImagen ;
    protected Color color;
    protected int numero;
    protected String nombre;
    protected TipoCartas tc;
    
    public CartaGenericas(String imagen,int n, String nombre,TipoCartas tc)
    {
        this.sImagen=imagen;
        this.numero=n;
        this.tc=tc;
        this.nombre=nombre;
        
    }
    
    
    public String getDireccion()
    {
        return  Util.pathCartas() + sImagen;
    }
    
    public String getDireccionEfec()
    {
        return  Util.pathEfectos() + sImagen;
    }
    
    public ImageIcon Image()
    {
        return (new ImageIcon (getDireccion()));
    }
    
    public ImageIcon ImageOver()
    {
        return (new ImageIcon (getDireccionEfec()));
    }
      
    
}
