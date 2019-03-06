package com.ttest.testwap.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "action")
public class Action implements Serializable {

	@Id
	Integer id;

	@Column(nullable = false)
	String name;

}

@Entity
@Table(name = "use_case")
public class UseCase implements Serializable {

	@Id
	Integer id;

	@Column(nullable = false)
	String name;

	@JsonManagedReference
    @OneToMany(mappedBy = "useCase", fetch = FetchType.EAGER)
    List<Step> steps;

}

@Table(name = "step")
@Entity
public class Step implements Serializable {

	@Id
	Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idUseCase")
	@JsonBackReference
	UseCase useCase;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idAction")
	Action action;

	@Column(name = "num")
	Integer num;

	@Column(name = "elementValue")
	String elementValue;

	@Column(name = "searchCriteria")
	String searchCriteria;

	@Column(name = "url")
	String url;

	@Column(name = "idTargetElement")
	String targetElementId;

}
