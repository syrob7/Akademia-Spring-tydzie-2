package pl.akademiaspring.task2.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.akademiaspring.task2.products.Product;
import pl.akademiaspring.task2.utils.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("Pro")
public class ShopPro {

    @Value("${app.taxVAT}")
    private int taxVAT;

    @Value("${app.discount}")
    private int discount;

    private List<Product> furnitureList;

    public ShopPro() {
        furnitureList = new ArrayList<>();

        Product chair = new Product("chair", Utils.getRandomNumberInRange(50, 300));
        Product table = new Product("table", Utils.getRandomNumberInRange(50, 300));
        Product desk = new Product("desk", Utils.getRandomNumberInRange(50, 300));
        Product armchair = new Product("armchair", Utils.getRandomNumberInRange(50, 300));
        Product cabinet = new Product("cabinet", Utils.getRandomNumberInRange(50, 300));

        furnitureList.add(chair);
        furnitureList.add(table);
        furnitureList.add(desk);
        furnitureList.add(armchair);
        furnitureList.add(cabinet);

    }

    @EventListener(ApplicationReadyEvent.class)
    public void showAllProductsFromBucket() {

        BigDecimal sum = furnitureList.stream().map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal taxVatValue = sum.multiply(new BigDecimal(taxVAT)).divide(new BigDecimal("100"));
        BigDecimal bonusValue = sum.add(taxVatValue).multiply(new BigDecimal(discount)).divide(new BigDecimal("100"));

        System.out.println("Cena wszystkich produktów po uwzględnieniu podatku VAT i rabatu (brutto) : "
                + sum.add(taxVatValue).subtract(bonusValue).setScale(2, RoundingMode.HALF_UP));
    }
}
