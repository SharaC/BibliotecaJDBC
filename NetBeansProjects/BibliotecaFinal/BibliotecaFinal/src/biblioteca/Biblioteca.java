
package biblioteca;
import java.util.Scanner;

import biblioteca.Informacion;
import biblioteca.Prestamo;
public class Biblioteca {

    
    public static void main(String[] args) {
        
         
        
        Informacion libro1= new Informacion ();
        Prestamo libro2= new Prestamo();
        Scanner teclado= new Scanner(System.in);
        boolean salida= false;
        
         try {
            do{
            
            System.out.println("\n  Binenvenido al menú principal. \n Si desea gestionar información de un libro digite '1'"
                + "si desea gestionar el préstamo de un libro digite '2', \n para finalizar el programa digite cuallquier otro número");
            int opcion= teclado.nextInt();
            switch (opcion){
                case 1:
                  libro1.inicializar();
                                      
                    break;
                case 2:
                   libro2.inicializar();
                   
                    break;
                default:
                    salida=true;
                    
            }System.out.println("\n "); 
            System.out.println("Gracias por usar el servicio \n");
            
        }while (salida==false);
        } catch (Exception e){
            System.out.println("Operación fallida, ingresó una opción no válida ");
        }
    }
    
}
