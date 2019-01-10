package com.github.jachinlin.springlite.service;

public class PetStoreServiceV2 {
	
	private Account account;
	private String owner;
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public Account getAccount() {
		
		return account;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
