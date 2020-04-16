package com.italia.marxmind.gso.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.italia.marxmind.ConnectDB;
import com.italia.marxmind.InitDB;
import com.italia.marxmind.appUtils.LogU;
import com.italia.marxmind.appUtils.SessionBean;
import com.italia.marxmind.appUtils.Whitelist;
import com.italia.marxmind.common.rsc.RSCActivate;
import com.italia.marxmind.gso.controller.Login;
import com.italia.marxmind.gso.global.GlobalVar;

@Named
@ViewScoped
public class LoginBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 464670687446631L;
	
	private String uname;
	private String password;
	private String keyPress;
	
	@PostConstruct
	public void init() {
		
		InitDB.getInstance().setPathFileLocation(GlobalVar.APP_DATABASE_CONF);
		RSCActivate.getInstance().setLicenseDataFile(GlobalVar.LICENSE_DATA_FILE);
		RSCActivate.getInstance().setLicenseFileName(GlobalVar.LICENSE_FILE_NAME);
		RSCActivate.getInstance().setAppExpiration(GlobalVar.LICENSE_EXP);
		
	}
	
		//validate login
		public String validateUserNamePassword(){
			
			 
			 boolean isOk = false;
			
			 
			 
			String result="login";
			LogU.open(GlobalVar.LOG_ENABLE,GlobalVar.LOG_FOLDER);
			LogU.add("Guest with username : " + uname + " and password ********* is trying to log in the system. is valid? " + isOk);
			Login in = Login.validUser(Whitelist.remove(getUname()), getPassword()); 
			if(in!=null){
				
				System.out.println("user validated");
				
		        HttpSession session = SessionBean.getSession();
		        session.setAttribute("username", uname);
				session.setAttribute("userid", 0);
				
				return "main";
				
			}else{
				FacesContext.getCurrentInstance().addMessage(
						null,new FacesMessage(
								FacesMessage.SEVERITY_WARN, 
								"Incorrect username and password", 
								"Please enter correct username and password"
								)
						);
				result= "login";
			}
			LogU.close();
			return result;
		}
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getKeyPress() {
		keyPress = "logId";
		return keyPress;
	}
	public void setKeyPress(String keyPress) {
		this.keyPress = keyPress;
	}
	
}
