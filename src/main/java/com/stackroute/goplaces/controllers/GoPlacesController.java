package com.stackroute.goplaces.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.goplaces.exception.PlaceAlreadyExistsException;
import com.stackroute.goplaces.exception.PlaceNotExistsException;
import com.stackroute.goplaces.model.Place;
import com.stackroute.goplaces.services.GoPlacesServices;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/v1.0/placeservice")
@Api(value="onlineplacesearch", description="Operations pertaining to  GoPlaces")
public class GoPlacesController {
	
	@Autowired
	private GoPlacesServices ps;
/*-----------------------------------------------------------------------------
  
 
    @CrossOrigin(origins = "https://localhoast:<port>")
	
	if cross origin  error happens please enter server to allow here.
	e.g @CrossOrigin(origins = "https://localhoast:8080")
--------------------------------------------------------------------------------------*/	

	
//----------------------------POST for add new place--------------------------------------------------

	@RequestMapping(value="/place", method = RequestMethod.POST)
	public ResponseEntity addNewPlace(RequestEntity<Place>  p) throws Exception {

	try {

			if(p.getBody().getFormatted_address() == null || p.getBody().getName() == null){
					return new ResponseEntity<String>("Insufficient parameter",HttpStatus.PARTIAL_CONTENT);
						
				}
			else
				{

					ps.addPlaceService(p.getBody());
					return new ResponseEntity<String>("User saved successfully",HttpStatus.OK);
					
				}
		}
	catch(PlaceAlreadyExistsException paee)
		{

			System.out.println(paee.getMessage());
			return new ResponseEntity<String>("Data already exists",HttpStatus.CONFLICT);
		}
    }
//--------------------------GET----- for-------getAllPlace-------------------------------
	@RequestMapping(value="/place",method = RequestMethod.GET)
	public ResponseEntity getAllPlaces() {
		if(ps.getAllPlaces() == null)
			return new ResponseEntity<String>("Database is empty",HttpStatus.PARTIAL_CONTENT);
		else
			return new ResponseEntity<List<Place>>(ps.getAllPlaces(),HttpStatus.OK);
		
		
	}
//---------------------------DELETE for deletePlaces----------------------------------------	
	@RequestMapping(value="/place/{id}",method = RequestMethod.DELETE)
	public ResponseEntity deletePlace(@PathVariable int id) {
		
	try {
		ps.deletePlaceService(id);
			return new ResponseEntity<String>("User deleted successfully",HttpStatus.OK);
	}
	catch(PlaceNotExistsException ee) {
		System.out.println(ee.getMessage());
		return new ResponseEntity<String>("Data does not exists",HttpStatus.NOT_FOUND);
	}
		
	}
//-----------------------------------------	PUT for update  -----------------------------------
	@RequestMapping(value="/place",method = RequestMethod.PUT)
	public ResponseEntity updatePlace(RequestEntity<Place>  p) {
		
		try {

			if(p.getBody().getFormatted_address() == null || p.getBody().getName() == null){
					return new ResponseEntity<String>("Insufficient parameter",HttpStatus.PARTIAL_CONTENT);
						
				}
			else
				{

					ps.updatePlaceService(p.getBody());
					return new ResponseEntity<String>("User saved successfully",HttpStatus.OK);
					
				}
		}
	catch(PlaceNotExistsException pnee)
		{

			System.out.println(pnee.getMessage());
			return new ResponseEntity<String>("Data does not exists",HttpStatus.NOT_FOUND);
		}
		
		
	}
	
//-----------------------------default----------------------------------------------------
	@RequestMapping()
	public ResponseEntity defaultMapping() {
		
			String  msg = "Request not found";
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		
		
	}
	
	
	
}
