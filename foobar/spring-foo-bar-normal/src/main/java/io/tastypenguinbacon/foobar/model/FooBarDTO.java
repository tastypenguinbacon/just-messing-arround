package io.tastypenguinbacon.foobar.model;

public class FooBarDTO {
    private final int numberValue;
    private final String stringValue;

    public FooBarDTO(int numberValue, String stringValue) {
        this.numberValue = numberValue;
        this.stringValue = stringValue;
    }

    public int getNumberValue() {
        return numberValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    @Override
    public String toString() {
        return "FooBarDTO{" +
                "numberValue=" + numberValue +
                ", stringValue='" + stringValue + '\'' +
                '}';
    }
}
