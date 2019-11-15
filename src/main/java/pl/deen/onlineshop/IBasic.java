package pl.deen.onlineshop;

import java.math.BigDecimal;

public interface IBasic {
    BigDecimal countPriceOfBasket();
    void addProductToShoppingCart(Product product);
}
