package ca.sheridancollege.shaikhdo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.shaikhdo.beans.Item;
import ca.sheridancollege.shaikhdo.databases.DatabaseAccess;

@Controller

public class itemController {

	@Autowired
	private DatabaseAccess da;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("item", new Item());

		model.addAttribute("getItems", da.getItems());
		return "index";
	}

	@PostMapping("/addItem")
	public String addItem(Model model, @ModelAttribute Item item) {
		da.addItem(item);
		model.addAttribute("item", new Item());
		model.addAttribute("getItems", da.getItems());
		return "index";
	}

	@GetMapping("/deleteItemById/{id}")
	public String deleteItemById(Model model, @PathVariable int id) {
		da.deleteItemById(id);
		model.addAttribute("item", new Item());
		model.addAttribute("getItems", da.getItems());
		return "index";
	}

	@GetMapping("/editItemById/{id}")
	public String editItemById(Model model, @PathVariable int id) {
		Item item = da.getItemById(id).get(0);
		da.deleteItemById(id);
		model.addAttribute("item", item);
		model.addAttribute("getItems", da.getItems());
		return "index";
	}

}
