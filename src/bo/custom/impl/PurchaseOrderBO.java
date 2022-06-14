package bo.custom.impl;

import dao.CrudUtil;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailsDAO;
import db.DbConnection;
import dto.CustomerChart;
import dto.OrderDTO;
import dto.OrderDetailsDTO;
import entity.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderBO implements bo.custom.PurchaseOrderBO {
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public String orderId() throws SQLException, ClassNotFoundException {
        return orderDAO.genarateOrderId();
    }

    @Override
    public boolean placeOder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        if (orderDAO.add(new Order(orderDTO.getOrderID(), orderDTO.getOrderDate(), orderDTO.getCustID(), orderDTO.getCost()))) {
            if (saveOrderDetails(orderDTO.getOrderID(), orderDTO.getItems())) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } else {
            connection.rollback();
            return false;
        }

    }

    @Override
    public boolean saveOrderDetails(String orderID, ArrayList<OrderDetailsDTO> items) throws SQLException, ClassNotFoundException {
        for (OrderDetailsDTO temp : items
        ) {
            OrderDetailsDTO details = new OrderDetailsDTO();
            System.out.println(details);
            if (orderDetailsDAO.add(new OrderDetailsDTO(temp.getItemCode(), orderID, temp.getOrderQty(), temp.getDiscount()))) {
                if (upadateQty(temp.getItemCode(), temp.getOrderQty())) {

                } else {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean upadateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {

        return itemDAO.upadateQty(itemCode, qty);
    }

    @Override
    public List<String> getOrderIdes(String custId) throws SQLException, ClassNotFoundException {
        return orderDAO.getOrderIdes(custId);

    }

    @Override
    public List<CustomerChart> getCustomerAndOrderDetails() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.executeQuery("SELECT * FROM `Order`");
        List<CustomerChart> cutomerIncome = new ArrayList<>();
        while (set.next()) {
            cutomerIncome.add(new CustomerChart(
                    set.getString(3),
                    Double.parseDouble(set.getString(4))
            ));
        }
        return cutomerIncome;
    }
}
