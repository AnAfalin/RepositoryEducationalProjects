import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class Order {
    private Long id;
    private String product;
    private BigDecimal price;
    private LocalDateTime dateTimeOrder;
    private Long clientId;

    public Order(String product, double price, LocalDateTime dateTimeOrder, int clientId) {
        this.product = product;
        this.price = new BigDecimal(price, new MathContext(2));
        this.dateTimeOrder = dateTimeOrder;
        this.clientId = (long) clientId;
    }

    public Order(Long id, String product, double price, LocalDateTime dateTimeOrder, Long clientId) {
        this.id = id;
        this.product = product;
        this.price = new BigDecimal(price).setScale(2, RoundingMode.CEILING);

        this.dateTimeOrder = dateTimeOrder;
        this.clientId = clientId;
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

    public double getPrice() {
        return price.doubleValue();
    }

    public void BigDecimal(Double price) {
        this.price = new BigDecimal(price).setScale(2, RoundingMode.CEILING);
    }

    public LocalDateTime getDateTimeOrder() {
        return dateTimeOrder;
    }

    public void setDateTimeOrder(LocalDateTime dateTimeOrder) {
        this.dateTimeOrder = dateTimeOrder;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", price=" + price +
                ", dateTimeOrder=" + dateTimeOrder +
                ", client_id=" + clientId +
                '}';
    }
}