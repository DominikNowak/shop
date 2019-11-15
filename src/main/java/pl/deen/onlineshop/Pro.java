package pl.deen.onlineshop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Getter
@Setter
@Profile("pro")
@ConfigurationProperties(prefix = "pro-properties")
public class Pro implements IBasic {
    private ShoppingCart shoppingCart;
    private float discount;
    private float vat;

    @Autowired
    public Pro(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @EventListener(ApplicationReadyEvent.class)
    public BigDecimal addDiscount() {
        BigDecimal sumWithVatAndDiscount = addVAT().multiply(BigDecimal.ONE.subtract(BigDecimal.valueOf(discount)));
        System.out.println("Suma z VAT po " + (int) (discount * 100) + "% rabacie: " + sumWithVatAndDiscount.setScale(2, RoundingMode.CEILING));
        return sumWithVatAndDiscount;
    }

    public BigDecimal addVAT() {
        BigDecimal sumWithVat = countPriceOfBasket().multiply(BigDecimal.ONE.add(BigDecimal.valueOf(vat)));
        System.out.println("Suma z VAT " + (int) (vat * 100) + "%: " + sumWithVat.setScale(2, RoundingMode.CEILING));
        return sumWithVat;
    }

    @Override
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

