package service;

import model.Customer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class CustomerService {

    private static CustomerService customerService;

    public Collection<Customer> customers = new HashSet<>();

    public static CustomerService getInstance(){
        if(customerService == null){
            customerService = new CustomerService();
        }
        return customerService;
    }

    public void  addCustomer(String email, String firstName, String lastName){

        Customer newCustomer = new Customer(firstName,lastName,email);
        customers.add(newCustomer);

    }
    public Customer getCustomer(String customerEmail) {
        for (Customer customer : customers) {
            if (customerEmail.equals(customer.getEmail())) {
                System.out.println(customerEmail);
                return customer;
            }
        }
        return null;
    }

    public Collection<Customer> getAllCustomers() {
        if(!customers.isEmpty()){
            Iterator<Customer> customerIterator = customers.iterator();
            while(customerIterator.hasNext()){
                System.out.println(customerIterator.next());
            }
        }
        return customers;
    }


}
