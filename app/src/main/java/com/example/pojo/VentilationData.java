package com.example.pojo;

public class VentilationData {
    private int id;//主键
    private int number;//编号
    private String address;//测定地点
    private String roadway;//巷道形状
    private double width;//宽度
    private double height;//高度
    private double airspeed1;//风速1
    private double airspeed2;//风速2
    private double airspeed3;//风速3
    private double airamend1;//风表修正系数a
    private double airamend2;//风表修正系数b
    private double avgair;//平均风速
    private double airgion;//风量
    private double gassdensity;//瓦斯浓度
    private double leeway;//风压
    private double temperature;//温度
    private String routingdate;//巡检时间
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

    public double getAirspeed1() {
        return airspeed1;
    }

    public void setAirspeed1(double airspeed1) {
        this.airspeed1 = airspeed1;
    }

    public double getAirspeed2() {
        return airspeed2;
    }

    public void setAirspeed2(double airspeed2) {
        this.airspeed2 = airspeed2;
    }

    public double getAirspeed3() {
        return airspeed3;
    }

    public void setAirspeed3(double airspeed3) {
        this.airspeed3 = airspeed3;
    }

    public double getAiramend1() {
        return airamend1;
    }

    public void setAiramend1(double airamend1) {
        this.airamend1 = airamend1;
    }

    public double getAiramend2() {
        return airamend2;
    }

    public void setAiramend2(double airamend2) {
        this.airamend2 = airamend2;
    }

    public double getAvgair() {
        return avgair;
    }

    public void setAvgair(double avgair) {
        this.avgair = avgair;
    }

    public double getAirgion() {
        return airgion;
    }

    public void setAirgion(double airgion) {
        this.airgion = airgion;
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

    public String getRoutingdate() {
        return routingdate;
    }

    public void setRoutingdate(String routingdate) {
        this.routingdate = routingdate;
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
