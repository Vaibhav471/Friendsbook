package com.eschool.dao;

import java.io.InputStream;

public class Pphoto {
	int pid;
	String sender,pic_retrieve,doup;
	InputStream pic_upload;
	
	public Pphoto() {
	}
	
	public Pphoto(String sender, InputStream pic_upload,String doup) {
		this.sender = sender;
		this.doup = doup;
		this.pic_upload = pic_upload;
	}
	public Pphoto(int pid, String sender, String pic_retrieve, String doup) {
		this.pid = pid;
		this.sender = sender;
		this.pic_retrieve = pic_retrieve;
		this.doup = doup;
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
	public String getPic_retrieve() {
		return pic_retrieve;
	}
	public void setPic_retrieve(String pic_retrieve) {
		this.pic_retrieve = pic_retrieve;
	}
	public String getDoup() {
		return doup;
	}
	public void setDoup(String doup) {
		this.doup = doup;
	}
	public InputStream getPic_upload() {
		return pic_upload;
	}
	public void setPic_upload(InputStream pic_upload) {
		this.pic_upload = pic_upload;
	}

}
