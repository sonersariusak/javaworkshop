package com.foriba.jws1.web.page;


//import com.fit.earsiv.util.CryptoUtil;
import com.foriba.jws1.web.model.WebUser;
import com.foriba.jws1.web.service.ServiceLocator;

public class LoginPage extends AbstractPage {

	private static final long serialVersionUID = 4066303450701564629L;

	public static final String AUTH_KEY = "auth.login.success";

	private WebUser webUser;
	private boolean isLoggedIn;
	private String loginMessage;

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	public LoginPage(){
		webUser= new WebUser();
	}

	public String login() throws Exception {
		try {
			String authkey = "order";
			
			if (authkey.equals((webUser.getUsername() + webUser.getPassword()).getBytes("UTF-8"))) {
				isLoggedIn = true;
				storeOnSession(AUTH_KEY, webUser);
				setLoginMessage("Success");
				return "loginSuccess";
			}
			setLoginMessage("Invalid credentials");
			return null;
		}
		catch (Exception e) {
		e.printStackTrace();
		return null;
		}
		
	}

	public String getLoginMessage() {
		return loginMessage;
	}

	public void setLoginMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}

	public WebUser getWebUser() {
		return webUser;
	}

	public void setWebUser(WebUser webUser) {
		this.webUser = webUser;
	}

}
