package Modelo;

import Vista.frmPilotos;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class tbPilotos {
    
    private String UUID_Piloto;
    private String Nombre_Piloto;
    private int Edad_Piloto;
    private double Peso_Piloto;
    private String Correo_Piloto;

    public String getUUID_Piloto() {
        return UUID_Piloto;
    }

    public void setUUID_Piloto(String UUID_Piloto) {
        this.UUID_Piloto = UUID_Piloto;
    }

    public String getNombre_Piloto() {
        return Nombre_Piloto;
    }

    public void setNombre_Piloto(String Nombre_Piloto) {
        this.Nombre_Piloto = Nombre_Piloto;
    }

    public int getEdad_Piloto() {
        return Edad_Piloto;
    }

    public void setEdad_Piloto(int Edad_Piloto) {
        this.Edad_Piloto = Edad_Piloto;
    }

    public double getPeso_Piloto() {
        return Peso_Piloto;
    }

    public void setPeso_Piloto(double Peso_Piloto) {
        this.Peso_Piloto = Peso_Piloto;
    }

    public String getCorreo_Piloto() {
        return Correo_Piloto;
    }

    public void setCorreo_Piloto(String Correo_Piloto) {
        this.Correo_Piloto = Correo_Piloto;
    }
    
    public void Guardar() {
        
        Connection conexion = ClaseConexion.getConexion();
        try {
            PreparedStatement addProducto = conexion.prepareStatement("INSERT INTO tbPiloto (UUID_Piloto, Nombre_Piloto, Edad_Piloto, Peso_Piloto, Correo_Piloto) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addProducto.setString(1, UUID.randomUUID().toString());
            addProducto.setString(2, getNombre_Piloto());
            addProducto.setInt(3, getEdad_Piloto());
            addProducto.setDouble(4, getPeso_Piloto());
            addProducto.setString(5, getCorreo_Piloto());
            addProducto.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Piloto", "Nombre_Piloto", "Edad_Piloto", "Peso_Piloto", "Correo_Piloto"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbPiloto");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_Piloto"), 
                    rs.getString("Nombre_Piloto"), 
                    rs.getInt("Edad_Piloto"),
                    rs.getDouble("Peso_Piloto"),
                    rs.getString("Correo_Piloto")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    
    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
 
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tbPiloto where UUID_Piloto = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
    public void cargarDatosTabla(frmPilotos vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbPilotos.getSelectedRow();
 
        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbPilotos.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbPilotos.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.jtbPilotos.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTB = vista.jtbPilotos.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTB = vista.jtbPilotos.getValueAt(filaSeleccionada, 4).toString();
 
            // Establece los valores en los campos de texto
            vista.txtNombrePiloto.setText(NombreDeTB);
            vista.txtEdadPiloto.setText(EdadDeTb);
            vista.txtPeso.setText(PesoDeTB);
            vista.txtCorreoPiloto.setText(CorreoDeTB);
        }
    }
    
    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
 
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbPiloto set Nombre_Piloto= ?, Edad_Piloto = ?, Peso_Piloto = ?, Correo_Piloto = ? where UUID_Piloto = ?");
 
                updateUser.setString(1, getNombre_Piloto());
                updateUser.setInt(2, getEdad_Piloto());
                updateUser.setDouble(3, getPeso_Piloto());
                updateUser.setString(4, getCorreo_Piloto());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
    
    public void Limpiar(frmPilotos vista){
       vista.txtNombrePiloto.setText("");
       vista.txtEdadPiloto.setText("");
       vista.txtPeso.setText("");
       vista.txtCorreoPiloto.setText("");
    }
}