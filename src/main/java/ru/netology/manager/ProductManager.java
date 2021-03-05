package ru.netology.manager;

import java.util.logging.Logger;
import org.apache.commons.lang3.ArrayUtils;
import ru.netology.domain.Book;
import ru.netology.domain.Phone;
import ru.netology.domain.Product;
import ru.netology.repository.NotFoundException;
import ru.netology.repository.ProductRepository;

public class ProductManager {
    private final static Logger LOGGER = Logger.getLogger(Logger.class.getName());
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
            throw new IllegalArgumentException("product with id=" + id + " can't be removed", e);
        }
    }

    private void handleNFE(int id, Exception e) {
        LOGGER.severe("Something wrong happened at '" +
                        e.getStackTrace()[0].getClassName() +
                        "." +
                        e.getStackTrace()[0].getMethodName() +
                        "': 'NotFoundException' has been caught"
        );
        var msg = new StringBuilder(
            "\nCurrent list of Products:\n" +
            "-------------------------\n");
        for (var p : this.findAll()) {
            msg.append(p.toString()).append('\n');
        }
        LOGGER.info(msg.toString());
    }

    public Product[] findAll() {
        return this.repository.findAll();
    }

}
