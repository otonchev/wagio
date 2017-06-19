package com.concretepage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.IMemberDAO;
import com.concretepage.entity.Member;
@Service
public class MemberService implements IMemberService {
	@Autowired
	private IMemberDAO memberDAO;
	@Override
	public Member getMemberById(int memberId) {
		Member obj = memberDAO.getMemberById(memberId);
		return obj;
	}
	@Override
	public Member getMemberByEmail(String email) {
		Member obj = memberDAO.getMemberByEmail(email);
		return obj;
	}
	@Override
	public List<Member> getAllMembers(){
		return memberDAO.getAllMembers();
	}
	@Override
	public List<Member> getTeamMembersById(int memberId) {
		return memberDAO.getTeamMembersById(memberId);
	}
	@Override
	public synchronized boolean addMember(Member member){
		if (memberDAO.memberExists(member.getEmail())) {
			return false;
		} else {
			memberDAO.addMember(member);
			return true;
		}
	}
	@Override
	public boolean isMemberParentOf(int parentId, int memberId) {
		return memberDAO.isMemberParentOf(parentId, memberId);
	}
	@Override
	public void updateMember(Member member) {
		memberDAO.updateMember(member);
	}
	@Override
	public void deleteMember(int memberId) {
		memberDAO.deleteMember(memberId);
	}
}
