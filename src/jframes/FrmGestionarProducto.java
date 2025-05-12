/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package jframes;

import conexion.Conexion;
import controlador.ctrlProducto;
import java.awt.Dimension;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Producto;

public class FrmGestionarProducto extends javax.swing.JInternalFrame {

    public FrmGestionarProducto() {

        initComponents();
        mostrarProductos();
        jTable_producto.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        jTable_productoMouseClicked(evt);
    }
});
        this.setSize(new Dimension(900, 500));
        this.setTitle("Gestionar Categorias");

    }

    private void limpiarCampos() {
        txt_nombre.setText("");
        txt_stock.setText("");
        txt_precio.setText("");
        txt_descripcion.setText("");
        txt_itbis.setText("");
        txt_stock.setText("");
        jTable_producto.clearSelection();
    }

    void mostrarProductos() {
    // Creamos el modelo de tabla
    DefaultTableModel modelo = new DefaultTableModel();

    // Añadimos las columnas al modelo
    modelo.addColumn("ID");
    modelo.addColumn("Nombre");
    modelo.addColumn("Descripción");
    modelo.addColumn("Precio");
    modelo.addColumn("Stock");
    modelo.addColumn("Categoría");
    modelo.addColumn("ITBIS");

    // Sentencia SQL para obtener los datos
    String sql = "SELECT * FROM productos";

    try {
        // Conexión a la base de datos
        Connection con = Conexion.getConnection(); // Asegúrate que tu clase de conexión se llame así
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        // Recorremos los resultados
        while (rs.next()) {
            Object[] fila = new Object[7];
            fila[0] = rs.getInt("id_producto");
            fila[1] = rs.getString("nombre");
            fila[2] = rs.getString("descripcion");
            fila[3] = rs.getDouble("precio");
            fila[4] = rs.getInt("stock");
            fila[5] = rs.getString("categoria");
            fila[6] = rs.getInt("porcentajeitbis");

            modelo.addRow(fila);
        }

        // Asignamos el modelo a la tabla
        jTable_producto.setModel(modelo);

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al mostrar productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void jTable_productoMouseClicked(java.awt.event.MouseEvent evt) {
    int filaSeleccionada = jTable_producto.getSelectedRow();

    if (filaSeleccionada >= 0) {
        
        txt_nombre.setText(jTable_producto.getValueAt(filaSeleccionada, 1).toString());
        txt_descripcion.setText(jTable_producto.getValueAt(filaSeleccionada, 2).toString());
        txt_precio.setText(jTable_producto.getValueAt(filaSeleccionada, 3).toString());
        txt_stock.setText(jTable_producto.getValueAt(filaSeleccionada, 4).toString());

        // Seleccionar categoría en el JComboBox
        String categoria = jTable_producto.getValueAt(filaSeleccionada, 5).toString();
        cbo_categoria.setSelectedItem(categoria);

        txt_itbis.setText(jTable_producto.getValueAt(filaSeleccionada, 6).toString());
    }
}
    
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_producto = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_stock = new javax.swing.JTextField();
        txt_precio = new javax.swing.JTextField();
        txt_descripcion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_itbis = new javax.swing.JTextField();
        cbo_categoria = new javax.swing.JComboBox<>();
        jLabel_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(95, 47, 35));
        jLabel1.setText("Administrar Productos");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_producto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable_producto);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 690, 230));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 730, 270));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(162, 210, 255));
        jButton1.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(95, 47, 35));
        jButton1.setText("Actualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 110, -1));

        jButton2.setBackground(new java.awt.Color(254, 176, 200));
        jButton2.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(95, 47, 35));
        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 110, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, 130, 270));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(95, 47, 35));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Nombre:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txt_nombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel3.add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 170, -1));

        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(95, 47, 35));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Stock:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel4.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(95, 47, 35));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Precio:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        jLabel5.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(95, 47, 35));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Descripción:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, -1));

        jLabel7.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(95, 47, 35));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("ITBIS:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, -1, -1));

        txt_stock.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel3.add(txt_stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 170, -1));

        txt_precio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel3.add(txt_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 160, -1));

        txt_descripcion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel3.add(txt_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 160, -1));

        jLabel8.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(95, 47, 35));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Categoría:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));

        txt_itbis.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jPanel3.add(txt_itbis, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 180, -1));

        cbo_categoria.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        cbo_categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione categoria ", "Galletas", "Postres", "Sandwiches", "Bebidas", "Cafes" }));
        jPanel3.add(cbo_categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 180, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 870, 100));
        getContentPane().add(jLabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int fila = jTable_producto.getSelectedRow();
    if (fila < 0) {
        JOptionPane.showMessageDialog(null, "Debes seleccionar un producto de la tabla.");
        return;
    }

    try {
        int id_producto = Integer.parseInt(jTable_producto.getValueAt(fila, 0).toString());

        Producto producto = new Producto();
        producto.setNombre(txt_nombre.getText().trim());
        producto.setDescripcion(txt_descripcion.getText().trim());
        producto.setPrecio(Double.parseDouble(txt_precio.getText().trim()));
        producto.setStock(Integer.parseInt(txt_stock.getText().trim()));
        producto.setCategoria(cbo_categoria.getSelectedItem().toString());
        producto.setPorcentajeitbis(Integer.parseInt(txt_itbis.getText().trim()));

        ctrlProducto controlProducto = new ctrlProducto();
        if (controlProducto.actualizar(producto, id_producto)) {
            JOptionPane.showMessageDialog(null, "Producto actualizado correctamente.");
            mostrarProductos();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el producto.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e);
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int fila = jTable_producto.getSelectedRow();
    if (fila < 0) {
        JOptionPane.showMessageDialog(null, "Debes seleccionar un producto para eliminar.");
        return;
    }

    int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este producto?", "Confirmar", JOptionPane.YES_NO_OPTION);

    if (confirmacion == JOptionPane.YES_OPTION) {
        int id_producto = Integer.parseInt(jTable_producto.getValueAt(fila, 0).toString());

        ctrlProducto controlProducto = new ctrlProducto();
        if (controlProducto.eliminar(id_producto)) {
            JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
            mostrarProductos();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto.");
        }
    }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbo_categoria;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_wallpaper;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable_producto;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_itbis;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_precio;
    private javax.swing.JTextField txt_stock;
    // End of variables declaration//GEN-END:variables
}
