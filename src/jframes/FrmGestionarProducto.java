/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package jframes;

import java.awt.Dimension;


public class FrmGestionarProducto extends javax.swing.JInternalFrame {

    
    public FrmGestionarProducto() {
       
        initComponents();
        this.setSize(new Dimension(600, 400));
        this.setTitle("Gestionar Categorias");
       
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
        txt_cantidad = new javax.swing.JTextField();
        txt_precio = new javax.swing.JTextField();
        txt_descripcion = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
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
        jLabel3.setText("Cantidad:");
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
        jLabel7.setText("Categoria:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));

        txt_cantidad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel3.add(txt_cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 170, -1));

        txt_precio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel3.add(txt_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 160, -1));

        txt_descripcion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel3.add(txt_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 160, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 150, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 870, 100));
        getContentPane().add(jLabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_wallpaper;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_producto;
    private javax.swing.JTextField txt_cantidad;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_precio;
    // End of variables declaration//GEN-END:variables
}
