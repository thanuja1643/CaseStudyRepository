package com.pixogram.controller;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pixogram.authentication.configuration.JWTUtils;
import com.pixogram.dto.ApiResponse;
import com.pixogram.dto.JwtAuthenticationResponse;
import com.pixogram.dto.LoginRequest;
import com.pixogram.dto.SignUpRequest;
import com.pixogram.dto.UserDetail;
import com.pixogram.exceptions.AppException;
import com.pixogram.model.Role;
import com.pixogram.model.RoleNames;
import com.pixogram.model.User;

import com.pixogram.repository.UserRepository;

@RestController
@RequestMapping("/pixoauth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

	/*
	 * @Autowired RoleRepository roleRepository;
	 */

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JWTUtils tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

       user.setPassword(passwordEncoder.encode(user.getPassword()));

		/*
		 * Role userRole = roleRepository.findByName(RoleNames.ROLE_USER)
		 * .orElseThrow(() -> new AppException("User Role not set."));
		 * 
		 * user.setRoles(Collections.singleton(userRole));
		 */
        User result = userRepository.save(user);

		/*
		 * URI location = ServletUriComponentsBuilder
		 * .fromCurrentContextPath().path("/pixogram/users/{username}")
		 * .buildAndExpand(result.getUsername()).toUri();
		 */

        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
    }
    
	@PostMapping("/user/{id}")
	public ResponseEntity<List<UserDetail>> getUserDetails(@PathVariable List<Long> ids) {
		List<User> usersList = userRepository.findAllById(ids);
		List<UserDetail> udList = new ArrayList<UserDetail>();
		// Creating user's account
		usersList.parallelStream().forEach(e -> {
			UserDetail userDet = new UserDetail();
			userDet.setUsername(e.getUsername());
			userDet.setEmail(e.getEmail());
			udList.add(userDet);
		});

		return ResponseEntity.ok(udList);
	}
  
}