package mvc.mvc2Study.springmvc.fileupload.repository;

import mvc.mvc2Study.springmvc.fileupload.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FileItemRepository {

    private final Map<Long, Item> store = new HashMap<>();
    private long sequence = 0L;


    public Item itemSave(Item item){
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
}
