package com.ds.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 用户的实体类
 * @author asus
 *
 */
public class User {
	/**
	 * 用户的id
	 */
	private int userId;
	/**
	 * 用户名
	 */
	@Pattern(regexp="^[a-zA-Z][a-zA-Z0-9-_]{5,15}$",message="用户名不规范")
	private String username;
	/**
	 * 用户最后登录的日期
	 */
	private String lastIp;
	
	/**
	 * 用户的密码。
	 */
	@Pattern(regexp="^.{6,18}$",message="密码长度不正确")
	private String password;
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User(int userId, String username, String lastIp, int age) {
		super();
		this.userId = userId;
		this.username = username;
		this.lastIp = lastIp;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", lastIp=" + lastIp + ", password=" + password
				+ "]";
	}
	
}
