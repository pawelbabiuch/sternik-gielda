package pl.sternik.pb.weekend.services;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.pb.weekend.entities.Samochod;
import pl.sternik.pb.weekend.entities.Status;
import pl.sternik.pb.weekend.repositories.SamochodAlreadyExistsException;
import pl.sternik.pb.weekend.repositories.SamochodyRepository;
import pl.sternik.pb.weekend.repositories.NoSuchSamochodException;


@Service
@Qualifier("tablica")
public class GieldaServiceImpl implements GieldaService {

    @Autowired
   // @Qualifier("tablica")
    @Qualifier("lista")
    private SamochodyRepository bazaDanych;

    @Override
    public List<Samochod> findAll() {
        return bazaDanych.findAll();
    }

    @Override
    public List<Samochod> findAllToSell() {
    	   return bazaDanych.findAll()
			    			.stream()
			    			.filter(p -> Objects.equals(p.getStatus(), Status.UZYWANA))
			                .collect(Collectors.toList());
    }
    
    public List<Samochod> findCrashed(){
    	return bazaDanych.findAll()
    			.stream()
    			.filter(p -> Objects.equals(p.getStatus(), Status.USZKODZONA))
    			.collect(Collectors.toList());
    }

    @Override
    public Optional<Samochod> create(Samochod samochod) {
        try {
            return Optional.of(bazaDanych.create(samochod));
        } catch (SamochodAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Samochod> edit(Samochod samochod) {
        try {
            return Optional.of(bazaDanych.update(samochod));
        } catch (NoSuchSamochodException e) {
            return Optional.empty();
        }

    }

    @Override
    public List<Samochod> findLatest3() {
        //TODO: 
        return Collections.emptyList();
    }

	@Override
	public Optional<Samochod> findByVin(Long vin) {
        try {
            return Optional.of(bazaDanych.readByVin(vin));
        } catch (NoSuchSamochodException e) {
            return Optional.empty();
        }
	}

	@Override
	public Optional<Boolean> deleteByVin(Long vin) {
        try {
            bazaDanych.deleteByVin(vin);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchSamochodException e) {
            return Optional.of(Boolean.FALSE);
        }
	}

}
