package com.sample.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.model.Article;
import com.sample.model.CategoryArticle;
import com.sample.model.ItemsAccount;
import com.sample.model.ListActionCategory;

@Service
public class ItemsAccountService {

	private final KieContainer kieCentainer;
	
	@Autowired
	public ItemsAccountService(KieContainer kieContaint){
		this.kieCentainer = kieContaint;
	}

	public ItemsAccount popust(ItemsAccount itemsAccount, ListActionCategory listAction) {

		KieSession kieSession = kieCentainer.newKieSession("PopustArtikla");
		kieSession.insert(itemsAccount);
		kieSession.insert(listAction);
		kieSession.fireAllRules();
		kieSession.dispose();
		return itemsAccount;
	}

	public ItemsAccount popustArtikle(ItemsAccount itemsAccount) {
		KieSession kieSession = kieCentainer.newKieSession("PopustStavke");
		kieSession.insert(itemsAccount);
		kieSession.fireAllRules();
		kieSession.dispose();
		return itemsAccount;
	}


	
}
