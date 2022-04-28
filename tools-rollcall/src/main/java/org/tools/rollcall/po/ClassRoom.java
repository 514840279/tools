package org.tools.rollcall.po;

import javax.persistence.Entity;

import org.chuxue.application.common.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ClassRoom extends BaseEntity {
	
	private String name;
}
