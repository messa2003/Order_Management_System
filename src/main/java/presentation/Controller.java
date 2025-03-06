package presentation;

import businessLayer.ClientBL;
import businessLayer.OrderBL;
import businessLayer.ProductBL;
import connection.ConnectionFactory;
import model.Client;
import model.Order;
import model.Product;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

public class Controller
{
    private View view;
    private ClientBL clientBL;
    private OrderBL orderBL;
    private ProductBL productBL;
    public Controller(View view, ConnectionFactory connector)
    {
        this.view = view;
        orderBL = new OrderBL();
        productBL = new ProductBL();
        clientBL = new ClientBL();

        view.displayClientsButtonListener(new AdaugareDisplayClientsListener(this));
        view.addProductsButtonListener(new AdaugareAddProductsListener(this));
        view.editProductButtonListener(new AdaugareEditProductsListener(this));
        view.displayProductsButtonListener(new AdaugareDisplayProductsListener(this));
        view.addClientButtonListener(new AdaugareAddClientsListener(this));
        view.editClientButtonListener(new AdaugareEditClientsListener(this));
        view.deleteProductButtonListener(new AdaugareDeleteProductsListener(this));
        view.deleteClientButtonListener(new AdaugareDeleteClientsListener(this));
        view.newOrderButtonListener(new AdaugareNewOrderListener(this));
        this.view.setBoxes(clientBL.findAll(), productBL.findAll());
    }

    public static class AdaugareNewOrderListener implements ActionListener {
        private Controller controller;

        public AdaugareNewOrderListener(Controller controller) {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int quantity = controller.view.getQuantityOrder();
            Product product = controller.view.getSelectedProduct();

            if (quantity <= product.getStock()) {
                List<Order> orders = controller.orderBL.findAll();
                int nr;

                if (orders.isEmpty()) {
                    nr = 1; // Start order numbering from 1 if there are no existing orders
                } else {
                    nr = Integer.parseInt(orders.get(orders.size() - 1).getOrderID()) + 1;
                }

                String orderID = String.valueOf(nr);
                String clientID = String.valueOf(controller.view.getSelectedClient().getId());
                String productID = product.getId();

                controller.orderBL.insert(orderID, clientID, productID, quantity);
                controller.productBL.update(product.getId(), product.getName(), product.getManufacture(), product.getPrice(), product.getStock() - quantity);
                product.decreaseStock(quantity);


                System.out.println("New order placed - Order ID: " + orderID +
                        ", Client: " + controller.view.getSelectedClient().getName() +
                        ", Product: " + product.getName() + " " + product.getManufacture() +
                        ", Quantity: " + quantity +
                        ", Total Price: " + quantity * product.getPrice());
            } else {
                controller.view.ShowNotEnoughStock();
            }
        }
    }
    public static class AdaugareAddClientsListener implements ActionListener
    {
        private Controller controller;
        public AdaugareAddClientsListener(Controller controller)
        {
            this.controller = controller;
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String clientID = controller.view.getClientID();
            String clientName = controller.view.getClientName();
            String clientAddress = controller.view.getClientAddress();
            String clientEmail = controller.view.getClientEmail();
            String clientPhoneNumber = controller.view.getClientPhoneNumber();
            if (clientID.isEmpty() || clientName.isEmpty() || clientAddress.isEmpty() || clientEmail.isEmpty() || clientPhoneNumber.isEmpty())
            {
                controller.view.ShowBlankFields();
                return;
            }
            int success = controller.clientBL.insert(clientID, clientName, clientAddress, clientEmail, clientPhoneNumber);
            if (success == 0)
            {
                controller.view.ShowInvalidClient();
            }
            if (success == -1)
            {
                controller.view.ShowNotUniqueClientId();
            }
        }
    }
    public static class AdaugareEditClientsListener implements ActionListener
    {
        private Controller controller;
        public AdaugareEditClientsListener(Controller controller)
        {
            this.controller = controller;
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String clientID = controller.view.getSearchedClientId();
            String clientName = controller.view.getNewClientName();
            String clientAddress = controller.view.getNewClientAddress();
            String clientEmail = controller.view.getNewClientEmail();
            String clientPhoneNumber = controller.view.getNewClientPhoneNumber();
            if (clientID.isEmpty() || clientName.isEmpty() || clientAddress.isEmpty() || clientEmail.isEmpty() || clientPhoneNumber.isEmpty())
            {
                controller.view.ShowBlankFields();
                return;
            }
            controller.clientBL.update(clientID, clientName, clientAddress, clientEmail, clientPhoneNumber);
        }
    }
    public static class AdaugareDeleteClientsListener implements ActionListener
    {
        private Controller controller;
        public AdaugareDeleteClientsListener(Controller controller)
        {
            this.controller = controller;
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String clientID = controller.view.getClientID();
            controller.clientBL.delete(clientID);
        }
    }
    public static class AdaugareDisplayClientsListener implements ActionListener
    {
        private Controller controller;
        public AdaugareDisplayClientsListener(Controller controller)
        {
            this.controller = controller;
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            List<Client> clients;
            Object[] tableHeader;
            Object[][] table;
            clients = controller.clientBL.findAll();
            table = controller.clientBL.getTable(clients);
            tableHeader = controller.clientBL.getTableHeader();
            controller.view.displayClients(new JTable(table, tableHeader));
        }
    }
    public static class AdaugareAddProductsListener implements ActionListener
    {
        private Controller controller;
        public AdaugareAddProductsListener(Controller controller)
        {
            this.controller = controller;
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String productID = controller.view.getProductCode();
            String productName = controller.view.getProductName();
            String productManufacture = controller.view.getProductManufacture();
            Double productPrice = controller.view.getProductPrice();
            int productStock = controller.view.getProductStock();
            controller.productBL.insert(productID, productName, productManufacture, productPrice, productStock);
        }
    }
    public static class AdaugareEditProductsListener implements ActionListener
    {
        private Controller controller;
        public AdaugareEditProductsListener(Controller controller)
        {
            this.controller = controller;
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String productID = controller.view.getSearchedProductCode();
            String productName = controller.view.getNewProductName();
            String productManufacture = controller.view.getNewProductManufacture();
            double productPrice = controller.view.getNewProductPrice();
            int productStock = controller.view.getNewProductStock();
            controller.productBL.update(productID, productName, productManufacture, productPrice, productStock);
        }
    }
    public static class AdaugareDeleteProductsListener implements ActionListener
    {
        private Controller controller;
        public AdaugareDeleteProductsListener(Controller controller)
        {
            this.controller = controller;
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String productID = controller.view.getProductCode();
            controller.productBL.delete(productID);
        }
    }
    public static class AdaugareDisplayProductsListener implements ActionListener
    {
        private Controller controller;

        public AdaugareDisplayProductsListener(Controller controller)
        {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            List<Product> products;
            Object[] tableHeader;
            Object[][] table;
            products = controller.productBL.findAll();
            table = controller.productBL.getTable(products);
            tableHeader = controller.productBL.getTableHeader();
            controller.view.displayProducts(new JTable(table, tableHeader));
        }
    }
}