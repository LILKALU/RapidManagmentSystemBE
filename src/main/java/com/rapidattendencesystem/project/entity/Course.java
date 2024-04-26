package com.rapidattendencesystem.project.entity;


import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "grade_id")
	private Grade grade;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subject_id")
	private Subject subject;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hall_id")
	private Hall hall;
//	
//	@OneToMany(mappedBy = "course")
//	@JsonIgnore
//	private List<ClassFee> classfee;
	
	private String date;
	private String startTime;
	private String endTime;
	private Boolean isActive;
	private BigDecimal classFeeAmount;
	

}

