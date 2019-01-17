package com.github.jachinlin.springlite.service;

import com.github.jachinlin.springlite.beans.factory.annotation.Autowired;
import com.github.jachinlin.springlite.stereotype.Component;

@Component(value="petStore")
public class PetStoreServiceV4 {
	@Autowired
	private Account account;
	
	public Account getAccount() {
		return account;
	}
}
