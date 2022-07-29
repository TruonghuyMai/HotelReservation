package com.skillstorms.backend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorms.backend.model.CityState;
import com.skillstorms.backend.model.Hotel;
import com.skillstorms.backend.repository.HotelRepository;

@RestController // @RestController = @Controller + @ResponseBody
@CrossOrigin(origins = "*")
@RequestMapping(path="/hotel") // This means URL's start with /demo (after Application path)
public class HotelController {
  @Autowired // This means to get the bean called userRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private HotelRepository hotelRepository;

  @PostMapping
  public Hotel create(@Valid @RequestBody Hotel hotel) {
	return hotelRepository.save(hotel);
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Hotel> getAllCustomers() {
    return hotelRepository.findAll();
  }
  
  @GetMapping(path="/test/{hotelId}")
  public Hotel findByID(@PathVariable (value = "hotelId") int hotelID) {
	  	Optional<Hotel> hotel = hotelRepository.findById(hotelID);
		return hotel.isPresent() ? hotel.get() : null;
  }
  
  @GetMapping(path="/statecity")
  public @ResponseBody ArrayList<CityState> getStateAndCity() {
	  Iterable<Hotel> hotel = hotelRepository.findAll();
	    HashMap<String, String> cities = new HashMap<String, String>();

      int n = 10; 

	  ArrayList<CityState> arr = new ArrayList<CityState>(n);	  
	  hotel.forEach((element) -> { 
		  CityState temp = new CityState();
		  String city = element.getCity();
		  String state = element.getState();
		  String key = city+state;
		  if ( cities.put(key, city) == null) {
			  temp.setCity(city);
			  temp.setState(state);
			  arr.add(temp);
		  }

	  });
	  return arr;
  }
}

