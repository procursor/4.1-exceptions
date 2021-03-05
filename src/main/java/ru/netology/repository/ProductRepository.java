package ru.netology.repository;

import static org.apache.commons.lang3.ArrayUtils.add;
import static org.apache.commons.lang3.ArrayUtils.removeElement;

import org.apache.commons.lang3.ArrayUtils;
import ru.netology.domain.Product;

public class ProductRepository {
    private Product[] products = new Product[0];

    public void save(Product item) {
        this.products = add(products, item);
    }

    public Product findById(int id) {
        for (Product item : products) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeById(int id) {
        products = removeElement(products, findById(id));
    }

    public Product[] findAll() {
        return ArrayUtils.clone(products);
    }
}

