package entity;

import dto.OrderDetailsDTO;

import java.util.ArrayList;

public class Order {
    private String orderID;
    private String orderDate;
    private String custID;
    private double cost;

    public Order() {
    }

    public Order(String orderID, String orderDate, String custID, double cost) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.custID = custID;
        this.cost = cost;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}
