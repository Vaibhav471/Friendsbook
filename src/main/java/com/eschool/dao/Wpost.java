package com.eschool.dao;

import java.io.InputStream;

public class Wpost {
	int pid;
	String sender,postcontent,dop;
	InputStream photo;
	String retrieve_photo="";

	
	

	 
	
	
	public Wpost(int pid, String sender, String postcontent, String dop, String retrieve_photo) {
		this.pid = pid;
		this.sender = sender;
		this.postcontent = postcontent;
		this.dop = dop;
		this.retrieve_photo = retrieve_photo;
	}


	public String getRetrieve_photo() {
		return retrieve_photo;
	}


	public void setRetrieve_photo(String retrieve_photo) {
		this.retrieve_photo = retrieve_photo;
	}


	public Wpost(String sender, String postcontent, String dop, InputStream photo) {
		this.sender = sender;
		this.postcontent = postcontent;
		this.dop = dop;
		this.photo=photo;
	}


	public Wpost(int pid, String sender, String postcontent, String dop, InputStream photo, String retrive_photo) {
		this.pid = pid;
		this.sender = sender;
		this.postcontent = postcontent;
		this.dop = dop;
		this.photo=photo;
		this.retrieve_photo=retrieve_photo;

	}


	public Wpost(int pid, String sender, String postcontent, String dop) {
		this.pid = pid;
		this.sender = sender;
		this.postcontent = postcontent;
		this.dop = dop;
	}


	public InputStream getPhoto() {
		return photo;
	}


	public void setPhoto(InputStream photo) {
		this.photo = photo;
	}


	public int getPid() {
		return pid;
	}


	public void setPid(int pid) {
		this.pid = pid;
	}


	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public String getPostcontent() {
		return postcontent;
	}


	public void setPostcontent(String postcontent) {
		this.postcontent = postcontent;
	}


	public String getDop() {
		return dop;
	}


	public void setDop(String dop) {
		this.dop = dop;
	}


	public Wpost(String sender, String postcontent) {
		this.sender = sender;
		this.postcontent = postcontent;
	}

}
