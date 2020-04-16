package com.italia.marxmind.gso.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * 
 * @author Mark Italia
 * @version 1.0
 * @since 04/09/2020
 *
 */
@Named
@ViewScoped
public class MainBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 157679870575475L;
	
	private String searchParam;

	public String getSearchParam() {
		return searchParam;
	}

	public void setSearchParam(String searchParam) {
		this.searchParam = searchParam;
	}
	

}
