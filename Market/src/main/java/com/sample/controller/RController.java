package com.sample.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.dto.ArticleDTO;
import com.sample.dto.DodatniDTO;
//import com.sample.dto.RDTO;
import com.sample.model.Article;
import com.sample.model.CategoryArticle;
import com.sample.model.OrderArticle;
import com.sample.model.RModel;
import com.sample.model.User;
import com.sample.recommendation.CollaborativeFilltering;
import com.sample.repository.ArticleRepository;
import com.sample.repository.CategoryArticleRepository;
import com.sample.repository.OrderArticleRepository;
import com.sample.repository.RRepository;
import com.sample.repository.UserRepository;

@RestController
@RequestMapping(value = "api/r")
public class RController {

	@Autowired
	private RRepository rRepository;
	
	@Autowired
	private CategoryArticleRepository cateogiryRepository;
	
	@RequestMapping(value = "/kategorija", method = RequestMethod.GET)
	public ResponseEntity<List<ArticleDTO>> getKategorija() {
		
		List<RModel> model = rRepository.findAll();
		
		double count = 0;
		for(RModel r: model) {
			count += r.getCounter();	
		}
		
		List<DodatniDTO> dodatni = new ArrayList<>();
		for(RModel ra: model) {
			
			DodatniDTO d = new DodatniDTO();
			d.setIdKat(ra.getCategoryArticle().getId());
			d.setCount(ra.getCounter() / count);
			dodatni.add(d);
		}
		//System.out.println(dodatni);
		
		List<ArticleDTO> article = Racuna(dodatni);
		//System.out.println(id);
		return new ResponseEntity<>(article, HttpStatus.OK);
	}
	
	public List<ArticleDTO> Racuna(List<DodatniDTO> dodatni) {
		
		List<CategoryArticle> category = cateogiryRepository.findAll();

		List<DodatniDTO> dodatno = new ArrayList<>();
		int count = 0;

		for(CategoryArticle r: category) {

			double zbir = 0;
			for(int i=0; i<dodatni.size(); i++){
				if(dodatni.get(i).getIdKat() == r.getId()){
					zbir += dodatni.get(i).getCount();
					count += 1;
				}

			}

			
			DodatniDTO d = new DodatniDTO();
			d.setIdKat(r.getId());
			d.setCount(zbir/count);
			dodatno.add(d);
		}

		Long idKategorije = Sort(dodatno);
		
		List<ArticleDTO> article = Lista(idKategorije);
		
		return article;
	}
	
	private List<ArticleDTO> Lista(Long idKategorije) {
		
		CategoryArticle category = cateogiryRepository.findOne(idKategorije);
		
		//List<Article> artikle = articleRepository.findByCategoryArticle(category);
		List<ArticleDTO> articleDTO = new ArrayList<>();
		//for(Article a: artikle) {
		//	articleDTO.add(new ArticleDTO(a));
		//}
		
		return articleDTO;
	}

	public Long Sort(List<DodatniDTO> dodatni){
	
		double sort = 0;
		Long id = null;
		for(DodatniDTO d: dodatni) {
			System.out.println("kat: " + d.getIdKat() + " | resenje: " + d.getCount()*1000);
			if(d.getCount() > sort) {
				sort = d.getCount();
				id = d.getIdKat();
			}
		}
		
		return id;
	}

}
