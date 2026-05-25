package org.lesson6.delivery.app;

import org.lesson6.delivery.model.ExpressParcel;
import org.lesson6.delivery.model.FragileParcel;
import org.lesson6.delivery.model.Parcel;
import org.lesson6.delivery.service.ParcelService;

public class Main {
     public static void main(String[] args){
        Parcel regularParcel = new Parcel(
                "Иван Иванов",
                "г. Москва, ул. Ленина, 10",
                5.5,
                "REG-001"
        );

        FragileParcel fragileParcel = new FragileParcel(
                "Петр Петров",
                "г. Самара, ул. Победы, 15",
                2.0,
                "FRG-001",
                true
        );

        ExpressParcel expressParcel = new ExpressParcel(
                "Анна Смирнова",
                "г. Казань, ул. Центральная, 7",
                3.0,
                "EXP-001",
                12
        );

        Parcel emptyParcel = new Parcel();

        Parcel[] parcels = {regularParcel, fragileParcel, expressParcel, emptyParcel};

        ParcelService parcelService = new ParcelService();
        parcelService.printParcelsReport(parcels);
    }
}
