package com.sample.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sample.dto.ArticleDTO;
import com.sample.dto.MessagesDTO;
import com.sample.model.Article;
import com.sample.model.CategoryArticle;
import com.sample.model.PodCategory;
import com.sample.repository.ArticleRepository;
import com.sample.repository.CategoryArticleRepository;
import com.sample.repository.PodCategoryRepository;
import com.sample.service.ArticleService;

@RestController
@RequestMapping(value = "api/article")
public class ArticleController {

	@Autowired
	private ArticleRepository articleResource;
	
	@Autowired
	private PodCategoryRepository podCategoryRepository;
	
	@Autowired
	private CategoryArticleRepository categoryRepository;
	
	@Autowired
	private ArticleService articleService;
	
	/*
	 * Get new article
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ResponseEntity<List<ArticleDTO>> getAllCategory() {
		
		Page<Article> article = articleResource.findAll(new PageRequest(0, 10));
		
		List<ArticleDTO> articleDTO = new ArrayList<>();
		for(Article a: article) {
			articleDTO.add(new ArticleDTO(a));
		}
		return new ResponseEntity<>(articleDTO, HttpStatus.OK);
	}
	
	/*
	 * Get one article
	 */
	@RequestMapping(value = "/new/{id}", method = RequestMethod.GET)
	public ResponseEntity<ArticleDTO> getArticle(@PathVariable Long id) {

		Article article = articleResource.findOne(id);

		
		return new ResponseEntity<>(new ArticleDTO(article), HttpStatus.OK);
	}
	
	/*
	 * Vraca nam sve artikle date kategorje
	 * prolazi kroz sve podkategorije i trazi artikle
	 */
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<ArticleDTO>> getArticlCategory(@PathVariable Long id) {
		
		CategoryArticle categoryArticle = categoryRepository.findOne(id);
		List<PodCategory> podCategory = podCategoryRepository.findByCategoryArticle(categoryArticle);
		
		List<ArticleDTO> articleDTO = new ArrayList<>();
		for(PodCategory pc: podCategory){
			List<Article> article = articleResource.findByPodCategory(pc);
			for(Article a: article){
				articleDTO.add(new ArticleDTO(a));
			}
		}
		
		return new ResponseEntity<>(articleDTO, HttpStatus.OK);
	}
	
	
	
	
	/*
	 * Vraca broj svih artikala
	 */
	@RequestMapping(value = "/length", method = RequestMethod.GET)
	public ResponseEntity<MessagesDTO> getAllLength() {
		MessagesDTO messageDTO = new MessagesDTO();
		List<Article> article = articleResource.findAll();
		
		messageDTO.setSize(article.size());
		return new ResponseEntity<>(messageDTO, HttpStatus.OK);
	}
	
	
	/**
	 * Provera da li je prodata roba
	 */
	@RequestMapping(value = "/complement", method = RequestMethod.GET)
	public ResponseEntity<String> getAllDiscount() {

		List<Article> article = articleResource.findAll();
		for(Article a: article){
			Article art = articleService.complement(a);
			articleResource.save(art);
		}
		
		article.clear();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * iscitavamo prodavcu sve artikle koji fale
	 */
	@RequestMapping(value = "/complement/all", method = RequestMethod.GET)
	public ResponseEntity<List<ArticleDTO>> getComplement() {
		
		getAllDiscount();
		
		List<Article> article = articleResource.findAll();
		List<ArticleDTO> articleDTO = new ArrayList<>();
		
		for(Article a: article){
			
			if(!a.isComplement())
				articleDTO.add(new ArticleDTO(a));
		}
		article.clear();
		return new ResponseEntity<>(articleDTO, HttpStatus.OK);
	}
	
	/*
	 * Add new article
	 */
	@RequestMapping(value = "/add/article", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessagesDTO> setArticle(@RequestBody ArticleDTO articleDTO) {
		
		
		System.out.println("upao");
		MessagesDTO messageDTO = new MessagesDTO();
		
		Date date = new Date();
		
		PodCategory podCategory = podCategoryRepository.findOne(articleDTO.getPodCategoryDTO().getId());
		
		Article artikle = new Article();
		artikle.setNameArticle(articleDTO.getNameArticle());
		artikle.setPrice(articleDTO.getPrice());
		artikle.setAmount(articleDTO.getAmount());
		artikle.setDataAddArticle(date);
		artikle.setComplement(true);
		artikle.setStatusRecord(true);
		artikle.setMinState(articleDTO.getMinState());
		artikle.setPodCategory(podCategory);
		
		articleResource.save(artikle);
		
		
		messageDTO.setMessage("save");
		return new ResponseEntity<>(messageDTO, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/complement/update/{id}/{amount}", method = RequestMethod.GET)
	public ResponseEntity<MessagesDTO> updatArticle(@PathVariable Long id, @PathVariable Integer amount) {
		
		
		
		MessagesDTO messageDTO = new MessagesDTO();
		
		Article article = articleResource.findOne(id);
		article.setAmount(article.getAmount() + amount);
		Article article2 = articleService.complement(article);
		articleResource.save(article2);
		
		messageDTO.setMessage("save");
		return new ResponseEntity<>(messageDTO, HttpStatus.OK);
	}
	
	/**
	 * Dodavanje nove slike 
	 */
	@RequestMapping(value = "/slika/{id}", method = RequestMethod.POST)
	public ResponseEntity<MessagesDTO> addImageByArticle(@RequestParam("file") MultipartFile file, @PathVariable Long id) throws IOException {

		String nameArticle = file.getOriginalFilename();
		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("static/data/" + id + "/"+nameArticle)));
            stream.write(bytes);
            stream.close();
            return new ResponseEntity<>( HttpStatus.OK);
		} else {
			return new ResponseEntity<>( HttpStatus.OK);
		}
		
	}
	
}
