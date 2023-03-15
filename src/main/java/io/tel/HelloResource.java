package io.tel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.tel.model.AuthenticationRequest;
import io.tel.model.AuthenticationResponse;
import io.tel.service.MyUserDetailsService;

@RestController
public class HelloResource {
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private MyUserDetailsService userDetailsService; 
	
	@Autowired
	private JwtUtil jwtTokenUtil;; 
	
	@RequestMapping({"/hello"})
	public String hello() {
		return "Hello World"; 
	}
	
	@RequestMapping(value="/authenticate",method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		}catch(BadCredentialsException e) {
			throw new Exception("Incorrect username or password",e);
		}
		final UserDetails userdetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt = jwtTokenUtil.generateToken(userdetails);
		
		
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}