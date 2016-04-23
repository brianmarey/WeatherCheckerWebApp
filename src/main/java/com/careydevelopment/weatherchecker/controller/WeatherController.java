package com.careydevelopment.weatherchecker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.careydevelopment.ipreader.IPLocation;
import com.careydevelopment.ipreader.IPLocationException;
import com.careydevelopment.ipreader.IPLocationReader;
import com.careydevelopment.weatherchecker.Weather;
import com.careydevelopment.weatherchecker.WeatherChecker;
import com.careydevelopment.weatherchecker.WeatherCheckerException;

@Controller
public class WeatherController {
	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);
	
    @RequestMapping("/weather")
    public String weather(@RequestParam(value="postal", required=true) String postal, Model model) {    	    
    	
    	try {
    		Weather weather = WeatherChecker.getWeather(postal);
    		model.addAttribute("weather",weather);
    	} catch (WeatherCheckerException we) {
    		LOGGER.error("Problem reading weather!",we);
    	}
    		
        return "weather :: weatherfragment";
    }
        
}
