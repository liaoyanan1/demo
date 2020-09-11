package com.example.gatewayservice.entity;


public class GrayRelease {

    private int id;
    private String serviceId;
    private String path;
    private int enableGrayRelease;

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    private int percent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getEnableGrayRelease() {
        return enableGrayRelease;
    }

    public void setEnableGrayRelease(int enableGrayRelease) {
        this.enableGrayRelease = enableGrayRelease;
    }
}
