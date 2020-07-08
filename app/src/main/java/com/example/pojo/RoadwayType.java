package com.example.pojo;

public class RoadwayType {
    private int id;
    private String status;//巷道形状
    private String described;//巷道形状描述

    public RoadwayType(){

    }

    public RoadwayType(String status){
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescribed() {
        return described;
    }

    public void setDescribed(String described) {
        this.described = described;
    }
}
