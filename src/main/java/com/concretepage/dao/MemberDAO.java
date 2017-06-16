package com.concretepage.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.entity.Member;
@Transactional
@Repository
public class MemberDAO implements IMemberDAO {
	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public Member getMemberById(int memberId) {
		return entityManager.find(Member.class, memberId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getAllMembers() {
		String hql = "FROM Member as mbr ORDER BY mbr.memberId";
		return (List<Member>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void addMember(Member member) {
		entityManager.persist(member);
	}
	@Override
	public void updateMember(Member member) {
		Member mbr = getMemberById(member.getMemberId());
		mbr.setEnabled(member.getEnabled());
		mbr.setEmail(member.getEmail());
		entityManager.flush();
	}
	@Override
	public void deleteMember(int memberId) {
		entityManager.remove(getMemberById(memberId));
	}
	@Override
	public boolean memberExists(String email) {
		String hql = "FROM Member as mbr WHERE mbr.email = ?";
		int count = entityManager.createQuery(hql).setParameter(1, email)
				.getResultList().size();
		              //.setParameter(2, category).getResultList().size();
		return count > 0 ? true : false;
	}
}
