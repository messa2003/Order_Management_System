package presentation;

import model.Client;
import model.Product;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JTextField clientIDField = new JTextField();
    private JTextField clientNameField = new JTextField();
    private JTextField clientAddressField = new JTextField();
    private JTextField clientEmailField = new JTextField();
    private JTextField clientPhoneNumberField = new JTextField();
    private JTextField clientIDFieldEdit = new JTextField();
    private JTextField clientNameFieldEdit = new JTextField();
    private JTextField clientAddressFieldEdit = new JTextField();
    private JTextField clientEmailFieldEdit = new JTextField();
    private JTextField clientPhoneNumberFieldEdit = new JTextField();

    private JTextField productCodeField = new JTextField();
    private JTextField productNameField = new JTextField();
    private JTextField productManufactureField = new JTextField();
    private JTextField productPriceField = new JTextField();
    private JTextField productStockField = new JTextField();
    private JTextField productNameFieldEdit = new JTextField();
    private JTextField productCodeFieldEdit = new JTextField();
    private JTextField productManufactureFieldEdit = new JTextField();
    private JTextField productPriceFieldEdit = new JTextField();
    private JTextField productStockFieldEdit = new JTextField();

    private JLabel clientIDLabel = new JLabel("ID: ");
    private JLabel clientNameLabel = new JLabel("Name: ");
    private JLabel clientAddressLabel = new JLabel("Address: ");
    private JLabel clientEmailLabel = new JLabel("Email: ");
    private JLabel clientPhoneNumberLabel = new JLabel("Phone Number: ");
    private JLabel clientIDLabelEdit = new JLabel("Searching by ID: ");
    private JLabel clientNameLabelEdit = new JLabel("New Name: ");
    private JLabel clientAddressLabelEdit = new JLabel("New Address: ");
    private JLabel clientEmailLabelEdit = new JLabel("New Email: ");
    private JLabel clientPhoneNumberLabelEdit = new JLabel("New Phone Number: ");

    private JLabel productCodeLabel = new JLabel("Product code: ");
    private JLabel productNameLabel = new JLabel("Product name: ");
    private JLabel productManufactureLabel = new JLabel("Manufacture: ");
    private JLabel productPriceLabel = new JLabel("Price: ");
    private JLabel productStockLabel = new JLabel("Stock: ");
    private JLabel productCodeLabelEdit = new JLabel("Searching by Code: ");
    private JLabel productNameLabelEdit = new JLabel("New name: ");
    private JLabel productManufactureLabelEdit = new JLabel("New Manufacture: ");
    private JLabel productPriceLabelEdit = new JLabel("New Price: ");
    private JLabel productStockLabelEdit = new JLabel("New Stock: ");

    private JButton addClientButton = new JButton("Add new Client");
    private JButton editClientButton = new JButton("Edit Client");
    private JButton deleteClientButton = new JButton("Delete Client, searching by ID");
    private JButton displayClientsButton = new JButton("Display Clients");
    private JButton addProductButton = new JButton("Add new Product");
    private JButton editProductButton = new JButton("Edit Product");
    private JButton deleteProductButton = new JButton("Delete Product, searching by Code");
    private JButton displayProductsButton = new JButton("Display Products");
    private JButton createOrderButton = new JButton("Create Order");

    private JComboBox<Client> clientsBox = new JComboBox<>();
    private JComboBox<Product> productsBox = new JComboBox<>();
    private JLabel productQuantityOrderLabel = new JLabel("Quantity: ");
    private JTextField productQuantityOrderField = new JTextField();
    private JButton createNewOrderButton = new JButton("Order");

    private JPanel editClientsPanel = new JPanel();
    private JPanel modifyClientsPanel = new JPanel();
    private JPanel displayClientsPanel = new JPanel();
    private JPanel createOrderPanel = new JPanel();

    private JPanel editProductsPanel = new JPanel();
    private JPanel modifyProductsPanel = new JPanel();
    private JPanel displayProductsPanel = new JPanel();

    private JTabbedPane mainTab = new JTabbedPane();
    private JTabbedPane tab1 = new JTabbedPane();
    private JTabbedPane tab2 = new JTabbedPane();

    private JTable table = new JTable();
    private JTable table2 = new JTable();

    public View() {
        editClientsPanel.setLayout(new GridLayout(0,1));
        editClientsPanel.add(clientIDLabel);
        editClientsPanel.add(clientIDField);
        editClientsPanel.add(clientNameLabel);
        editClientsPanel.add(clientNameField);
        editClientsPanel.add(clientAddressLabel);
        editClientsPanel.add(clientAddressField);
        editClientsPanel.add(clientEmailLabel);
        editClientsPanel.add(clientEmailField);
        editClientsPanel.add(clientPhoneNumberLabel);
        editClientsPanel.add(clientPhoneNumberField);
        editClientsPanel.add(addClientButton);
        editClientsPanel.add(deleteClientButton);
        tab1.add("Add Clients", editClientsPanel);

        displayClientsPanel.setLayout(new BoxLayout(displayClientsPanel, BoxLayout.Y_AXIS));
        displayClientsPanel.add(table);
        displayClientsPanel.add(displayClientsButton);

        modifyClientsPanel.setLayout(new GridLayout(0,1));
        modifyClientsPanel.add(clientIDLabelEdit);
        modifyClientsPanel.add(clientIDFieldEdit);
        modifyClientsPanel.add(clientNameLabelEdit);
        modifyClientsPanel.add(clientNameFieldEdit);
        modifyClientsPanel.add(clientAddressLabelEdit);
        modifyClientsPanel.add(clientAddressFieldEdit);
        modifyClientsPanel.add(clientEmailLabelEdit);
        modifyClientsPanel.add(clientEmailFieldEdit);
        modifyClientsPanel.add(clientPhoneNumberLabelEdit);
        modifyClientsPanel.add(clientPhoneNumberFieldEdit);
        modifyClientsPanel.add(editClientButton);

        tab1.add("Edit Client", modifyClientsPanel);
        tab1.add("Display Clients", displayClientsPanel);

        editProductsPanel.setLayout(new GridLayout(0,1));
        editProductsPanel.add(productCodeLabel);
        editProductsPanel.add(productCodeField);
        editProductsPanel.add(productNameLabel);
        editProductsPanel.add(productNameField);
        editProductsPanel.add(productManufactureLabel);
        editProductsPanel.add(productManufactureField);
        editProductsPanel.add(productPriceLabel);
        editProductsPanel.add(productPriceField);
        editProductsPanel.add(productStockLabel);
        editProductsPanel.add(productStockField);
        editProductsPanel.add(addProductButton);
        editProductsPanel.add(deleteProductButton);
        tab2.add("Add Products", editProductsPanel);

        displayProductsPanel.setLayout(new BoxLayout(displayProductsPanel, BoxLayout.Y_AXIS));
        displayProductsPanel.add(table2);
        displayProductsPanel.add(displayProductsButton);

        modifyProductsPanel.setLayout(new GridLayout(0,1));
        modifyProductsPanel.add(productCodeLabelEdit);
        modifyProductsPanel.add(productCodeFieldEdit);
        modifyProductsPanel.add(productNameLabelEdit);
        modifyProductsPanel.add(productNameFieldEdit);
        modifyProductsPanel.add(productManufactureLabelEdit);
        modifyProductsPanel.add(productManufactureFieldEdit);
        modifyProductsPanel.add(productPriceLabelEdit);
        modifyProductsPanel.add(productPriceFieldEdit);
        modifyProductsPanel.add(productStockLabelEdit);
        modifyProductsPanel.add(productStockFieldEdit);
        modifyProductsPanel.add(editProductButton);

        tab2.add("Edit Product", modifyProductsPanel);
        tab2.add("Display Products", displayProductsPanel);

        createOrderPanel.setLayout(new GridLayout(0,1));
        createOrderPanel.add(clientsBox);
        createOrderPanel.add(productsBox);
        createOrderPanel.add(productQuantityOrderLabel);
        createOrderPanel.add(productQuantityOrderField);
        createOrderPanel.add(createNewOrderButton);

        mainTab.add("Clients", tab1);
        mainTab.add("Products", tab2);
        mainTab.add("Order", createOrderPanel);

        this.setContentPane(mainTab);
        this.setPreferredSize(new Dimension(1000,700));
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setBoxes(List<Client> clients, List<Product> products) {
        clientsBox.removeAllItems();
        productsBox.removeAllItems();
        for (Client client : clients) {
            this.clientsBox.addItem(client);
        }
        for (Product product : products) {
            this.productsBox.addItem(product);
        }
    }

    public void displayProducts(JTable tabel) {
        table2 = tabel;
        table2.setPreferredScrollableViewportSize(new Dimension(500,50));

        displayProductsPanel.removeAll();

        displayProductsPanel.add(table2.getTableHeader(), BorderLayout.PAGE_START);
        displayProductsPanel.add(table2, BorderLayout.CENTER);
        displayProductsPanel.add(displayProductsButton);
        displayProductsPanel.revalidate();
        displayProductsPanel.setVisible(false);
        displayProductsPanel.setVisible(true);
    }

    public void displayClients(JTable tabel) {
        table = tabel;
        table.setPreferredScrollableViewportSize(new Dimension(500,50));

        displayClientsPanel.removeAll();

        displayClientsPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        displayClientsPanel.add(table, BorderLayout.CENTER);
        displayClientsPanel.add(displayClientsButton);
        displayClientsPanel.revalidate();
        displayClientsPanel.setVisible(false);
        displayClientsPanel.setVisible(true);
    }

    public void ShowInvalidClient() {
        JOptionPane.showMessageDialog(null, "Invalid Client Data!", "ERROR ", JOptionPane.ERROR_MESSAGE);
    }

    public void ShowNotUniqueClientId() {
        JOptionPane.showMessageDialog(null, "Client ID must be unique!", "ERROR ", JOptionPane.ERROR_MESSAGE);
    }

    public void ShowBlankFields() {
        JOptionPane.showMessageDialog(null, "Do not leave empty fields!", "ERROR ", JOptionPane.ERROR_MESSAGE);
    }

    public void ShowNotEnoughStock() {
        JOptionPane.showMessageDialog(null, "Not enough stock!", "ERROR ", JOptionPane.ERROR_MESSAGE);
    }

    public void addClientButtonListener(ActionListener e) {
        addClientButton.addActionListener(e);
    }

    public void newOrderButtonListener(ActionListener e) {
        createNewOrderButton.addActionListener(e);
    }

    public void editClientButtonListener(ActionListener e) {
        editClientButton.addActionListener(e);
    }

    public void deleteClientButtonListener(ActionListener e) {
        deleteClientButton.addActionListener(e);
    }

    public void displayClientsButtonListener(ActionListener e) {
        displayClientsButton.addActionListener(e);
    }

    public void addProductsButtonListener(ActionListener e) {
        addProductButton.addActionListener(e);
    }

    public void editProductButtonListener(ActionListener e) {
        editProductButton.addActionListener(e);
    }

    public void deleteProductButtonListener(ActionListener e) {
        deleteProductButton.addActionListener(e);
    }

    public void displayProductsButtonListener(ActionListener e) {
        displayProductsButton.addActionListener(e);
    }

    public String getClientID() {
        return clientIDField.getText();
    }

    public String getClientName() {
        return clientNameField.getText();
    }

    public String getClientAddress() {
        return clientAddressField.getText();
    }

    public String getClientEmail() {
        return clientEmailField.getText();
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumberField.getText();
    }

    public String getProductCode() {
        return productCodeField.getText();
    }

    public String getProductName() {
        return productNameField.getText();
    }

    public String getProductManufacture() {
        return productManufactureField.getText();
    }

    public Double getProductPrice() {
        return parseDoubleOrShowError(productPriceField.getText(), "Please enter a valid product price.");
    }

    public Integer getProductStock() {
        return parseIntegerOrShowError(productStockField.getText(), "Please enter a valid product stock.");
    }

    public String getNewProductManufacture() {
        return productManufactureFieldEdit.getText();
    }

    public Double getNewProductPrice() {
        return parseDoubleOrShowError(productPriceFieldEdit.getText(), "Please enter a valid new product price.");
    }

    public Integer getNewProductStock() {
        return parseIntegerOrShowError(productStockFieldEdit.getText(), "Please enter a valid new product stock.");
    }

    public String getSearchedClientId() {
        return clientIDFieldEdit.getText();
    }

    public String getNewClientName() {
        return clientNameFieldEdit.getText();
    }

    public String getNewClientAddress() {
        return clientAddressFieldEdit.getText();
    }

    public String getNewClientEmail() {
        return clientEmailFieldEdit.getText();
    }

    public String getNewClientPhoneNumber() {
        return clientPhoneNumberFieldEdit.getText();
    }

    public String getSearchedProductCode() {
        return productCodeFieldEdit.getText();
    }

    public String getNewProductName() {
        return productNameFieldEdit.getText();
    }

    public Integer getQuantityOrder() {
        return parseIntegerOrShowError(productQuantityOrderField.getText(), "Please enter a valid quantity.");
    }

    public Product getSelectedProduct() {
        return (Product) productsBox.getSelectedItem();
    }

    public Client getSelectedClient() {
        return (Client) clientsBox.getSelectedItem();
    }

    private Double parseDoubleOrShowError(String text, String errorMessage) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, errorMessage, "Input Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private Integer parseIntegerOrShowError(String text, String errorMessage) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, errorMessage, "Input Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
