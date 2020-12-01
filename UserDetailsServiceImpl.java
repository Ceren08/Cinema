package com.cinema.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinema.dao.AuthoritiesDao;
import com.cinema.dao.UserDetailsDao;
import com.cinema.model.users;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserDetailsDao userDetailsDao;
  
  @Autowired
  private AuthoritiesDao authoritiesDao;
  

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    users user = userDetailsDao.findUserByUsername(username);
 
    
    UserBuilder builder = null;
    if (user != null) {
      
      builder = org.springframework.security.core.userdetails.User.withUsername(username);
      builder.disabled(!user.isEnabled());
      builder.password(user.getPassword().trim());
 
      String[] authorities = {(authoritiesDao.findAuthorityByName(username).get(0)).toString().trim()}; 
      /*user.getAuthorities().stream().map(a -> a.getAuthority()).toArray(String[]::new)*//*{"ADMIN"}*/;
            
      builder.authorities(authorities);
    } else {
      throw new UsernameNotFoundException("User not found.");
    }
    return builder.build();
  }
  
  
  private static final AtomicLong counter = new AtomicLong();
	
	private static List<users> users;
	

	public List<users> findAllUsers() {
		return users;
	}
	
	public users findById(long id) {
		for(users user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	
	public users findByName(String name) {
		for(users user : users){
			if(user.getUsername().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}
//	
//	public void saveUser(User user) {
//		user.setId(counter.incrementAndGet());
//		users.add(user);
//	}
//
//	public void updateUser(User user) {
//		int index = users.indexOf(user);
//		users.set(index, user);
//	}
//
//	public void deleteUserById(long id) {
//		
//		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
//		    User user = iterator.next();
//		    if (user.getId() == id) {
//		        iterator.remove();
//		    }
//		}
//	}
//
//	public boolean isUserExist(User user) {
//		return findByName(user.getName())!=null;
//	}
//
//	private static List<User> populateDummyUsers(){
//		List<User> users = new ArrayList<User>();
//		users.add(new User(counter.incrementAndGet(),"Sam",30, 70000));
//		users.add(new User(counter.incrementAndGet(),"Tom",40, 50000));
//		users.add(new User(counter.incrementAndGet(),"Jerome",45, 30000));
//		users.add(new User(counter.incrementAndGet(),"Silvia",50, 40000));
//		return users;
//	}
//
//	public void deleteAllUsers() {
//		users.clear();
//	}


  
}
