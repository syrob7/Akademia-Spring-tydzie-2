package pl.akademiaspring.task2.services;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.akademiaspring.task2.products.Product;
import pl.akademiaspring.task2.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("Start")
public class ShopStart {

    private List<Product> furnitureList;

    public ShopStart() {
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

        System.out.println("Cena netto wszystkich produktów : " + sum);
    }
}
