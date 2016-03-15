/**
 * use a CSVParser to parse a csv file into a parser of records of countries and their export information
 * find countries that export different things
 * @DinhHuuNguyen
 * @01/12/2016
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class exporters {
    public void printArrayList (ArrayList list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    
    public String countryInfo (CSVParser parser, String country) {
        String line = "NOT FOUND";
        for (CSVRecord record : parser) {
            if (record.get("Country").equals(country)) {
                line = record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }
        return line;
    }
    
    public ArrayList<String> exportersofOneProduct(CSVParser parser, String product) {
        ArrayList<String> exportersof1product = new ArrayList<String>();
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(product)) {
                String country = record.get("Country");
                exportersof1product.add(country);
            }
        }
        return exportersof1product;
    }
    
    public ArrayList<String> exportersofTwoProducts(CSVParser parser, String product1, String product2) {
        ArrayList<String> exportersof2products = new ArrayList<String>();
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(product1) && exports.contains(product2)) {
                String country = record.get("Country");
                exportersof2products.add(country);
            }
        }
        return exportersof2products;
    }
    
    public HashMap<String, String> exportersofMoreThan (CSVParser parser, String amount) {
        HashMap<String, String> bigexporters = new HashMap<String, String>();
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            if (amount.length() < value.length()) {
                bigexporters.put(record.get("Country"), value);
            }
        }
        return bigexporters;
    }
    
    public void testcountryInfo (String name) {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(name + " export info");
        System.out.println(countryInfo(parser, name));
    }
    
    public void testexportersofOneProduct(String product) {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("countries that export " + product);
        printArrayList(exportersofOneProduct(parser, product));
    }
    
    public void testexportersofTwoProducts(String product1, String product2) {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("countries that export " + product1 + " and " + product2);
        printArrayList(exportersofTwoProducts(parser, product1, product2));
    }
    
    public void testexportersofMoreThan() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String amount = "$999,999,999,999";
        HashMap<String, String> bigexporters = exportersofMoreThan(parser, amount);
        System.out.println("\n" + "countries that export more than " + amount);
        for (String country : bigexporters.keySet()) {
            System.out.println(country + "\t" + bigexporters.get(country));
        }
    }
}