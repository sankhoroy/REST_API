package com.stackroute.goplaces.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import com.stackroute.goplaces.model.Place;

public interface PlaceRepository extends MongoRepository<Place,Integer>{

}
