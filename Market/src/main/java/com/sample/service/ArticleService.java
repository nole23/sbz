package com.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.model.Account;
import com.sample.model.Article;
import com.sample.model.ItemsAccount;
import com.sample.repository.ArticleRepository;

@Service
public class ArticleService {
	
	private final KieContainer kieCentainer;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	public ArticleService(KieContainer kieContaint){
		this.kieCentainer = kieContaint;
	}
	
	

	public List<Article> search(String nameArticle) {
		List<Article> article = new ArrayList<Article>();
		
		article = articleRepository.search(nameArticle);
		
		return article;
	}

	public Article complement(Article a) {
		KieSession kieSession = kieCentainer.newKieSession("ComplementArticle");
		kieSession.insert(a);
		kieSession.fireAllRules();
		kieSession.dispose();
		return a;
	}



	public Article proveraRaspolozivosti(ItemsAccount itemsAccount, Article article) {
		KieSession kieSession = kieCentainer.newKieSession("RaspolozivostArtikla");
		kieSession.insert(itemsAccount);
		kieSession.insert(article);
		kieSession.fireAllRules();
		kieSession.dispose();
		return article;
	}




	

	
	
}
