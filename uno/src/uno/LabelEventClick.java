
package uno;

import java.awt.Component;

import java.awt.event.ActionEvent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 *
 * @author DAVID
 */
public class LabelEventClick implements MouseListener  {

    public void actionPerformed(ActionEvent e)
	{
            
          //  JOptionPane.showMessageDialog(null,"hola mundo actionPerformed");
	}


    public void mouseClicked(MouseEvent e) {
               
        try
        {
            
        if (!Movimientos.TurnoValido(e) )
        {
            //JOptionPane.showMessageDialog(null, "ESPERA TU TURNO!!");
            return;
        }
        
        if (Util.JuegoConcluido)
        {return;}
        
        //Obtener el JLabel que representa el conjunto de cartas jugadas            
        Component c =getComponent(e , "ListCartasJugadas");
        
        if (c==null){return;}
        
        
                String Name = ((JLabel)e.getSource()).getName() ;
                JPanel Parent = ((JPanel)   ((JLabel)e.getSource()).getParent());
                
                String parentName =Parent.getName();
                
                //obtener la carta segun la imagen presionada
                Cartas card =Util.getCarta(Name);
               
                if (card ==null)
                {                   
                   JOptionPane.showMessageDialog(null, "Carta invalida - " +((JLabel)e.getSource()).getName()+ " - padre :" + parentName );
                   return;
                }              
               
                 Component cpanel =getComponent(e,"jPanelInfo");
                 JPanel panel =((JPanel)cpanel);
                 
                 
                 
                //Agregar la nueva carta a cartas jugadas
                Util.cartasJugadas.add(card);
                
                //Quitarle la carta jugador de turno actual
                Util.jugadores[Util.turno].cartas.remove(card);
                
                               
               
                
                //Cambiar la imagen de la cartas jugadas por la imagen de la carta seleccinada
                ((JLabel)c).setIcon(card.Image());
                
                int count =Util.jugadores[Util.turno].cartas.size();
                
                Util.UltimaCarta = card;
                
                 //Cambiar el turno del jugador   
                
                int turno_ejecutado =Util.getTurnoActual();
                Movimientos.cambiarTurno();
                
                //Actualizar etiquetas de informacion
                c = getComponent(panel.getComponents(), "jdisponibles");
                ((JLabel)c).setText("Cartas Disponibles:"+Util.cartasDisponibles() );
                
                c = getComponent(panel.getComponents(), "jcartasJugadas");
                ((JLabel)c).setText("Cartas Jugadas:"+Util.cartasJugadas());
                
                c = getComponent(panel.getComponents(), "JLturno");
                ((JLabel)c).setText("Turno:"+Util.getTurnoActual());
                
                //Ocultar las cartas de los adversarios
                Movimientos.CambiarCartas(e);
                
                //eliminar imagen el panel de cartas
                Movimientos.QuitarCarta(e);
                
                
          
            
                
                if (count ==0)
                {
                    Util.JuegoConcluido=true;
                    JOptionPane.showMessageDialog(null, "Felicidades el jugador con el turno " + turno_ejecutado + " gana !!" );
                }
                else if (count ==1)
                {
                    Component b = getComponent(panel.getComponents(), "jbUno");
                    ((JButton) b).setVisible(true);
                    
                }
                
        }
        
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void mousePressed(MouseEvent e) {
            
    }

    public void mouseReleased(MouseEvent e) {
     
    }

    public void mouseEntered(MouseEvent e) {
       
          //Se envia a un proceso para que se ejecute en el fondo
           pThread p= new pThread();
           p.start();
           
                   
            if (Movimientos.TurnoValido(e) ){
                
              String dir = Util.pathEfectos() +((JLabel)e.getSource()).getName().split("_")[0];            
              ((JLabel)e.getSource()).setIcon(new ImageIcon(dir + ".jpg"));
            
            }
            else
            {
                ((JLabel)e.getSource()).setIcon(Util.ImageReversaBlock());
            }
               
//                
//              if (((JLabel)e.getSource()).getParent().getCursor().equals(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR )))
//              {
//                  ((JLabel)e.getSource()).getParent().setCursor (Cursor.getPredefinedCursor(Cursor.HAND_CURSOR ));
//                  Toolkit toolkit = Toolkit.getDefaultToolkit();
//        	  Image image = toolkit.getImage("icons/cursorSmall.png");
//        	  Cursor c = toolkit.createCustomCursor(image , new Point(((JLabel)e.getSource()).getParent().getX(),((JLabel)e.getSource()).getParent().getY()), "img");
//        	 ((JLabel)e.getSource()).getParent().setCursor (c);
//              }
    }
                
    public void mouseExited(MouseEvent e) {
        
         if (Movimientos.TurnoValido(e) ){
            String n = Util.pathCartas()+((JLabel)e.getSource()).getName().split("_")[0];
           ((JLabel)e.getSource()).setIcon(new ImageIcon(n+".jpg"));
   
        }
         else
         {
              ((JLabel)e.getSource()).setIcon(Util.ImageReversa());
         }
        
         

    }   

    
    private Component getComponent(MouseEvent e,String Control)
    {
        for (Component c : ((JLabel)e.getSource()).getParent().getParent().getComponents() )
        {
            if (Control.equals(c.getName()))
            {
                return c;
            }
        }
        
        return null;
    }
    
    private Component getComponent(Component comps[],String Control)
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
