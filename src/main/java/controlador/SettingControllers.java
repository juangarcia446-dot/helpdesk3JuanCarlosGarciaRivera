
package controlador;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vista.InterfazPrincipal;

public class SettingControllers implements MouseListener {
    private InterfazPrincipal views;
    public SettingControllers (InterfazPrincipal views){
     this.views=views;
    
     this.views.jLabelincidencias.addMouseListener(this);
     this.views.jLabelusuario.addMouseListener(this);
     this.views.jLabeltecnicos.addMouseListener(this);
     this.views.jLabelcategorias.addMouseListener(this);
     this.views.jLabelespecialidades.addMouseListener(this);
     this.views.lblReportess.addMouseListener(this);
     this.views.jLabelasistente.addMouseListener(this);

    }
    
     

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()== views.jLabelincidencias){
        views.jPanelProduc1.setBackground(new Color(152,202,63));
        }
        else if(e.getSource()==views.jLabelusuario){
        views.jPanelPutches2.setBackground(new Color(152,202,63));
        }
        else if(e.getSource()==views.jLabeltecnicos){
        views.jPanelCusromen3.setBackground(new Color(152,202,63));
        }
          else if(e.getSource()==views.jLabelcategorias){
        views.jPanelEmployes4.setBackground(new Color(152,202,63));
        }
       
        else if(e.getSource()==views.jLabelespecialidades){
        views.jPanelSuplimer5.setBackground(new Color(152,202,63));
        }
        else if(e.getSource()==views.lblReportess){
        views.jPanelCategories6.setBackground(new Color(152,202,63));
        }
        else if(e.getSource()==views.jLabelasistente){
        views.jPanelReport7.setBackground(new Color(152,202,63));
        }
    }
    

    @Override
    public void mouseExited(MouseEvent e) {
        
         if(e.getSource()== views.jLabelincidencias){
        views.jPanelProduc1.setBackground(new Color(51,255,255));
        }
        else if(e.getSource()==views.jLabelusuario){
        views.jPanelPutches2.setBackground(new Color(51,255,255));
        }
        else if(e.getSource()==views.jLabeltecnicos){
        views.jPanelCusromen3.setBackground(new Color(51,255,255));
        }
           else if(e.getSource()==views.jLabelcategorias){
        views.jPanelEmployes4.setBackground(new Color(51,255,255));
        }
        else if(e.getSource()==views.jLabelespecialidades){
        views.jPanelSuplimer5.setBackground(new Color(51,255,255));
        }
        else if(e.getSource()==views.lblReportess){
        views.jPanelCategories6.setBackground(new Color(51,255,255));
        }
        else if(e.getSource()==views.jLabelasistente){
        views.jPanelReport7.setBackground(new Color(51,255,255));
        }
    }
    
    
    
}
