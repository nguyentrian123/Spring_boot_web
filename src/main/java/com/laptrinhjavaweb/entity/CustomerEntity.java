package com.laptrinhjavaweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class CustomerEntity extends BaseEntity {


	private static final long serialVersionUID = 1L;

		@Column(name = "fullname", nullable = false)
	    private String fullName;

	    @Column(name = "phone", nullable = false)
	    private String phone;

	    @Column(name = "email", unique = true)
	    private String email;
	    
	    @OneToMany(mappedBy="customer",  cascade = CascadeType.ALL)
	    private List<TransactionEntity> transactions = new ArrayList<>();
	    
	    @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable( name="assignmentcustomer",
	    			joinColumns = @JoinColumn(name = "customer_id", nullable = false),
	    			inverseJoinColumns = @JoinColumn(name = "user_id" , nullable = false)
	    		)
	    private List<UserEntity> users = new ArrayList<>();

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public List<TransactionEntity> getTransactions() {
			return transactions;
		}

		public void setTransactions(List<TransactionEntity> transactions) {
			this.transactions = transactions;
		}

		public List<UserEntity> getUsers() {
			return users;
		}

		public void setUsers(List<UserEntity> users) {
			this.users = users;
		}
	    
	    
	    
	    
}
