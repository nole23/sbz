package com.sample.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.sample.dto.ArticleDTO;
import com.sample.dto.CategoryArticleDTO;
import com.sample.dto.MessagesDTO;
import com.sample.dto.PodCategoryDTO;
import com.sample.dto.SearchDTO;
import com.sample.model.Article;
import com.sample.model.CategoryArticle;
import com.sample.model.PodCategory;
import com.sample.repository.ArticleRepository;
import com.sample.repository.CategoryArticleRepository;
import com.sample.repository.PodCategoryRepository;
import com.sample.service.ArticleService;
import com.sample.service.SearchService;

@RestController
@RequestMapping(value = "api/category")
public class CategoryController {

	@Autowired
	private CategoryArticleRepository categoryRepositry;
	
	@Autowired
	private PodCategoryRepository podCategoryRepository;
	
	@Autowired
	private ArticleRepository articleResource;
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private ArticleService articleService;
	
	/*
	 * Get all category
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryArticleDTO>> getAllCategory() {
		
		List<CategoryArticle> category = categoryRepositry.findAll();
		
		List<CategoryArticleDTO> categoryDTO = new ArrayList<>();
		for(CategoryArticle ca: category){
			categoryDTO.add(new CategoryArticleDTO(ca));
		}
		
		return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/podcategory", method = RequestMethod.GET)
	public ResponseEntity<List<PodCategoryDTO>> getAllPodCategory() {
		
		List<PodCategory> podCategory = podCategoryRepository.findAll();
		
		List<PodCategoryDTO> podCategoryDTO = new ArrayList<>();
		for(PodCategory pc: podCategory){
			podCategoryDTO.add(new PodCategoryDTO(pc));
		}
		
		return new ResponseEntity<>(podCategoryDTO, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/podcategory/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<PodCategoryDTO>> getPodcategoryByCategory(@PathVariable Long id) {
		
		CategoryArticle categoryArticle = categoryRepositry.findOne(id);
		
		List<PodCategory> podCategory = podCategoryRepository.findByCategoryArticle(categoryArticle);
		
		List<PodCategoryDTO> podCategoryDTO = new ArrayList<>();
		for(PodCategory pc: podCategory){
			podCategoryDTO.add(new PodCategoryDTO(pc));
		}
		
		return new ResponseEntity<>(podCategoryDTO, HttpStatus.OK);
	}
	
	
	/**
	 * Get 4 category
	 */
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryArticleDTO>> getForCategory() {
		
		Page<CategoryArticle> category = categoryRepositry.findAll(new PageRequest(0, 5));
		
		List<CategoryArticleDTO> categoryDTO = new ArrayList<>();
		for(CategoryArticle ca: category){
			categoryDTO.add(new CategoryArticleDTO(ca));
		}
		
		return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public ResponseEntity<CategoryArticleDTO> getCategoryById(@PathVariable Long id) {
		
		CategoryArticle category = categoryRepositry.findOne(id);
		
		return new ResponseEntity<>(new CategoryArticleDTO(category), HttpStatus.OK);
	}
	
	/**
	 * Azuriranje podataka kategorije
	 */
	@RequestMapping(value = "/update/category", method = RequestMethod.GET)
	public ResponseEntity<MessagesDTO> getCategoryById(@RequestBody CategoryArticleDTO categoryArticleDTO) {
		
		MessagesDTO messagesDTO = new MessagesDTO();
		
		CategoryArticle category = categoryRepositry.findOne(categoryArticleDTO.getId());
		category.setMaxDiscount(categoryArticleDTO.getMaxDiscount());
		category.setNameCategory(categoryArticleDTO.getNameCategory());
		categoryRepositry.save(category);
		
		messagesDTO.setMessage("sacuvano");
		return new ResponseEntity<>(messagesDTO, HttpStatus.OK);
	}
	
	
	
	/**
	 * Pretraga po svim parametrima zasebno ili odvojeno
	 */
	@RequestMapping(value = "/category/search", method = RequestMethod.POST)
	public ResponseEntity<List<ArticleDTO>> search(@RequestBody SearchDTO searchDTO) {
		List<ArticleDTO> articleDTO = new ArrayList<ArticleDTO>();
		
		//pretraga po ceni
		double min = 1l;
		if(searchDTO.getMinCena() != 0){
			min = searchDTO.getMinCena();
		}

		double max = 200000l;
		if(searchDTO.getMaxCena() != 0){
			min = searchDTO.getMaxCena();
		}

		if(!searchDTO.getNameArticle().equals("null")){

			List<Article> article = articleService.search(searchDTO.getNameArticle());
			if(searchDTO.getPodCategory() != null){
				PodCategory podCategory = podCategoryRepository.findOne(searchDTO.getPodCategory());
				for(Article a: article){
					if(a.getPrice() >= min){
						if(a.getPrice() <= max) {
							if(a.getPodCategory() == podCategory){
								articleDTO.add(new ArticleDTO(a));
							}
						}
					}
				}
			} else {
				for(Article a: article){
					if(a.getPrice() >= min){
						if(a.getPrice() <= max) {
							articleDTO.add(new ArticleDTO(a));
						}
					}
				}
			}
		} else {
			List<Article> article = articleResource.findAll();
			if(searchDTO.getPodCategory() != null){
				PodCategory podCategory = podCategoryRepository.findOne(searchDTO.getPodCategory());
				for(Article a: article){
					if(a.getPrice() >= min){
						if(a.getPrice() <= max) {
							if(a.getPodCategory() == podCategory){
								articleDTO.add(new ArticleDTO(a));
							}
						}
					}
				}
				
			} else {
				for(Article a: article){
					if(a.getPrice() >= min){
						if(a.getPrice() <= max) {
							
							articleDTO.add(new ArticleDTO(a));
							
						}
					}
				}

			}
		}
		
		return new ResponseEntity<>(articleDTO, HttpStatus.OK);
			
	
		
		
	}
	
	
	@RequestMapping(value = "/add/pod/category", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessagesDTO> setArticle(@RequestBody PodCategoryDTO podCategoryDTO) {
		
		
		System.out.println("upao");
		MessagesDTO messageDTO = new MessagesDTO();
		
		CategoryArticle category = categoryRepositry.findOne(podCategoryDTO.getCategoryArticleDTO().getId());
		
		PodCategory podCategory = new PodCategory();
		podCategory.setNamePodCateogry(podCategoryDTO.getNamePodCateogry());
		podCategory.setCategoryArticle(category);
		
		podCategoryRepository.save(podCategory);

		messageDTO.setMessage("save");
		return new ResponseEntity<>(messageDTO, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/add/category", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessagesDTO> setCategory(@RequestBody CategoryArticleDTO categoryArticleDTO) {
		
		
		System.out.println("upao");
		MessagesDTO messageDTO = new MessagesDTO();
		
		CategoryArticle categoryArticle = new CategoryArticle();
		categoryArticle.setNameCategory(categoryArticleDTO.getNameCategory());
		categoryArticle.setUniqueCode(categoryArticleDTO.getUniqueCode());
		categoryArticle.setSirokePotrosnje(categoryArticleDTO.isSirokePotrosnje());
		categoryArticle.setMaxDiscount(categoryArticleDTO.getMaxDiscount());
		
		categoryRepositry.save(categoryArticle);
		
		messageDTO.setMessage("save");
		return new ResponseEntity<>(messageDTO, HttpStatus.OK);
	}
}
