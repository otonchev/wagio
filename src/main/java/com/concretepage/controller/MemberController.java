package com.concretepage.controller;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.concretepage.entity.Member;
import com.concretepage.service.IMemberService;

@Controller
@RequestMapping("user")
public class MemberController {
	@Autowired
	private IMemberService memberService;
	@GetMapping("logged")
	public ResponseEntity<Member> getMemberLoggedIn(HttpServletRequest request) {
		Member member;
		Principal principal = request.getUserPrincipal();
		String email = principal.getName();
		member = memberService.getMemberByEmail(email);
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	@GetMapping("member/{id}")
	public ResponseEntity<Member> getMemberById(@PathVariable("id") Integer id, HttpServletRequest request) {
		Member member;
		
		boolean user = request.isUserInRole("USER");
		boolean admin = request.isUserInRole("ADMIN");
		boolean manager = request.isUserInRole("MANAGER");
		
		boolean granted = admin;
		
		if (!granted && user) {
			Principal principal = request.getUserPrincipal();
			String email = principal.getName();
			member = memberService.getMemberByEmail(email);
			
			if (id == member.getMemberId()) {
				granted = true;
			}
		}
		
		if (!granted && manager) {
			Principal principal = request.getUserPrincipal();
			String email = principal.getName();
			member = memberService.getMemberByEmail(email);
			
			if (memberService.isMemberParentOf (member.getMemberId(), id.intValue()))
				granted = true;
		}
		
		if (!granted)
				return new ResponseEntity<Member>(HttpStatus.FORBIDDEN);
		
		member = memberService.getMemberById(id);
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	@GetMapping("member/{id}/team")
	public ResponseEntity<List<Member>> getTeamMembersById(@PathVariable("id") Integer id, HttpServletRequest request) {
		List<Member> list;

		boolean manager = request.isUserInRole("MANAGER");
		boolean admin = request.isUserInRole("ADMIN");
		
		if (!admin) {
			if (manager) {
				Principal principal = request.getUserPrincipal();
				String email = principal.getName();
				Member member = memberService.getMemberByEmail(email);
			
				if (id != member.getMemberId())
					return new ResponseEntity<List<Member>>(HttpStatus.FORBIDDEN);
			} else
				return new ResponseEntity<List<Member>>(HttpStatus.FORBIDDEN);
		}
		
		list = memberService.getTeamMembersById(id);
		return new ResponseEntity<List<Member>>(list, HttpStatus.OK);
	}
	@GetMapping("members")
	public ResponseEntity<List<Member>> getAllMembers(HttpServletRequest request) {
		List<Member> list;
		
		boolean manager = request.isUserInRole("MANAGER");
		boolean admin = request.isUserInRole("ADMIN");
		
		if (!admin) {
			if (manager) {
				Principal principal = request.getUserPrincipal();
				String email = principal.getName();
				Member member = memberService.getMemberByEmail(email);
				
				list = memberService.getTeamMembersById(member.getMemberId());
			} else
				return new ResponseEntity<List<Member>>(HttpStatus.FORBIDDEN);
		} else
			list = memberService.getAllMembers();
		return new ResponseEntity<List<Member>>(list, HttpStatus.OK);
	}
	@PostMapping("member")
	public ResponseEntity<Void> addMember(@RequestBody Member member, UriComponentsBuilder builder) {
        	boolean flag = memberService.addMember(member);
        	if (flag == false) {
        		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        	}
        	HttpHeaders headers = new HttpHeaders();
        	headers.setLocation(builder.path("/member/{id}").buildAndExpand(member.getMemberId()).toUri());
        	return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("member")
	public ResponseEntity<Member> updateMember(@RequestBody Member member) {
		memberService.updateMember(member);
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	@DeleteMapping("member/{id}")
	public ResponseEntity<Void> deleteMember(@PathVariable("id") Integer id) {
		memberService.deleteMember(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 
