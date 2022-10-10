import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class OrderRepository {
    private final Connection connection;

    public OrderRepository() {
        this.connection = JDBCConnection.getConnection();
    }

    public boolean createTableOrders() {
//        String SQL = "CREATE TABLE orders " +
//                "(id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
//                "product VARCHAR(30) NOT NULL," +
//                "price DOUBLE(8,2) NOT NULL," +
//                "date_time_order DATETIME DEFAULT NOW() not null, " +
//                "client_id BIGINT, " +
//                "FOREIGN KEY (client_id) REFERENCES clients(id))";

        String SQL = SQLFileUtils.readSqlQuery("JDBC-Homework\\src\\main\\sqlFiles\\createTableOrders.txt");

        try (Statement statement = connection.createStatement()){
            System.out.println("table 'orders' was created");
            return statement.execute(SQL);
        }catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        System.out.println("table 'orders' wasn't created");
        return false;
    }

    //получение всех заказов
    public List<Order> getAllOrders(){

        String SQL = "SELECT * FROM orders";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)){
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String product = resultSet.getString("product");
                Double price = resultSet.getDouble("price");
                LocalDateTime dateTimeOrder = resultSet.getTimestamp("date_time_order").toLocalDateTime();
                Long client_id = resultSet.getLong("client_id");
                orders.add(new Order(id, product, price, dateTimeOrder, client_id));
            }
            return orders;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return List.of();
        }
    }

    //вставка нового заказа
    public boolean saveOrder(Order order){
        String SQL = "INSERT INTO orders VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            preparedStatement.setLong(1, java.sql.Types.NULL);
            preparedStatement.setString(2, order.getProduct());
            preparedStatement.setDouble(3, order.getPrice());
            if(order.getDateTimeOrder() != null){
                preparedStatement.setTimestamp(4, Timestamp.valueOf(order.getDateTimeOrder()));
            }else {
                preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            }
            preparedStatement.setLong(5, order.getClientId());

            ClientRepository clientRepository = new ClientRepository();
            Double oldAmountOrder = clientRepository.getAmountOrder(order.getClientId());
            if(oldAmountOrder != null){
                clientRepository.updateTotalAmountOrderByID(order.getClientId(), oldAmountOrder + order.getPrice());
            }else {
                clientRepository.updateTotalAmountOrderByID(order.getClientId(), order.getPrice());
            }
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
    }


}
