package ca.jbrains.pos.test;

import java.util.HashMap;
import java.util.Map;

public class FindPriceInMemoryCatalogTest extends FindPriceInCatalogContract {
    @Override
    protected Catalog catalogWith(String barcode, Price matchingPrice) {
        return new InMemoryCatalog(new HashMap<>() {{
            put(String.format("not %s", barcode), Price.cents(-1));
            put(String.format("definitely not %s", barcode), Price.cents(-2));
            put(barcode, matchingPrice);
            put(String.format("not %s, you idiot", barcode), Price.cents(-3));
        }});
    }

    @Override
    protected Catalog catalogWithout(String barcodeToAvoid) {
        return new InMemoryCatalog(new HashMap<>() {{
            put(String.format("not %s", barcodeToAvoid), Price.cents(-1));
            put(String.format("definitely not %s", barcodeToAvoid), Price.cents(-2));
            put(String.format("not %s, you idiot", barcodeToAvoid), Price.cents(-3));
        }});
    }

    private class InMemoryCatalog implements Catalog {
        private Map<String, Price> pricesByBarcode;

        public InMemoryCatalog(Map<String, Price> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        public Price findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }
    }
}
