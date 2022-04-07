package mvc.mvc2Study.springmvc.itemService.repository;

import mvc.mvc2Study.springmvc.itemService.domain.item.entity.Item;
import mvc.mvc2Study.springmvc.itemService.domain.item.repository.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class FileItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @AfterEach
    public void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void itemTest() throws Exception {
        // given
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 20);

        // when
        itemRepository.save(itemA);
        itemRepository.save(itemB);
        
        // then
        Item findItem = itemRepository.findById(itemA.getId());
        assertThat(findItem).isEqualTo(itemA);

        List<Item> all = itemRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        Item itemC = new Item("itemC", 30000, 30);
        itemRepository.update(itemA.getId(), itemC);
        Item findItem2 = itemRepository.findById(itemA.getId());
        assertThat(findItem2.getItemName()).isEqualTo(itemC.getItemName());
    }
}