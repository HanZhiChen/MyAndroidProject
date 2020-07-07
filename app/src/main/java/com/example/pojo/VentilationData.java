package com.example.pojo;

public class VentilationData {
    private int id;//主键
    private int number;//编号
    private String address;//测定地点
    private String roadway;//巷道形状
    private double width;//宽度
    private double height;//高度
    private double airSpeed1;//风速1
    private double airSpeed2;//风速2
    private double airSpeed3;//风速3
    private double airAmend1;//风表修正系数a
    private double airAmend2;//风表修正系数b
    private double avgAir;//平均风速
    private double airGion;//风量
    private double gassdensity;//瓦斯浓度
    private double leeway;//风压
    private double temperature;//温度
    private String routingDate;//巡检时间
    private int inspector;//巡检员
    private String picture;//现场图片

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoadway() {
        return roadway;
    }

    public void setRoadway(String roadway) {
        this.roadway = roadway;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getAirSpeed1() {
        return airSpeed1;
    }

    public void setAirSpeed1(double airSpeed1) {
        this.airSpeed1 = airSpeed1;
    }

    public double getAirSpeed2() {
        return airSpeed2;
    }

    public void setAirSpeed2(double airSpeed2) {
        this.airSpeed2 = airSpeed2;
    }

    public double getAirSpeed3() {
        return airSpeed3;
    }

    public void setAirSpeed3(double airSpeed3) {
        this.airSpeed3 = airSpeed3;
    }

    public double getAirAmend1() {
        return airAmend1;
    }

    public void setAirAmend1(double airAmend1) {
        this.airAmend1 = airAmend1;
    }

    public double getAirAmend2() {
        return airAmend2;
    }

    public void setAirAmend2(double airAmend2) {
        this.airAmend2 = airAmend2;
    }

    public double getAvgAir() {
        return avgAir;
    }

    public void setAvgAir(double avgAir) {
        this.avgAir = avgAir;
    }

    public double getAirGion() {
        return airGion;
    }

    public void setAirGion(double airGion) {
        this.airGion = airGion;
    }

    public double getGassdensity() {
        return gassdensity;
    }

    public void setGassdensity(double gassdensity) {
        this.gassdensity = gassdensity;
    }

    public double getLeeway() {
        return leeway;
    }

    public void setLeeway(double leeway) {
        this.leeway = leeway;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getRoutingDate() {
        return routingDate;
    }

    public void setRoutingDate(String routingDate) {
        this.routingDate = routingDate;
    }

    public int getInspector() {
        return inspector;
    }

    public void setInspector(int inspector) {
        this.inspector = inspector;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
