package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Usuarios;
import modelo.UsuariosDao;
import vista.InterfazPrincipal;


public class LoginController implements ActionListener, MouseListener, KeyListener {

    // Encapsular variables
    private Usuarios usuarios;
    private UsuariosDao usuariosDao;
    private InterfazPrincipal interfazPrincipal;

    DefaultTableModel model = new DefaultTableModel();

    // Constructor
    public LoginController(Usuarios usuarios, UsuariosDao usuariosDao, InterfazPrincipal interfazPrincipal) {

        this.usuarios = usuarios;
        this.usuariosDao = usuariosDao;
        this.interfazPrincipal = interfazPrincipal;

        // Botón Registrar Usuario
        this.interfazPrincipal.btnRegistrar.addActionListener(this);

        // Tabla
        this.interfazPrincipal.txtCorreo.addMouseListener(this);

        // Buscador
        this.interfazPrincipal.txtBuscar.addKeyListener(this);

        // Modificar
        this.interfazPrincipal.btnModificar.addActionListener(this);

        // Eliminar
        this.interfazPrincipal.btnEliminar.addActionListener(this);

        // Cancelar
        this.interfazPrincipal.btnCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // REGISTRAR USUARIo
        if (e.getSource() == interfazPrincipal.btnRegistrar) {

            // Validar campos
            if (interfazPrincipal.txtIdentificacion.getText().equals("")
                    || interfazPrincipal.txtCorreo.getText().equals("")
                    || interfazPrincipal.txtTelefono.getText().equals("")
                    || interfazPrincipal.txtCorreo.getText().equals("")) 
                 
                            {

                JOptionPane.showMessageDialog(
                        null,
                        "Todos los campos son obligatorios"
                );

            } else {

                // Obtener datos de la vista
                usuarios.setNombreCompleto(
                        interfazPrincipal.txtIdentificacion.getText().trim()
                );

                usuarios.setDireccion(
                        interfazPrincipal.txtDireccion.getText().trim()
                );

                usuarios.setTelefono(
                        interfazPrincipal.txtTelefono.getText().trim()
                );

                usuarios.setCorreo(
                        interfazPrincipal.txtCorreo.getText().trim()
                );

                // Registrar usuario
                if (usuariosDao.registrarUsuariosQuery(usuarios)) {

                    cleanTabla();
                    listarUsuarios();

                    JOptionPane.showMessageDialog(
                            null,
                            "Usuario registrado con éxito"
                    );

                    cleanFields();

                } else {

                    JOptionPane.showMessageDialog(
                            null,
                            "Ha ocurrido un error al registrar el usuario"
                    );
                }
            }

        // ACTUALIZAR USUARIO
        } else if (e.getSource() == interfazPrincipal.btnModificar) {

            // Verificar que se haya seleccionado un usuario
            if (interfazPrincipal.txtIdentificacion.getText().equals("")) {

                JOptionPane.showMessageDialog(
                        null,
                        "Selecciona una fila para continuar"
                );

            } else {

                // Validar campos
                if (interfazPrincipal.txtIdentificacion.getText().equals("")
                        || interfazPrincipal.txtDireccion.getText().equals("")
                        || interfazPrincipal.txtTelefono.getText().equals("")
                        || interfazPrincipal.txtCorreo.getText().equals("")) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Todos los campos son obligatorios"
                    );

                } else {

                    // Obtener datos
                    usuarios.setId(
                            Integer.parseInt(
                                    interfazPrincipal.txtIdentificacion
                                            .getText().trim()
                            )
                    );

                    usuarios.setNombreCompleto(
                            interfazPrincipal.txtIdentificacion
                                    .getText().trim()
                    );

                    usuarios.setDireccion(
                            interfazPrincipal.txtDireccion
                                    .getText().trim()
                    );

                    usuarios.setTelefono(
                            interfazPrincipal.txtTelefono
                                    .getText().trim()
                    );

                    usuarios.setCorreo(
                            interfazPrincipal.txtCorreo
                                    .getText().trim()
                    );

                    // Actualizar usuario
                    if (usuariosDao.modificarUsuarioQuery(usuarios)) {

                        cleanTabla();
                        cleanFields();
                        listarUsuarios();

                        JOptionPane.showMessageDialog(
                                null,
                                "Datos del usuario modificados con éxito"
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                null,
                                "Ha ocurrido un error al modificar usuario"
                        );
                    }
                }
            }

