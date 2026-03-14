package fr.adriencaubel.ddd.domain;

import lombok.Getter;

@Getter
public class Stock {
    private int input;
    private int output;
    private int total;

    public Stock(int input, int output) {
        this.input = input;
        this.output = output;
    }

    public int getTotal() {
        return input - output;
    }

    public void reserve(int quantity) {
        if (total < quantity) {
            throw new IllegalStateException("Not enough stock");
        }

        total -= quantity;
    }
}
