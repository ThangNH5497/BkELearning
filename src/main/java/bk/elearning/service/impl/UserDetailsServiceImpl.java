package bk.elearning.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
/*
		vn.nongsanquehuong.entity.User user = userService.findByUserName(username);
		if (user == null) {
			return null;
		}
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            System.out.println(role.getName());
        }
        /*
		return new User(username, user.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked,grantedAuthorities);
				*/
        //return custom user them truong email,addr
      //  return new CustomUserDetails(username, user.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
		//		accountNonLocked,grantedAuthorities,user.getId(),user.getName(),user.getEmail(),user.getPhoneNumber(),user.getAddr());
		return null;
	}

}