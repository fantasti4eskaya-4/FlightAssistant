package com.flightsearch.controllers;

import com.flightsearch.TicketSearchAmadeusService;
import com.flightsearch.data.Ticket;
import com.flightsearch.exeptions.NoTicketThisDataException;
import com.flightsearch.exeptions.WrongInputDataException;
import com.flightsearch.utils.DataGenerator;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class TicketController {

    private TicketSearchAmadeusService ticketSearch;

    @Autowired
    public void setTicketSearchAmadeusService(TicketSearchAmadeusService ticketSearchAmadeusService) {
        this.ticketSearch = ticketSearchAmadeusService;
    }

    @RequestMapping(value = "/historicalWeather", method = RequestMethod.POST)
    public List<Ticket[]> getWeather(@RequestBody JSONObject flightData) throws IOException, ParseException, NoTicketThisDataException, WrongInputDataException {
        System.out.println(flightData);
        String originIATA = DataGenerator.getIATA("Москва");
        String departureIATA = DataGenerator.getIATA("Казань");
        LocalDate originDate = LocalDate.now().plusDays(3);
        return ticketSearch.getOffers(originIATA, departureIATA, originDate,null);
    }
}
