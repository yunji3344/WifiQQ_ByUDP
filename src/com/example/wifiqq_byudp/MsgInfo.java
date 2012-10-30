package com.example.wifiqq_byudp;

import java.io.Serializable;

/**
 * 消息结构体
 */
public class MsgInfo implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * ip地址
	 */
	private String ip;
	/**
	 * 消息内容
	 */
	private String content;
	
	public MsgInfo() {

    }
	
	public MsgInfo(String content) {
	    this.content = content;
    }
	
	public MsgInfo(String ip, String content) {
	    this.ip = ip;
	    this.content = content;
    }
	
	public String getName() {
    	return name;
    }
	
	public void setName(String name) {
    	this.name = name;
    }
	
	public String getIp() {
    	return ip;
    }
	
	public void setIp(String ip) {
    	this.ip = ip;
    }
	
	public String getContent() {
    	return content;
    }
	
	public void setContent(String content) {
    	this.content = content;
    }
}
