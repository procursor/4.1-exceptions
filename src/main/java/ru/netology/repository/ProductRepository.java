package ru.netology.repository;

import static org.apache.commons.lang3.ArrayUtils.add;
import static org.apache.commons.lang3.ArrayUtils.removeElement;

import ru.netology.domain.Product;

public class ProductRepository {
    private Product[] products = new Product[0];

    public void removeById(int id) {
        var p = findById(id);
        if (p == null) {
            throw new NotFoundException(id);
        }
        else {
            products = removeElement(products, p);
        }
    }

    public Product findById(int id) {
        for (Product item : products) {
            if (item.getId() == id) {
                return item;
            }
        }
        // Extremely bad pattern. Null should never be used as a legal return value!
        return null;
    }

    public void save(Product item) {
        this.products = add(products, item);
    }

    public Product[] findAll() {
        return products.clone();
    }
}
