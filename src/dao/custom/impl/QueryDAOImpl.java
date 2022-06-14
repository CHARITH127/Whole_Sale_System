package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.QueryDAO;
import dto.CustomDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<CustomDTO> getOrderDetailsFromOrderID(String oid) throws SQLException, ClassNotFoundException {
        ArrayList<CustomDTO> dtos = new ArrayList<>();
        ResultSet set = CrudUtil.executeQuery("select o.OrderID,o.OrderDate,o.CustID,od.ItemCode,od.OrderQty,od.Discount from `order` o inner join `order detail` od on o.OrderID=od.OrderID where o.OrderID=?;", oid);
        while (set.next()) {
            dtos.add(new CustomDTO(set.getString("OrderID"),set.getDate("OrderDate"),set.getString("CustID"),set.getString("ItemCode"),set.getInt("OrderQty"),set.getDouble("Discount")));
        }
        return dtos;
    }
}
