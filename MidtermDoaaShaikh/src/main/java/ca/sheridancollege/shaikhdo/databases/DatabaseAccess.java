package ca.sheridancollege.shaikhdo.databases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import ca.sheridancollege.shaikhdo.beans.Item;

@Repository

public class DatabaseAccess {
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	
	public void addItem(Item item) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();;
		String query = "INSERT INTO item(name, price, descr, link, qty) VALUES (:name, :price, :descr, :link, :qty)";
		namedParameters.addValue("name", item.getName()); 
		namedParameters.addValue("price", item.getPrice());
		namedParameters.addValue("descr", item.getDescr()); 
		namedParameters.addValue("link", item.getLink()); 
		namedParameters.addValue("qty", item.getQty()); 
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0)
			System.out.println("Added item to shopping list!.");
	}
	
	public List<Item> getItems() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * from item";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Item>(Item.class));
	}
	
	public void deleteItemById(int id){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE FROM item WHERE id= :id";
		namedParameters.addValue("id", id);
		
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0)
			System.out.println("Deleted item " + id + " from database.");
	}
	
	public List<Item> getItemById(int id){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query="SELECT * FROM item WHERE id = :id";
		namedParameters.addValue("id", id);
		
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Item>(Item.class));
	}
	
	

}
