package itcast.core.exception;

public abstract class SysException extends Exception {
    String errMessage;

	

	public SysException(String message) {
		super();
		this.errMessage = message;
	}

	public SysException() {
		super();
	}
	
	public SysException(Throwable cause) {
		super(cause);
	}
	
	 public SysException(String message, Throwable cause) {
	        super(message, cause);
	        this.errMessage = message;
	    }
	 
	
		public String getErrMessage() {
			return errMessage;
		}

		
		public void setErrMessage(String errMessage) {
			this.errMessage = errMessage;
		}
    
}
