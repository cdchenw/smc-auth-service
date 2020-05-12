package com.smc.service;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.smc.model.User;
import com.smc.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

   private final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

   private final UserRepository userRepository;

   public UserDetailsServiceImpl(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Override
   @Transactional
   public UserDetails loadUserByUsername(final String login) {
      log.debug("Authenticating user '{}'", login);

      if (new EmailValidator().isValid(login, null)) {
         return userRepository.findOneWithAuthoritiesByEmailIgnoreCase(login)
            .map(user -> createSpringSecurityUser(login, user))
            .orElseThrow(() -> {
            	return new UsernameNotFoundException("User with email " + login + " was not found in the database");
            });
      }

      String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
      return userRepository.findOneWithAuthoritiesByUsername(lowercaseLogin)
         .map(user -> createSpringSecurityUser(lowercaseLogin, user))
         .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
   }

   private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
      List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
      grantedAuthorities.add(new SimpleGrantedAuthority(user.getUserType().name()));  
      return new org.springframework.security.core.userdetails.User(user.getEmail(),
         user.getPassword(),
         grantedAuthorities);
   }
}
