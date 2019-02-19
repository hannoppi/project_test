package dto;

import java.sql.Timestamp;

public class GalleryVO {
	private int number;
	private String id;
	private String name;
	private String category;
	private String subject;
	private String content;
	private String address;
	private int count;
	private Timestamp regdate;
	private String image;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "GalleryVO [number=" + number + ", id=" + id + ", name=" + name + ", category=" + category + ", subject="
				+ subject + ", content=" + content + ", address=" + address + ", count=" + count + ", regdate="
				+ regdate + ", image=" + image + "]";
	}
}
