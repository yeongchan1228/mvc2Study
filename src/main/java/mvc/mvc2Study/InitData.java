package mvc.mvc2Study;

import lombok.RequiredArgsConstructor;
import mvc.mvc2Study.springmvc.itemService.domain.item.entity.Item;
import mvc.mvc2Study.springmvc.itemService.domain.item.repository.ItemRepository;
import mvc.mvc2Study.springmvc.itemService.domain.member.entity.Member;
import mvc.mvc2Study.springmvc.itemService.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitData {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @PostConstruct
    public void initItem(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

    @PostConstruct
    public void initMember(){
        memberRepository.save(new Member("test", "테스터", "test"));
    }
}