        // ELIMINAR USUARIO
        } else if (e.getSource() == interfazPrincipal.btnEliminar) {

            int row = interfazPrincipal.txtTablaUsuarios.getSelectedRow();

            if (row == -1) {

                JOptionPane.showMessageDialog(
                        null,
                        "Debes seleccionar un usuario para eliminar"
                );

            } else {

                int id = Integer.parseInt(
                        interfazPrincipal.txtTablaUsuarios
                                .getValueAt(row, 0)
                                .toString()
                );

                int question = JOptionPane.showConfirmDialog(
                        null,
                        "¿Realmente quieres eliminar este usuario?",
                        "Eliminar usuario",
                        JOptionPane.YES_NO_OPTION
                );

                if (question == JOptionPane.YES_OPTION) {

                    if (usuariosDao.eliminarUsuariosQuery(id)) {

                        cleanFields();
                        cleanTabla();
                        listarUsuarios();

                        interfazPrincipal.btnRegistrar.setEnabled(true);

                        JOptionPane.showMessageDialog(
                                null,
                                "Usuario eliminado con éxito"
                        );
                    }
                }
            }

        // CANCELAR
        } else if (e.getSource() == interfazPrincipal.btnCancelar) {

            interfazPrincipal.btnRegistrar.setEnabled(true);

            cleanFields();
        }
    }

    // LIMPIAR CAMPOS
    public void cleanFields() {

        interfazPrincipal.txtIdentificacion.setText("");
        interfazPrincipal.txtIdentificacion.setEditable(true);

        interfazPrincipal.txtIdentificacion.setText("");
        interfazPrincipal.txtDireccion.setText("");
        interfazPrincipal.txtTelefono.setText("");
        interfazPrincipal.txtCorreo.setText("");
    }
    // LISTAR USUARIOS
  
    public void listarUsuarios() {

        List<Usuarios> list = usuariosDao.listaUsuariosQuery(
                interfazPrincipal.txtBuscar.getText()
        );

        model = (DefaultTableModel) interfazPrincipal.txtTablaUsuarios.getModel();

        Object[] row = new Object[5];

        for (int i = 0; i < list.size(); i++) {

            row[0] = list.get(i).getId();
            row[1] = list.get(i).getNombreCompleto();
            row[2] = list.get(i).getTelefono();
            row[3] = list.get(i).getDireccion();
            row[4] = list.get(i).getCorreo();

            model.addRow(row);
        }

        interfazPrincipal.txtTablaUsuarios.setModel(model);
    }

    // CLICK EN LA TABLA
    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == interfazPrincipal.txtTablaUsuarios) {

            int row = interfazPrincipal.txtTablaUsuarios.rowAtPoint(e.getPoint());

            if (row >= 0) {

                interfazPrincipal.txtIdentificacion.setText(
                        interfazPrincipal.txtTablaUsuarios
                                .getValueAt(row, 0)
                                .toString()
                );

                interfazPrincipal.txtIdentificacion.setText(
                        interfazPrincipal.txtTablaUsuarios
                                .getValueAt(row, 1)
                                .toString()
                );

                interfazPrincipal.txtTelefono.setText(
                        interfazPrincipal.txtTablaUsuarios
                                .getValueAt(row, 2)
                                .toString()
                );

                interfazPrincipal.txtDireccion.setText(
                        interfazPrincipal.txtTablaUsuarios
                                .getValueAt(row, 3)
                                .toString()
                );

                interfazPrincipal.txtCorreo.setText(
                        interfazPrincipal.txtTablaUsuarios
                                .getValueAt(row, 4)
                                .toString()
                );

                interfazPrincipal.btnRegistrar.setEnabled(false);

                interfazPrincipal.txtIdentificacion.setEditable(false);
            }
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

    // TECLADO
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getSource() == interfazPrincipal.txtBuscar) {

            cleanTabla();
            listarUsuarios();
        }
    }

    // LIMPIAR TABLA

    public void cleanTabla() {

        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
}