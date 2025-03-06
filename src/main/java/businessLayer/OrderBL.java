package businessLayer;

import dataAccessLayer.OrderDAO;
import model.Order;

import java.util.List;

/**
 * Clasa Business Layer pentru gestionarea operațiunilor legate de {@link Order}.
 */
public class OrderBL {
    private OrderDAO orderDAO;

    /**
     * Constructorul clasei {@code OrderBL}.
     * Inițializează obiectul {@link OrderDAO}.
     */
    public OrderBL() {
        orderDAO = new OrderDAO();
    }

    /**
     * Introduce o nouă comandă în baza de date.
     * @param orderID ID-ul comenzii.
     * @param clientID ID-ul clientului care a plasat comanda.
     * @param productID ID-ul produsului comandat.
     * @param quantity Cantitatea produsului comandat.
     */
    public void insert(String orderID, String clientID, String productID, int quantity) {
        Order order = new Order();
        order.setProductID(productID);
        order.setOrderID(orderID);
        order.setQuantity(quantity);
        order.setClientID(clientID);
        orderDAO.insert(order);
    }

    /**
     * Găsește și returnează toate comenzile din baza de date.
     * @return O listă de obiecte {@link Order}.
     */
    public List<Order> findAll() {
        return orderDAO.findAll();
    }
}
