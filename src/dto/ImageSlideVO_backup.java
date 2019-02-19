package dto;

import java.sql.Timestamp;

public class ImageSlideVO_backup {
	private int number;
	private String file;
	private String subject;
	private String content;
	private Timestamp regdate;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
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
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "ImageSlideVO [number=" + number + ", file=" + file + ", subject=" + subject + ", content=" + content
				+ ", regdate=" + regdate + "]";
	}
}
