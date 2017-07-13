package com.sample.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.model.Account;
import com.sample.model.Article;
import com.sample.model.ItemsAccount;
import com.sample.model.Popusti;
import com.sample.repository.PopustiRepository;

@Service
public class AccountService {

	private final KieContainer kieCentainer;
	
	@Autowired
	private PopustiRepository popustiRepository;
	
	@Autowired
	public AccountService(KieContainer kieContaint){
		this.kieCentainer = kieContaint;
	}
	
	public Account getPopust(Account a, Popusti p){
		KieSession kieSession = kieCentainer.newKieSession("ExampleSession");
		kieSession.insert(a);
		kieSession.insert(p);
		kieSession.fireAllRules();
		kieSession.dispose();
		if(p != null) {
			Popusti p1 = new Popusti();
			p1.setAccount(a);
			p1.setProcenat(p.getProcenat());
			p1.setTipPopusta(p.getTipPopusta());
			popustiRepository.save(p1);
		}
		
		KieSession kieSess = kieCentainer.newKieSession("ZlatniSrebreniSession");
		kieSess.insert(a);
		kieSess.insert(p);
		kieSess.fireAllRules();
		kieSess.dispose();
		if(p != null) {
			Popusti p2 = new Popusti();
			p2.setAccount(a);
			p2.setProcenat(p.getProcenat());
			p2.setTipPopusta(p.getTipPopusta());
			popustiRepository.save(p2);
		}
		
		KieSession kieSess1 = kieCentainer.newKieSession("GodineSession");
		kieSess1.insert(a);
		kieSess1.insert(p);
		kieSess1.fireAllRules();
		kieSess1.dispose();
		if(p != null) {
			Popusti p3 = new Popusti();
			p3.setAccount(a);
			p3.setProcenat(p.getProcenat());
			p3.setTipPopusta(p.getTipPopusta());
			popustiRepository.save(p3);
		}
		
		return a;
	}


}
