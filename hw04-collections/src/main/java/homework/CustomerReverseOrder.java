package homework;

import java.util.ArrayDeque;

@SuppressWarnings({"java:S1186", "java:S1135", "java:S1172"}) // при выполнении ДЗ эту аннотацию надо удалить
public class CustomerReverseOrder {
    private final ArrayDeque<Customer> stackCustomerReverseOrder;
    // todo: 2. надо реализовать методы этого класса
    // надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    public CustomerReverseOrder() {
        stackCustomerReverseOrder = new ArrayDeque<Customer>();
    }

    public void add(Customer customer) {
        if (stackCustomerReverseOrder != null && customer != null) {
            stackCustomerReverseOrder.push(customer);
        }
    }

    public Customer take() {
        if (!stackCustomerReverseOrder.isEmpty()) return stackCustomerReverseOrder.pop();
        else return null; // это "заглушка, чтобы скомилировать"
    }
}
