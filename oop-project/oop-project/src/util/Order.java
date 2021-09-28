package util;

import java.io.Serializable;

public class Order extends Message implements Serializable{
	private int id;
	private OrderStatus orderStatus;
	public Order(int id, int senderId, int receiverId, String time, String text, OrderStatus orderStatus) {
		super(senderId, receiverId, time, text);
		this.id = id;
		this.orderStatus = orderStatus;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderStatus=" + orderStatus + "] " + super.toString();
	}

	
}
