package com.javalec.dto;

import java.sql.Timestamp;

public class SignDto {
	
	// Field
	String custid;
	String custpw;
	String custname;
	String phone;
	String birthday;
	String question1;
	String answer1;
	String question2;
	String answer2;
	String joinactive;
	String modidate;
	Timestamp deactive;
	
	// Constructor
	public SignDto() {
		
	}
	
	public SignDto(String custid) {
		super();
		this.custid = custid;
	}
	
	public SignDto(String custid, String custname, String phone, String birthday, String question1, String answer1,
			String question2, String answer2, Timestamp deactive) {
		super();
		this.custid = custid;
		this.custname = custname;
		this.phone = phone;
		this.birthday = birthday;
		this.question1 = question1;
		this.answer1 = answer1;
		this.question2 = question2;
		this.answer2 = answer2;
		this.deactive = deactive;
	}

	public SignDto(String custname, String phone, String birthday, String question1, String answer1, String question2,
			String answer2) {
		super();
		this.custname = custname;
		this.phone = phone;
		this.birthday = birthday;
		this.question1 = question1;
		this.answer1 = answer1;
		this.question2 = question2;
		this.answer2 = answer2;
	}

	public SignDto(String custid, String custpw, String custname, String phone, String birthday, String question1,
			String answer1, String question2, String answer2, Timestamp deactive) {
		super();
		this.custid = custid;
		this.custpw = custpw;
		this.custname = custname;
		this.phone = phone;
		this.birthday = birthday;
		this.question1 = question1;
		this.answer1 = answer1;
		this.question2 = question2;
		this.answer2 = answer2;
		this.deactive = deactive;
	}

	public SignDto(String custid, String custpw, String custname, String phone, String birthday, String question1,
			String answer1, String question2, String answer2, String joinactive, String modidate, Timestamp deactive) {
		super();
		this.custid = custid;
		this.custpw = custpw;
		this.custname = custname;
		this.phone = phone;
		this.birthday = birthday;
		this.question1 = question1;
		this.answer1 = answer1;
		this.question2 = question2;
		this.answer2 = answer2;
		this.joinactive = joinactive;
		this.modidate = modidate;
		this.deactive = deactive;
	}

	public SignDto(String custid, String custpw, String custname, String phone, String birthday, String question1,
			String answer1, String question2, String answer2) {
		super();
		this.custid = custid;
		this.custpw = custpw;
		this.custname = custname;
		this.phone = phone;
		this.birthday = birthday;
		this.question1 = question1;
		this.answer1 = answer1;
		this.question2 = question2;
		this.answer2 = answer2;
	}

	// Method
	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getCustpw() {
		return custpw;
	}

	public void setCustpw(String custpw) {
		this.custpw = custpw;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getJoinactive() {
		return joinactive;
	}

	public void setJoinactive(String joinactive) {
		this.joinactive = joinactive;
	}

	public String getModidate() {
		return modidate;
	}

	public void setModidate(String modidate) {
		this.modidate = modidate;
	}

	public Timestamp getDeactive() {
		return deactive;
	}

	public void setDeactive(Timestamp deactive) {
		this.deactive = deactive;
	}

	
	
	
}
