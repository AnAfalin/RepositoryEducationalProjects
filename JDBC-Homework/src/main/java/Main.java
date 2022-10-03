import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        ClientRepository clientRepository = new ClientRepository();
        clientRepository.createTableClients();

        OrderRepository orderRepository = new OrderRepository();
        orderRepository.createTableOrders();

        clientRepository.saveClient(new Client("Mike", 25, "Russia"));
        clientRepository.saveClient(new Client("Liza", 20, "USA"));
        clientRepository.saveClient(new Client("Slava", 30, "France"));
        clientRepository.getAllClients().forEach(System.out::println);

        System.out.println("*------*");

        clientRepository.deleteClientById(2);
        clientRepository.saveClient(new Client("Misha", 35, "Russia"));
        clientRepository.getAllClients().forEach(System.out::println);

        orderRepository.saveOrder(new Order("iPhone", 40000.00, LocalDateTime.of(2022, 8, 15, 10, 0), 1));
        orderRepository.saveOrder(new Order("MP3-pleer", 10000.00, LocalDateTime.of(2022, 5, 10, 15, 0), 3));
        orderRepository.saveOrder(new Order("Laptop", 90000.50, null, 4));
        orderRepository.saveOrder(new Order("mobile Phone", 15000.50, null, 4));
        System.out.println("*------*");
        clientRepository.getAllClients().forEach(System.out::println);


        System.out.println(clientRepository.getMapClientOrderById(4));
    }
}
