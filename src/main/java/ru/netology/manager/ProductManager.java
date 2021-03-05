package ru.netology.manager;

import org.apache.commons.lang3.ArrayUtils;
import ru.netology.domain.Book;
import ru.netology.domain.Phone;
import ru.netology.domain.Product;
import ru.netology.repository.ProductRepository;

public class ProductManager {
    private final ProductRepository repository;

    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    public Product[] searchBy(String text) {
        var found = new Product[0];
        for (var p : repository.findAll()) {
            if (matches(p, text))
                found = ArrayUtils.add(found, p);
        }
        return found;
    }

    private boolean matches(Product product, String search) {
        if (product.getName().equalsIgnoreCase(search)) {
            return true;
        }
        if (product instanceof Book) {
            var book = (Book) product;
            return book.getAuthor().equalsIgnoreCase(search);
        }
        if (product instanceof Phone) {
            var phone = (Phone) product;
            return phone.getVendor().equalsIgnoreCase(search);
        }
        return false;
    }

    public void add(Product item) {
        repository.save(item);
    }

}
