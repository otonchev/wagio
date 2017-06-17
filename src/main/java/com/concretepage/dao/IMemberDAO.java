package com.concretepage.dao;
import java.util.List;
import com.concretepage.entity.Member;
public interface IMemberDAO {
    List<Member> getAllMembers();
    Member getMemberById(int memberId);
    Member getMemberByEmail(String email);
    void addMember(Member member);
    void updateMember(Member member);
    void deleteMember(int memberId);
    boolean memberExists(String email);
}
 
