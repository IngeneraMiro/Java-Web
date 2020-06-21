package com.ingenera.examprep.services;

import com.ingenera.examprep.models.bindmodels.ItemBindModel;
import com.ingenera.examprep.models.entities.Item;

public interface ItemService {

   Item addItem(ItemBindModel model);
   Long countItems();

}
