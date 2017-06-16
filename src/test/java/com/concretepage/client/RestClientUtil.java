package com.concretepage.client;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.concretepage.entity.Member;

public class RestClientUtil {
    public void getMemberByIdDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/member/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Member> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Member.class, 1);
        Member member = responseEntity.getBody();
        System.out.println("Id:"+member.getMemberId());      
    }
	public void getAllMemberDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/members";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Member[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Member[].class);
        Member[] members = responseEntity.getBody();
        for(Member member : members) {
              System.out.println("Id:"+member.getMemberId());
        }
    }
    public void addMemberDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/member";
	    Member objMember = new Member();
	    objMember.setFirstName("Test");
	    objMember.setLastName("Test");
	    objMember.setEmail("test@example.com");
	    objMember.setEnabled(true);
	    objMember.setPassword("test");
        HttpEntity<Member> requestEntity = new HttpEntity<Member>(objMember, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());    	
    }
    public void updateMemberDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/member";
	    Member objMember = new Member();
	    objMember.setMemberId(1);
        HttpEntity<Member> requestEntity = new HttpEntity<Member>(objMember, headers);
        restTemplate.put(url, requestEntity);
    }
    public void deleteMemberDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/member/{id}";
        HttpEntity<Member> requestEntity = new HttpEntity<Member>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);        
    }
    public static void main(String args[]) {
    	RestClientUtil util = new RestClientUtil();
        //util.getMemberByIdDemo();
    	util.getAllMembersDemo();
    	//util.addMemberDemo();
    	//util.updateMemberDemo();
    	//util.deleteMemberDemo();
    }    
}
