package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class FindPriceInMemoryCatalogTest {
    @Test
    void productFound() {
        final String barcode = "12345";
        final Price matchingPrice = Price.cents(795);

        InMemoryCatalog catalog = new InMemoryCatalog(new HashMap<>() {{
            put(barcode, matchingPrice);
        }});
        Assertions.assertEquals(matchingPrice, catalog.findPrice(barcode));
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
