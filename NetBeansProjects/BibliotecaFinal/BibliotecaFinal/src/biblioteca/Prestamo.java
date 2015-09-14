
package biblioteca;

import java.util.Arrays;
import java.util.Scanner;
import biblioteca.Informacion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Prestamo extends Informacion {
    Informacion objeto = new Informacion ();
    
    Scanner teclado= new Scanner(System.in);
      
    
    public void inicializar(){
        Prestamo objeto3= new Prestamo();
        boolean salir =false;
        do{
            System.out.println("Ingrese la opción de gestion de prestamo que desee asi: \n"
                + "'1' para prestar un libro, '2' para devolver un libro prestado,\n"
                + "'3' para consultar todos los libros prestados, '4' para regresar al menú principal ");
        int opcion= teclado.nextInt();
        try{
        switch(opcion){
                case 1:
                    objeto3.Prestar();
                    
                    break;
                    
                case 2:
                    objeto3.devolver();
                    
                    break;
                    
                case 3:
                    objeto3.mostrar();
                    
                    break;
                case 4:
                    salir=true;
                    break;
                default:    
                    System.out.println("Instruccion incorrecta, debe volver al menú principal \n");
                    salir= true;
        }
        }catch (Exception e){}
        }while (salir==false);
    }
    public void Prestar(){
       
        
        
        objeto.buscar();
        System.out.println("Ingrese su cedula");
        String cedula= teclado.next();
        int nueva_cantidad= cantidad-1;
        //System.out.println(nueva_cantidad);
        Scanner lector =new Scanner(System.in);
        try {
            
            System.out.println("Intentando conectar a la base de datos...");
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con= DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", user, password);// crea conexion
            Connection con= DriverManager.getConnection("jdbc:mysql://db4free.net/contactos_saca", user2, password2);
            
            //System.out.println("conexión exitosa...");
            
            Statement estado= con.createStatement();
            
            //cargar todos los contactos
            ResultSet resultado = estado.executeQuery("SELECT * FROM `prestados`");
            //System.out.println(resultado.next());
            while (resultado.next()==true){// mirar si la siguiente linea tiene algo
                System.out.println(resultado.getString("id")+"\t" + resultado.getString("nombre")
                +"\t"+ resultado.getString("cedula"));
            }
           
            resultado = estado.executeQuery("SELECT * FROM `prestados`");
            
           
            estado.executeUpdate("INSERT INTO `prestados` VALUES (NULL, '"+nombre+"', '"+cedula+"')");
            System.out.println("\n");
            System.out.println("El prestamo del libro fue exitoso");
            System.out.println("Libro prestado \t Cedula del prestador");
            resultado = estado.executeQuery("SELECT * FROM `prestados` WHERE `nombre` LIKE '"+nombre+"' AND `cedula` LIKE '"+cedula+"'"); // no distingue mayus ni minusculas, pero si los espacios.
            
            while (resultado.next()){// mirar si la siguiente linea tiene algo
                System.out.println(resultado.getString("nombre")
                +"\t\t"+ resultado.getString("cedula"));
            }
            //System.out.println(nombre);
            ResultSet resultado2 = estado.executeQuery("SELECT * FROM `libros`");
            resultado2 = estado.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '"+nombre+"'");
            if(resultado2.next()==true){
               ide= resultado2.getInt("id");
              // System.out.println(ide);
               estado.executeUpdate("UPDATE `contactos_saca`.`libros` SET `cantidad` = '"+nueva_cantidad+"' WHERE `libros`.`id` = '"+ide+"';");
            }
           
        }catch (SQLException ex) {
            System.out.println("error de mysql");// genera un error de sintaxis mysql
        } catch (Exception e){
            System.out.println("se ha encontrado un error que es: "+e.getMessage());
        }
        
                
    }
    public void devolver(){
        Scanner teclado= new Scanner(System.in);
        System.out.println("Ingrese su cedula");
        String cedula1= teclado.nextLine();
        System.out.println("Ingrese el nombre del libro que desea devolver");
        String nombre1= teclado.nextLine();
       
        Scanner lector =new Scanner(System.in);
        try {
            
            System.out.println("Intentando conectar a la base de datos...");
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con= DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", user, password);// crea conexion
            Connection con= DriverManager.getConnection("jdbc:mysql://db4free.net/contactos_saca", user2, password2);
            
            Statement estado= con.createStatement();
            
           
            ResultSet resultado = estado.executeQuery("SELECT * FROM `prestados`");
            
            
           
            
            resultado = estado.executeQuery("SELECT * FROM `prestados` WHERE `cedula` LIKE '"+cedula1+"' and `nombre` like '"+nombre1+"'"); // no distingue mayus ni minusculas, pero si los espacios.
            
            if(resultado.next()==true){
              int ide2= resultado.getInt("id");
               estado.executeUpdate("DELETE FROM `prestados` WHERE `prestados`.`id` = '"+ide2+"';");
               ResultSet resultado2 = estado.executeQuery("SELECT * FROM `libros`");
            resultado2 = estado.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '"+nombre1+"'");
            int nueva_cantidad= cantidad +1;
            if(resultado2.next()==true){
               ide2= resultado2.getInt("id");
               //System.out.println(ide);
               estado.executeUpdate("UPDATE `contactos_saca`.`libros` SET `cantidad` = '"+nueva_cantidad+"' WHERE `libros`.`id` = '"+ide2+"';");
            }
            }else System.out.println("\n El libro no existe en la base de datos de libros prestados");
            
            
            
            
            
            }catch (SQLException ex) {
            System.out.println("error de mysql");// genera un error de sintaxis mysql
        } catch (Exception e){
            System.out.println("se ha encontrado un error que es: "+e.getMessage());
        }
        
    }
    public void mostrar(){
        //System.out.println("Ingrese su cedula");
        //String cedula= teclado.next();
        
        Scanner lector =new Scanner(System.in);
        try {
            
            System.out.println("Intentando conectar a la base de datos...");
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con= DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", user, password);// crea conexion
            Connection con= DriverManager.getConnection("jdbc:mysql://db4free.net/contactos_saca", user2, password2);
            
            Statement estado= con.createStatement();
            
           
            ResultSet resultado = estado.executeQuery("SELECT * FROM `prestados`");
            
                 System.out.println("\n Nombre del libro \t  Cédula del prestador");      
            while (resultado.next()){// mirar si la siguiente linea tiene algo
                
                
                System.out.println(resultado.getString("nombre")
                +"\t \t \t"+ resultado.getString("cedula"));
            }
            System.out.println("\t");
            }catch (SQLException ex) {
            System.out.println("error de mysql");// genera un error de sintaxis mysql
        } catch (Exception e){
            System.out.println("se ha encontrado un error que es: "+e.getMessage());
        }
      
}

}
