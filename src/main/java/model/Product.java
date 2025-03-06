package model;

public class Product
{
    private String id;
    private String Name;
    private String Manufacture;
    private double Price;
    private int Stock;

    public String getId()
    {
        return id;
    }
    public String getName()
    {
        return Name;
    }
    public String getManufacture()
    {
        return Manufacture;
    }
    public double getPrice()
    {
        return Price;
    }
    public int getStock()
    {
        return Stock;
    }
    public void setName(String Name)
    {
        this.Name = Name;
    }
    public void setId(String productID)
    {
        this.id = productID;
    }
    public void setManufacture(String Manufacture)
    {
        this.Manufacture = Manufacture;
    }
    public void setPrice(double Price)
    {
        this.Price = Price;
    }
    public void setStock(int Stock)
    {
        this.Stock = Stock;
    }
    public void decreaseStock(int amount)
    {
        this.Stock = this.Stock - amount;
    }

    @Override
    public String toString()
    {
        return "Product [productID = " + id + ", name = " + Name + ", manufacture = " + Manufacture + ", price = " + Price + ", stock = " + Stock + "]";
    }
}