package app.railway.up.service;

import app.railway.up.domain.item.Item;

import java.util.List;

public interface ItemService {
    void create(List<Item> itemList);
}
