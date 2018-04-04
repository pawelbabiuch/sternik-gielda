package pl.sternik.pb.weekend.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.pb.weekend.entities.Samochod;
import pl.sternik.pb.weekend.entities.Status;


@Service
@Qualifier("lista")
public class SamochodyRepositoryJ8Impl implements SamochodyRepository {

    private List<Samochod> samochody = new ArrayList<Samochod>()
    {
	    private static final long serialVersionUID = 1L;
	    {
	        add(Samochod.produceSamochod(1L, "Opel", "Astra", "H", "PLN", BigDecimal.valueOf(20000), new Date(), "Polska", Status.NOWA));
	    }
	};
    
    
    //    		

    @Override
    public List<Samochod> findAll() {
        return this.samochody;
    }

    @Override
    public Samochod readByVin(long vin) throws NoSuchSamochodException {
        return this.samochody.stream().filter(p -> Objects.equals(p.getVin(), vin)).findFirst()
                .orElseThrow(NoSuchSamochodException::new);
    }

    @Override
    public Samochod create(Samochod samochod) {
        if (!samochody.isEmpty()) {
            samochod.setVin(this.samochody.stream().mapToLong(p -> p.getVin()).max().getAsLong() + 1);
        } else {
            samochod.setVin(1L);
        }
        this.samochody.add(samochod);
        return samochod;
    }

    @Override
    public Samochod update(Samochod samochod) throws NoSuchSamochodException {
        for (int i = 0; i < this.samochody.size(); i++) {
            if (Objects.equals(this.samochody.get(i).getVin(), samochod.getVin())) {
                this.samochody.set(i, samochod);
                return samochod;
            }
        }
        throw new NoSuchSamochodException("Nie ma takiej Monety: " + samochod.getVin());
    }

    @Override
    public void deleteByVin(long vin) throws NoSuchSamochodException {
        for (int i = 0; i < this.samochody.size(); i++) {
            if (Objects.equals(this.samochody.get(i).getVin(), vin)) {
                this.samochody.remove(i);
            }
        }
        throw new NoSuchSamochodException("Nie ma takiego samochodu: " + vin);
    }

}
