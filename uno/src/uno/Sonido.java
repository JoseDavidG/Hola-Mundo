/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

/**
 *
 * @author DAVID
 */

import javax.sound.sampled.*; 
import java.io.*; 

public class Sonido{ 

   public Sonido(){ 
   
   } 
   
   public static void Over()
   {
      File sf=new File(Util.pathSonidos()+ "over.wav"); 
      AudioFileFormat aff; 
      AudioInputStream ais; 

      try{ 
         aff=AudioSystem.getAudioFileFormat(sf); 
         ais=AudioSystem.getAudioInputStream(sf); 
         AudioFormat af=aff.getFormat(); 
         DataLine.Info info = new DataLine.Info( 
                        Clip.class, 
                        ais.getFormat(), 
                        ((int) ais.getFrameLength() * 
                        af.getFrameSize())); 
         Clip ol = (Clip) AudioSystem.getLine(info); 
         ol.open(ais); 
        ol.loop(0);
      } 
      catch(UnsupportedAudioFileException ee){} 
      catch(IOException ea){} 
      catch(LineUnavailableException LUE){}; 
   }


} 