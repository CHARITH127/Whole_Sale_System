package dao.custom;

import dao.CrudDAO;
import dto.ItemDTO;
import entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item, String> {
    List<String> getItemIdeas() throws SQLException, ClassNotFoundException;
    boolean upadateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;
}
