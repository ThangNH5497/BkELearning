package bk.elearning.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bk.elearning.entity.User;
import bk.elearning.entity.dto.CustomUserDetails;
import bk.elearning.service.IUserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	IUserService userService;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userService.getByUsername(username);
		if (user == null) {
			return null;
		}
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));

		/*
		 * return new User(username, user.getPassword(), enabled, accountNonExpired,
		 * credentialsNonExpired, accountNonLocked,grantedAuthorities);
		 */
		// return custom user them truong email,addr
		return new CustomUserDetails(username, user.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, grantedAuthorities, user.getId(), user.getCode(), user.getFullName(), user.getRole(),user.getImage());
	}

}