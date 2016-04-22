package com.careydevelopment.weatherchecker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.careydevelopment.ipreader.IPLocation;
import com.careydevelopment.ipreader.IPLocationException;
import com.careydevelopment.ipreader.IPLocationReader;
import com.careydevelopment.weatherchecker.Weather;
import com.careydevelopment.weatherchecker.WeatherChecker;
import com.careydevelopment.weatherchecker.WeatherCheckerException;

@Controller
public class HomeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
    @RequestMapping("/")
    public String home(Model model) {    	    
    	model.addAttribute("homeActive", "active");
    	
    	try {
    		IPLocation ipl = IPLocationReader.getIPLocation();
    		Weather weather = WeatherChecker.getWeather(ipl.getPostal());
    		model.addAttribute("weather",weather);
    	} catch (IPLocationException ie) {
    		LOGGER.error("Problem determining location!",ie);
    	} catch (WeatherCheckerException we) {
    		LOGGER.error("Problem reading weather!",we);
    	}
    		
        return "index";
    }
        
}
