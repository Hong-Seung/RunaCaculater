
package com.hseunghyun.runacaculater.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("lunDay")
    @Expose
    private Integer lunDay;
    @SerializedName("lunIljin")
    @Expose
    private String lunIljin;
    @SerializedName("lunLeapmonth")
    @Expose
    private String lunLeapmonth;
    @SerializedName("lunMonth")
    @Expose
    private Integer lunMonth;
    @SerializedName("lunNday")
    @Expose
    private Integer lunNday;
    @SerializedName("lunSecha")
    @Expose
    private String lunSecha;
    @SerializedName("lunWolgeon")
    @Expose
    private String lunWolgeon;
    @SerializedName("lunYear")
    @Expose
    private Integer lunYear;
    @SerializedName("solDay")
    @Expose
    private String solDay;
    @SerializedName("solJd")
    @Expose
    private Integer solJd;
    @SerializedName("solLeapyear")
    @Expose
    private String solLeapyear;
    @SerializedName("solMonth")
    @Expose
    private String solMonth;
    @SerializedName("solWeek")
    @Expose
    private String solWeek;
    @SerializedName("solYear")
    @Expose
    private Integer solYear;

    public Integer getLunDay() {
        return lunDay;
    }

    public void setLunDay(Integer lunDay) {
        this.lunDay = lunDay;
    }

    public String getLunIljin() {
        return lunIljin;
    }

    public void setLunIljin(String lunIljin) {
        this.lunIljin = lunIljin;
    }

    public String getLunLeapmonth() {
        return lunLeapmonth;
    }

    public void setLunLeapmonth(String lunLeapmonth) {
        this.lunLeapmonth = lunLeapmonth;
    }

    public Integer getLunMonth() {
        return lunMonth;
    }

    public void setLunMonth(Integer lunMonth) {
        this.lunMonth = lunMonth;
    }

    public Integer getLunNday() {
        return lunNday;
    }

    public void setLunNday(Integer lunNday) {
        this.lunNday = lunNday;
    }

    public String getLunSecha() {
        return lunSecha;
    }

    public void setLunSecha(String lunSecha) {
        this.lunSecha = lunSecha;
    }

    public String getLunWolgeon() {
        return lunWolgeon;
    }

    public void setLunWolgeon(String lunWolgeon) {
        this.lunWolgeon = lunWolgeon;
    }

    public Integer getLunYear() {
        return lunYear;
    }

    public void setLunYear(Integer lunYear) {
        this.lunYear = lunYear;
    }

    public String getSolDay() {
        return solDay;
    }

    public void setSolDay(String solDay) {
        this.solDay = solDay;
    }

    public Integer getSolJd() {
        return solJd;
    }

    public void setSolJd(Integer solJd) {
        this.solJd = solJd;
    }

    public String getSolLeapyear() {
        return solLeapyear;
    }

    public void setSolLeapyear(String solLeapyear) {
        this.solLeapyear = solLeapyear;
    }

    public String getSolMonth() {
        return solMonth;
    }

    public void setSolMonth(String solMonth) {
        this.solMonth = solMonth;
    }

    public String getSolWeek() {
        return solWeek;
    }

    public void setSolWeek(String solWeek) {
        this.solWeek = solWeek;
    }

    public Integer getSolYear() {
        return solYear;
    }

    public void setSolYear(Integer solYear) {
        this.solYear = solYear;
    }

}
