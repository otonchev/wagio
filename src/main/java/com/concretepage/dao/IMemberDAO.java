package com.concretepage.dao;
import java.util.List;
import com.concretepage.entity.Member;
public interface IMemberDAO {
    List<Member> getAllMembers();
    List<Member> getTeamMembersById(int memberId);
    Member getMemberById(int memberId);
    Member getMemberByEmail(String email);
    void addMember(Member member);
    void updateMember(Member member);
    void deleteMember(int memberId);
    boolean memberExists(String email);
    boolean isMemberParentOf(int parentId, int memberId);
}
 
