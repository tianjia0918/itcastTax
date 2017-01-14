package itcast.core.exception;

public class ActionException extends SysException {

	public ActionException() {
		super("Action层出现错误");
		
	}

	public ActionException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ActionException(String message) {
		super(message);
		
	}

	public ActionException(Throwable cause) {
		super(cause);
		
	}
       
}
