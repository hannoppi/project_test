package dto;

import java.sql.Timestamp;

public class CommentVO {
	private int board;
	private int number;
	private String id;
	private String name;
	private String content;
	private int reference;
	private int sequence;
	private int level;
	private int together;
	private Timestamp regdate;
	
	public int getBoard() {
		return board;
	}
	public void setBoard(int board) {
		this.board = board;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReference() {
		return reference;
	}
	public void setReference(int reference) {
		this.reference = reference;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getTogether() {
		return together;
	}
	public void setTogether(int together) {
		this.together = together;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "CommentVO [board=" + board + ", number=" + number + ", id=" + id + ", name=" + name + ", content="
				+ content + ", reference=" + reference + ", sequence=" + sequence + ", level=" + level + ", together="
				+ together + ", regdate=" + regdate + "]";
	}
}