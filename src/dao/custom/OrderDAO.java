package dao.custom;

import dao.CrudDAO;
import dto.OrderDTO;
import entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends CrudDAO<Order,String> {
    String genarateOrderId() throws SQLException, ClassNotFoundException;
    List<String> getOrderIdes(String custId) throws SQLException, ClassNotFoundException;
}
