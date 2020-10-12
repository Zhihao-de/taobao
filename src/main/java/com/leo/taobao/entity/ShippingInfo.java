package com.leo.taobao.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ShippingInfo {
    private Byte shipId;

    private String shipName;

    private String shipContact;

    private String telephone;

    private BigDecimal price;

    private Date modifiedTime;

    public Byte getShipId() {
        return shipId;
    }

    public void setShipId(Byte shipId) {
        this.shipId = shipId;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName == null ? null : shipName.trim();
    }

    public String getShipContact() {
        return shipContact;
    }

    public void setShipContact(String shipContact) {
        this.shipContact = shipContact == null ? null : shipContact.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}