package org.apache.ibatis.demo.model;

import java.io.Serializable;

/**
 * @author admin
 * @date 2020/3/26 18:30
 */
public class People implements Serializable {

    private int id;
    private String name;
    private String identifyId;
    private double weight;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifyId() {
        return identifyId;
    }

    public void setIdentifyId(String identifyId) {
        this.identifyId = identifyId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "[id=" + id + ",name=" + name + ",identifyid=" + identifyId + ",weight=" + weight + "]";
    }

}
