package businessLayer;

import dataAccessLayer.ProductDAO;
import model.Product;

import java.util.List;

/**
 * Clasa Business Layer pentru gestionarea operațiunilor legate de {@link Product}.
 */
public class ProductBL {
    private ProductDAO productDAO;

    /**
     * Constructorul clasei {@code ProductBL}.
     * Inițializează obiectul {@link ProductDAO}.
     */
    public ProductBL() {
        productDAO = new ProductDAO();
    }

    /**
     * Actualizează detaliile unui produs în baza de date.
     * @param productID ID-ul produsului.
     * @param name Numele produsului.
     * @param manufacture Producătorul produsului.
     * @param price Prețul produsului.
     * @param stock Stocul disponibil al produsului.
     */
    public void update(String productID, String name, String manufacture, double price, int stock) {
        Product product = new Product();
        product.setPrice(price);
        product.setStock(stock);
        product.setManufacture(manufacture);
        product.setId(productID);
        product.setName(name);
        productDAO.update(product);
    }

    /**
     * Șterge un produs din baza de date după ID-ul său.
     * @param id ID-ul produsului de șters.
     */
    public void delete(String id) {
        productDAO.delete(productDAO.searchByID(Integer.parseInt(id)));
    }

    /**
     * Introduce un nou produs în baza de date.
     * @param productID ID-ul produsului.
     * @param Name Numele produsului.
     * @param Manufacture Producătorul produsului.
     * @param Price Prețul produsului.
     * @param Stock Stocul disponibil al produsului.
     */
    public void insert(String productID, String Name, String Manufacture, Double Price, int Stock) {
        Product product = new Product();
        product.setStock(Stock);
        product.setManufacture(Manufacture);
        product.setName(Name);
        product.setId(productID);
        product.setPrice(Price);
        productDAO.insert(product);
    }

    /**
     * Găsește și returnează toate produsele din baza de date.
     * @return O listă de obiecte {@link Product}.
     */
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    /**
     * Obține antetul tabelului cu produse.
     * @return Un obiect array conținând anteturile coloanelor tabelului.
     */
    public Object[] getTableHeader() {
        return productDAO.getTableHeader();
    }

    /**
     * Generează tabelul cu produsele date.
     * @param productList Lista de produse pentru care se generează tabelul.
     * @return Un obiect bidimensional array reprezentând tabelul cu produse.
     */
    public Object[][] getTable(List<Product> productList) {
        return productDAO.getTable(productList);
    }
}
