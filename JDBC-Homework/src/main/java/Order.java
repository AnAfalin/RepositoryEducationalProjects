import java.time.LocalDateTime;

public class Order {
    private Long id;
    private String product;
    private Double price;
    private LocalDateTime dateTimeOrder;
    private Long client_id;

    public Order(String product, Double price, LocalDateTime dateTimeOrder, int client_id) {
        this.product = product;
        this.price = price;
        this.dateTimeOrder = dateTimeOrder;
        this.client_id = (long) client_id;
    }

    public Order(Long id, String product, Double price, LocalDateTime dateTimeOrder, Long client_id) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.dateTimeOrder = dateTimeOrder;
        this.client_id = client_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getDateTimeOrder() {
        return dateTimeOrder;
    }

    public void setDateTimeOrder(LocalDateTime dateTimeOrder) {
        this.dateTimeOrder = dateTimeOrder;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", price=" + price +
                ", dateTimeOrder=" + dateTimeOrder +
                ", client_id=" + client_id +
                '}';
    }
}