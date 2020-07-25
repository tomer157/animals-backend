package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thomas.findlocation.entities.Users;
import com.thomas.findlocation.repos.UserDao;

@Service

public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userDao.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return null;
	}

	@Override
	public Users findByUsername(String username) {
		
		return userDao.findByUsername(username);
	}

}
