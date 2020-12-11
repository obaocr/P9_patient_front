package com.ocr.p9front.domain;

import java.time.LocalDateTime;
import java.util.Date;

public class PatientDTO {

    // TODO Annotations format de champs
    private Integer Id;
    private String familly;
    private String given;
    private String address;
    private String phone;
    private String sex;
    private Date birthDate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public PatientDTO() {
    }

    public PatientDTO(String familly, String given, String address, String phone, String sex, Date birthDate) {
        this.familly = familly;
        this.given = given;
        this.address = address;
        this.phone = phone;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getFamilly() {
        return familly;
    }

    public void setFamilly(String familly) {
        this.familly = familly;
    }

    public String getGiven() {
        return given;
    }

    public void setGiven(String given) {
        this.given = given;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
