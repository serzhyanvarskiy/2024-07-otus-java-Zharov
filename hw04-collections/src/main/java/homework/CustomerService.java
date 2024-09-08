package homework;

import java.util.*;

@SuppressWarnings({"java:S1186", "java:S1135", "java:S1172"}) // при выполнении ДЗ эту аннотацию надо удалить
public class CustomerService {
    private final TreeMap<Customer, String> mapCustomerService;

    public CustomerService() {
        mapCustomerService = new TreeMap<Customer, String>(Comparator.comparingLong(Customer::getScores));
    }

    private Map.Entry<Customer, String> getClone(Map.Entry<Customer, String> src) {
        if (src == null) return null;
        String data = src.getValue() != null ? new String(src.getValue()) : null;
        return Map.entry(new Customer(src.getKey()), data);
    }
    // todo: 3. надо реализовать методы этого класса
    // важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        // Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        if (mapCustomerService == null || mapCustomerService.isEmpty()) return null;
        Map.Entry<Customer, String> res = mapCustomerService.firstEntry();
        return getClone(res);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        if (mapCustomerService == null || mapCustomerService.isEmpty()) return null;
        Map.Entry<Customer, String> res = mapCustomerService.higherEntry(customer);
        return getClone(res);
    }

    public void add(Customer customer, String data) {
        if (mapCustomerService == null) return;
        String put = mapCustomerService.put(customer, data);
    }
}
