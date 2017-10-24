package de.uwepost;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners({AuditingEntityListener.class})
@Table(name="customer_order")
public class Order extends BaseEntity {

	private int totalCost;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="customer_id")
    public Customer customer;

	// content...
	
	public int getTotalCost() {
		return totalCost;
	}
	
	public Date getDate() {
		return date;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public Order(int totalCost, Customer customer) {
		super();
		this.totalCost = totalCost;
		this.date = new Date();
		this.customer = customer;
	}
	
	public Order() {
		super();
	}
	
}
