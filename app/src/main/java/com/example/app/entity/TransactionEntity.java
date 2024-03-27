package com.example.app.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TransactionEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double amount;
	
	@Enumerated(EnumType.STRING)
	private TransactionTypeEntity transactionType;
	
	private Date transaction_date;

	@ManyToOne
	@JoinColumn(name = "source_account_id")
	private AccountEntity sourceAccount;

	@ManyToOne
	@JoinColumn(name = "target_account_id")
	private AccountEntity targetAccount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public TransactionTypeEntity getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionTypeEntity transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}

	public AccountEntity getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(AccountEntity sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public AccountEntity getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(AccountEntity targetAccount) {
		this.targetAccount = targetAccount;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", transactionType=" + transactionType
				+ ", transaction_date=" + transaction_date + ", sourceAccount=" + sourceAccount + ", targetAccount="
				+ targetAccount + "]";
	}
}
