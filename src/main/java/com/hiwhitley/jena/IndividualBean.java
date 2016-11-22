package com.hiwhitley.jena;

/**
 * Created by hiwhitley on 16-11-18.
 */
public class IndividualBean {
    private String name = "";
    private int consumption = 0;
    private String address = "";
    private String feature = "";
    private String tel = "";
    private double grade = 0.0;

    public int getConsumption() {
        return consumption;
    }

    public IndividualBean setConsumption(int consumption) {
        this.consumption = consumption;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public IndividualBean setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getFeature() {
        return feature;
    }

    public IndividualBean setFeature(String feature) {
        this.feature = feature;
        return this;
    }

    public String getTel() {
        return tel;
    }

    public IndividualBean setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public double getGrade() {
        return grade;
    }

    public IndividualBean setGrade(double grade) {
        this.grade = grade;
        return this;
    }

    public String getName() {

        return name;
    }

    public IndividualBean setName(String name) {
        this.name = name;
        return this;
    }


}
