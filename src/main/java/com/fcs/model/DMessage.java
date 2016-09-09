package com.fcs.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 站内信
 *
 */
public class DMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 交易的唯一Key */
	private String onlyKey ;
	
	private long id ;
	/** */
	private int account_id ;
	
	/** */
	private String name ;
	
	/** */
	private String content ;
	
	private Date addtime ;
	
	/** */
	private String addip ;

	/** */
	private long resid ;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOnlyKey() {
		return onlyKey;
	}

	public void setOnlyKey(String onlyKey) {
		this.onlyKey = onlyKey;
	}

	

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public long getResid() {
		return resid;
	}

	public void setResid(long resid) {
		this.resid = resid;
	}

	@Override
	public String toString() {
		return "TMessage [onlyKey=" + onlyKey + ", id=" + id + ", account_id=" + account_id + ", name=" + name + ", content=" + content + ", addtime=" + addtime + ", addip=" + addip + ", resid=" + resid + "]";
	}
	
	
}
