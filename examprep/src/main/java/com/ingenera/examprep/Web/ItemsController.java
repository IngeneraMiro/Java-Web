package com.ingenera.examprep.Web;

import com.ingenera.examprep.models.bindmodels.ItemBindModel;
import com.ingenera.examprep.models.entities.Gender;
import com.ingenera.examprep.models.entities.Item;
import com.ingenera.examprep.models.viewmodels.ItemDetailModel;
import com.ingenera.examprep.services.CategoryService;
import com.ingenera.examprep.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/items")
public class ItemsController {
    private final ItemService itemService;
    private final CategoryService categoryService;

    @Autowired
    public ItemsController(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
    }

    @GetMapping("/details/{id}")
    public String getItemDetails(Model model,HttpSession session, @PathVariable("id")String id){
        if(session.getAttribute("userId")!=null) {
            model.addAttribute("detail",this.itemService.getItemById(id));
            return "details-item";
        }
        return "redirect:/user/login";
    }

    @GetMapping("/add")
    public String addItem(Model model,HttpSession session){
        if(!model.containsAttribute("itemBindModel")){
            model.addAttribute("itemBindModel",new ItemBindModel());
        }
        if(session.getAttribute("userId")!=null){
            List<String> list = this.categoryService.getCategoriesList();
            List<String> genders = Arrays.stream(Gender.values()).map(Enum::name).collect(Collectors.toList());
            model.addAttribute("categories",list);
            model.addAttribute("genders",genders);
            return "add-item";
        }
        return "redirect:/user/login";
    }

    @PostMapping("/add")
    public String addItemConfirm(@Valid @ModelAttribute("itemBindModel")ItemBindModel itemBindModel,
                                 BindingResult result, HttpSession session, RedirectAttributes redirectAttributes){
        if(session.getAttribute("userId")==null){
            return "redirect:/user/login";
        }
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("itemBindModel",itemBindModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.itemBindModel",result);
            return "redirect:/items/add";
        }
        this.itemService.addItem(itemBindModel);
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String removeItem(@PathVariable("id")String id,HttpSession session){
        if(session.getAttribute("userId")==null){
            return "redirect:/user/login";
        }
        this.itemService.removeItem(id);
        return "redirect:/home";
    }

}
