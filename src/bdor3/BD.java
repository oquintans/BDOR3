/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdor3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oracle
 */
public class BD {

    static Connection conn;
    ResultSet rs;

    public void conexion() {
        try {
            String driver = "jdbc:oracle:thin:";
            String host = "localhost.localdomain"; // tambien puede ser una ip como "192.168.1.14"
            String porto = "1521";
            String sid = "orcl";
            String usuario = "hr";
            String password = "hr";
            String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;

            conn = DriverManager.getConnection(url);
            System.out.println("Conexion establecida.");

        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listar() {
        try {
            PreparedStatement ps = conn.prepareStatement("select empregado.* from empregado");
            rs = (ResultSet) ps.executeQuery();

            while (rs.next()) {
                java.sql.Struct jqs = (java.sql.Struct) rs.getObject(1);
                Object[] x = jqs.getAttributes();
                String nombre = (String) x[0];
                java.math.BigDecimal num = (java.math.BigDecimal) x[1];
                System.out.println("Nombre: " + nombre + "\nNum: " + num);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
