package pl.deen.onlineshop;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ShoppingCart {
    private List<Product> productsList;

    public ShoppingCart() {
        this.productsList = new ArrayList<>();
        addProductToShoppingCart(new Product("Product1", BigDecimal.valueOf(generateFloatFrom50To300())));
        addProductToShoppingCart(new Product("Product2", BigDecimal.valueOf(generateFloatFrom50To300())));
        addProductToShoppingCart(new Product("Product3", BigDecimal.valueOf(generateFloatFrom50To300())));
        addProductToShoppingCart(new Product("Product4", BigDecimal.valueOf(generateFloatFrom50To300())));
        addProductToShoppingCart(new Product("Product5", BigDecimal.valueOf(generateFloatFrom50To300())));
    }

    List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }

    void addProductToShoppingCart(Product product) {
        productsList.add(product);
    }

    private float generateFloatFrom50To300(){
        Random random = new Random();
        float generatedNumber = random.nextFloat()*(300f-50f)+50f;
        System.out.println(generatedNumber);
        return generatedNumber;
    }

}
