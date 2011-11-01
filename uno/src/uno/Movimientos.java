/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.awt.Component;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Movimientos {
    
      
    public static boolean TurnoValido(MouseEvent e)
    {
       return ((JLabel)(e.getSource())).getParent().getName().endsWith(String.valueOf(Util.getTurnoActual()));
    }
    
    
    public static void cambiarTurno()
    {
        if (Util.turno ==Util.numJugadores )
            {
                Util.turno =0;     
            }
        else    
            {
                Util.turno =+1;
            }
    }
    
    public static void QuitarCarta(MouseEvent e)
    {
      //eliminar imagen el panel de cartas
      ((JLabel)e.getSource()).setVisible(false);
      ((JLabel)e.getSource()).getParent().remove((JLabel)e.getSource());   
    }
    
    public static void CambiarCartas(MouseEvent e)
    {
     //volvear las cartas de los jugadores
     for (int i=0;i<=Util.numJugadores;i++)
     {
        Component co=getComponent(((JLabel)e.getSource()).getParent().getParent().getComponents(), "jPanel"+ (i+1));
        
        if (co ==null){continue;}
        
        for(Component l : ((JPanel) (co)).getComponents())
           {
             //ocultar cartas para el jugador contrario
             if ((i ==Util.turno))
             {
                //mostrar las cartas para el turno del jugador
                 String img = Util.pathCartas()+ ((JLabel) l).getName().split("_")[0];
                 ((JLabel) l).setIcon(new ImageIcon(img+".jpg"));
             }
             else
             {
                ((JLabel) l).setIcon(Util.ImageReversa());      
             }
                            
               }
           }
     }
    
   public static void CambiarCartas(JPanel main)
    {
     //volvear las cartas de los jugadores
     for (int i=0;i<=Util.numJugadores;i++)
     {
        Component co=getComponent(main.getComponents(), "jPanel"+ (i+1));
        
        if (co ==null){continue;}
        
        for(Component l : ((JPanel) (co)).getComponents())
           {
             //ocultar cartas para el jugador contrario
             if ((i ==Util.turno))
             {
                //mostrar las cartas para el turno del jugador
                 String img = Util.pathCartas()+ ((JLabel) l).getName().split("_")[0];
                 ((JLabel) l).setIcon(new ImageIcon(img+".jpg"));
             }
             else
             {
                ((JLabel) l).setIcon(Util.ImageReversa());      
             }
                            
               }
           }
     }
       
    private static Component getComponent(Component comps[],String Control)
    {
        for (Component c : comps)
        {
            if (Control.equals(c.getName()))
            {
                return c;
            }
        }
        
        return null;
    }
       
    
}
