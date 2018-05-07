package com.aligntechnology.sampletask;
 
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import com.aligntechnology.sampletask.model.Product;
import org.springframework.web.client.RestTemplate;
 

public class RestTestClient {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/SpringBootRestApi/api";

    /* GET */
    private static void getLeftovers(){
        System.out.println("Testing getLeftovers API----------");
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> productsMap = restTemplate.getForObject(REST_SERVICE_URI+"/product/leftovers", List.class);
        if(productsMap!=null){
            for(LinkedHashMap<String, Object> map : productsMap){
                System.out.println("Product : Name="+map.get("name")+", Brand="+map.get("brand")+", Price="+map.get("price") +", Quantity="+map.get("quantity"));
            }
        }else{
            System.out.println("No product exist----------");
        }
    }

    /* GET */
    private static void getProduct(){
        System.out.println("Testing getProduct API----------");
        RestTemplate restTemplate = new RestTemplate();
        Product product = restTemplate.getForObject(REST_SERVICE_URI+"/product/washingmachine/electrolux", Product.class);
        System.out.println(product);
    }

    /* POST */
    private static void createProduct() {
        System.out.println("Testing create Product API----------");
        RestTemplate restTemplate = new RestTemplate();
        Product product = new Product("toaster", "Hansa",12.5,4);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/product/", product, Product.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
 
    /* PUT */
    private static void updateProduct() {
        System.out.println("Testing update Product API----------");
        RestTemplate restTemplate = new RestTemplate();
        Product product  = new Product("washingmachine", "electrolux",33, 33);
        restTemplate.put(REST_SERVICE_URI+"/product/washingmachine/electrolux", product);
        System.out.println(product);
    }
 
    /* DELETE */
    private static void deleteProduct() {
        System.out.println("Testing delete Product API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/product/multivarka/redmond");
    }
 
 
    public static void main(String args[]){
        getLeftovers();
        getProduct();
        createProduct();
        updateProduct();
        deleteProduct();
    }
}