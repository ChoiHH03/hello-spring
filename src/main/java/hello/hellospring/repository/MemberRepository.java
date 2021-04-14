package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id); /*** Optional은 null값을 다루기 위한 클래스 ***/
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
