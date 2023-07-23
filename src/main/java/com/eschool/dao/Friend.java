package com.eschool.dao;

public class Friend {
int fid,status;
String sender,rec,dor;
public int getFid() {
	return fid;
}
public void setFid(int fid) {
	this.fid = fid;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public String getSender() {
	return sender;
}
public void setSender(String sender) {
	this.sender = sender;
}
public String getRec() {
	return rec;
}
public void setRec(String rec) {
	this.rec = rec;
}
public String getDor() {
	return dor;
}
public void setDor(String dor) {
	this.dor = dor;
}
public Friend(int fid, int status, String sender, String rec, String dor) {
	this.fid = fid;
	this.status = status;
	this.sender = sender;
	this.rec = rec;
	this.dor = dor;
}


public Friend(int fid) {
	this.fid = fid;
}
public Friend() {
}

}
