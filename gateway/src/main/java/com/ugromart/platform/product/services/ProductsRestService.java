package com.ugromart.platform.product.services;

import com.ugromart.platform.product.models.Product;
import com.ugromart.platform.product.models.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductsRestService {
    @Autowired
    ProductsRestConnector productsRestConnector;

    public List<Product> getProducts(){
        Product[] products=productsRestConnector.get(Product[].class,"/products");
        return Arrays.asList(products);
    }
    public Product getProductById(int id){
        Product product=productsRestConnector.get(Product.class,"/products/"+id);
        return product;
    }

    public List<Vendor> getVendors() {
        Vendor[] vendors =productsRestConnector.get(Vendor[].class,"/vendors");
        return Arrays.asList(vendors);
    }

}
