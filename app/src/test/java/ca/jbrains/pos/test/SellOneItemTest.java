package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class SellOneItemTest {
    @Test
    void productFound() {
        Display display = new Display();
        Sale sale = new Sale(display, new HashMap<>() {{
            put("12345", "EUR 7.95");
            put("23456", "EUR 12.50");
        }});

        sale.onBarcode("12345");

        Assertions.assertEquals("EUR 7.95", display.getText());
    }

    @Test
    void anotherProductFound() {
        Display display = new Display();
        Sale sale = new Sale(display, new HashMap<>() {{
            put("12345", "EUR 7.95");
            put("23456", "EUR 12.50");
        }});

        sale.onBarcode("23456");

        Assertions.assertEquals("EUR 12.50", display.getText());
    }

    @Test
    void productNotFound() {
        Display display = new Display();
        Sale sale = new Sale(display, new HashMap<>());

        sale.onBarcode("99999");

        Assertions.assertEquals("Product not found: 99999", display.getText());
    }

    @Test
    void emptyBarcode() {
        Display display = new Display();
        Sale sale = new Sale(display, new HashMap<>());

        sale.onBarcode("");

        Assertions.assertEquals("Scanning error: empty barcode", display.getText());
    }

    public static class Sale {
        private final Map<String, String> pricesByBarcode;
        private Display display;

        public Sale(Display display, Map<String, String> pricesByBarcode) {
            this.display = display;
            this.pricesByBarcode = pricesByBarcode;
        }

        public void onBarcode(String barcode) {
            // SMELL Guard Clause; maybe this belongs somewhere else.
            if ("".equals(barcode)) {
                display.displayEmptyBarcodeMessage();
                return;
            }

            String price = findPrice(barcode);
            if (price == null) {
                display.displayProductNotFoundMessage(barcode);
            } else {
                display.displayPrice(price);
            }
        }

        private String findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }
    }

    public static class Display {
        private String text;

        public String getText() {
            return text;
        }

        private void displayPrice(String price) {
            this.text = price;
        }

        private void displayEmptyBarcodeMessage() {
            this.text = "Scanning error: empty barcode";
        }

        private void displayProductNotFoundMessage(String barcode) {
            this.text = String.format("Product not found: %s", barcode);
        }
    }
}
