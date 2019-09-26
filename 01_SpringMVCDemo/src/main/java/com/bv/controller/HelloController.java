package com.bv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bv.model.Reservation;
import com.bv.servive.ReservationService;

@Controller
public class HelloController {
	private ReservationService reservationService;
	
	@Autowired(required = true)
	@Qualifier("reservationService")
	public void setReservationService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	@RequestMapping(value = "/reservation", method = RequestMethod.GET)
	public String listPersons(Model model) {
		model.addAttribute("person", new Reservation());
		model.addAttribute("listPersons", this.reservationService.getAllPassengers());
		System.out.println(reservationService.getAllPassengers());
		return "ResrvationForm";
	}
	/*
	 * @RequestMapping("/hello") public ModelAndView getData(@RequestParam("name")
	 * String name, @RequestParam("age") int age) { ModelAndView mv = new
	 * ModelAndView("hello"); mv.addObject("name", name); mv.addObject("age", age);
	 * return mv; }
	 */
	@RequestMapping( value = "/reservation/add", method = RequestMethod.POST)
	public String getData(@ModelAttribute("reservation") Reservation reservation) {
		if(reservation.getId()==0)
		{
			reservationService.addPassenger(reservation);
		}
		else
		{
			reservationService.updatePassenger(reservation);
		}
		return "reservation";
	}
	@RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
		
        this.reservationService.removePassenger(id);
        return "redirect:/reservation";
    }
 
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.reservationService.getPassengerById(id));
        model.addAttribute("listPersons", this.reservationService.getAllPassengers());
        return "reservation";
    }
}
