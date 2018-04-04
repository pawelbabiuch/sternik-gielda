package pl.sternik.pb.weekend.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import pl.sternik.pb.weekend.entities.Samochod;
import pl.sternik.pb.weekend.entities.Status;


@Repository
@Qualifier("tablica")
public class ProstaBazaDanych implements SamochodyRepository {

    private Samochod[] baza;

    public ProstaBazaDanych() {
        baza = new Samochod[15];
        
        baza[0] = Samochod.produceSamochod(1L, "Opel", "Astra", "H", "PLN", BigDecimal.valueOf(20000), new Date(), "Polska", Status.NOWA);
        baza[1] = Samochod.produceSamochod(2L, "Mazda", "6", "II", "PLN", BigDecimal.valueOf(18000), new Date(), "Szwecja", Status.UZYWANA);

    }

    public ProstaBazaDanych(int rozmiarBazy) {
        baza = new Samochod[rozmiarBazy];
    }

    @Override
    public Samochod create(Samochod samochod) throws SamochodAlreadyExistsException{
        if (samochod != null && baza[(int)samochod.getVin()] != null) {
            if (samochod.getVin() == (baza[(int)samochod.getVin()].getVin())) {
                throw new SamochodAlreadyExistsException("Już jest samochod o takim numerze vin.");
            }
        }
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] == null) {
                baza[i] = samochod;
                samochod.setVin((long) i);
                return samochod;
            }
        }
        throw new RuntimeException("Brak miejsca w tablicy");
    }

    @Override
    public void deleteByVin(long vin) throws NoSuchSamochodException {
        if (!sprawdzPoprawnoscVin(vin)) {
            throw new NoSuchSamochodException("Niepoprawny numer vin");
        }

        baza[(int)vin] = null;
    }

    @Override
    public Samochod update(Samochod samochod) throws NoSuchSamochodException {
        if (!sprawdzPoprawnoscVin(samochod.getVin())) {
            throw new NoSuchSamochodException("Niepoprawny numer vin");
        }

        Samochod m = baza[(int)samochod.getVin()];
        if (m == null) {
            throw new NoSuchSamochodException("Brak takiego samochodu.");
        } else {
            baza[(int)samochod.getVin()] = samochod;
        }
        return samochod;
    }

    @Override
    public Samochod readByVin(long vin) throws NoSuchSamochodException {
        if (!sprawdzPoprawnoscVin(vin) || czyWolne(vin)) {
            throw new NoSuchSamochodException();
        }
        return baza[(int)vin];
    }

    private boolean czyWolne(long vin) {
        if(baza[(int) vin]!= null)
            return false;
        return true;
    }

    @Override
    public List<Samochod> findAll() {
        List<Samochod> tmp = new ArrayList<>();
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] != null)
                tmp.add(baza[i]);
        }
        return tmp;
    }

    public void wyswietlBaze() {
        for (int i = 0; i < baza.length; i++) {
            System.out.println("" + i + ":" + baza[i]);
        }
    }

    private boolean sprawdzPoprawnoscVin(long vin) {
        if (vin < 0 || vin >= baza.length) {
            System.out.println("Zły numer vin");
            return false;
        }
        return true;
    }

}
