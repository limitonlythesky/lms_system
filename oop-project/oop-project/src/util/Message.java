package util;

import java.io.Serializable;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1603586120297097147L;
	private int senderId;
	private int receiverId;
	private String text;
	public Message(int senderId, int receiverId, String time, String text) {
		super();
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.text = text;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getReceiverId() {
		return receiverId;
	}



	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + receiverId;
		result = prime * result + senderId;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (receiverId != other.receiverId)
			return false;
		if (senderId != other.senderId)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [senderId=" + senderId + ", receiverId=" + receiverId + ", text=" + text + "]";
	}

	
}
