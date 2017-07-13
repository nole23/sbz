package com.sample.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.model.Account;
import com.sample.model.ProfileUser;

@Service
public class ProfileUserService {

	private final KieContainer kieCentainer;
	
	@Autowired
	public ProfileUserService(KieContainer kieContaint){
		this.kieCentainer = kieContaint;
	}

	public ProfileUser nagradni(ProfileUser profileUser, Account account) {
		KieSession kieSession = kieCentainer.newKieSession("NagradniBodovi");
		kieSession.insert(profileUser);
		kieSession.insert(account);
		kieSession.fireAllRules();
		kieSession.dispose();
		return profileUser;
	}
}
