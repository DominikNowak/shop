package pl.deen.onlineshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class Start implements IBasic{
    private ShoppingCart shoppingCart;

    @Autowired
    public Start(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @EventListener(ApplicationReadyEvent.class)
    public BigDecimal countPriceOfBasket() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : shoppingCart.getProductsList()) {
            sum = sum.add(product.getPrice());
        }
        System.out.println("Suma: " + sum.setScale(2, RoundingMode.CEILING));
        return sum;
    }

    @Override
    public void addProductToShoppingCart(Product product) {
        shoppingCart.addProductToShoppingCart(product);
    }
}
