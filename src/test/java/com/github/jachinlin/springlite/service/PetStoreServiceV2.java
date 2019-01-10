package com.github.jachinlin.springlite.service;

public class PetStoreServiceV2 {
	
	private Account account;
	private String owner;
	private int version;
	private boolean active;
	
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
