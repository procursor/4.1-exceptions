package ru.netology.manager;

import static org.apache.commons.lang3.ArrayUtils.isEmpty;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Phone;
import ru.netology.domain.Product;
import ru.netology.repository.ProductRepository;

class ProductManagerTest {
    final ProductManager manager = new ProductManager(new ProductRepository());

    final Book book1;
    final Book book2;
    final Phone phone1;
    final Phone phone2;

    ProductManagerTest() {
        book1 = new Book(1, "Objective C", 100, "Быстрый");
        phone1 = new Phone(2, "быстрый", -1, "Apple");
        book2 = new Book(3, "Java", 300, "Android");
        phone2 = new Phone(4, "медленный", 400, "java");
    }

    @Test
    @DisplayName("Should not throw exception")
    void shouldNotThrowEx() {
        assertDoesNotThrow(() -> manager.removeById(0));
    }

    @Test
    void shouldNotFindAny() {
        manager.add(new Product(0, "", 0));
        assertTrue(isEmpty(manager.searchBy("no such product")));
    }

    @BeforeEach
    void setUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(phone1);
        manager.add(phone2);
    }

    @Test
    void shouldRemoveByIdIfExists() throws IllegalArgumentException {
        var expected = new Product[] {book2, phone1, phone2};

        manager.removeById(1);

        assertArrayEquals(expected, manager.findAll());
    }

    /* provide 100% */
    @Test
    void shouldFindByNameOrAuthor() {
        var actual = manager.searchBy("быстрый");
        var expected = new Product[] {book1, phone1};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindByNameOrVendor() {
        var actual = manager.searchBy("java");
        var expected = new Product[] {book2, phone2};

        assertArrayEquals(expected, actual);
    }

}