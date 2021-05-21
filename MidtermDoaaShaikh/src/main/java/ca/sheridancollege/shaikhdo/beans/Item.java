package ca.sheridancollege.shaikhdo.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@Data


public class Item {

	private int id;
	private String name;
	private Double price; 
	private String descr;
	private String link;
	private int qty;
}
