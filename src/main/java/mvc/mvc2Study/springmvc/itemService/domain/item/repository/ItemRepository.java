package mvc.mvc2Study.springmvc.itemService.domain.item.repository;

import mvc.mvc2Study.springmvc.itemService.domain.item.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();
    private static Long sequence = 0L;

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        Item findItem = store.get(id);
        return findItem;
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }


    public void update(Long id, Item upgradeParam){
        Item findItem = store.get(id);
        findItem.setItemName(upgradeParam.getItemName());
        findItem.setPrice(upgradeParam.getPrice());
        findItem.setQuantity(upgradeParam.getQuantity());
    }

    public void clearStore(){
        store.clear();
    }
}
