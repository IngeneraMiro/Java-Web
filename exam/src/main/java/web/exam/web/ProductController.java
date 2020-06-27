package web.exam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.exam.models.bindmodels.ProductBundModel;
import web.exam.services.ProductService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController  {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public String addProduct(Model model, HttpSession session, RedirectAttributes redirectAttributes){

        if(session.getAttribute("userId")==null){
            return "redirect:/users/login";
        }

        if(!model.containsAttribute("productBundModel")){
            model.addAttribute("productBundModel",new ProductBundModel());
        }

        return "product-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("productBundModel")ProductBundModel productBundModel,
                             BindingResult result,HttpSession session,RedirectAttributes redirectAttributes){
        if(session.getAttribute("userId")== null){
            return "redirect:/users/login";
        }

        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("productBundModel",productBundModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.org.springframework.validation.productBundModel",result);
            return "redirect:add";
        }
        try {
            this.productService.addProduct(productBundModel);
        }catch (Error e){
            redirectAttributes.addFlashAttribute("productBundModel",productBundModel);
            return "redirect:add";
        }
        return "redirect:/home";
    }



}
