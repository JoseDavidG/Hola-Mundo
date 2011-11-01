/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;



import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

public  class Util {
  //  public TipoCartas tc;
    public static org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(uno.UnoApp.class).getContext().getResourceMap(UnoView.class);
    
    public static  ArrayList<Cartas> cartas;
    public static  ArrayList<Cartas> cartasJugadas;
    public static int turno =0;
    
    public static final int numJugadores=1;
    public static final int numCartas=11;
    public static final int countCartas=12;
    public static final int maxNumCartas=9;
    public static Jugador jugadores[];
    public static int CartaActual=0;
    public static Cartas UltimaCarta;
    public static boolean JuegoConcluido =false;
    
    private static int _cartasTotales =0;
    
    static
    {
         init();
    }
    
    public static void init()
    {
         cartas =new ArrayList<Cartas>();
         cartasJugadas=new  ArrayList<Cartas>();
         jugadores=new Jugador[ numJugadores+1];
         
         for (int i=0;i<  jugadores.length ;i++)
         {
             jugadores[i]=new Jugador("",'H',"");
         
         }
         
         GenerarCartas();
         UltimaCarta= new Cartas("inicio.jpg", -1, "inicio", TipoCartas.Inicio); 
         JuegoConcluido=false;
    }
    
    public static int cartasDisponibles()
    {
     return cartas.size();   
    }
    
    public static int cartasJugadas()
    {
     return cartasJugadas.size();   
    }
       
    public static int cartasTotales()
    {
     return _cartasTotales;   
    }
    
    public Util()
    {
       
        GenerarCartas();
    }
     public static void RemoverCarta(Cartas carta)
             
    {
        cartas.remove(carta);
    }
    
    public static void RemoverCarta(int i)
    {
        cartas.remove(cartas.get(i));
    }
     
    private static ArrayList<String> cNormales()
    {
    
        ArrayList<String> c = new ArrayList<String>();
        c.add("Rojo");
        c.add("Verde");
        c.add("Azul");
        c.add("Amarillo");
        c.add("AmarilloR");
        c.add("AmarilloS");
        c.add("AmarilloT");
        c.add("AzulR");
        c.add("AzulS");
        c.add("AzulT");
        c.add("RojoR");
        c.add("RojoS");
        c.add("RojoT");
        c.add("VerdeR");
        c.add("VerdeS");
        c.add("VerdeT");
        Collections.shuffle(c);
        return c;
                
    }
    
    private static ArrayList<String> cEspeciales()
    {
    
        ArrayList<String> c = new ArrayList<String>();
       
        c.add("Rojo0");
        c.add("Verde0");
        c.add("Azul0");
        c.add("Amarillo0");
        c.add("Comodin");
        c.add("Tome4");
        
        Collections.shuffle(c);
        return c;
                
    }
            
    public static void GenerarCartas()
    {
        cartas.removeAll(cartas);
        
        ArrayList<String> color =cNormales();
        Collections.shuffle(color);       
        
        for(String col : color)
        {
           int num =1;
           TipoCartas t;
           boolean cartaEspecial=true;
         
           if (col.endsWith("R")){t =TipoCartas.Reversa; }
           else if (col.endsWith("S")){t =TipoCartas.Skin; }
           else if (col.endsWith("T")){t =TipoCartas.T2; }
           else if (col.endsWith("0")){t =TipoCartas.Bloqueo ; }
           else {t =TipoCartas.Normal; num = maxNumCartas; cartaEspecial=false; }

          //ciclo 1->1 o 1->9 dependiendo si es normal o especial
           for(int x=1; x<= num ; x++)
            {
                //Ciclo para generar dos cartas
                for(int j=0;j< 2;j++)
                {
                    Cartas c;
                    if (cartaEspecial){
                        //AmarilloS.jpg,Amarillo0.jpg
                        c = new Cartas( col + (col.endsWith("0")?"0":"")+  ".jpg", x, col+("_" + (j+1)),t);}
                    else{
                        //Amarrillo1.jpg
                        c = new Cartas(col + String.valueOf(x) + ".jpg", x,col +String.valueOf(x)+("_" + (j+1)),t);}
                    
                    cartas.add(c);
                }
             }
        }
        
       barajear();
         
        ArrayList<String> esp =cEspeciales();
        Collections.shuffle(esp);
        
         for(String e : esp)
            {   int num=4;
                TipoCartas t;
                
                if (e.endsWith("0")) { num =1 ; t= TipoCartas.Bloqueo; }
                else if (e.endsWith("4")) {t= TipoCartas.Bloqueo; }
                else {t= TipoCartas.Comodin; }
                
                for(int j=0;j< num;j++)
                {
                    Cartas c = new Cartas(e+".jpg", -1,e+("_" + (j+1)), t );
                    cartas.add(c);
                }
            }
         
        barajear();
        
         _cartasTotales =cartas.size();
         
      }
    
    public static void barajear()
    {
        Collections.shuffle(cartas);
        Collections.shuffle(cartas);
        Collections.shuffle(cartas);
        Collections.shuffle(cartas);
        
    }
    
   
    public static Cartas  getCarta(String n)
    {
     
       for (Cartas c : jugadores[turno].cartas)
        {
            if (c.nombre.equalsIgnoreCase(n))
            {
                return c;
            }
        }
        return null;
    }
    
 
    
    public static int getTurnoActual()
      {
          return (turno+1);
      }
    
    public static String path()
    {
        return System.getProperty("user.dir"); 
    }
    
     public static String pathSonidos()
    {
        return System.getProperty("user.dir")+ "\\sonidos\\";
    }
     
      public static String pathCartas()
    {
        return System.getProperty("user.dir")+ "\\cartas\\"; 
    }
      
      public static String pathEfectos()
    {
        return System.getProperty("user.dir")+ "\\efectos\\"; 
    }
      
      public static Border getBorder()
      {
           
          return javax.swing.BorderFactory.createLineBorder(resourceMap.getColor("jLabel1.border.lineColor"));
          
      }
      
      public static ImageIcon ImageReversa()
      {
          return  new ImageIcon(pathEfectos()+"Reversa.jpg");
      }
      
      public static ImageIcon ImageReversaBlock()
      {
          return  new ImageIcon(pathEfectos()+"ReversaBloc.jpg");
      }
      
      public static ImageIcon ImageReversaOK()
      {
          return  new ImageIcon(pathEfectos()+"ReversaOK.jpg");
      }
      
}
