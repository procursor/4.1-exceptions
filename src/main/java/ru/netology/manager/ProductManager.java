package ru.netology.manager;

import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import ru.netology.domain.Book;
import ru.netology.domain.Phone;
import ru.netology.domain.Product;
import ru.netology.repository.NotFoundException;
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

    public void removeById(int id) throws IllegalArgumentException {
        try {
            repository.removeById(id);
        } catch (NotFoundException e) {
            handleNFE(id, e);
        }
    }

    private void handleNFE(int id, Exception cause) throws IllegalArgumentException {
        var msg = new StringBuilder(
          "Something wrong happened at 'repository.removeById(id)': " +
            "NotFoundException has been caught\n\n" +

            "Current list of Products:\n" +
            "-------------------------\n");
        for (var p : this.findAll()) {
            msg.append(p.toString()).append('\n');
        }
        System.err.print(msg.toString());
        throw new IllegalArgumentException("product with id=" + id + " can't be removed", cause);
    }

    public Product[] findAll() {
        return this.repository.findAll();
    }

}
