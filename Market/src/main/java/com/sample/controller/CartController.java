package com.sample.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.dto.AccountDTO;
import com.sample.dto.CartDTO;
import com.sample.dto.ItemsAccountDTO;
import com.sample.dto.MessagesDTO;
import com.sample.dto.PopustiDTO;
import com.sample.model.Account;
import com.sample.model.Article;
import com.sample.model.CategoryArticle;
import com.sample.model.ItemsAccount;
import com.sample.model.ListActionCategory;
import com.sample.model.ListItemsAccount;
import com.sample.model.Popusti;
import com.sample.model.ProfileUser;
import com.sample.model.User;
import com.sample.repository.AccountReposotory;
import com.sample.repository.ArticleRepository;
import com.sample.repository.CategoryArticleRepository;
import com.sample.repository.ItemsAccountRepository;
import com.sample.repository.ListActionCategoryRepository;
import com.sample.repository.ListItemsAccountRepository;
import com.sample.repository.PopustiRepository;
import com.sample.repository.ProfileUserRepository;
import com.sample.repository.UserRepository;
import com.sample.service.AccountService;
import com.sample.service.ArticleService;
import com.sample.service.ItemsAccountService;
import com.sample.service.ProfileUserService;

@RestController
@RequestMapping(value = "api/cart")
public class CartController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryArticleRepository categoryRepository;
	
	@Autowired
	private ListActionCategoryRepository actionRepository;
	
	@Autowired
	private AccountReposotory accountReposotory;
	
	@Autowired
	private ItemsAccountRepository itemsAccountRepository;
	
	@Autowired
	private ListItemsAccountRepository listItemAccountRepository;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PopustiRepository popustRepository;
	
	@Autowired
	private ItemsAccountService itemsAccountService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ProfileUserRepository profilUserRepository;
	
	@Autowired
	private ProfileUserService profilUserService;
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<MessagesDTO> addCart(@RequestBody CartDTO cartDTO, Principal principal) {
		MessagesDTO messageDTO = new MessagesDTO();
		
		System.out.println("Prvi");
		Date date = new Date();
		User user = userRepository.findByUsername(principal.getName());
		
		List<Account> aaa= accountReposotory.findByUser(user);				//Provera da li postoji racun
		ItemsAccount itemsAccount = new ItemsAccount();				//popunjava artikle racuna
		ListItemsAccount listItemsAccount = new ListItemsAccount(); //Lista koja povezuje racun i artikle
		
		boolean status = false;
		Long idAcc = 0l;
		for(Account a: aaa){
			if(a.getStanje().equals("KREIRA")){
				idAcc = a.getId();
				status = true;
				break;
			}
		}
	
		
		Article article = articleRepository.findOne(cartDTO.getArticleDTO().getId());
		CategoryArticle category = categoryRepository.findOne(article.getPodCategory().getCategoryArticle().getId());
		ListActionCategory listAction = actionRepository.findByCategoryArticle(category);
		
		if(!status){
			Account accNew = new Account();
			System.out.println("Drugi");
			itemsAccount.setOriginalPrice(article.getPrice() * cartDTO.getCount());
			itemsAccount.setCount(cartDTO.getCount());
			itemsAccount.setArticle(article);
			
			//Pravilo iz drools da li treba da artikl ide na popust ili ne
			ItemsAccount ia2 = itemsAccountService.popust(itemsAccount, listAction);
			itemsAccountRepository.save(ia2);
			
			accNew.setDateCreateAccount(date);
			accNew.setOriginalPrice(ia2.getOriginalPrice());
			accNew.setNewPrice(ia2.getNewPrice());
			accNew.setStanje("KREIRA");
			accNew.setUser(user);
			accountReposotory.save(accNew);

			listItemsAccount.setAccount(accNew);
			listItemsAccount.setItemsAccount(ia2);
			listItemAccountRepository.save(listItemsAccount);

			//System.out.println(ia.getNewPrice());
			
			return new ResponseEntity<>(messageDTO, HttpStatus.OK);
		} else if(status){
			//Racun postoji zato samo azuriramo neke podatke
			System.out.println("treci");
			Account account = accountReposotory.findOne(idAcc);
			List<ListItemsAccount> proveraListe = listItemAccountRepository.findByAccount(account);
			
			Long idArtc = 0l;
			boolean statusItems = false;
			for(ListItemsAccount lia: proveraListe){
				if(lia.getItemsAccount().getArticle() == article){
					idArtc = lia.getItemsAccount().getId();
					statusItems = true;
					break;
				}
			}
			
			if(statusItems){
				//Ukoliko postoji artikl povecavamo samo njegov countrer
				ItemsAccount postoji = itemsAccountRepository.findOne(idArtc);
				if(listAction != null){
					//postoji popust na artikl
					ItemsAccount item = new ItemsAccount();
					item.setOriginalPrice(article.getPrice());
					ItemsAccount ia2 = itemsAccountService.popust(item, listAction);
					postoji.setCount(postoji.getCount() + 1);
					postoji.setOriginalPrice(postoji.getOriginalPrice() + article.getPrice());
					postoji.setNewPrice(postoji.getNewPrice()  + ia2.getNewPrice());
					itemsAccountRepository.save(postoji);
					
					account.setOriginalPrice(account.getOriginalPrice() + article.getPrice());
					account.setNewPrice(account.getNewPrice() + ia2.getNewPrice());
					accountReposotory.save(account);
					System.out.println("postoji popust za dupli artikl");
					
				} else {
					// ne postoji popust na artikl
					postoji.setCount(postoji.getCount() + 1);
					postoji.setOriginalPrice(postoji.getOriginalPrice() + article.getPrice());
					postoji.setNewPrice(postoji.getNewPrice()  + article.getPrice());
					itemsAccountRepository.save(postoji);
					
					account.setOriginalPrice(account.getOriginalPrice() + article.getPrice());
					account.setNewPrice(account.getNewPrice() + article.getPrice());
					accountReposotory.save(account);
					
					System.out.println("Ne postoji popust za dupli artikl");
				}
				return new ResponseEntity<>(messageDTO, HttpStatus.OK);
			} else {
				//Ukoliko ne postoji artikli dodajemo ga kao novi
				ItemsAccount item = new ItemsAccount();
				item.setOriginalPrice(article.getPrice());
				item.setArticle(article);
				item.setCount(cartDTO.getCount());
				
				if(listAction != null){
					//postoji popust
					ItemsAccount ia2 = itemsAccountService.popust(item, listAction);
					itemsAccountRepository.save(ia2);
					
					account.setOriginalPrice(account.getOriginalPrice() + ia2.getOriginalPrice());
					account.setNewPrice(account.getNewPrice() + ia2.getNewPrice());
					accountReposotory.save(account);
					
					listItemsAccount.setAccount(account);
					listItemsAccount.setItemsAccount(ia2);
					listItemAccountRepository.save(listItemsAccount);
					System.out.println("na postojeci racun dodat novi artikl sa popustom");
					
				} else {
					//nepostoji popust
					
					item.setNewPrice(item.getOriginalPrice());
					item.setDiscountPrecentage(0);
					itemsAccountRepository.save(item);
					
					account.setOriginalPrice(account.getOriginalPrice() + item.getOriginalPrice());
					account.setNewPrice(account.getNewPrice() + item.getNewPrice());
					accountReposotory.save(account);
					
					listItemsAccount.setAccount(account);
					listItemsAccount.setItemsAccount(item);
					listItemAccountRepository.save(listItemsAccount);
					System.out.println("na postojeci racun dodat novi artikl bez popustom");
				}
				
			}
			return new ResponseEntity<>(messageDTO, HttpStatus.OK);
		} else{
			
			System.out.println("Postoji racun ali je zatvoren");
			return null;
		}
		
	}
	
	/**
	 * Smanjujemo velicinu koju kupujemo i azuriramo automatski konacnu cenu i povecavanje
	 * u yavisnosti sta zelimo
	 */
	@RequestMapping(value = "/minus/{tip}/{id}/{idAccount}", method = RequestMethod.GET)
	public ResponseEntity<MessagesDTO> minus(Principal principal, @PathVariable Long id, @PathVariable Long idAccount, @PathVariable String tip) {
		System.out.println(idAccount);
		MessagesDTO messageDTO = new MessagesDTO();
		
		User user = userRepository.findByUsername(principal.getName());
		if(user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		ItemsAccount itemsAccaunt = itemsAccountRepository.findOne(id);
		Account account = accountReposotory.findOne(idAccount);
		
		int count = itemsAccaunt.getCount();
		double originalPrice = itemsAccaunt.getOriginalPrice() / count;
		double newPrice = itemsAccaunt.getNewPrice() / count;
		
		if(tip.equals("minus")){
			itemsAccaunt.setCount(itemsAccaunt.getCount() - 1);
			itemsAccaunt.setOriginalPrice(itemsAccaunt.getOriginalPrice() - originalPrice);
			itemsAccaunt.setNewPrice(itemsAccaunt.getNewPrice() - newPrice);
		} else {
			itemsAccaunt.setCount(itemsAccaunt.getCount() + 1);
			itemsAccaunt.setOriginalPrice(itemsAccaunt.getOriginalPrice() + originalPrice);
			itemsAccaunt.setNewPrice(itemsAccaunt.getNewPrice() + newPrice);
		}
		
		itemsAccountRepository.save(itemsAccaunt);
		
		if(tip.equals("minus")){
			account.setOriginalPrice(account.getOriginalPrice() - originalPrice);
			account.setNewPrice(account.getNewPrice() - newPrice);
		} else {
			account.setOriginalPrice(account.getOriginalPrice() + originalPrice);
			account.setNewPrice(account.getNewPrice() + newPrice);
		}
		
		accountReposotory.save(account);
		
		
		messageDTO.setMessage("smanjeno");
		return new ResponseEntity<>(messageDTO, HttpStatus.OK);
	}
	
	
	/**
	 * Brisanje iz korpe
	 */
	@RequestMapping(value = "/delete/{id}/{idAccount}", method = RequestMethod.GET)
	public ResponseEntity<MessagesDTO> deleteCartItem(Principal principal, @PathVariable Long id, @PathVariable Long idAccount) {
		
		MessagesDTO messagesDTO = new MessagesDTO();
		
		User user = userRepository.findByUsername(principal.getName());
		if(user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Account account = accountReposotory.findOne(idAccount);
		
		ItemsAccount itemsAccount = itemsAccountRepository.findOne(id);
		ListItemsAccount listItemsAccount = listItemAccountRepository.findByItemsAccount(itemsAccount);
		
		account.setOriginalPrice(account.getOriginalPrice() - itemsAccount.getOriginalPrice());
		account.setNewPrice(account.getNewPrice() - itemsAccount.getNewPrice());
		accountReposotory.save(account);
		
		listItemAccountRepository.delete(listItemsAccount);
		itemsAccountRepository.delete(itemsAccount);
		
		if(account.getListItemsAccount().isEmpty()){
			accountReposotory.delete(account);
		}
			
		
		messagesDTO.setMessage("Obrisano");
		return new ResponseEntity<>(messagesDTO, HttpStatus.OK);
	}

	/**
	 * Prilikom kupovine treba da odradimo pravila koja nam umanjuju dodatno 
	 */
	@RequestMapping(value = "/kupiti/{id}/{zetoni}", method = RequestMethod.GET)
	public ResponseEntity<MessagesDTO> kupiti(Principal principal, @PathVariable Long id, @PathVariable String zetoni) {
		/*
		 * Ovo se sve brise
		 */
		MessagesDTO messagesDTO = new MessagesDTO();
		
		User user = userRepository.findByUsername(principal.getName());
		Account account = accountReposotory.findOne(id);
		
		List<Popusti> popu = popustRepository.findByAccount(account);
		List<PopustiDTO> popustiDTO = new ArrayList<>();
		int discAll = 0;
		for(Popusti p: popu){
			popustiDTO.add(new PopustiDTO(p));
			discAll += p.getProcenat();
		}
		
		int zeton = 0;
		if(zetoni.equals("da")){
			zeton = user.getProfilUser().getRewardPoints();
		}
		
		account.setNewPrice((account.getNewPrice() - (( account.getNewPrice() * discAll ) / 100)) - zeton);
		account.setStanje("PORUCIVANJE");
		
		account.setSetPoint(zeton);
		
		accountReposotory.save(account);
		
		messagesDTO.setMessage("kreiran racun");
		return new ResponseEntity<>(messagesDTO, HttpStatus.OK);
	}
	
	
	/**
	 * Treba da proveri da li racun racun moze da se ostvari ako moze prihvatamo ga ako ne otkazujemo
	 */
	@RequestMapping(value = "/admin/prihvati", method = RequestMethod.POST)
	public ResponseEntity<MessagesDTO> adminKupiti(@RequestBody AccountDTO accountDTO) {
		/*
		 * Ovo se sve brise
		 */
		MessagesDTO messagesDTO = new MessagesDTO();
		System.out.println("upao kako treba " +accountDTO.getId());
		
		User user = userRepository.findOne(accountDTO.getUserDTO().getId());
		Account account = accountReposotory.findOne(accountDTO.getId());
		
		List<ListItemsAccount> listItemsAccounts = listItemAccountRepository.findByAccount(account);
		for(ListItemsAccount lia: listItemsAccounts){
			ItemsAccount itemsAccount = itemsAccountRepository.findOne(lia.getItemsAccount().getId());
			Article article = articleRepository.findOne(itemsAccount.getArticle().getId());
			Article item = articleService.proveraRaspolozivosti(itemsAccount, article);
			System.out.println(item.getAmount() +  " " + article.getAmount());
			if(item.getAmount() > article.getAmount()) {
				account.setStanje("OTKAZANO");
				
				accountReposotory.save(account);
				messagesDTO.setError("nema srestava");
				return new ResponseEntity<>(messagesDTO, HttpStatus.OK);
			}
			articleRepository.save(item);
		}
		
		
		
		account.setStanje("KUPLJENO");
		
		
		
		ProfileUser profileUser = profilUserRepository.findOne(user.getProfilUser().getId());
		profileUser.setRewardPoints(profileUser.getRewardPoints() - account.getSetPoint());
		int ostali = profileUser.getRewardPoints();
		
		ProfileUser profileUserNagradni = profilUserService.nagradni(profileUser, account);
		
		profileUserNagradni.setRewardPoints(profileUserNagradni.getRewardPoints() + ostali);
		profilUserRepository.save(profileUserNagradni);
		
		account.setGetPoint(profileUserNagradni.getRewardPoints());
		
		accountReposotory.save(account);
		
		messagesDTO.setMessage("kreiran racun");
		return new ResponseEntity<>(messagesDTO, HttpStatus.OK);
	}
	
	/**
	 * Provera drools pravila
	 */
	@RequestMapping(value = "/popust/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<PopustiDTO>> popust(Principal principal, @PathVariable Long id) {
		
		User user = userRepository.findByUsername(principal.getName());
		if(user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		//System.out.println(user.getProfilUser().getCategoryUser().getNameCategory());
		Account account = accountReposotory.findOne(id);
		Popusti popusti = new Popusti();
		Account i2 = accountService.getPopust(account, popusti);
		List<Popusti> popu = popustRepository.findByAccount(account);
		List<PopustiDTO> popustiDTO = new ArrayList<>();
		for(Popusti p: popu){
			popustiDTO.add(new PopustiDTO(p));
		}
		return new ResponseEntity<>(popustiDTO, HttpStatus.OK);
	}
	
	/**
	 * Brisati dodate popuste
	 */
	@RequestMapping(value = "/otkazati/popuste/{id}", method = RequestMethod.GET)
	public ResponseEntity<MessagesDTO> popustOtkazat(Principal principal, @PathVariable Long id) {
		MessagesDTO messagesDTO = new MessagesDTO();
		
		User user = userRepository.findByUsername(principal.getName());
		if(user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Account account = accountReposotory.findOne(id);
		List<Popusti> popusti = popustRepository.findByAccount(account);
		
		for(Popusti p: popusti){
			popustRepository.delete(p);
		}
		
		messagesDTO.setMessage("Otkazano");
		return new ResponseEntity<>(messagesDTO, HttpStatus.OK);
	}

	/**
	 * Ispis svih artikala za koji su u korpi za odredjenog korisnika
	 * @param principal
	 * @return
	 */
	
	@RequestMapping(value = "/all/{tip}", method = RequestMethod.GET)
	public ResponseEntity<AccountDTO> allByUser(Principal principal, @PathVariable String tip) {
		
		if(principal == null)
			return new ResponseEntity<>(HttpStatus.OK);
		
		User user = userRepository.findByUsername(principal.getName());
		List<Account> acc = accountReposotory.findByUser(user);
		if(!acc.isEmpty()){
			
			Long id = 0l;
			for(Account a: acc){
				if(a.getStanje().equals(tip)){
					id = a.getId();
				}
			}
			Account account = accountReposotory.findOne(id);
			if(account == null){
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(new AccountDTO(account), HttpStatus.OK);
		} else {
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<AccountDTO>> allByUserFinis(Principal principal) {
		
		
		if(principal != null){
			User user = userRepository.findByUsername(principal.getName());
			List<Account> acc = accountReposotory.findByUser(user);
			List<AccountDTO> accountDTO = new ArrayList<>();
			for(Account a: acc){
				if(a.getStanje().equals("KUPLJENO")){
					accountDTO.add(new AccountDTO(a));
				}
			}
			return new ResponseEntity<>(accountDTO, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
	/**
	 * pregled svih racuna koji postoje u bazi
	 */
	@RequestMapping(value = "/admin/all/{tip}", method = RequestMethod.GET)
	public ResponseEntity<List<AccountDTO>> allAdmin(@PathVariable String tip) {
		
		List<Account> acc = accountReposotory.findAll();
		List<AccountDTO> accountDTO = new ArrayList<>();
		for(Account a: acc){
			if(a.getStanje().equals(tip))
				accountDTO.add(new AccountDTO(a));
		}
		System.out.println("Upao");
		acc.clear();
		return new ResponseEntity<>(accountDTO, HttpStatus.OK);
	}
	
	
	/**
	 * Prikaz jedog racuna koji je zavrsen
	 */
	@RequestMapping(value = "/finis/{id}", method = RequestMethod.GET)
	public ResponseEntity<AccountDTO> getFinisAccout(@PathVariable Long id) {
		
		//User user = userRepository.findByUsername(principal.getName());
		Account account = accountReposotory.findOne(id);
		
		return new ResponseEntity<>(new AccountDTO(account), HttpStatus.OK);
	}
	
	
}
