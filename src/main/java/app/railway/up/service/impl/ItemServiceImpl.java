package app.railway.up.service.impl;

import app.railway.up.model.Item;
import app.railway.up.repository.ItemRepository;
import app.railway.up.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Override
    public void create(List<Item> itemList) {
        itemRepository.saveAll(itemList);
    }
}
