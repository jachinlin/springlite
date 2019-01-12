package com.github.jachinlin.springlite.service;

public class PetStoreServiceV3 {

	
	private Account account;
	private int version;
	
	public PetStoreServiceV3(Account account) {
		this(account, -1);
	}
	
	public PetStoreServiceV3(Account account, int version) {
		this.account = account;
		this.version = version;
	}
	
	public Account getAccount() {
		
		return account;
	}

	public int getVersion() {
		return version;
	}
}
