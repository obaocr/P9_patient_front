package com.ocr.poseidon.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer Id;
    private Integer curveId;
    @Basic
    private LocalDateTime asOfDate;
    @Digits(integer=5, fraction=0)
    private Double term;
    @Digits(integer=5, fraction=0)
    private Double value;
    @Basic
    private LocalDateTime creationDate;

    public CurvePoint(Integer curveId, Double term, Double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    public CurvePoint() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = Id;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public LocalDateTime getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(LocalDateTime asOfDate) {
        this.asOfDate = asOfDate;
    }

    public Double getTerm() {
        return term;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}

