package com.example.demo.security.services;

import com.example.demo.domain.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Data
public class JwtUser implements UserDetails {
	private static final long serialVersionUID = 1L;

	private UUID jwtUserId;
	private String username;
	private String email;
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public JwtUser(UUID jwtUserId, String username, String email, String password,
				   Collection<? extends GrantedAuthority> authorities) {
		this.jwtUserId = jwtUserId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static JwtUser build(User user) {

		List<GrantedAuthority> authorities =  List.of(new SimpleGrantedAuthority(user.getRole()));

		return new JwtUser(
				user.getUserId(),
				user.getUserName(),
				user.getUserEmail(),
				user.getUserPassword(),
				authorities);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
