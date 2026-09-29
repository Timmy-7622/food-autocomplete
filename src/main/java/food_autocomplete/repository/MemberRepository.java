package food_autocomplete.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import food_autocomplete.entity.Member;

public interface MemberRepository
        extends JpaRepository<Member, Long> {

    Optional<Member> findByAccount(String account);

}
