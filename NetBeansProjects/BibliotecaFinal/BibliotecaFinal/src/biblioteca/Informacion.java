
package biblioteca;
import java.util.Scanner;
import java.util.Arrays;

import biblioteca.Prestamo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Informacion {
    
    
    String user = "root";
            String password= "MOVILES2015";
            String user2= "sharac";// db4free.net  -- MI USUARIO es sharac y password: moviles2015 (minuscula)
            String password2="moviles2015";// db4free.net
            
            
            Scanner lector =new Scanner(System.in);
    
    static String nombre;
    static String autor;
    static String an_publi;
    static String codigo;
    static int cantidad;
    static int area;
    static int ide;
    
    
    public void inicializar(){
        
      //  System.out.println(Arrays.asList(libros));
    Scanner teclado= new Scanner(System.in);
    Informacion objeto= new Informacion();
    Prestamo objeto1= new Prestamo();
    boolean salir =false;
    int otro;
        
        do{
            System.out.println("Ingrese la opción de gestion que desee asi: \n"
                + "'1' para ingresar un libro nuevo,  '2' para actualizar información de un libro existente, \n"
                + "'3' para eliminar un libro existente, '4' para buscar un libro en la base de datos \n 5 para regresar al menú principal");
        int opcion= teclado.nextInt();
        try{
            switch(opcion){
                
                case 1:
                    objeto.Ingresar_libro();
                    System.out.println("si desea realizar otra gestión de información \n"
                            + " digite '1', en caso contrario digite '2' \n" );
                       try{
                        otro= teclado.nextInt();
                        if(otro ==1){
                            salir= false;
                        } else if (otro==2){
                            salir = true;
                        }else {
                            System.out.println("Instrucción incorrecta, debe volver al menú \n\n ");
                            salir=true;
                        }
                       }catch (Exception e){}
                    break;
                    
                case 2:
                    objeto.actualizar();
                    System.out.println("si desea realizar otra gestión de información \n"
                            + " digite '1', en caso contrario digite '2' ");
                    try{
                        otro= teclado.nextInt();
                        if(otro ==1){
                            salir= false;
                        } else if (otro==2){
                            salir = true;
                        }else {
                            System.out.println("Instrucción incorrecta, debe volver al menú \n\n");
                            salir=true;
                        }
                        }catch (Exception e){}
                    break;
                    
                case 3:
                    objeto.eliminar();
                    System.out.println("si desea realizar otra gestión de información \n"
                            + " digite '1', en caso contrario digite '2' ");
                    try{
                        otro= teclado.nextInt();
                        if(otro ==1){
                            salir= false;
                        } else if (otro==2){
                            salir = true;
                        }else {
                            System.out.println("Instrucción incorrecta, debe volver al menú \n\n");
                            salir=true;
                        }
                        }catch (Exception e){}
                    break;
                case 4:
                    objeto.buscar();
                    System.out.println("si desea realizar otra gestión de información \n " 
                            + " digite '1', en caso contrario digite '2' ");
                    try{
                        otro= teclado.nextInt();
                        if(otro ==1){
                            salir= false;
                        } else if (otro==2){
                            salir = true;
                        }else {
                            System.out.println("Instrucción incorrecta, debe volver al menú \n\n");
                            salir=true;
                        }
                        }catch (Exception e){}
                    break;
                    
                case 5:
                    
                    salir= true;
                    break;
                    
                default: 
                    System.out.println("ingrese una opción válida por favor \n\n");
                    salir = true;
                    
               
            }
            }catch(Exception e){
                
                    } 
            
        }while (salir == false);
}    
        
    public void Ingresar_libro(){
        
        
        
        
        Scanner lector =new Scanner(System.in);
        try {
            
            System.out.println("Conectando a la base de datos...");
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con= DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", user, password);// crea conexion
            Connection con= DriverManager.getConnection("jdbc:mysql://db4free.net/contactos_saca", user2, password2);
                        
            Statement estado= con.createStatement();
            ResultSet resultado = estado.executeQuery("SELECT * FROM `libros`"); 
            
            //Ingresar libro
            
            resultado = estado.executeQuery("SELECT * FROM `libros`");
            
            System.out.println("Ingrese el Nombre");
            nombre= lector.nextLine();
            System.out.println("\n\n Ingrese el autor");
            autor=lector.nextLine();
            System.out.println("\n\n Ingrese el año de publicación");
            an_publi= lector.nextLine();
            System.out.println("\n\n Ingrese el código");
            codigo= lector.nextLine();
            System.out.println("\n\n Ingrese la cantidad de libros");
            cantidad= lector.nextInt();
            System.out.println("\n\n Ingrese el área (recuerde ingresarlo asi: \n"
                    + "digite '1' para quimica,\t "
                + "'2' para fisica,\t '3' para tecnologia, \t '4' para cálculo y '5' para programación )");
            area= lector.nextInt();
            System.out.println("\n\n");
            System.out.println("\n\n");
            estado.executeUpdate("INSERT INTO `libros` VALUES (NULL, '"+nombre+"', '"+autor+"', '"+an_publi+"', '"+codigo+"', '"+cantidad+"', '"+area+"')");
            System.out.println("\n\n");
            System.out.println("El libro se guardó exitosamente ");
            System.out.println("nombre \t autor \t anio \t cantidad");
            resultado = estado.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '"+nombre+"'"); // no distingue mayus ni minusculas, pero si los espacios.
            
            while (resultado.next()){// mirar si la siguiente linea tiene algo
                System.out.println(resultado.getString("nombre")+"\t" + resultado.getString("autor")
                +"\t"+ resultado.getString("anio")+"\t" + resultado.getString("cantidad")+"\n");
            }
            
            
            
        }catch (SQLException ex) {
            System.out.println("error de mysql");// genera un error de sintaxis mysql
        } catch (Exception e){
            System.out.println("se ha encontrado un error que es: "+e.getMessage());
        }
                
                
    }
    
    public void actualizar(){
        
        
        boolean salir= false;
        int otro;
        Scanner teclado= new Scanner(System.in);
        System.out.println("Ingrese el nombre del libro a actualizar ");
        nombre= teclado.nextLine();
        //for(int i=0;i<=9;i++){
          //  if (libros[i]!=null){
           // if (libros[i].equals(nombre)){
         Scanner lector =new Scanner(System.in);
        try {
            
           System.out.println("Intentando conectar a la base de datos...");
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con= DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", user, password);
            Connection con= DriverManager.getConnection("jdbc:mysql://db4free.net/contactos_saca", user2, password2);
            //System.out.println("conexión exitosa...");
            
            Statement estado= con.createStatement();
            
            //cargar todos los contactos
            ResultSet resultado = estado.executeQuery("SELECT * FROM `libros`");
            resultado = estado.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '"+nombre+"'");
            if(resultado.next()==true){
                do{
                System.out.println("\n\npara actualizar:  el autor digite '1', "
                        + " el año de publicación digite '2', \n  el código del "
                        + "libro digite '3', la cantidad de libros digite '4' \n,"
                        + " el area del libro digite '5' \n");
                int cambio= teclado.nextInt();
                //teclado.next();
                switch (cambio){
                    case 1:
                        resultado = estado.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '"+nombre+"'");
                        System.out.println("Ingrese el autor del libro "); 
                        autor= teclado.next();
                        
                        if(resultado.next()==true){
                        
                        ide= resultado.getInt("id");
                            
                        //System.out.println(ide);
                        estado.executeUpdate("UPDATE `contactos_saca`.`libros` SET `autor` = '"+autor+"' WHERE `libros`.`id` = '"+ide+"';");
                        }
                        System.out.println("\n\nsi desea actualizar otro dato del "
                                + "mismo libro digite '1' ,\n en caso contrario digite '2' \n\n");
                        otro= teclado.nextInt();
                        if(otro ==1){
                            salir= false;
                        } else if (otro==2){
                            salir = true;
                        }else {
                            System.out.println("Instrucción incorrecta, debe volver al menú \n\n");
                            salir=true;
                        }
                    break;
                    case 2:
                        resultado = estado.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '"+nombre+"'");
                        System.out.println("Ingrese el año de publicación del libro ");
                        an_publi= teclado.next();
                                              
                        if(resultado.next()==true){
                        ide= resultado.getInt("id");
                            
                        //System.out.println(ide);
                        estado.executeUpdate("UPDATE `contactos_saca`.`libros` SET `anio` = '"+an_publi+"' WHERE `libros`.`id` = "+ide+";");
                        }
                        System.out.println("\n\n si desea actualizar otro dato del "
                                + "mismo libro digite '1', en caso contrario digite '2'\n\n");
                        otro= teclado.nextInt();
                        if(otro ==1){
                            salir= false;
                        } else if (otro==2){
                            salir = true;
                        }else {
                            System.out.println("Instrucción incorrecta, debe volver al menú \n\n");
                            salir=true;
                        }
                    break;
                    case 3:
                        System.out.println("Ingrese el codigo del libro");
                        codigo= teclado.next();
                        resultado = estado.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '"+nombre+"'");
                                                                      
                        if(resultado.next()==true){
                        ide= resultado.getInt("id");
                            
                        System.out.println(ide);
                        estado.executeUpdate("UPDATE `contactos_saca`.`libros` SET `codigo` = '"+codigo+"' WHERE `libros`.`id` = "+ide+";");
                        }
                        System.out.println("si desea actualizar otro dato del "
                                + "mismo libro digite '1',en caso contrario digite '2'\n");
                        otro= teclado.nextInt();
                        if(otro ==1){
                            salir= false;
                        } else if (otro==2){
                            salir = true;
                        }else {
                            System.out.println("Instrucción incorrecta, debe volver al menú \n\n");
                            salir=true;
                        }
                    break;
                    case 4:
                        System.out.println("Ingrese la cantidad de libros a añadir. ");
                        
                        cantidad= teclado.nextInt();
                        resultado = estado.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '"+nombre+"'");
                                                                      
                        if(resultado.next()==true){
                        ide= resultado.getInt("id");
                            
                        //System.out.println(ide);
                        estado.executeUpdate("UPDATE `contactos_saca`.`libros` SET `cantidad` = '"+cantidad+"' WHERE `libros`.`id` = "+ide+";");
                        }
                        System.out.println("si desea actualizar otro dato del "
                                + "mismo libro digite '1',\n en caso contrario digite '2'\n\n");
                        otro= teclado.nextInt();
                        if(otro ==1){
                            salir= false;
                        } else if (otro==2){
                            salir = true;
                        }else {
                            System.out.println("Instrucción incorrecta, debe volver al menú \n\n");
                            salir=true;
                        }
                    break;
                    case 5:
                        System.out.println("Ingrese el area del libro asi: digite '1' para quimica,\n "
                + "'2' para fisica, '3' para tecnologia, \n'4' para cálculo y '5' para programación");
                        area= teclado.nextInt();
                        resultado = estado.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '"+nombre+"'");
                                                                      
                        if(resultado.next()==true){
                        ide= resultado.getInt("id");
                            
                        System.out.println(ide);
                        estado.executeUpdate("UPDATE `contactos_saca`.`libros` SET `area` = '"+area+"' WHERE `libros`.`id` = "+ide+";");
                        }
                        System.out.println("si desea actualizar otro dato del "
                                + "mismo libro digite '1',\n en caso contrario digite '2'");
                        otro= teclado.nextInt();
                        if(otro ==1){
                            salir= false;
                        } else if (otro==2){
                            salir = true;
                        }else {
                            System.out.println("Instrucción incorrecta, debe volver al menú \n\n");
                            salir=true;
                        }
                    break;
                        
                    default:
                        System.out.println("Por favor ingrese una opción correcta \n\n");
                        
                }
                } while  (salir == false);
            }else System.out.println("El libro no se encuentra en la base de datos\n\n");
                //i=10;
            //}else if(i==10)System.out.println("El libro solicitado no se encuentra registrado en la base de datos");
            //}else if(i==9) System.out.println("El libro solicitado no se encuentra registrado en la base de datos");
            //}*/
        
        }catch (SQLException ex) {
            System.out.println("error de mysql");// genera un error de sintaxis mysql
        } catch (Exception e){
            System.out.println("se ha encontrado un error que es: "+e.getMessage());
        }
        
    }
    
    
    public void eliminar(){
       
        
        
        Scanner lector =new Scanner(System.in);
        try {
            
            System.out.println("Intentando conectar a la base de datos...");
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con= DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", user, password);// crea conexion
            Connection con= DriverManager.getConnection("jdbc:mysql://db4free.net/contactos_saca", user2, password2);
            
            Statement estado= con.createStatement();
            
            //cargar todos 
            ResultSet resultado = estado.executeQuery("SELECT * FROM `libros`");
            
            
            //borrar 
            System.out.println("digite el nombre del libro a eliminar");
            nombre=lector.nextLine();
            estado.executeUpdate("DELETE FROM `libros` WHERE `nombre` LIKE '"+nombre+"'");
            System.out.println("\n ");
            //resultado = estado.executeQuery("SELECT * FROM `libros`"); // no distingue mayus ni minusculas, pero si los espacios.
            System.out.println("El libro ha sido eliminado de la base de datos");
             System.out.println("\n ");           
            }catch (SQLException ex) {
            System.out.println("error de mysql");// genera un error de sintaxis mysql
        } catch (Exception e){
            System.out.println("se ha encontrado un error que es: "+e.getMessage());
        }
        
    }
    
    public void buscar(){
        
               
        Scanner lector =new Scanner(System.in);
        try {
            
            System.out.println("Intentando conectar a la base de datos...");
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con= DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", user, password);// crea conexion
            Connection con= DriverManager.getConnection("jdbc:mysql://db4free.net/contactos_saca", user2, password2);
            
            Statement estado= con.createStatement();
            
            //cargar todos 
            ResultSet resultado = estado.executeQuery("SELECT * FROM `libros`");
            
            
           // buscar por nombre
           
           //System.out.println("busqueda por nombre \n\n");
            System.out.println("digite el nombre del libro a buscar");
            nombre= lector.nextLine();
            
            resultado = estado.executeQuery("SELECT * FROM `libros` WHERE `nombre` LIKE '"+nombre+"'"); // no distingue mayus ni minusculas, pero si los espacios.
            //System.out.println(resultado.next());
            //System.out.println("\n\n");
            if(resultado.next()==true){
            // mirar si la siguiente linea tiene algo
                System.out.println("\n"); 
                System.out.println("\n Nombre"+"\t"+"Autor"+"\t"+"Año de publicación"+"\t"+"Codigo"+"\t"
                +"Cantidad de libros disponibles"+"\t"+"Area"+"\t");
               System.out.println(resultado.getString("nombre")
                +"\t"+ resultado.getString("autor")+"\t" + resultado.getString("anio")+
                        "\t"+"\t"+"\t"+resultado.getString("codigo")+"\t"+"\t"+resultado.getInt("cantidad")+
                        "\t"+"\t"+"\t"+resultado.getInt("area"));
               nombre=resultado.getString("nombre");
               autor=resultado.getString("autor");
               an_publi=resultado.getString("anio");
               codigo=resultado.getString("codigo");
               cantidad=resultado.getInt("cantidad");
               area=resultado.getInt("area");
               
            }else {System.out.println("\n "); 
                System.out.println("El libro no existe en la base de datos");
            System.out.println("\n\n");
            }
            }catch (SQLException ex) {
            System.out.println("error de mysql");// genera un error de sintaxis mysql
        } catch (Exception e){
            System.out.println("se ha encontrado un error que es: "+e.getMessage());
        }
        
    }
    
    
    
}
