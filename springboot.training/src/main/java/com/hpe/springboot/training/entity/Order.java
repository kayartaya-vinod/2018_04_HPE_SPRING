package com.hpe.springboot.training.entity;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "order_date")
	private Date orderDate = new Date();

	private String status = "Pending";

	// one-to-many association between order and line-item
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "order_id")
	private Set<LineItem> lineItems;
	
	// helper function to work with associations
	public void addLineItem(LineItem lineItem) {
		if (lineItems == null) {
			lineItems = new HashSet<>();
		}
		lineItem.setOrder(this);
		lineItems.add(lineItem);
	}

	// add all line-items from the shopping cart
	public void addLineItems(Collection<LineItem> lineItems) {
		if (this.lineItems == null) {
			this.lineItems = new HashSet<>();
		}
		this.lineItems.addAll(lineItems);
		for(LineItem item: lineItems) {
			item.setOrder(this);
		}
	}
}









