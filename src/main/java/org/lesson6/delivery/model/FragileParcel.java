package org.lesson6.delivery.model;

public class FragileParcel extends Parcel {
    private boolean requiresCarefulHandling;
    private static final double CAREFUL_HANDLING_EXTRA_PRICE = 200;

    public FragileParcel(String name, String deliveryAddress, double parcelWeight, String trackingNumber, boolean requiresCarefulHandling) {
        super(name, deliveryAddress, parcelWeight, trackingNumber);
        this.requiresCarefulHandling = requiresCarefulHandling;
    }

    public double calculateDeliveryPrice() {
        return (super.calculateDeliveryPrice() + CAREFUL_HANDLING_EXTRA_PRICE);
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("Handle with care: " + requiresCarefulHandling);
    }
}
