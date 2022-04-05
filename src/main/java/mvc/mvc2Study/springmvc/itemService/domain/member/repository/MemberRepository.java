package mvc.mvc2Study.springmvc.itemService.domain.member.repository;

import lombok.extern.slf4j.Slf4j;
import mvc.mvc2Study.springmvc.itemService.domain.member.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        log.info("member = {}"+member);
        return member;
    }

    public Member findById(Long id){
        Member findMember = store.get(id);
        return findMember;
    }

    public Optional<Member> findByLoginId(String loginId){
//        List<Member> all = findAll();
//        for (Member member : all) {
//            if(member.getLoginId().equals(loginId)){
//                return Optional.ofNullable(member);
//            }
//        }
//        return Optional.empty();

        return findAll().stream()
                .filter(member -> member.getLoginId().equals(loginId))
                .findFirst();
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }

}
