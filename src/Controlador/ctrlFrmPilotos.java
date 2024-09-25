package Controlador;

import Modelo.tbPilotos;
import Vista.frmPilotos;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ctrlFrmPilotos implements MouseListener{
    private tbPilotos modelo;
    private frmPilotos vista;
    
    public ctrlFrmPilotos(tbPilotos modelo, frmPilotos vista){
       this.modelo = modelo;
       this.vista = vista;
       
       vista.btnAgregar.addMouseListener(this);
       modelo.Mostrar(vista.jtbPilotos);
       vista.btnEliminar.addMouseListener(this);
       vista.jtbPilotos.addMouseListener(this);
       vista.btnActualizar.addMouseListener(this);
       vista.btnLimpiar.addMouseListener(this);
       vista.jtbPilotos.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vista.btnAgregar){
            modelo.setNombre_Piloto(vista.txtNombrePiloto.getText());
            modelo.setEdad_Piloto(Integer.parseInt(vista.txtEdadPiloto.getText()));
            modelo.setPeso_Piloto(Double.parseDouble(vista.txtPeso.getText()));
            modelo.setCorreo_Piloto(vista.txtCorreoPiloto.getText());
            
            modelo.Guardar();
            modelo.Mostrar(vista.jtbPilotos);
        }
        
        if(e.getSource() == vista.btnEliminar){
           modelo.Eliminar(vista.jtbPilotos);
           modelo.Mostrar(vista.jtbPilotos);
        }
        
        if(e.getSource() == vista.jtbPilotos){
          modelo.cargarDatosTabla(vista);  
        }
        
        if(e.getSource() == vista.btnActualizar){
           modelo.setNombre_Piloto(vista.txtNombrePiloto.getText());
           modelo.setEdad_Piloto(Integer.parseInt(vista.txtEdadPiloto.getText()));
           modelo.setPeso_Piloto(Double.parseDouble(vista.txtPeso.getText()));
           modelo.setCorreo_Piloto(vista.txtCorreoPiloto.getText());
           
           modelo.Actualizar(vista.jtbPilotos);
           modelo.Mostrar(vista.jtbPilotos);
        }
        
        if(e.getSource() == vista.btnLimpiar){
           modelo.Limpiar(vista);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
