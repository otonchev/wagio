package com.concretepage.service;

import java.util.List;

import com.concretepage.entity.Member;

public interface IMemberService {
     List<Member> getAllMembers();
     List<Member> getTeamMembersById(int memberId);
     Member getMemberById(int memberId);
     Member getMemberByEmail(String email);
     boolean addMember(Member member);
     boolean isMemberParentOf(int parentId, int memberId);
     void updateMember(Member member);
     void deleteMember(int memberId);
}
