package com.bcu.ccshop.model;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import org.antlr.v4.codegen.model.SrcOp;

public class orderItemIcon {
    public String getoID() {
        return oID;
    }

    public void setoID(String oID) {
        this.oID = oID;
    }

    public String getoNmae() {
        return oNmae;
    }

    public void setoNmae(String oNmae) {
        this.oNmae = oNmae;
    }

    public Bitmap getoImage() {
        return oImage;
    }

    public void setoImage(Bitmap oImage) {
        this.oImage = oImage;
    }

    public String getoCount() {
        return oCount;
    }

    public void setoCount(String oCount) {
        this.oCount = oCount;
    }

    public String getoFee() {
        return oFee;
    }

    public void setoFee(String oFee) {
        this.oFee = oFee;
    }

    public String getoGFee() {
        return oGFee;
    }

    public void setoGFee(String oGFee) {
        this.oGFee = oGFee;
    }

    public orderItemIcon(String oID, String oNmae, Bitmap oImage, String oCount, String oFee, String oGFee,String oFDate) {
        this.oID = oID;
        this.oNmae = oNmae;
        this.oImage = oImage;
        this.oCount = oCount;
        this.oFee = oFee;
        this.oGFee = oGFee;
        this.oFDate=oFDate;
    }

    private String oID;
    private String oNmae;
    private Bitmap oImage;
    private String oCount;
    private String oFee;
    private String oGFee;

    public String getoFDate() {
        return oFDate;
    }

    public void setoFDate(String oFDate) {
        this.oFDate = oFDate;
    }

    private String oFDate;










}
