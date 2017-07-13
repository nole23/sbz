package com.sample.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.dto.LoginDTO;
import com.sample.dto.MessagesDTO;
import com.sample.dto.ProfileUserDTO;
import com.sample.dto.UserDTO;
import com.sample.model.ProfileUser;
import com.sample.model.Role;
import com.sample.model.User;
import com.sample.model.UserRola;
import com.sample.repository.ProfileUserRepository;
import com.sample.repository.RoleRepository;
import com.sample.repository.UserRepository;
import com.sample.repository.UserRoleRepository;
import com.sample.security.TokenUtils;

@RestController
@RequestMapping(value = "api/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProfileUserRepository profileUserRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	AuthenticationManager authenticationMenager;

	@Autowired
	private UserDetailsService userDetailsService;
	

	MessagesDTO messagesDTO = new MessagesDTO();

	/**
	 * Create first user of system
	 * @return
	 * radi
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<String> getFirstUser() {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Role role = new Role();
		User user = new User();
		UserRola userRola = new UserRola();
		ProfileUser profileUser = new ProfileUser();
		
		String[] roles = { "USER", "MENAGER", "ADMIN" };
		
		//Cuvanje rola na sistem
		for(int i=0; i<roles.length; i++) {
			Role r = roleRepository.findByName(roles[i]);
			if(r == null) {
				role = new Role();
				role.setName(roles[i]);
				roleRepository.save(role);
			}
		}
		
		roleRepository.save(role);
		
		
		Date dt = new Date();
		profileUser.setCountry("Srbija");
		profileUser.setCity("Novi Sad");
		profileUser.setStreet("Doktora Svetislava Kasapinovica");
		profileUser.setNumberStreet("21");
		profileUser.setRewardPoints(0);
		profileUserRepository.save(profileUser);

		user.setName("Novica");
		user.setSurname("Nikolic");
		user.setEmail("nole0223@gmail.com");
		user.setUsername("nole");
		user.setPassword(encoder.encode("123"));
		user.setCreateProfile(dt);
		user.setProfilUser(profileUser);
		user.setProfilUser(null);
		userRepository.save(user);

		Role r = roleRepository.findByName("ADMIN");
		userRola.setRole(r);
		userRola.setUser(user);
		userRoleRepository.save(userRola);

		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Create list for all User ours application
	 * @return
	 * radi
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> allUser() {
		List<User> user = userRepository.findAll();

		List<UserDTO> userDTO = new ArrayList<>();
		for (User u : user) {
			userDTO.add(new UserDTO(u));
		}
		
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	/**
	 * Login User 
	 * @return
	 * radi
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<MessagesDTO> loginUser(@RequestBody LoginDTO loginDTO) {

		User user = userRepository.findByUsername(loginDTO.getUsername());
		if (user == null) {
			messagesDTO.setError("login");
			return new ResponseEntity<MessagesDTO>(messagesDTO, HttpStatus.OK);
		}

		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					loginDTO.getUsername(), loginDTO.getPassword());
			Authentication authentication = authenticationMenager
					.authenticate(token);
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);

			UserDetails details = userDetailsService
					.loadUserByUsername(loginDTO.getUsername());

			
			messagesDTO.setJwt(tokenUtils.generateToken(details));
			messagesDTO.setRole(user.getUserRola().getRole().getName());
			messagesDTO.setName(user.getName());
			messagesDTO.setSurname(user.getSurname());
			return new ResponseEntity<MessagesDTO>(messagesDTO, HttpStatus.OK);
		} catch (Exception ex) {
			messagesDTO.setError("login");
			return new ResponseEntity<MessagesDTO>(messagesDTO, HttpStatus.OK);
		}
	}

	/**
	 * Registration new users. User saving own personal date and address.
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<MessagesDTO> registrationUser(
			@RequestBody UserDTO userDTO) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = new User();
		UserRola userRola = new UserRola();

		Date dt = new Date();
		Role r = roleRepository.findByName("USER");

		if (userDTO.getEmail().equals("")) {
			messagesDTO.setError("email");
			return new ResponseEntity<MessagesDTO>(HttpStatus.OK);
		}

		if (userDTO.getPassword().equals("")) {
			messagesDTO.setError("pass");
			return new ResponseEntity<MessagesDTO>(HttpStatus.OK);
		}

		user.setName(userDTO.getName());
		user.setSurname(userDTO.getSurname());
		user.setEmail(userDTO.getEmail());
		user.setUsername(userDTO.getUsername());
		user.setPassword(encoder.encode(userDTO.getPassword()));
		user.setCreateProfile(dt);
		userRepository.save(user);

		userRola.setRole(r);
		userRola.setUser(user);
		userRoleRepository.save(userRola);

		messagesDTO.setMessage("registration");
		return new ResponseEntity<MessagesDTO>(HttpStatus.OK);
	}
	
	/**
	 * Registration new Admin of system. New addmin must be registered 
	 * on the system. Exist admin under user add new role.
	 * @return
	 */
	@RequestMapping(value = "/admin/add/{{id}}", method = RequestMethod.GET)
	public ResponseEntity<MessagesDTO> addAdmin(@PathVariable Long id, Principal principal) {
		
		User role = userRepository.findByUsername(principal.getName());
		if(role.getUserRola().getRole().getName().equals("ADMIN")) {
			messagesDTO.setError("admin");
			return new ResponseEntity<MessagesDTO>(messagesDTO, HttpStatus.OK);
		}
		
		User user = userRepository.findOne(id);
		if(user == null) {
			messagesDTO.setError("user.no");
			return new ResponseEntity<MessagesDTO>(messagesDTO, HttpStatus.OK);
		}
		Role r = roleRepository.findByName("ADMIN");
		UserRola userRole = userRoleRepository.findByUser(user);
		userRole.setRole(r);
		userRoleRepository.save(userRole);
		
		messagesDTO.setMessage("admin.update");
		return new ResponseEntity<MessagesDTO>(messagesDTO, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> profile(Principal principal) {
		
		User user = userRepository.findByUsername(principal.getName());
		
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}
	
	/**
	 * Registration new Menager of system. New menager must be registered 
	 * on the system. Exist admin under user add new role.
	 * @return
	 */
	@RequestMapping(value = "/menager/add/{{id}}", method = RequestMethod.GET)
	public ResponseEntity<MessagesDTO> addMenager(@PathVariable Long id, Principal principal) {
		
		User role = userRepository.findByUsername(principal.getName());
		if(role.getUserRola().getRole().getName().equals("ADMIN") || role.getUserRola().getRole().getName().equals("MENAGER")) {
			messagesDTO.setError("admin");
			return new ResponseEntity<MessagesDTO>(messagesDTO, HttpStatus.OK);
		}
		
		User user = userRepository.findOne(id);
		if(user == null) {
			messagesDTO.setError("user.no");
			return new ResponseEntity<MessagesDTO>(messagesDTO, HttpStatus.OK);
		}
		Role r = roleRepository.findByName("MENAGER");
		UserRola userRole = userRoleRepository.findByUser(user);
		userRole.setRole(r);
		userRoleRepository.save(userRole);
		
		messagesDTO.setMessage("menager.update");
		return new ResponseEntity<MessagesDTO>(messagesDTO, HttpStatus.OK);
	}
	
	/**
	 * Registration new SALLER of system. New saller must be registered 
	 * on the system. Exist admin under user add new role.
	 * @return
	 */
	@RequestMapping(value = "/soller/add/{{id}}", method = RequestMethod.GET)
	public ResponseEntity<MessagesDTO> addSoller(@PathVariable Long id, Principal principal) {
		
		User role = userRepository.findByUsername(principal.getName());
		if(role.getUserRola().getRole().getName().equals("MENAGER")) {
			messagesDTO.setError("admin");
			return new ResponseEntity<MessagesDTO>(messagesDTO, HttpStatus.OK);
		}
		
		User user = userRepository.findOne(id);
		if(user == null) {
			messagesDTO.setError("user.no");
			return new ResponseEntity<MessagesDTO>(messagesDTO, HttpStatus.OK);
		}
		Role r = roleRepository.findByName("SOLLER");
		UserRola userRole = userRoleRepository.findByUser(user);
		userRole.setRole(r);
		userRoleRepository.save(userRole);
		
		messagesDTO.setMessage("menager.update");
		return new ResponseEntity<MessagesDTO>(messagesDTO, HttpStatus.OK);
	}
	
	/**
	 * Edit our profile
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseEntity<MessagesDTO> edditProfile(@RequestBody UserDTO userDTO, Principal principal) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		User user = userRepository.findByUsername(principal.getName());
		if(user == null) {
			messagesDTO.setError("login");
			return new ResponseEntity<MessagesDTO>(messagesDTO, HttpStatus.OK);
		}
		
		user.setName(userDTO.getName());
		user.setEmail(user.getEmail());
		user.setPassword(encoder.encode(userDTO.getPassword()));
		user.setSurname(userDTO.getSurname());
		user.setUsername(userDTO.getUsername());
		
		userRepository.save(user);
		this.logout(principal);
		
		messagesDTO.setMessage("save");
		return new ResponseEntity<MessagesDTO>(messagesDTO, HttpStatus.OK);
	}
	
	/**
	 * Edit Profile users
	 * @return
	 */
	@RequestMapping(value = "/edit/profile", method = RequestMethod.POST)
	public ResponseEntity<MessagesDTO> edditProfile(@RequestBody ProfileUserDTO profileUserDTO, Principal principal) {
		
		User user = userRepository.findByUsername(principal.getName());
		if(user == null) {
			messagesDTO.setError("login");
			return new ResponseEntity<MessagesDTO>(messagesDTO, HttpStatus.OK);
		}
		
		ProfileUser profileUser = profileUserRepository.findOne(user.getProfilUser().getId());

		profileUser.setCity(profileUserDTO.getCity());
		profileUser.setCountry(profileUserDTO.getCountry());
		profileUser.setNumberStreet(profileUserDTO.getNumberStreet());
		profileUser.setRewardPoints(profileUserDTO.getRewardPoints());
		profileUser.setStreet(profileUserDTO.getStreet());
		
		profileUserRepository.save(profileUser);
		
		messagesDTO.setMessage("save");
		return new ResponseEntity<MessagesDTO>(messagesDTO, HttpStatus.OK);
	}
	
	
	/**
	 * Logoutn user
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<MessagesDTO> logout(Principal principal) {
		
		
		
		User user = userRepository.findByUsername(principal.getName());
		if(user == null) {
			messagesDTO.setError("login");
			return new ResponseEntity<MessagesDTO>(messagesDTO, HttpStatus.OK);
		}
		
		
        //treba implementirati

		
		messagesDTO.setMessage("logout");
		return new ResponseEntity<MessagesDTO>(messagesDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/size", method = RequestMethod.GET)
	public ResponseEntity<MessagesDTO> size() {

		List<User> user = userRepository.findAll();
		System.out.println("dosao: "+user.size());
		messagesDTO.setSize(user.size());
		return new ResponseEntity<MessagesDTO>(messagesDTO, HttpStatus.OK);
	}
	
	/**
	 * Iscitavamo nagradne bodove korisnika
	 */
	@RequestMapping(value = "/rewared/points", method = RequestMethod.GET)
	public ResponseEntity<MessagesDTO> bodovi(Principal principal) {

		MessagesDTO message = new MessagesDTO();
		if(principal != null){
			User user = userRepository.findByUsername(principal.getName());
			System.out.println("1");
			if(user.getProfilUser() != null && user.getProfilUser().getRewardPoints()>0){
				System.out.println("2");
				message.setSize(user.getProfilUser().getRewardPoints());
				
				return new ResponseEntity<MessagesDTO>(message, HttpStatus.OK);
			} else {
				System.out.println("3");
				message.setMessage("prayno");
				return new ResponseEntity<MessagesDTO>(message, HttpStatus.OK);
			}
		}
		return new ResponseEntity<MessagesDTO>(message, HttpStatus.OK);
	
		
	}
	
}
