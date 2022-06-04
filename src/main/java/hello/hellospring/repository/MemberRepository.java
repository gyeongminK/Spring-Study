package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;

public interface MemberRepository {
	Member save(Member member);
	Optional<Member> findById(Long id); //null이 반환될 수 있으므로 null을 optional로 감싸서 반환
	Optional<Member> findByName(String name);
	List<Member> findAll();
	
}
