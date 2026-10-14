package modelo;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class UsuariosDao {
    ConexionMySQL cn = new ConexionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    //Registrar Cliente
    public boolean registrarUsuariosQuery(Usuarios usuarios){
    String query = "Insert into customers (id,full_name, address, telephone, email,"
           + "reated, updated) values('?,?,?,?,?,?,?";
    
    Timestamp dateTime = new Timestamp(new Date().getTime());
    try{
    conn = cn.getConexion();
    pst = conn.prepareStatement(query);
    pst.setInt(1, usuarios.getId());
    pst.setString(2, usuarios.getNombreCompleto());
    pst.setString(3, usuarios.getDireccion());
    pst.setString(4, usuarios.getTelefono());
    pst.setString(5, usuarios.getCorreo());
    pst.setTimestamp(6, dateTime);
    pst.setTimestamp(7, dateTime);
    
    pst.execute();
    return true;
   
    }catch(SQLException e){
    JOptionPane.showMessageDialog(null,"Error al registrar cliente"+e);
    return false;
    }
  }
    
    //Listar usuarios
    public List listaUsuariosQuery(String value){
    List<Usuarios> list_customers =new ArrayList();
    
    String query ="SELEC * FROM customers";
    String query_search_customers ="Selec * from customers WHERE id LIKE '%"+value+"%'";
    try{
    conn= cn.getConexion();
    if(value.equalsIgnoreCase("")){
    pst = conn.prepareStatement(query);
    rs = pst.executeQuery();
    }else{
    pst=conn.prepareStatement(query_search_customers);
    rs=pst.executeQuery();
    }
    while(rs.next()){
    Usuarios usuarios = new Usuarios();
    usuarios.setId(rs.getInt("id"));
    usuarios.setNombreCompleto(rs.getString("NombreCompleto"));
    usuarios.setDireccion(rs.getString("address"));
    usuarios.setTelefono(rs.getString("telefono"));
    usuarios.setCorreo(rs.getString("Email"));
    list_customers.add(usuarios);
    }
    }catch(SQLException e){
    JOptionPane.showMessageDialog(null, e.toString());
    }
    return list_customers;
    }
    
    //Modificar usuarios
    public boolean modificarUsuarioQuery(Usuarios usuarios){
    String query = "UPDATE customers SET full_name = ?, address = ?, telephone = ?, "
        + "email = ?, updated = ? WHERE id = ?";
    Timestamp dateTime = new Timestamp(new Date().getTime());
    try{
    conn = cn.getConexion();
    pst=conn.prepareStatement(query);
    
    pst.setString(1, usuarios.getNombreCompleto());
    pst.setString(2, usuarios.getDireccion());
    pst.setString(3, usuarios.getTelefono());
    pst.setString(4, usuarios.getCorreo());
    pst.setTimestamp(5, dateTime);
    pst.setInt(6, usuarios.getId());
    
    pst.execute();
    return true;
    }catch(SQLException e){
    JOptionPane.showMessageDialog(null,"Error al modificar datos del cliente"+e);
    return false;
    }
    }
    
    
    // Eliminar usuario
    public boolean eliminarUsuariosQuery(int identificacion) {

        String query = "DELETE FROM usuario WHERE identificacion = ?";

        try {

            conn = cn.getConexion();

            pst = conn.prepareStatement(query);

            pst.setInt(1, identificacion);

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "No se puede eliminar un cliente "
                    + "que tenga relación con otra tabla: " + e
            );

            return false;
        }
    }
}
