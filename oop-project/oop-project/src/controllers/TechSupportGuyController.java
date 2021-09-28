package controllers;

import java.io.BufferedReader;
import java.io.IOException;

import util.DataBase;
import util.Message;
import util.Order;
import util.OrderStatus;

public class TechSupportGuyController extends Controller{

	public TechSupportGuyController(DataBase db) {
		super(db);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println("Choose an option:\n"
				+ "1.View all orders\n"
				+ "2.View new orders\n"
				+ "3.View accepted orders\n"
				+ "4.View rejected orders\n"
				+ "5.View done orders\n"
				+ "6.Change order status\n");
		try {
			String ans = reader.readLine();
			switch(ans) {
			case "1":
				viewAllOrders();
				break;
			case "2":
				viewOrdersByStatus(OrderStatus.NEW);
				break;
			case "3":
				viewOrdersByStatus(OrderStatus.ACCEPTED);
				break;
			case "4":
				viewOrdersByStatus(OrderStatus.REJECTED);
				break;
			case "5":
				viewOrdersByStatus(OrderStatus.DONE);
				break;
			case "6":
				changeOrderStatus();
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void viewAllOrders() {
		for(Message message : getDb().getMessages()) {
			if(message instanceof Order) {
				System.out.println((Order)message);
			}
		}
	}
	
	public void viewOrdersByStatus(OrderStatus orderStatus) {
		for(Message message :getDb().getMessages()) {
			if(message instanceof Order && ((Order)message).getOrderStatus().equals(orderStatus)) {
				System.out.println((Order)message);
			}
		}
	}
	
	public Order getOrderById(int id) {
		for(Message message : getDb().getMessages()) {
			if(message instanceof Order && ((Order) message).getId() == id) {
				return (Order) message;
			}
		}
		return null;
	}
	public void changeOrderStatus() throws NumberFormatException, IOException {
		viewAllOrders();
		System.out.println("Enter osrder's id:");
		int id = Integer.parseInt(reader.readLine());
		Order order = getOrderById(id);
		getDb().getMessages().remove(order);
		System.out.println("Choose a new status:\n"
				+ "1.NEW\n"
				+ "2.ACCEPTED\n"
				+ "3.REJECTED\n"
				+ "4.DONE\n");
		String ans = reader.readLine();
		switch(ans) {
			case "1":
				order.setOrderStatus(OrderStatus.NEW);
				break;
			case "2":
				order.setOrderStatus(OrderStatus.ACCEPTED);
				break;
			case "3":
				order.setOrderStatus(OrderStatus.REJECTED);
				break;
			case "4":
				order.setOrderStatus(OrderStatus.DONE);
				break;
		}
		
	}
}
