package businessLayer;

import dataAccessLayer.ClientDAO;
import model.Client;
import businessLayer.validators.EmailValidator;
import businessLayer.validators.PhoneNumberValidator;
import businessLayer.validators.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa Business Layer pentru gestionarea operațiunilor legate de {@link Client}.
 */
public class ClientBL {
    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    /**
     * Constructorul clasei {@code ClientBL}.
     * Inițializează lista de validatori și obiectul {@link ClientDAO}.
     */
    public ClientBL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        validators.add(new PhoneNumberValidator());
        clientDAO = new ClientDAO();
    }

    /**
     * Introduce un nou client în baza de date.
     * @param clientID ID-ul clientului.
     * @param Name Numele clientului.
     * @param Address Adresa clientului.
     * @param Email Email-ul clientului.
     * @param Phone_Number Numărul de telefon al clientului.
     * @return 1 dacă inserția a avut succes, 0 dacă a eșuat din cauza unei validări nereușite.
     */
    public int insert(String clientID, String Name, String Address, String Email, String Phone_Number) {
        Client client = new Client();
        client.setId(clientID);
        client.setName(Name);
        client.setEmail(Email);
        client.setAddress(Address);
        client.setPhone_nr(Phone_Number);

        try {
            validators.get(0).validate(client);
            validators.get(1).validate(client);
        } catch (Exception e) {
            return 0;
        }

        return clientDAO.insert(client);
    }

    /**
     * Șterge un client din baza de date pe baza ID-ului.
     * @param id ID-ul clientului care trebuie șters.
     */
    public void delete(String id) {
        clientDAO.delete(clientDAO.searchByID(Integer.parseInt(id)));
    }

    /**
     * Actualizează informațiile unui client existent în baza de date.
     * @param clientID ID-ul clientului.
     * @param name Numele clientului.
     * @param address Adresa clientului.
     * @param email Email-ul clientului.
     * @param phoneNumber Numărul de telefon al clientului.
     */
    public void update(String clientID, String name, String address, String email, String phoneNumber) {
        Client client = new Client();
        client.setId(clientID);
        client.setEmail(email);
        client.setName(name);
        client.setAddress(address);
        client.setPhone_nr(phoneNumber);
        clientDAO.update(client);
    }

    /**
     * Găsește și returnează toți clienții din baza de date.
     * @return O listă de obiecte {@link Client}.
     */
    public List<Client> findAll() {
        return clientDAO.findAll();
    }

    /**
     * Obține antetul tabelului de clienți.
     * @return Un array de obiecte care reprezintă antetul tabelului.
     */
    public Object[] getTableHeader() {
        return clientDAO.getTableHeader();
    }

    /**
     * Obține datele tabelului pentru o listă de clienți.
     * @param clientList Lista de clienți.
     * @return Un array bidimensional de obiecte care reprezintă datele tabelului.
     */
    public Object[][] getTable(List<Client> clientList) {
        return clientDAO.getTable(clientList);
    }
}
