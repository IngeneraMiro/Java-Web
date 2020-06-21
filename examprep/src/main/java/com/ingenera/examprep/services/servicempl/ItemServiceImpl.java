package com.ingenera.examprep.services.servicempl;

import com.ingenera.examprep.models.bindmodels.ItemBindModel;
import com.ingenera.examprep.models.entities.Item;
import com.ingenera.examprep.models.viewmodels.ItemViewModel;
import com.ingenera.examprep.repositories.ItemRepository;
import com.ingenera.examprep.services.CategoryService;
import com.ingenera.examprep.services.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CategoryService categoryService;
    private final ModelMapper mapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryService categoryService, ModelMapper mapper) {
        this.itemRepository = itemRepository;
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    @Override
    public Item addItem(ItemBindModel model) {
        Item item = this.mapper.map(model,Item.class);
        item.setCategory(this.categoryService.getCategoryByName(model.getCategory()));
        return this.itemRepository.saveAndFlush(item);
    }

    @Override
    public Long countItems() {
        return this.itemRepository.count();
    }

    @Override
    public List<ItemViewModel> getAllItems() {
       return this.itemRepository.findAll().stream().map(i->this.mapper.map(i,ItemViewModel.class)).collect(Collectors.toList());

    }


}
