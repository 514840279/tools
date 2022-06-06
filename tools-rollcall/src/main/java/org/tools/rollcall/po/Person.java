package org.tools.rollcall.po;

import javax.persistence.Entity;

import org.chuxue.application.common.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Person extends BaseEntity {
	
	private String	classId;
	private String	no;
	private String	name;
	private String	img;
	
	public Person() {
		super();
	}
	
	public Person(String id, String no, String name, String classId) {
		this.uuid = id;
		this.no = no;
		this.name = name;
		this.classId = classId;
	}
	
	public Person(String classId) {
		super();
		this.classId = classId;
	}
	
}
