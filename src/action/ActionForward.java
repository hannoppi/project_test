package action;

public class ActionForward {
	private boolean isRedirect = false;
	private String path = null;
	
	public boolean isRedirect() {
		System.out.println("[ActionForward.java] (setRedirect) isRedirect: " + isRedirect);
		
		return isRedirect;
	}
	
	public String getPath() {
		System.out.println("[ActionForward.java] (setRedirect) path: " + path);
		
		return path;
	}
	
	public void setRedirect(boolean b) {
		System.out.println("[ActionForward.java] (setRedirect) b: " + b);
		
		isRedirect = b;
		System.out.println("[ActionForward.java] (setRedirect) isRedirect: " + isRedirect);
	}
	
	public void setPath(String string) {
		System.out.println("[ActionForward.java] (setRedirect) string: " + string);
		
		path = string;
		System.out.println("[ActionForward.java] (setRedirect) path: " + path);
	}
}
