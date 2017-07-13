package com.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.dto.ActionEventDTO;
import com.sample.dto.MessagesDTO;
import com.sample.model.CategoryArticle;
import com.sample.model.ListActionCategory;
import com.sample.repository.ActionEvent;
import com.sample.repository.CategoryArticleRepository;
import com.sample.repository.ListActionCategoryRepository;


@RestController
@RequestMapping(value = "api/admin")
public class AdminController {

	@Autowired
	private ActionEvent actionEvent;
	
	@Autowired
	private ListActionCategoryRepository listActionCategoryRepository;
	
	@Autowired
	private CategoryArticleRepository categoryArticleRepository;
	
	@RequestMapping(value = "/event", method = RequestMethod.POST)
	public ResponseEntity<MessagesDTO> getAllCategory(@RequestBody ActionEventDTO actionEventDTO) {
		
		MessagesDTO messageDTO = new MessagesDTO();
		
		CategoryArticle categoryArticle = categoryArticleRepository.findOne(actionEventDTO.getKategorija());
		
		ListActionCategory listActionCategory = new ListActionCategory();
		
		com.sample.model.ActionEvent action = new com.sample.model.ActionEvent();
		action.setNameAction(actionEventDTO.getNameAction());
		action.setStartDate(actionEventDTO.getStartDate());
		action.setEndDate(actionEventDTO.getEndDate());
		action.setProcent(actionEventDTO.getProcent());
		actionEvent.save(action);
		
		listActionCategory.setActionEvent(action);
		listActionCategory.setCategoryArticle(categoryArticle);
		
		listActionCategoryRepository.save(listActionCategory);
		
		messageDTO.setMessage("Sacuvano");
		
		return new ResponseEntity<>(messageDTO, HttpStatus.OK);
	}
}
