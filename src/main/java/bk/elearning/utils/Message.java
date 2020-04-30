package bk.elearning.utils;

public class Message {

	private int status;
	
	private String msg;
	
	private Object data;
	
	

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

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
	public Message(int status,String msg,Object data) {
		super();
		this.msg=msg;
		this.status=status;
		this.data=data;
	}
	
}
