package com.stackroute.goplaces.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.goplaces.exception.PlaceAlreadyExistsException;
import com.stackroute.goplaces.exception.PlaceNotExistsException;
import com.stackroute.goplaces.model.Place;
import com.stackroute.goplaces.repository.PlaceRepository;

@Service
public class GoPlacesServices {

	@Autowired
	private PlaceRepository pr;
	
	
//---------------------------add-----------------------------------------------------
	public void addPlaceService (Place p)throws PlaceAlreadyExistsException
	{
		
		
		if(pr.exists(p.getId())) {
			throw new PlaceAlreadyExistsException("Id "+p.getId()+" already exists");
		} 		
	else
		pr.save(p);
	
		}
//---------------------------update-----------------------------------------------------	
	public void updatePlaceService(Place p) throws PlaceNotExistsException
	{
		if(pr.exists(p.getId()) == false) {
			throw new PlaceNotExistsException("Id "+p.getId()+" does not exists");
		} 		
	else
		pr.save(p);
	}
	
//------------------------------------------delete----------------------------------------
	public void deletePlaceService(int id)throws PlaceNotExistsException
	{
		if(pr.exists(id) == false) {
			throw new PlaceNotExistsException("Id "+id+" does not exists");
		} 		
	else
		pr.delete(id);
	}
//-------------------------------find----by---id----------------------	
	public Place getTopicService(int id)
	{
		return pr.findOne(id);
	}
//------------------------------------getall--------------------------------	
	public List<Place> getAllPlaces()
	{
		List<Place> l = new  ArrayList();
		pr.findAll().forEach(l::add);
		return  l;
	}
}
