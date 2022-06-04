package hello.hellospring.repository;

import org.junit.jupiter.api.AfterEach;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

class MemoryMemberRepositoryTest {
	
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	@AfterEach // 테스트코드가 끝날 때마다 데이터 리셋
	public void afterEach(){
		repository.clearStore();
	}
	
	@Test
	public void save(){
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		
		Member result = repository.findById(member.getId()).get();
		//Assertions.assertThat(member).isEqualTo(result);
		assertThat(member).isEqualTo(result);
	}
	
	@Test
	public void findByName(){
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		Member result = repository.findByName("spring1").get();
		
		assertThat(result).isEqualTo(member1);
	}
	
	@Test
	public void findAll(){
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		
		assertThat(result.size()).isEqualTo(2);
	}
}
// 모든 테스트는 순서와 상관 없이 메소드별로 따로 정상 작동하도록 해야 함.(의존 관계 없이)