package bk.elearning.utils;

public class Message {

	private int status;
	
	private String msg;
	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Message(String msg) {
		super();
		this.msg=msg;
		this.status=0;
	}
	public Message(int status,String msg) {
		super();
		this.msg=msg;
		this.status=status;
	}
	
}
