package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDetailsDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dto.OrderDetailsDTO;
import view.tm.ManageOrderTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    int quantity;
    @Override
    public boolean add(OrderDetailsDTO orderDetailsDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `Order Detail` VALUES (?,?,?,?)", orderDetailsDTO.getItemCode(), orderDetailsDTO.getOrderId(), orderDetailsDTO.getOrderQty(), orderDetailsDTO.getDiscount());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM `Order detail` WHERE OrderID=?",code);

    }

    @Override
    public boolean update(OrderDetailsDTO orderDetailsDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetailsDTO search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDetailsDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<ManageOrderTm> getItems(String orderId) throws SQLException, ClassNotFoundException {
        ResultSet set =CrudUtil.executeQuery("SELECT * FROM `Order Detail` WHERE OrderID=?",orderId);
        ObservableList<ManageOrderTm> itemsDetails = FXCollections.observableArrayList();
        while (set.next()) {
            itemsDetails.add(new ManageOrderTm(
                    set.getString(1),
                    Integer.parseInt(set.getString(3)),
                    Double.parseDouble(set.getString(4))
            ));
        }
        return itemsDetails;
    }

    @Override
    public List<String> getAllItemCodesOfOrderDetail() throws SQLException, ClassNotFoundException {
        ResultSet set =CrudUtil.executeQuery("SELECT * FROM `Order Detail` ");
        List<String > idemCodes = new ArrayList<>();
        while (set.next()){
            idemCodes.add(set.getString(1));
        }
        return idemCodes;
    }

    public int getItemQuantity(String itemCode) throws SQLException, ClassNotFoundException {
        ResultSet set =CrudUtil.executeQuery("SELECT * FROM `Order Detail` WHERE ItemCode=?",itemCode);
        while (set.next()){
            quantity+=Integer.parseInt(set.getString(3));
        }
        return quantity;
    }

}
