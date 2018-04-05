package pl.sternik.pb.weekend.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import pl.sternik.pb.weekend.entities.Samochod;
import pl.sternik.pb.weekend.entities.Status;
import pl.sternik.pb.weekend.repositories.SamochodAlreadyExistsException;
import pl.sternik.pb.weekend.repositories.SamochodyRepository;
import pl.sternik.pb.weekend.repositories.NoSuchSamochodException;


@Service
@Primary
public class GieldaServiceJ8Impl implements GieldaService {

    @Autowired
    @Qualifier("lista")
    private SamochodyRepository samochody;

    @Override
    public List<Samochod> findAll() {
        return samochody.findAll();
    }

    @Override
    public List<Samochod> findLatest3() {
        return samochody.findAll().stream().sorted((a, b) -> b.getData().compareTo(a.getData())).limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Samochod> findByVin(long vin) {
        try {
            return Optional.of(samochody.readByVin(vin));
        } catch (NoSuchSamochodException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Samochod> create(Samochod samochod) {
        try {
            return Optional.of(samochody.create(samochod));
        } catch (SamochodAlreadyExistsException e) {
            try {
                return Optional.of(samochody.readByVin(samochod.getVin()));
            } catch (NoSuchSamochodException e1) {
                return Optional.empty();
            }
        }

    }

    @Override
    public Optional<Samochod> edit(Samochod samochod) {
        try {
            return Optional.of(samochody.update(samochod));
        } catch (NoSuchSamochodException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> deleteByVin(long vin) {
        try {
            samochody.deleteByVin(vin);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchSamochodException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Samochod> findAllToSell() {
        return samochody.findAll().stream().filter(p -> Objects.equals(p.getStatus(), Status.UZYWANA))
                .collect(Collectors.toList());
    }

	@Override
	public List<Samochod> findCrashed() {
        return samochody.findAll().stream().filter(p -> Objects.equals(p.getStatus(), Status.USZKODZONA))
                .collect(Collectors.toList());
	}
}
