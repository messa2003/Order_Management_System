package dataAccessLayer;

import connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * AbstractDAO este o clasă generică pentru accesul la date din baza de date.
 * Oferă operații CRUD comune pentru orice tip de obiect.
 *
 * @param <T> Tipul obiectului gestionat de acest DAO.
 */
public class AbstractDAO<T> {

    private Logger logger = Logger.getLogger(AbstractDAO.class.getName());
    private Class<T> type;

    /**
     * Constructor pentru AbstractDAO.
     * Determină tipul de clasă T la runtime.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Creează o interogare SQL SELECT pentru un anumit câmp.
     *
     * @param field Numele câmpului după care se face interogarea.
     * @return Interogarea SQL sub formă de string.
     */
    private String createSelectQuery(String field) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append(" * ");
        stringBuilder.append(" FROM ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" WHERE `" + field + "` =?");
        return stringBuilder.toString();
    }

    /**
     * Găsește toate înregistrările din baza de date pentru tipul de obiect T.
     *
     * @return O listă de obiecte de tip T.
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM `" + type.getSimpleName().toLowerCase() + "`";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            logger.log(Level.WARNING, type.getName() + "DAO : findAll " + e.getMessage());
        } finally {
            /*ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);*/
        }
        return null;
    }

    /**
     * Găsește un obiect de tip T după ID.
     *
     * @param id ID-ul obiectului căutat.
     * @return Obiectul de tip T găsit sau null dacă nu a fost găsit.
     */
    public T searchByID(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            logger.log(Level.WARNING, type.getName() + "DAO : findByID " + e.getMessage());
        } finally {
        }
        return null;
    }

    /**
     * Creează o listă de obiecte de tip T dintr-un ResultSet.
     *
     * @param resultSet ResultSet-ul din care se vor crea obiectele.
     * @return O listă de obiecte de tip T.
     */
    public List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] constructors = type.getDeclaredConstructors();
        Constructor constructor = null;
        for (int i = 0; i < constructors.length; i++) {
            constructor = constructors[i];
            if (constructor.getGenericParameterTypes().length == 0) {
                break;
            }
        }
        try {
            while (resultSet.next()) {
                constructor.setAccessible(true);
                T entry;
                entry = (T) constructor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(entry, value);
                }
                list.add(entry);
            }
        } catch (InstantiationException | IllegalAccessException | SecurityException |
                 IllegalArgumentException | InvocationTargetException | SQLException |
                 IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Obține header-ul tabelului sub formă de array de obiecte.
     *
     * @return Un array de obiecte reprezentând header-ul tabelului.
     */
    public Object[] getTableHeader() {
        Field[] fields = type.getDeclaredFields();
        Object[] header = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            header[i] = (String.valueOf((name.charAt(0)))).toUpperCase() + name.substring(1, name.length());
        }
        return header;
    }

    /**
     * Obține datele tabelului sub formă de matrice de obiecte.
     *
     * @param list Lista de obiecte de tip T din care se va crea matricea.
     * @return O matrice de obiecte reprezentând datele tabelului.
     */
    public Object[][] getTable(List<T> list) {
        Field[] fields = type.getDeclaredFields();
        Object[][] table = new Object[list.size()][fields.length];
        try {
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < fields.length; j++) {
                    fields[j].setAccessible(true);
                    table[i][j] = fields[j].get(list.get(i));
                }
            }
            return table;
        } catch (IllegalAccessException e) {
            logger.log(Level.WARNING, type.getName() + "DAO : getTable " + e.getMessage());
        }
        return null;
    }

    /**
     * Inserează un obiect de tip T în baza de date.
     *
     * @param t Obiectul de inserat.
     * @return 1 dacă inserarea a reușit, -1 dacă a eșuat.
     */
    public int insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "insert into `" + type.getSimpleName() + "` values(";
        try {
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(t);
                if (value instanceof String) {
                    query = query + "'" + value.toString() + "',";
                } else {
                    query = query + value.toString() + ",";
                }
            }
            query = query.substring(0, query.length() - 1) + ")";
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            System.out.println(statement);
            statement.execute();
            return 1;
        } catch (SQLException | IllegalAccessException e) {
            logger.log(Level.WARNING, type.getName() + "DAO : insert " + e.getMessage());
            return -1;
        } finally {
            //ConnectionFactory.close(statement);
            //ConnectionFactory.close(connection);
        }
    }

    /**
     * Actualizează un obiect de tip T în baza de date.
     *
     * @param t Obiectul de actualizat.
     */
    public void update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "update `" + type.getSimpleName() + "` set ";
        String id = null;
        try {
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(t);
                if (field.getName().equals("id")) {
                    id = (String) value;
                } else {
                    if (value instanceof String) {
                        query = query + field.getName() + "='" + value + "',";
                    } else {
                        query = query + field.getName() + "=" + value.toString() + ",";
                    }
                }
            }
            query = query.substring(0, query.length() - 1) + " where id=" + id;
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            System.out.println(statement);
            statement.execute();
        } catch (SQLException | IllegalAccessException e) {
            logger.log(Level.WARNING, type.getName() + "DAO : update " + e.getMessage());
        } finally {
        }
    }

    /**
     * Șterge un obiect de tip T din baza de date.
     *
     * @param t Obiectul de șters.
     */
    public void delete(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "delete from `" + type.getSimpleName() + "` where id=";
        String id = null;
        try {
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(t);
                if (field.getName().equals("id")) {
                    id = (String) value;
                }
            }
            query = query + id;
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            System.out.println(statement);
            statement.execute();
        } catch (SQLException | IllegalAccessException e) {
            logger.log(Level.WARNING, type.getName() + "DAO : delete " + e.getMessage());
        } finally {
        }
    }
}
