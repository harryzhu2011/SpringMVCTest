package com.aot.webservice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.aot.webservice.domain.Customer;
import com.aot.webservice.domain.CustomerList;
import com.aot.webservice.service.CustomerService;

@Controller
@RequestMapping("customer")
public class CustomerController {
   
    @Autowired
    CustomerService customerService;
   
    // Return all customers
    // Use view
/*    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView getAllCustomers(ModelMap model)
    {
        List<Customer> customers = customerService.getCustomers();
        CustomerList customerList = new CustomerList(customers);
        return new ModelAndView("customerView", "customers", customerList);
    }*/
    
    // Use @ResponseBody
/*    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody CustomerList getAllCustomers(ModelMap model)
    {
        List<Customer> customers = customerService.getCustomers();
        CustomerList customerList = new CustomerList(customers);
        return customerList;
    }*/
    
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody CustomerList getAllCustomers(ModelMap model)
    {
        List<Customer> customers = customerService.getCustomers();
        CustomerList customerList = new CustomerList(customers);
        return customerList;
    }
    

/*    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ModelAndView getCustomer(@PathVariable Long id){
        Customer customer = customerService.getCustomer(id);
        return new ModelAndView("customerView", "customer", customer);

    }*/
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces="application/xml") // application/json
    public @ResponseBody Customer getCustomer(@PathVariable Long id){
        Customer customer = customerService.getCustomer(id);
        return customer;

    }
   
    @RequestMapping(method = RequestMethod.POST)
    public View addCustomer(@RequestBody Customer customer)
    {
        customerService.saveCustomer(customer);
        return new RedirectView("/customerView/"+ customer.getId());
    }
}
