package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest /*** 스프링 컨테이너와 테스트를 함께 실행 ***/
@Transactional  /*** 테스트 시작 전에 트랜잭션하고 완료 후 항상 롤백 ***/
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemoryMemberRepository memberRepository;

    @Test
    void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findmember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(),findmember.getName());
    }

    @Test
    void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2)); //예외가 발생해야 한다.
        /*** 코드 정리 필요 ***/

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}