/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

/**
 *
 * @author David
 */
public class Encriptador {
    private String patronBusqueda = "qwertyuiopasdfghjklzxcvbnmMNBVCXZLKJHGFDSAPOIUYTREWQ0246813579";
    private String patronEncripta = "mnbvcxzlkjhgfdsapoiuytrewq0975318642PLMOKNIJBUHVYGCTFXRDZESWAQ";
    
    public String encriptarCadena(String cadena){
        String cad="";
        for(int x=0; x<cadena.length(); x++){
            cad += encriptarCaracter(cadena.charAt(x), cadena.length(), x);
        }
        
        return cad;       
        
    }
    
    private char encriptarCaracter(char caracter, int tamañoCadena, int pocicion){
        String caracterEncriptado;
        int index;
        
        for(int x = 0; x<patronBusqueda.length();x++){
            if(patronBusqueda.charAt(x)==caracter){
                  index = (x+tamañoCadena+pocicion)%patronBusqueda.length();
                  return patronEncripta.charAt(index);
            }
        }
        return caracter;               
    }
    
    public String desEncriptarCadena(String cadena){
        String cad="";
        for(int x=0; x<cadena.length();x++){
            cad = cad+desEncriptarCaracter(cadena.charAt(x), cadena.length(), x);
        }
        return cad;
    }
    
    private char desEncriptarCaracter(char caracter, int tamañocadena, int pocision){
        
        int index=0;
        
        for(int x = 0; x<patronEncripta.length();x++){
            if(patronEncripta.charAt(x)==caracter){
                if(x>0){
                    index = (x-tamañocadena-pocision)%patronEncripta.length();
                }else{
                    index = (patronBusqueda.length()+patronEncripta.length()-tamañocadena-pocision)%patronEncripta.length();
                }
            }
        }    
        index = (index)%(patronEncripta.length());
        return patronBusqueda.charAt(index);                
    }
    
    
    
    
    
}
