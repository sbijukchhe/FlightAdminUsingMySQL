package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    FlightRepository flightRepository;

    @RequestMapping("/")
    public String homepage(){
       return "index";
    }

    @RequestMapping("/list")
    public String flightList(Model model){
        model.addAttribute("flights",flightRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String addFlight(Model model){
        model.addAttribute("flight", new Flight());
        return "flightform";
    }
    /*@PostMapping("/processsearch")
    public String searchResult(Model model,@RequestParam(name="search") String search) {
        model.addAttribute("flights", flightRepository.findByAirlineName(search));
        return "searchlist";
    }*/

    @PostMapping("/processsearch")
    public String searchResult(Model model,@RequestParam(name="search")String search ,
                                            @RequestParam(name="category") String category) {
        if (category.equals("1")) {
            model.addAttribute("flights", flightRepository.findByOriginFromContainingIgnoreCase(search));
        }
        else if (category.equals("2")) {
            model.addAttribute("flights", flightRepository.findByDestinationToContainingIgnoreCase(search));
        }
        else if(category.equals("3")) {
            model.addAttribute("flights", flightRepository.findByAirlineNameContainingIgnoreCase(search));
        }
    return "searchlist";
    }

    @PostMapping("/processflight")
    public String processForm(@ModelAttribute Flight flight, @RequestParam(name = "date")
            String date){
        String pattern = "yyyy-MM-dd'T'hh:mm";
        try {
            String formattedDate = date.substring(1,date.length()-1);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date realDate = simpleDateFormat.parse(formattedDate);
            flight.setDate(realDate);
        }
        catch (java.text.ParseException e){
            e.printStackTrace();
        }

        flightRepository.save(flight);
        return "redirect:/list";
    }

    @RequestMapping("/detail/{id}")
    public String showflight(@PathVariable("id") long id, Model model) {
        model.addAttribute("flight", flightRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateFlight(@PathVariable("id") long id, Model model) {
        model.addAttribute("flight", flightRepository.findById(id).get());
        return "flightform";
    }

    @RequestMapping("/delete/{id}")
    public String deleteFlight(@PathVariable("id")long id) {
       flightRepository.deleteById(id);
        return "redirect:/list";
    }
}
