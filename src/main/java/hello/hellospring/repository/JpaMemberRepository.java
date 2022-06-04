package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
	private final EntityManager em; //jpa에 자동으로 entity manager 생성
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	public Member save(Member member) {
		em.persist(member); // 저장(영구 저장하다의 의미)
		return member;
	}
	public Optional<Member> findById(Long id) {
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}
	public List<Member> findAll() {
		// 객체 대상으로 쿼리를 날리면 자동으로 sql문으로 변형해줌.
		return em.createQuery("select m from Member m", Member.class)
				 .getResultList();
	}
	public Optional<Member> findByName(String name) {
		List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
			.setParameter("name", name)
			.getResultList();
		return result.stream().findAny();
	} }