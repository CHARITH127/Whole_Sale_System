package bo.custom;

import bo.SuperBO;
import dto.CustomerChart;
import dto.OrderDTO;
import dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PurchaseOrderBO extends SuperBO {
    String orderId() throws SQLException, ClassNotFoundException;

    boolean placeOder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;

    boolean saveOrderDetails(String oderID,ArrayList<OrderDetailsDTO> items) throws SQLException, ClassNotFoundException;

    boolean upadateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;

    List<String> getOrderIdes(String custId) throws SQLException, ClassNotFoundException;

    List<CustomerChart> getCustomerAndOrderDetails() throws SQLException, ClassNotFoundException;
}
