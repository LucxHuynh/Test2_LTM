/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.MatHang;

/**
 *
 * @author Than
 */
public class DAO {
    private Connection conn;
    
    public DAO(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=VANPHONGPHAM;"
                    + "username=sa;password=huynhtanloc_0x");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean addMatHang(MatHang s){
        
        String sql = "INSERT INTO MAT_HANG(MAMH, TENMH, GIABAN, DVT, MALOAI, MOTA, VOHIEUHOA) "
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getMaMH());
            ps.setString(2, s.getTenMH());
            ps.setInt(3, s.getGiaBan());
            ps.setString(4, s.getDVT());
            ps.setString(5, s.getMaLoai());
            ps.setString(6, s.getMoTa());
            ps.setBoolean(7, s.isDisable());
            
            return (ps.executeUpdate() > 0);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
    public boolean updateMatHang(MatHang s) {
    String sql = "UPDATE MAT_HANG SET TENMH=?, GIABAN=?, DVT=?, MALOAI=?, MOTA=?, VOHIEUHOA=? WHERE MAMH=?";
    
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, s.getTenMH());
        ps.setInt(2, s.getGiaBan());
        ps.setString(3, s.getDVT());
        ps.setString(4, s.getMaLoai());
        ps.setString(5, s.getMoTa());
        ps.setBoolean(6, s.isDisable());
        ps.setString(7, s.getMaMH());

        return (ps.executeUpdate() > 0);

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
    }
    public ArrayList<MatHang> getListMatHang(){
        ArrayList<MatHang> list = new ArrayList<>();
        String sql = "SELECT * FROM MAT_HANG";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                MatHang s = new MatHang();
                s.setMaMH(rs.getString("MAMH"));
                s.setTenMH(rs.getString("TENMH"));
                s.setGiaBan(rs.getInt("GIABAN"));
                s.setDVT(rs.getString("DVT"));
                s.setMaLoai(rs.getString("MALOAI"));
                s.setMoTa(rs.getString("MOTA"));
                s.setDisable(rs.getBoolean("VOHIEUHOA"));
                
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    public boolean deleteMatHang(String maMH) {
    String sqlSelect = "SELECT * FROM MAT_HANG WHERE MAMH=?";
    String sqlDelete = "DELETE FROM MAT_HANG WHERE MAMH=?";
    
    try {
        // Kiểm tra xem mặt hàng có tồn tại hay không
        PreparedStatement psSelect = conn.prepareStatement(sqlSelect);
        psSelect.setString(1, maMH);
        ResultSet rs = psSelect.executeQuery();

        if (!rs.next()) {
            // Mặt hàng không tồn tại
            return false;
        }

        // Thực hiện xóa dữ liệu từ cơ sở dữ liệu
        PreparedStatement psDelete = conn.prepareStatement(sqlDelete);
        psDelete.setString(1, maMH);

        boolean deleteSuccess = (psDelete.executeUpdate() > 0);

        if (deleteSuccess) {
            // Xóa thành công, có thể thực hiện thêm các bước khác sau khi xóa
        } else {
            // Xóa không thành công, có thể thực hiện các xử lý khác hoặc thông báo lỗi
        }

        return deleteSuccess;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
    }


    public boolean updateListMatHangFromForm(MatHang updatedMatHang, ArrayList<MatHang> matHangList) {
    boolean success = false;

    // Duyệt qua danh sách mặt hàng
    for (MatHang matHang : matHangList) {
        // Kiểm tra nếu mã mặt hàng của mặt hàng hiện tại trùng với mã mặt hàng cần cập nhật
        if (matHang.getMaMH().equals(updatedMatHang.getMaMH())) {
            // Cập nhật thông tin mặt hàng
            matHang.setTenMH(updatedMatHang.getTenMH());
            matHang.setGiaBan(updatedMatHang.getGiaBan());
            matHang.setDVT(updatedMatHang.getDVT());
            matHang.setMaLoai(updatedMatHang.getMaLoai());
            matHang.setMoTa(updatedMatHang.getMoTa());
            matHang.setDisable(updatedMatHang.isDisable());

            // Gọi hàm updateMatHang để cập nhật vào cơ sở dữ liệu
            if (updateMatHang(matHang)) {
                success = true;
            }
            break; // Dừng vòng lặp sau khi tìm thấy và cập nhật mặt hàng
        }
    }

    return success;
    }

    public static void main(String[] args) {
        new DAO();
    }
}
