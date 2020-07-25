package service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.thomas.findlocation.entities.Users;

public interface UserService extends UserDetailsService {
	Users findByUsername(String username);

}
