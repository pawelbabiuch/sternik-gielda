package pl.sternik.pb.weekend.services;

import java.util.List;
import java.util.Optional;

import pl.sternik.pb.weekend.entities.Samochod;


public interface GieldaService {
    List<Samochod> findAll();

    List<Samochod> findAllToSell();
    
    List<Samochod> findCrashed();

    Optional<Samochod> findByVin(long vin);

    Optional<Samochod> create(Samochod samochod);

    Optional<Samochod> edit(Samochod samochod);

    Optional<Boolean> deleteByVin(long vin);

    List<Samochod> findLatest3();
}