package io.pivotal.controller;

import org.apache.geode.cache.client.ClientCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.pivotal.domain.Customer;
import io.pivotal.repo.CustomerRepository;

import java.util.Iterator;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepository repository;
	
	@Autowired
	private ClientCache clientCache;
	
	Fairy fairy = Fairy.create();
	
	@RequestMapping(method = RequestMethod.GET, path = "/create")
	@ResponseBody
	public String create() throws Exception {

		Person person = fairy.person();
		Customer customer = new Customer(person.passportNumber(), person.firstName(), person.lastName());
		repository.save(customer);

		return "New Customer Info Created! <Customer>: " + customer;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/show")
	@ResponseBody
	public String show() throws Exception {
		String resultString = "";
		
		Iterator<?> iter = repository.findAll().iterator();		
		while (iter.hasNext()) {
			resultString += iter.next().toString() + "<br/>";
		}

		return resultString;
	}
	
}
