package com.karthi.repository;
import org.springframework.data.jpa.repository.JpaRepository;


import com.karthi.model.User;
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmailAndPassword(String email, String password);

	public User findByEmail(String email);
	}
 