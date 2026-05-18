package org.lesson6.delivery.model;

public class ExpressParcel extends Parcel{
    private static final int DELIVERY_DEADLINE_HOURS = 24;
    private static final double EXPRESS_EXTRA_PRICE = 500.0;
    private int deliveryHours;

    public ExpressParcel(String name, String deliveryAddress, double parcelWeight, String trackingNumber, int deliveryHours) {
        super(name, deliveryAddress, parcelWeight, trackingNumber);
        this.deliveryHours = deliveryHours;
    }

    public double calculateDeliveryPrice() {
        if (deliveryHours < DELIVERY_DEADLINE_HOURS) {
            return (super.calculateDeliveryPrice() + EXPRESS_EXTRA_PRICE);
        } else {
            return super.calculateDeliveryPrice();
        }
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("Delivery deadline: " + deliveryHours);
    }
}
