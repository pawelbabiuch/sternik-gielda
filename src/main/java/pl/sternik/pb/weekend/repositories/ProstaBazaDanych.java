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
        
        Samochod s = new Samochod();
        s.setVin(1L);
        s.setMarka("Opel");
        s.setModel("Astra");
        s.setWersja("H");
        s.setWaluta("PLN");
        s.setCena(BigDecimal.valueOf(15234));
        s.setData(new Date());
        s.setKrajPochodzenia("Polska");
        s.setStatus(Status.NOWA);
        baza[0] = s;
        
        s = new Samochod();
        s.setVin(2L);
        s.setMarka("Mazda");
        s.setModel("6");
        s.setWersja("II");
        s.setWaluta("PLN");
        s.setCena(BigDecimal.valueOf(21300));
        s.setData(new Date());
        s.setKrajPochodzenia("Szwecja");
        s.setStatus(Status.UZYWANA);
        baza[1] = s;
        
      
    }

    public ProstaBazaDanych(int rozmiarBazy) {
        baza = new Samochod[rozmiarBazy];
    }

    @Override
    public Samochod create(Samochod samochod) throws SamochodAlreadyExistsException{
        if (samochod != null && baza[samochod.getVin().intValue()] != null) {
            if (samochod.getVin() == (baza[samochod.getVin().intValue()].getVin())) {
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

        Samochod m = baza[samochod.getVin().intValue()];
        if (m == null) {
            throw new NoSuchSamochodException("Brak takiego samochodu.");
        } else {
            baza[samochod.getVin().intValue()] = samochod;
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
