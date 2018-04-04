package pl.sternik.pb.weekend.repositories;

import java.util.List;

import pl.sternik.pb.weekend.entities.Samochod;


public interface SamochodyRepository {
    Samochod create(Samochod samochod) throws SamochodAlreadyExistsException;
    Samochod readByVin(long vin) throws NoSuchSamochodException;
    Samochod update(Samochod samochod) throws NoSuchSamochodException;
    void deleteByVin(long vin) throws NoSuchSamochodException;
    List<Samochod> findAll();
}