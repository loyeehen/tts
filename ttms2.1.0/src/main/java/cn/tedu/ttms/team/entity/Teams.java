package cn.tedu.ttms.team.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Teams implements Serializable{
private static final long serialVersionUID = 1L;
private Integer id;
private String name;
private Integer projectId;
private Integer valid;
private String note;
private Date createdTime;
private Date modifiedTime;
private String createdUser;
private String modifiedUser;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Integer getProjectId() {
	return projectId;
}
public void setProjectId(Integer projectId) {
	this.projectId = projectId;
}
public Integer getValid() {
	return valid;
}
public void setValid(Integer valid) {
	this.valid = valid;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}
public Date getCreatedTime() {
	return createdTime;
}
public void setCreatedTime(Date createdTime) {
	this.createdTime = createdTime;
}
public Date getModifiedTime() {
	return modifiedTime;
}
public void setModifiedTime(Date modifiedTime) {
	this.modifiedTime = modifiedTime;
}
public String getCreatedUser() {
	return createdUser;
}
public void setCreatedUser(String createdUser) {
	this.createdUser = createdUser;
}
public String getModifiedUser() {
	return modifiedUser;
}
public void setModifiedUser(String modifiedUser) {
	this.modifiedUser = modifiedUser;
}
@Override
public String toString() {
	return "Teams [id=" + id + ", name=" + name + ", projectId=" + projectId + ", valid=" + valid + ", note=" + note
			+ ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime + ", createdUser=" + createdUser
			+ ", modifiedUser=" + modifiedUser + "]";
}



}
