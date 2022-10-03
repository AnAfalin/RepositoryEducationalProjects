public class Client {
    private Long id;
    private String name;
    private Integer age;
    private String country;
    private Double totalAmountOrder;

    public Client(String name, Integer age, String country) {
        this.name = name;
        this.age = age;
        this.country = country;
    }

    public Client(Long id, String name, Integer age, String country, Double totalAmountOrder) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.country = country;
        this.totalAmountOrder = totalAmountOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getTotalAmountOrder() {
        return totalAmountOrder;
    }

    public void setTotalAmountOrder(Double totalAmountOrder) {
        this.totalAmountOrder = totalAmountOrder;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", totalAmountOrder=" + totalAmountOrder +
                '}';
    }
}
