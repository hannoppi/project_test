package dto;

import java.sql.Timestamp;

public class BoardVO {
	private int number;
	private String id;
	private String name;
	private String category;
	private String subject;
	private String content;
	private String file;
	private String uri;
	private String thumbnail;
	private int replyReference;
	private int replyDepth;
	private int replySequence;
	private String address;
	private int count;
	private Timestamp regdate;
	
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
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public int getReplyReference() {
		return replyReference;
	}
	public void setReplyReference(int replyReference) {
		this.replyReference = replyReference;
	}
	public int getReplyDepth() {
		return replyDepth;
	}
	public void setReplyDepth(int replyDepth) {
		this.replyDepth = replyDepth;
	}
	public int getReplySequence() {
		return replySequence;
	}
	public void setReplySequence(int replySequence) {
		this.replySequence = replySequence;
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
	
	@Override
	public String toString() {
		return "BoardVO [number=" + number + ", id=" + id + ", name=" + name + ", category=" + category + ", subject="
				+ subject + ", content=" + content + ", file=" + file + ", uri=" + uri + ", thumbnail=" + thumbnail
				+ ", replyReference=" + replyReference + ", replyDepth=" + replyDepth + ", replySequence="
				+ replySequence + ", address=" + address + ", count=" + count + ", regdate=" + regdate + "]";
	}
	
}
