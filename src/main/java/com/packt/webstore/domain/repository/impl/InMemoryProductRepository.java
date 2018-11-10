package com.packt.webstore.domain.repository.impl;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private final static String BRAND = "brand";
    private final static String CATEGORY = "category";
    private List<Product> listOfProducts = new ArrayList<>();

    public InMemoryProductRepository() {

        Product iphone = new Product("P1234", "Iphone 5s", new BigDecimal(500));
        iphone.setDescription("Apple Iphone 5s, smartphone with 4' screen 640x1131 and 8mp photo camera");
        iphone.setCategory("Smartphone");
        iphone.setManufacturer("Apple");
        iphone.setUnitsInStock(1000);

        Product laptop_dell = new Product("P1235", "Dell Inspiron", new BigDecimal(700));
        laptop_dell.setDescription("Dell Inspiron, 14' notebook (black) with Intel Core 3");
        laptop_dell.setCategory("Notebook");
        laptop_dell.setManufacturer("Dell");
        laptop_dell.setUnitsInStock(1000);

        Product tablet_nexus = new Product("P1236", "Nexus 7", new BigDecimal(300));
        tablet_nexus.setDescription("Google Nexus 7 inch 4th gen processor Qualcomm Snapdragon S4 Pro ");
        tablet_nexus.setCategory("Tablet");
        tablet_nexus.setManufacturer("Google");
        tablet_nexus.setUnitsInStock(1000);

        listOfProducts.add(iphone);
        listOfProducts.add(laptop_dell);
        listOfProducts.add(tablet_nexus);
    }

    public List<Product> getAllProducts() {
        return listOfProducts;
    }

    public Product getProductById(String productId) {
        return listOfProducts.stream()
                .filter(p -> productId.equalsIgnoreCase(p.getProductId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No product in stock: " + productId));
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> productsByCategory = listOfProducts.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());

        return productsByCategory;
    }

    public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
        Set<Product> productsByBrand = new HashSet<>();
        Set<Product> productsByCategory = new HashSet<>();
        Set<String> criterias = filterParams.keySet();

        if(criterias.contains("brand")) {
            for (String brandName : filterParams.get("brand")) {
                for (Product product : listOfProducts) {
                    if (brandName.equalsIgnoreCase(product.getManufacturer())) {
                        productsByBrand.add(product);
                    }
                }
            }
        }
        /*
        Set<Product> productsByBrand = filterParams.keySet().stream()
                .filter(v -> v.contains(BRAND))
                .map(filterParams::get)
                .flatMap(x -> listOfProducts.stream()
                        .filter(r -> r.getManufacturer().equalsIgnoreCase(BRAND)))
                .collect(Collectors.toSet());

        Set<Product> productsByCategory = filterParams.keySet().stream()
                .flatMap(x -> listOfProducts.stream().filter(v -> v.getCategory().equalsIgnoreCase(x)))
                .collect(Collectors.toSet());
        */
        if (criterias.contains("category")) {
            for (String category : filterParams.get("category")) {
                productsByCategory.addAll(this.getProductsByCategory(category));
            }
        }

        productsByCategory.retainAll(productsByBrand);

        return productsByCategory;
    }

    public List<Product> getProductsByManufacturer(String manufacturer) {
        List<Product> productsByManufacturer = listOfProducts.stream()
                .filter(p -> manufacturer.equalsIgnoreCase(p.getManufacturer()))
                .collect(Collectors.toList());

        return productsByManufacturer;
    }

    public void addProduct(Product product) {
        listOfProducts.add(product);
    }

}
