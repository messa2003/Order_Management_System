package model;

public class Order
{
    private String orderID;
    private String clientID;
    private String productID;
    private int quantity;

    public Order(String orderID, String clientID, String productID, int quantity)
    {
        this.clientID = clientID;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public Order()
    {

    }

    public String getClientID()
    {
        return clientID;
    }
    public String getOrderID()
    {
        return orderID;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public String getProductID()
    {
        return productID;
    }
    public void setOrderID(String orderID)
    {
        this.orderID = orderID;
    }
    public void setClientID(String clientID)
    {
        this.clientID = clientID;
    }
    public void setProductID(String productID)
    {
        this.productID = productID;
    }
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order with id " + orderID + " client id " + clientID + " and product id " + productID;
    }
}
