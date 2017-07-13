package com.sample.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.dto.CategoryUserDTO;
import com.sample.dto.MessagesDTO;
import com.sample.model.CategoryUser;
import com.sample.model.SpendingLimit;
import com.sample.repository.CategoryUserRepository;
import com.sample.repository.SpendingLimitRepository;

@RestController
@RequestMapping(value = "api/prag")
public class PragPotrosnjeController {

	@Autowired
	private CategoryUserRepository categoryUserRespository;
	
	@Autowired
	private SpendingLimitRepository spendigLimitRepository;
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryUserDTO>> popust(Principal principal) {
		
		List<CategoryUser> categoryUser = categoryUserRespository.findAll();
		List<CategoryUserDTO> categoryUserDTO = new ArrayList<>();
		for(CategoryUser cu: categoryUser){
			categoryUserDTO.add(new CategoryUserDTO(cu));
		}
		
		return new ResponseEntity<>(categoryUserDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/one/{id}", method = RequestMethod.GET)
	public ResponseEntity<CategoryUserDTO> jedna(Principal principal, @PathVariable Long id) {
		
		CategoryUser categoryUser = categoryUserRespository.findOne(id);
		
		
		return new ResponseEntity<>(new CategoryUserDTO(categoryUser), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<MessagesDTO> jedna(Principal principal, @RequestBody CategoryUserDTO categoryUserDTO) {
		MessagesDTO messagesDTO = new MessagesDTO();
		
		CategoryUser categoryUser = categoryUserRespository.findOne(categoryUserDTO.getId());
		if(categoryUserDTO.getSpendingLimitDTO().getId() == null){
			SpendingLimit spendingLimit = new SpendingLimit();
			spendingLimit.setMinLimint(categoryUserDTO.getSpendingLimitDTO().getMinLimint());
			spendingLimit.setMaxLimit(categoryUserDTO.getSpendingLimitDTO().getMaxLimit());
			spendingLimit.setDiscountPercent(categoryUserDTO.getSpendingLimitDTO().getDiscountPercent());
			spendigLimitRepository.save(spendingLimit);
			
			categoryUser.setSpendingLimit(spendingLimit);
			categoryUserRespository.save(categoryUser);
		} else {
			SpendingLimit spendingLimit = spendigLimitRepository.findOne(categoryUserDTO.getSpendingLimitDTO().getId());
			spendingLimit.setMinLimint(categoryUserDTO.getSpendingLimitDTO().getMinLimint());
			spendingLimit.setMaxLimit(categoryUserDTO.getSpendingLimitDTO().getMaxLimit());
			spendingLimit.setDiscountPercent(categoryUserDTO.getSpendingLimitDTO().getDiscountPercent());
			spendigLimitRepository.save(spendingLimit);
		}
		
		messagesDTO.setMessage("sacuvano");
		return new ResponseEntity<>(messagesDTO, HttpStatus.OK);
	}
}
