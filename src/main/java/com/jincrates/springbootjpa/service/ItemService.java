package com.jincrates.springbootjpa.service;

import com.jincrates.springbootjpa.domain.item.Item;
import com.jincrates.springbootjpa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    //변경감지
    @Transactional
    public void updateForm(Long itemId, String name, int price, int stockQunatity) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQunatity);

        //실무에선 setter를 쓰지말고 추적 메서드를 만들기
        //findItem.change(name, price, stockQuantity);  //이런식으로라도 만들기
    }
}
