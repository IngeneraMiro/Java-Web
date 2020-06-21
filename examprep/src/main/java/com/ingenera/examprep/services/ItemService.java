package com.ingenera.examprep.services;

import com.ingenera.examprep.models.bindmodels.ItemBindModel;
import com.ingenera.examprep.models.entities.Item;
import com.ingenera.examprep.models.viewmodels.ItemViewModel;

import java.util.List;

public interface ItemService {

   Item addItem(ItemBindModel model);
   Long countItems();
   List<ItemViewModel> getAllItems();

}
