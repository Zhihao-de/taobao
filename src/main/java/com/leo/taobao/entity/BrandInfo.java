package com.leo.taobao.entity;

import java.util.Date;

public class BrandInfo {
    private Short brandId;

    private String brandName;

    private String telephone;

    private String brandWeb;

    private String brandLogo;

    private String brandDesc;

    private Byte brandStatus;

    private Byte brandOrder;

    private Date modifiedTime;

    public Short getBrandId() {
        return brandId;
    }

    public void setBrandId(Short brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getBrandWeb() {
        return brandWeb;
    }

    public void setBrandWeb(String brandWeb) {
        this.brandWeb = brandWeb == null ? null : brandWeb.trim();
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo == null ? null : brandLogo.trim();
    }

    public String getBrandDesc() {
        return brandDesc;
    }

    public void setBrandDesc(String brandDesc) {
        this.brandDesc = brandDesc == null ? null : brandDesc.trim();
    }

    public Byte getBrandStatus() {
        return brandStatus;
    }

    public void setBrandStatus(Byte brandStatus) {
        this.brandStatus = brandStatus;
    }

    public Byte getBrandOrder() {
        return brandOrder;
    }

    public void setBrandOrder(Byte brandOrder) {
        this.brandOrder = brandOrder;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}