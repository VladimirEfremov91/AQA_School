package org.lesson6.delivery.model;

public class Parcel {
    private String name;
    private String deliveryAddress;
    protected double parcelWeight;
    String trackingNumber;

    public Parcel(String name, String deliveryAddress, double parcelWeight, String trackingNumber) {
        this.name = name;
        this.deliveryAddress = deliveryAddress;
        this.parcelWeight = parcelWeight;
        this.trackingNumber = trackingNumber;
    }

    public Parcel() {
    }

    public double getParcelWeight() {
        return parcelWeight;
    }

    public void setParcelWeight(double parcelWeight) {
        this.parcelWeight = parcelWeight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double calculateDeliveryPrice() {
        return 100 + parcelWeight * 30;
    }

    public void printInfo() {
        System.out.println("Данные посылки " + trackingNumber + ":");
        System.out.println("Имя получателя: " + name);
        System.out.println("Адрес доставки: " + deliveryAddress);
        System.out.println("Вес посылки: " + parcelWeight);
    }


}
