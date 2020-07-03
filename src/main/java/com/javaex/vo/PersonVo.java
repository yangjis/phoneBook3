package com.javaex.vo;

public class PersonVo {
	
	private int person_Id;
	private String name;
	private String hp;
	private String company;
	
	public PersonVo() {}
	
	public PersonVo(String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	
	public PersonVo(int personId, String name, String hp, String company) {
		this.person_Id = personId;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	public int getPersonId() {
		return person_Id;
	}

	public void setPersonId(int personId) {
		this.person_Id = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "PersonVo [personId=" + person_Id + ", name=" + name + ", hp=" + hp + ", company=" + company + "]";
	}

}
