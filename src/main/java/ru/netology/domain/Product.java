package ru.netology.domain;

public class Product {
    private final int id;
    private final String name;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final int price;

    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "id=" + id + ",\t name='" + name + "';";
    }
}
