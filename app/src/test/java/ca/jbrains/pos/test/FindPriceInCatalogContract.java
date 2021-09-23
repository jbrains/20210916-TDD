package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class FindPriceInCatalogContract {
    @Test
    void productFound() {
        final String barcode = "::barcode::";
        final Price matchingPrice = Price.cents(795);

        Assertions.assertEquals(matchingPrice, catalogWith(barcode, matchingPrice).findPrice(barcode));
    }

    protected abstract Catalog catalogWith(String barcode, Price matchingPrice);

    @Test
    void productNotFound() {
        Assertions.assertEquals(
                null,
                catalogWithout("::missing barcode::").findPrice("::missing barcode::"));
    }

    protected abstract Catalog catalogWithout(String barcodeToAvoid);
}
