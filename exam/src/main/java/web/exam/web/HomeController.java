package web.exam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.exam.models.entities.Product;
import web.exam.services.ProductService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Set;

@Controller
public class HomeController {
    private final ProductService productService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/home")
    public String home(Model model,HttpSession session){

        if(session.getAttribute("userId")==null){
            return "redirect:/users/login";
        }
        model.addAttribute("drinks",this.productService.getProductByCategory("DRINK"));
        model.addAttribute("foods",this.productService.getProductByCategory("FOOD"));
        model.addAttribute("home",this.productService.getProductByCategory("HOUSEHOLD"));
        model.addAttribute("oders",this.productService.getProductByCategory("OTHER"));
        BigDecimal sum = new BigDecimal(0);
        Set<Product> products = (Set<Product>)session.getAttribute("items");
        if(products.size()>0) {
            sum = products.stream().map(Product::getPrice).reduce(BigDecimal::add).get();
        }

        model.addAttribute("sum",sum);

        return "home";
    }

}
