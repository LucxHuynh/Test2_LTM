/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

import java.sql.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
/**
 *
 * @author ductu
 */
public class formPhanQuyen extends javax.swing.JFrame {
    private int counterQL = 1;
    private int counterNV = 1;
    ConnectDB cn = new ConnectDB();
    Connection conn = cn.getConnection();
    public formPhanQuyen() throws SQLException {
        initComponents();
        ConnectDB cn = new ConnectDB();
        Connection conn = cn.getConnection();
        tblQL.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblQLMouseClicked(evt);
        }
    });
        
        try {
            //QUAN LY
            String sql_QL = "SELECT TENDANGNHAP, TENNHANVIEN, SDT, GIOITINH, NGAYSINH FROM NHAN_VIEN WHERE LAQUANLY = 1";
            PreparedStatement pst_QL = conn.prepareStatement(sql_QL);

            DefaultTableModel model_QL = (DefaultTableModel) tblQL.getModel();

            ResultSet rs_QL = pst_QL.executeQuery();
            while (rs_QL.next()) {
                Object[] rowData = new Object[]{
                    counterQL++,
                    rs_QL.getObject("TENDANGNHAP"),
                    rs_QL.getObject("TENNHANVIEN"),
                    rs_QL.getObject("SDT"),
                    rs_QL.getObject("GIOITINH"),
                    rs_QL.getObject("NGAYSINH"),
                    
                };
                model_QL.addRow(rowData);
            }
            
            tblQL.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblQL.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblQL.getColumnModel().getColumn(2).setPreferredWidth(200);
            tblQL.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblQL.getColumnModel().getColumn(4).setPreferredWidth(50);
            tblQL.getColumnModel().getColumn(5).setPreferredWidth(100);
            tblQL.getColumnModel().getColumn(6).setPreferredWidth(150);
            
            rs_QL.close();
            pst_QL.close();
            
            //NHAN VIEN
            String sql_NV = "SELECT TENDANGNHAP, TENNHANVIEN, SDT, GIOITINH, NGAYSINH FROM NHAN_VIEN WHERE LAQUANLY = 0";
            PreparedStatement pst_NV = conn.prepareStatement(sql_NV);

            DefaultTableModel model_NV = (DefaultTableModel) tblNV.getModel();

            ResultSet rs_NV = pst_NV.executeQuery();
            while (rs_NV.next()) {
                Object[] rowData = new Object[]{
                    counterNV++,
                    rs_NV.getObject("TENDANGNHAP"),
                    rs_NV.getObject("TENNHANVIEN"),
                    rs_NV.getObject("SDT"),
                    rs_NV.getObject("GIOITINH"),
                    rs_NV.getObject("NGAYSINH"),
                    
                };
                model_NV.addRow(rowData);
            }
            
            tblNV.getColumnModel().getColumn(0).setPreferredWidth(10); // Row number
            tblNV.getColumnModel().getColumn(1).setPreferredWidth(100); // Tên Đăng Nhập
            tblNV.getColumnModel().getColumn(2).setPreferredWidth(200); // Tên Nhân Viên
            tblNV.getColumnModel().getColumn(3).setPreferredWidth(100); // Số Điện Thoại
            tblNV.getColumnModel().getColumn(4).setPreferredWidth(50); // Giới Tính
            tblNV.getColumnModel().getColumn(5).setPreferredWidth(100); // Ngày Sinh
            tblNV.getColumnModel().getColumn(6).setPreferredWidth(150); // Thay đổi
            
            rs_NV.close();
            pst_NV.close();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Loi: " + e.getMessage());
        }
    }
    
    private String getPasswordInput() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("De thuc hien cap quyen, ban can xac thuc mat khau quan ly ");
        JPasswordField passwordField = new JPasswordField(10);
        panel.add(label);
        panel.add(passwordField);

        String[] options = new String[]{"YES", "NO"};
        int option = JOptionPane.showOptionDialog(null, panel, "Xac nhan mat khau",
                        JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[1]);

        // Doc mat khau chi khi chon "YES"
        if (option == 0) {
            char[] password = passwordField.getPassword();
            return new String(password);
        } else {
            return null; //Gia tri mac dinh la "NO"
        }
    }
    
    private void updateDatabaseForLaQuanLy(String tendangnhap, int laquanly) {
        try {
            ConnectDB cn = new ConnectDB();
            Connection conn = cn.getConnection();

            String updateSql = "UPDATE NHAN_VIEN SET LAQUANLY = ? WHERE TENDANGNHAP = ?";
            PreparedStatement pst = conn.prepareStatement(updateSql);

            pst.setInt(1, laquanly); // Đặt tham số cho LAQUANLY
            pst.setString(2, tendangnhap); // Đặt tham số cho TENDANGNHAP

            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Database updated successfully for LAQUANLY=" + laquanly);
            } else {
                System.out.println("Failed to update database for LAQUANLY=" + laquanly);
            }

            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQL = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNV = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("Nhân Viên Quản Lý");
        jLabel1.setAutoscrolls(true);

        tblQL.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblQL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Tên Đăng Nhập", "Tên Nhân Viên", "Số Điện Thoại", "Giới Tính", "Ngày Sinh", "Thay Đổi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblQL.setRowHeight(20);
        tblQL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQL);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setText("Nhân Viên Bán Hàng");

        tblNV.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Tên Đăng Nhập", "Tên Nhân Viên", "Số Điện Thoại", "Giới Tính", "Ngày Sinh", "Thay Đổi"
            }
        ));
        tblNV.setMaximumSize(new java.awt.Dimension(2147483647, 80));
        tblNV.setMinimumSize(new java.awt.Dimension(75, 80));
        tblNV.setRowHeight(20);
        tblNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNVMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNV);

        jButton1.setText("Thoát");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(497, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(479, 479, 479))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(434, 434, 434))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(127, 127, 127))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblQLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLMouseClicked
        if (evt.getClickCount() == 1) {
            int selectedRow = tblQL.getSelectedRow();
            if (selectedRow != -1) {
                String tendangnhap = tblQL.getValueAt(selectedRow, 1).toString();
                Object[] rowData = new Object[]{
                    counterNV++,
                    tblQL.getValueAt(selectedRow, 1), // Ten Dang Nhap
                    tblQL.getValueAt(selectedRow, 2), // Ten Nhan Vien
                    tblQL.getValueAt(selectedRow, 3), // SDT
                    tblQL.getValueAt(selectedRow, 4), // Gioi Tinh
                    tblQL.getValueAt(selectedRow, 5), // Ngay Sinh
                };

                // Add data tblNV
                DefaultTableModel model_NV = (DefaultTableModel) tblNV.getModel();
                model_NV.addRow(rowData);

                // Remove data tblQL
                DefaultTableModel model_QL = (DefaultTableModel) tblQL.getModel();
                model_QL.removeRow(selectedRow);

                // Update row numbers in tblQL
                for (int i = 0; i < model_QL.getRowCount(); i++) {
                    model_QL.setValueAt(i + 1, i, 0);
                }

                // Update row numbers in tblNV
                for (int i = 0; i < model_NV.getRowCount(); i++) {
                    model_NV.setValueAt(i + 1, i, 0);
                }
                updateDatabaseForLaQuanLy(tendangnhap, 0);
            }
        }
    }//GEN-LAST:event_tblQLMouseClicked

    private void tblNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNVMouseClicked
        if (evt.getClickCount() == 1) {
            String enteredPassword = getPasswordInput();
            if (enteredPassword != null) {
                //Xac thuc mat khau va chuyen dong du lieu
                if (verifyPasswordForAdminRows(enteredPassword)) {
                    int selectedRow = tblNV.getSelectedRow();
                    if (selectedRow != -1) {
                        DefaultTableModel model_QL = (DefaultTableModel) tblQL.getModel();
                        String tendangnhap = tblNV.getValueAt(selectedRow, 1).toString();
                        Object[] rowData = new Object[]{
                            model_QL.getRowCount() + 1, 
                            tblNV.getValueAt(selectedRow, 1), // Ten Dang Nhap
                            tblNV.getValueAt(selectedRow, 2), // Ten Nhan Vien
                            tblNV.getValueAt(selectedRow, 3), // SDT
                            tblNV.getValueAt(selectedRow, 4), // Gioi Tinh
                            tblNV.getValueAt(selectedRow, 5), // Ngay Sinh
                        };

                        // Add data tblQL
                        model_QL.addRow(rowData);

                        // Remove data tblNV
                        DefaultTableModel model_NV = (DefaultTableModel) tblNV.getModel();
                        model_NV.removeRow(selectedRow);

                        // Update row numbers in tblQL
                        for (int i = 0; i < model_QL.getRowCount(); i++) {
                            model_QL.setValueAt(i + 1, i, 0);
                        }

                        // Update row numbers in tblNV
                        for (int i = 0; i < model_NV.getRowCount(); i++) {
                            model_NV.setValueAt(i + 1, i, 0);
                        }
                        
                        updateDatabaseForLaQuanLy(tendangnhap, 1);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Xac thuc mat khau khong thanh cong. Vui long thu lai.", "Loi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_tblNVMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.hide();
        frmHomePage frm= new frmHomePage();
        frm.show();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private boolean verifyPasswordForAdminRows(String enteredPassword) {
        try {
            String sql = "SELECT COUNT(*) AS count FROM NHAN_VIEN WHERE LAQUANLY = 1 AND MATKHAU =?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, enteredPassword);

            ResultSet rs = pst.executeQuery();
            rs.next();

            int rowCount = rs.getInt("count");

            //rs.close();
            //pst.close();
            //conn.close();

            //Neu co it nhat mot dong co mat khau khop, tra ve true
            return rowCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQL Exception: " + e.getMessage());
        }

        return false;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formPhanQuyen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formPhanQuyen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formPhanQuyen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formPhanQuyen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new formPhanQuyen().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(formPhanQuyen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblNV;
    private javax.swing.JTable tblQL;
    // End of variables declaration//GEN-END:variables
}
