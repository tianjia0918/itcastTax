package itcast.nsfw.complain.entity;

import java.sql.Timestamp;

/**
 * Reply entity. @author MyEclipse Persistence Tools
 */

public class Reply implements java.io.Serializable {

	// Fields

	private String replyId;
	private Complain complain;
	private String replyName;
	private String replyDept;
	private String replyContent;
	private Timestamp replyTime;

	// Constructors

	/** default constructor */
	public Reply() {
	}

	/** minimal constructor */
	public Reply(Complain complain) {
		this.complain = complain;
	}

	/** full constructor */
	public Reply(Complain complain, String replyName, String replyDept,
			String replyContent, Timestamp replyTime) {
		this.complain = complain;
		this.replyName = replyName;
		this.replyDept = replyDept;
		this.replyContent = replyContent;
		this.replyTime = replyTime;
	}

	// Property accessors

	public String getReplyId() {
		return this.replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public Complain getComplain() {
		return this.complain;
	}

	public void setComplain(Complain complain) {
		this.complain = complain;
	}

	public String getReplyName() {
		return this.replyName;
	}

	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}

	public String getReplyDept() {
		return this.replyDept;
	}

	public void setReplyDept(String replyDept) {
		this.replyDept = replyDept;
	}

	public String getReplyContent() {
		return this.replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Timestamp getReplyTime() {
		return this.replyTime;
	}

	public void setReplyTime(Timestamp replyTime) {
		this.replyTime = replyTime;
	}

}