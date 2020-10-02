package com.degloba.ecommerce.trader.webapp.reactive.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.degloba.ecommerce.trader.facade.dtos.AuthCheck;
import com.degloba.ecommerce.trader.facade.dtos.MyUser;
import com.degloba.ecommerce.trader.webapp.reactive.jwt.JwtTokenProvider;
import com.degloba.ecommerce.trader.webapp.reactive.jwt.Role;
import com.degloba.ecommerce.trader.webapp.reactive.utils.PasswordEncryption;
import com.degloba.ecommerce.trader.webapp.reactive.utils.WebUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/myuser")
public class MyUserController {
	@Autowired
	private ReactiveMongoOperations operations;
	@Autowired
	private PasswordEncryption passwordEncryption;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/authorize")
	public Mono<AuthCheck> postAuthorize(@RequestBody AuthCheck authcheck, @RequestHeader Map<String,String> header) {				
		Optional<String> token = WebUtils.extractToken(header);
		Query query = new Query();
		query.addCriteria(Criteria.where("salt").is(authcheck.getHash()));
		return this.operations.findOne(query, MyUser.class).switchIfEmpty(Mono.just(new MyUser()))
				.map(user -> mapMyUser(user, authcheck, token));
	}

	private AuthCheck mapMyUser(MyUser myUser, AuthCheck authcheck, Optional<String> token) {
		Optional<Jws<Claims>> claims = this.jwtTokenProvider.getClaims(token);
		if (myUser.getUserId() != null && claims.isPresent() 
				&& myUser.getUserId().equals(claims.get().getBody().getSubject())
				&& new Date().before(claims.get().getBody().getExpiration())) {
			return new AuthCheck(authcheck.getHash(), authcheck.getPath(), true);
		}
		return new AuthCheck(authcheck.getHash(), authcheck.getPath(), false);
	}

	@PostMapping("/signin")
	public Mono<MyUser> postUserSignin(@RequestBody MyUser myUser)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(myUser.getUserId()));
		MyUser user = this.operations.findOne(query, MyUser.class).switchIfEmpty(Mono.just(new MyUser())).block();
		if (user.getUserId() == null) {
			String salt = this.passwordEncryption.generateSalt();
			String encryptedPassword = this.passwordEncryption.getEncryptedPassword(myUser.getPassword(), salt);
			myUser.setPassword(encryptedPassword);
			myUser.setSalt(salt);
			this.operations.save(myUser).block();
			return Mono.just(myUser);
		}
		return Mono.just(new MyUser());
	}

	@PostMapping("/logout")
	public Mono<MyUser> postLogout(@RequestBody String hash, HttpServletRequest request) {
		Query query = new Query();
		query.addCriteria(Criteria.where("salt").is(hash));
		return this.operations.findOne(query, MyUser.class).switchIfEmpty(Mono.just(new MyUser()))
				.map(user1 -> loginHelp(user1, ""));
	}

	@PostMapping("/login")
	public Mono<MyUser> postUserLogin(@RequestBody MyUser myUser,HttpServletRequest request)
			throws NoSuchAlgorithmException, InvalidKeySpecException {		
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(myUser.getUserId()));
		return this.operations.findOne(query, MyUser.class).switchIfEmpty(Mono.just(new MyUser()))
				.map(user1 -> loginHelp(user1, myUser.getPassword()));
	}

	private MyUser loginHelp(MyUser user, String passwd) {
		if (user.getUserId() != null) {
			String encryptedPassword;
			try {
				encryptedPassword = this.passwordEncryption.getEncryptedPassword(passwd, user.getSalt());
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				return new MyUser();
			}
			if (user.getPassword().equals(encryptedPassword)) {				
				String jwtToken = this.jwtTokenProvider.createToken(user.getUserId(), Arrays.asList(Role.USERS));
				user.setToken(jwtToken);
				user.setPassword("XXX");
				return user;
			}
		}
		return new MyUser();
	}
}
