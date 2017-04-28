package com.karthi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthi.form.RegistrationForm;
import com.karthi.model.User;
import com.karthi.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findByEmailAndPassword(String email,String password){
		return userRepository.findByEmailAndPassword(email, password);
	}

	public void save(User user){
		userRepository.save(user);
	}
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
	
	public void register(RegistrationForm regUserForm) throws Exception {

		User user = userRepository.findByEmail(regUserForm.getEmail());
		System.out.println("Is email already exists? "+ user);
		if ( user != null) {
			throw new Exception ("Email already exists!!!");
		}
		User userObj = new User();
		userObj.setName(regUserForm.getName());
		userObj.setEmail(regUserForm.getEmail());
		userObj.setPassword(regUserForm.getPassword());
		userRepository.save(userObj);
        
		// Send Registration Notification Mail
		String subject = "Your account has been created";
		String body = "Welcome to Revature ! You can login to your account !";
		//emailUtil.send(user.getEmail(), subject, body);

}

}
