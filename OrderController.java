package com.qa.ims.controller;

import java.util.List;		

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {
	
	//PUT ITEMID IN HERE
	
	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	//private Item item;
	//private Customer customer;
	private Utils utils;
	
	private Long itemId;
	
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;

	}
	
	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter a customer ID");
		Long customerId = utils.getLong();
		Order order = orderDAO.create(new Order(customerId));
		LOGGER.info("Order created");
		return order;
	}

	@Override
	public Order update() {
		Order order = null;
		LOGGER.info("Please enter the id of the order you would like to update");
		Long orderId = utils.getLong();
		LOGGER.info("Please select if you would like to ADD or REMOVE from the order");
		String addRemove = utils.getString();
		if(addRemove.equalsIgnoreCase("add")) {
			LOGGER.info("Please enter the id of the item to be added to the order");
		order = orderDAO.addToOrder(orderId, itemId);
		}else if(addRemove.equalsIgnoreCase("remove")) {
			LOGGER.info("Please enter the id of the item to be removed from the order");	
		order = orderDAO.removeFromOrder(orderId, itemId);
		}else {
			LOGGER.info("Invalid Input");
		}
		return order;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
