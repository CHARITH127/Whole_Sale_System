package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDAO;
import dto.OrderDTO;
import entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Order order) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `Order` VALUES (?,?,?,?)", order.getOrderID(), order.getOrderDate(), order.getCustID(), order.getCost());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
      return   CrudUtil.executeUpdate("DELETE FROM `Order` WHERE OrderID=?",code);
    }

    @Override
    public boolean update(Order order) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public Order search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public String genarateOrderId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.executeQuery("SELECT * FROM `Order` ORDER BY orderId DESC LIMIT 1");
        if (set.next()) {
            int temp = Integer.parseInt(set.getString(1).split("-")[1]);
            temp = temp + 1;
            if (temp <= 9) {
                return "O-00" + temp;
            } else if (temp < 99) {
                return "O-0" + temp;
            } else {
                return "O-" + temp;
            }
        } else {
            return "O-001";
        }
    }

    @Override
    public List<String> getOrderIdes(String custId) throws SQLException, ClassNotFoundException {
        ResultSet set =CrudUtil.executeQuery("SELECT * FROM `Order` WHERE CustID=?",custId);
        List<String> OrderID = new ArrayList<>();
        while (set.next()) {
            OrderID.add(set.getString(1));
        }
        return OrderID;
    }
}
