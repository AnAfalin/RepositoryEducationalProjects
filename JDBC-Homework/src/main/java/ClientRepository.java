import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class ClientRepository {
    private final Connection connection;

    public ClientRepository() {
        this.connection = JDBCConnection.getConnection();
    }

    public boolean createTableClients() {
//        String SQL = "CREATE TABLE clients " +
//                "(id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
//                "name VARCHAR(15) NOT NULL, " +
//                "age INT, " +
//                "country VARCHAR(15), " +
//                "total_amount_order DECIMAL(8, 2));";

        String SQL =  SQLFileUtils.readSqlQuery("JDBC-Homework\\src\\main\\sqlFiles\\createTableClients.txt");

        try (Statement statement = connection.createStatement()){
            System.out.println("table 'clients' was created");
            return statement.execute(SQL);
        }catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        System.out.println("table 'clients' wasn't created");
        return false;
    }

    //получение всех клиентов
    public List<Client> getAllClients(){

        String SQL = "SELECT * FROM clients";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)){
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                String country = resultSet.getString("country");
                Double total_amount_order = resultSet.getDouble("total_amount_order");
                clients.add(new Client(id, name, age, country, total_amount_order));
            }
            return clients;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return List.of();
        }
    }

    //вставка нового клиента
    public boolean saveClient(Client client){
        String SQL = "INSERT INTO clients (name, age, country) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            //preparedStatement.setLong(1, java.sql.Types.NULL);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setInt(2, client.getAge());
            preparedStatement.setString(3, client.getCountry());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    //обновление данных клиента
    public boolean updateClient(Long id, Client client){
        String SQL = "UPDATE clients SET name = ?, age = ?, country = ?, total_amount_order = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            preparedStatement.setString(1, client.getName());
            preparedStatement.setInt(2, client.getAge());
            preparedStatement.setString(3, client.getCountry());
            preparedStatement.setDouble(4, client.getTotalAmountOrder());
            preparedStatement.setLong(5, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    public Double getAmountOrder(Long id){
        String SQL = "SELECT total_amount_order FROM clients WHERE id = " + id;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)){
            if(resultSet.next()){
                return resultSet.getDouble("total_amount_order");
            }else {
                return null;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    public boolean updateTotalAmountOrderByID(Long id, Double newAmountOrder){
        String SQL = "UPDATE clients SET total_amount_order = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            preparedStatement.setDouble(1, newAmountOrder);
            preparedStatement.setLong(2, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    //удаление клиента
    public boolean deleteClientById(int id){
        String SQL = "DELETE FROM clients WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            preparedStatement.setLong(1, Long.valueOf(id));

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }

    public Map<Client, Order> getMapClientOrderById(int id){
//        String SQL = "SELECT * FROM clients c " +
//                "INNER JOIN orders o " +
//                "ON c.id = o.client_id " +
//                "WHERE c.id = ";
        String SQL =  SQLFileUtils.readSqlQuery("JDBC-Homework\\src\\main\\sqlFiles\\getMapClientOrderById.txt") + id;

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery((SQL).toString())){

            while (resultSet.next()) {
                Long idClient = resultSet.getLong("c.id");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                String country = resultSet.getString("country");
                Double total_amount_order = resultSet.getDouble("total_amount_order");
                Long idOrder = resultSet.getLong("o.id");
                String product = resultSet.getString("product");
                Double price = resultSet.getDouble("price");
                LocalDateTime dateTimeOrder = resultSet.getTimestamp("date_time_Order").toLocalDateTime();

                return Map.of(new Client(idClient, name, age, country, total_amount_order), new Order(idOrder, product, price, dateTimeOrder, idClient));
            }

            return Map.of();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return Map.of();
        }
    }
}
