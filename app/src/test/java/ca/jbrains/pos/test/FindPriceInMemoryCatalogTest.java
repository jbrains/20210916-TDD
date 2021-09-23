package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class FindPriceInMemoryCatalogTest {
    @Test
    void productFound() {
        final String barcode = "::barcode::";
        final Price matchingPrice = Price.cents(795);

        Assertions.assertEquals(matchingPrice, catalogWith(barcode, matchingPrice).findPrice(barcode));
    }

    private InMemoryCatalog catalogWith(String barcode, Price matchingPrice) {
        return new InMemoryCatalog(new HashMap<>() {{
            put(String.format("not %s", barcode), Price.cents(-1));
            put(String.format("definitely not %s", barcode), Price.cents(-2));
            put(barcode, matchingPrice);
            put(String.format("not %s, you idiot", barcode), Price.cents(-3));
        }});
    }

    @Test
    void productNotFound() {
        Assertions.assertEquals(
                null,
                catalogWithout("::missing barcode::").findPrice("::missing barcode::"));
    }

    private InMemoryCatalog catalogWithout(String barcodeToAvoid) {
        return new InMemoryCatalog(new HashMap<>() {{
            put(String.format("not %s", barcodeToAvoid), Price.cents(-1));
            put(String.format("definitely not %s", barcodeToAvoid), Price.cents(-2));
            put(String.format("not %s, you idiot", barcodeToAvoid), Price.cents(-3));
        }});
    }

    private class InMemoryCatalog {
        private Map<String, Price> pricesByBarcode;

        public InMemoryCatalog(Map<String, Price> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        public Price findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }
    }
}
