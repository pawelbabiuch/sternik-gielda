package pl.sternik.pb.weekend.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.pb.weekend.entities.Samochod;
import pl.sternik.pb.weekend.repositories.NoSuchSamochodException;
import pl.sternik.pb.weekend.repositories.springdata.SamochodRepository;



@Service
@Qualifier("spring-data")
public class GieldaServiceJPAImpl implements GieldaService {

    @Autowired
    private SamochodRepository bazaDanych;

    @Override
    public List<Samochod> findAll() {
        List<Samochod> l = new ArrayList<>();
        for (Samochod item : bazaDanych.findAll()) {
            l.add(item);
        }
        return l;
    }

    @Override
    public List<Samochod> findAllToSell() {
        List<Samochod> l = new ArrayList<>();
        for (Samochod item : bazaDanych.findAll()) {
            l.add(item);
        }
        return l;
    }

    @Override
    public Optional<Samochod> findByVin(Long id) {
        return Optional.ofNullable(bazaDanych.findByVin(id));
    }

    @Override
    public Optional<Samochod> create(Samochod samochod) {
        return Optional.of(bazaDanych.save(samochod));
    }

    @Override
    public Optional<Samochod> edit(Samochod samochod) {
        return Optional.of(bazaDanych.save(samochod));
    }

    @Override
    public Optional<Boolean> deleteByVin(Long id) {
			bazaDanych.delete(id);
			return Optional.of(Boolean.TRUE);
    }

    @Override
    public List<Samochod> findLatest3() {
        return Collections.emptyList();
    }

	@Override
	public List<Samochod> findCrashed() {
		// TODO Auto-generated method stub
		return null;
	}

}