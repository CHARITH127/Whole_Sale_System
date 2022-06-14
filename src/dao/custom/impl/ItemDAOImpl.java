package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Item VALUES(?,?,?,?,?,?)", item.getItemCode(), item.getDescription(), item.getPackSize(), item.getUnitPrice(), item.getQtyOnHand(), item.getDiscount());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Item WHERE ItemCode = ?", code);
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Item SET Description=?, PackSize=?, UnitPrice=?,QtyOnHand=?,Discount=?  WHERE ItemCode=?", item.getDescription(), item.getPackSize(), item.getUnitPrice(), item.getQtyOnHand(), item.getDiscount(), item.getItemCode());
    }

    @Override
    public Item search(String code) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.executeQuery("SELECT * FROM Item WHERE ItemCode=?", code);
        set.next();
        return new Item(set.getString(1), set.getString(2), set.getString(3), set.getDouble(4), set.getInt(5), set.getDouble(6));
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public List<String> getItemIdeas() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.executeQuery("SELECT * FROM Item");
        List<String> itemIdes = new ArrayList<>();
        while (set.next()) {
            itemIdes.add(set.getString(1)
            );
        }
        return itemIdes;
    }

    @Override
    public boolean upadateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Item SET QtyOnHand=(QtyOnHand-" + qty + ")WHERE ItemCode =?",itemCode);
    }
}
