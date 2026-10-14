/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexionMySQL {
    private final String bd="helpdesk";
    private final String usuario="root";
    private final String password="";
    private final String url = "jdbc:mysql://localhost:3306/" + bd;
            Connection con=null;
            
            public Connection getConexion(){
            
            try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexion exitosa.");
            } catch (ClassNotFoundException e){
                System.out.println("Nose encontro el Driver MySQL");
            } catch (SQLException e){
            System.out.println("Error al conectar con la base de datos.");
            System.out.println(e.getMessage());
            }
            return con;
            }
}
