package model;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 1864423475508245309L;
	//物件序列化版本
	private String name;
	private String createTime;
	private String content;
	
	public Message() {
	}

	public Message(String name, String createTime, String content) {
		this.name = name;
		this.createTime = createTime;
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Message [name=" + name + ", createTime=" + createTime + ", content=" + content + "]";
	}
}
