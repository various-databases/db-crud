package com.hibernate.pojo;

public class User {

	private int id;
	private String first;
	private String last;
	private int age;

	public User() {
		//
	}

	public User(String first, String last, int age) {
		this.first = first;
		this.last = last;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
