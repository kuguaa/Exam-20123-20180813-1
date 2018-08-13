package bean;

/**
 * Created by 徐 on 2018/8/13.
 */
public class Customer {
    private String first_name;

    public Customer(String first_name) {
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
}
