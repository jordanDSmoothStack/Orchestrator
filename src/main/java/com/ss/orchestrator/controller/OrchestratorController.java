/**
 * 
 */
package com.ss.orchestrator.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import com.ss.orchestrator.model.Airports;

/**
 * @author jordandivina
 *
 */

@RestController
public class OrchestratorController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping(path= {"/utopia/airports", "/utopia/airports/", "/utopia/airports/list", "/utopia/airports/airport"}, produces= {"application/json", "application/xml"})
	public ResponseEntity<String> getAllAirports(@RequestHeader Map<String, String> requestHeader) {
		HttpHeaders headers = new HttpHeaders();
		headers = OrchestratorController.setAcceptType(requestHeader, headers);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/utopia/airports/", HttpMethod.GET, entity, String.class);
		return response;
	}
	
	@GetMapping(path = {"/utopia/airports/{AirportID}", "/utopia/airports/airport/{AirportID}"}, produces= {"application/json", "application/xml"})
	public ResponseEntity<String> getSpecificAirport(@PathVariable("AirportID") String airportId, @RequestHeader Map<String, String> requestHeader) {
		HttpHeaders headers = new HttpHeaders();
		headers = OrchestratorController.setAcceptType(requestHeader, headers);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/utopia/airports/"+ airportId, HttpMethod.GET, entity, String.class);
		return response;
	}
	
	@PostMapping(path = {"/utopia/airports/airport"}, consumes= {"application/json", "application/xml"}, produces= {"application/json", "application/xml"})
	public ResponseEntity<String> addAirport(@RequestBody Airports newAirport, @RequestHeader Map<String, String> requestHeader) {
		HttpHeaders headers = new HttpHeaders();
		headers = OrchestratorController.setAcceptType(requestHeader, headers);
		headers = OrchestratorController.setContentType(requestHeader, headers);
		HttpEntity<Airports> entity = new HttpEntity<Airports>(newAirport, headers);
		try {
			ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/utopia/airports/airport", HttpMethod.POST, entity, String.class);
			return response;
		}
		catch (HttpClientErrorException.BadRequest e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not add " + newAirport.toString() + ". Please check your request again.");
		}
	}
	
	@PutMapping(path = {"/utopia/airports/airport"}, consumes= {"application/json", "application/xml"}, produces= {"application/json", "application/xml"})
	public ResponseEntity<String> updateAirport(@RequestBody Airports newAirport, @RequestHeader Map<String, String> requestHeader) {
		HttpHeaders headers = new HttpHeaders();
		headers = OrchestratorController.setAcceptType(requestHeader, headers);
		headers = OrchestratorController.setContentType(requestHeader, headers);
		HttpEntity<Airports> entity = new HttpEntity<Airports>(newAirport, headers);
		try {
			ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/utopia/airports/airport", HttpMethod.PUT, entity, String.class);
			return response;
		}
		catch (HttpClientErrorException.BadRequest e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not update " + newAirport.toString() + ". Please check your request again.");
		}
	}
	
	@DeleteMapping(path = {"/utopia/airports/airport"}, consumes= {"application/json", "application/xml"}, produces= {"application/json", "application/xml"})
	public ResponseEntity<String> deleteAirport(@RequestBody Airports newAirport, @RequestHeader Map<String, String> requestHeader) {
		HttpHeaders headers = new HttpHeaders();
		headers = OrchestratorController.setAcceptType(requestHeader, headers);
		headers = OrchestratorController.setContentType(requestHeader, headers);
		HttpEntity<Airports> entity = new HttpEntity<Airports>(newAirport, headers);
		try {
			ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/utopia/airports/airport", HttpMethod.DELETE, entity, String.class);
			return response;
		}
		catch (HttpClientErrorException.BadRequest e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete " + newAirport.toString() + ". Please check your request again.");
		}
		catch (HttpServerErrorException.InternalServerError e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error with processing " + newAirport.toString());
		}
	}
	
	public static HttpHeaders setAcceptType(Map<String, String> requestHeader, HttpHeaders header) {
		String acceptHeader = requestHeader.get("accept");
		if (acceptHeader.equals("application/xml")) {
			header.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
		}
		else {
			header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		}
		return header;
	}
	
	public static HttpHeaders setContentType(Map<String, String> requestHeader, HttpHeaders header) {
		String contentType = requestHeader.get("content-type");
		if (contentType.equals("application/xml")) {
			header.setContentType(MediaType.APPLICATION_XML);
		}
		else {
			header.setContentType(MediaType.APPLICATION_JSON);
		}
		return header;
	}
}
