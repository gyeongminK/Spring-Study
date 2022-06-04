package hello.hellospring.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

class MemberServiceTest {
	
	MemberService memberService;
	// 다른 레포지토리를 사용 중(다른 객체)라 static이 아니면 다른 디비에 접근하는 게 돼버려서 좋지 않음.
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	public void beforeEach(){
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	} //각 테스트 실행 전 멤버 레포지토리를 생성해서 멤버 서비스에 넣어줌 -> 같은 레포지토리 사용
	// 외부에서 레포지토리를 주입 -> DI(의존성 주입)
	
	@AfterEach
	public  void afterEach(){
		memberRepository.clearStore();
	}
	
	
	@Test //테스트는 한글로 적어도 됨.
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("spring");
		
		//when
		Long saveId = memberService.join(member);
		
		//then
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	public void 중복_회원_예외(){
		//given
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		
		//when
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class,()-> memberService.join(member2));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		// memberService.join(member1);
		// try{
		// 	memberService.join(member2);
		// 	fail();
		// } catch(IllegalStateException e){
		// 	Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		// }
		
		//then
	}
	
	@Test
	void findMembers() {
	}
	
	@Test
	void findOne() {
	}
}