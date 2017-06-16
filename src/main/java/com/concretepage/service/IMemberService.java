package com.concretepage.service;

import java.util.List;

import com.concretepage.entity.Member;

public interface IMemberService {
     List<Member> getAllMembers();
     Member getMemberById(int memberId);
     boolean addMember(Member member);
     void updateMember(Member member);
     void deleteMember(int memberId);
}
