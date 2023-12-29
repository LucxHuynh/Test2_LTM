package model;


import java.io.Serializable;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Than
 */
public class MatHang implements Serializable {

    private String maMH,tenMH,DVT,maLoai,moTa;
    private int giaBan;
    private boolean disable;// dd/mm/yyyy

    public String getMaMH() {
        return maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public String getDVT() {
        return DVT;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

}
