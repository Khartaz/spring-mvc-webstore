package com.packt.webstore.controller;

import com.packt.webstore.domain.Product;
import com.packt.webstore.exception.NoProductsFoundUnderCategoryException;
import com.packt.webstore.exception.ProductNotFoundException;
import com.packt.webstore.service.ProductService;
import com.packt.webstore.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductValidator productValidator;


    @RequestMapping
    public String list(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @RequestMapping("/all")
    public ModelAndView allProducts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", productService.getAllProducts());
        modelAndView.setViewName("products");
        return modelAndView;
    }

    @RequestMapping("/{category}")
    public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
        List<Product> products = productService.getProductsByCategory(productCategory);
        if (products == null || products.isEmpty()) {
            throw new NoProductsFoundUnderCategoryException();
        }
        model.addAttribute("products", products);
        return "products";
    }

    @RequestMapping("/filter/{ByCriteria}")
    public String getProductsByFilter(@MatrixVariable(pathVar="ByCriteria") Map<String,List<String>> filterParams, Model model) {
        model.addAttribute("products", productService.getProductsByFilter(filterParams));
        return "products";
    }

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return "product";
    }

    @RequestMapping("/manufacturer/{ByManufacturer}")
    public String getProductsByManufacturer(@PathVariable("ByManufacturer") String filterParams, Model model) {
        model.addAttribute("products", productService.getProductsByManufacturer(filterParams));
        return "products";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddNewProductForm(Model model) {
        Product newProduct = new Product();
        model.addAttribute("newProduct", newProduct);
        return "addProduct";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddNewProductForm(@ModelAttribute("newProduct")
              @Valid Product productToBeAdded, BindingResult bindingResult, HttpServletRequest request) {
        String[] suppressedFields = bindingResult.getSuppressedFields();

        if(suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind prohibited fields: "
                    + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        MultipartFile productImage = productToBeAdded.getProductImage();
        String rootDirectoryImg = request.getSession().getServletContext().getRealPath("/");
        if(productImage!= null && !productImage.isEmpty()) {
            try {
                System.out.println(rootDirectoryImg);
                productImage.transferTo(new File(rootDirectoryImg + "resources/images/"
                        + productToBeAdded.getProductId() + ".png"));
            } catch (Exception e) {
                throw new RuntimeException("Failed when trying to save image ", e);
            }
        }
        if(bindingResult.hasErrors()) {
            return "addProduct";
        }

        MultipartFile productPdf = productToBeAdded.getProductPdf();
        String rootDirectoryPdf = request.getSession().getServletContext().getRealPath("/");
        if(productPdf!= null && !productPdf.isEmpty()) {
            try {
                productPdf.transferTo(new File(rootDirectoryPdf + "resources/images/"
                        + productToBeAdded.getProductId() + ".pdf"));
            } catch (Exception e) {
                throw new RuntimeException("Failed when trying to save pdf ", e);
            }
        }

        productService.addProduct(productToBeAdded);
        return "redirect:/products";
    }

    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {
        binder.setAllowedFields(
                "productId","name","unitPrice",
                "description","manufacturer","category", "language",
                "unitsInStock", "condition","productImage", "productPdf");
        binder.setValidator(productValidator);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handleError(HttpServletRequest request, ProductNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("invalidProductId", exception.getProductId());
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("url", request.getRequestURI() + "?" + request.getQueryString());
        modelAndView.setViewName("productNotFound");

        return modelAndView;
    }

    @RequestMapping("/invalidPromoCode")
    public String invalidPromoCode() {
        return "invalidPromoCode";
    }

}
