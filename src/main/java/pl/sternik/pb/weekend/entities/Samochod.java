package pl.sternik.pb.weekend.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Samochod {

	@Id
	private Long vin;
	
	@NotNull
    private String model;
	@NotNull
	private String marka;
	@NotNull
	private String wersja;
	@NotNull
	private String waluta;	
	@NotNull
	private BigDecimal cena;
	@NotNull
	private Date data;
	@NotNull
	private String krajPochodzenia;
	@Enumerated(EnumType.STRING)
	private Status status;

	
	
	public static Samochod produceSamochod(long vin,  String marka, String model, String wersja, String waluta,
										BigDecimal cena, Date data, String krajPochodzenia,
										Status status) {
		Samochod m = new Samochod();
		m.vin = vin;
		m.model = model;
		m.marka = marka;
		m.wersja = wersja;
		m.waluta = waluta;
		m.cena = cena;
		m.data = data;
		m.krajPochodzenia = krajPochodzenia;
		m.status = status;
		return m;
	}

	public String getModel() {
		return model;
	}

	public String getMarka() {
		return marka;
	}

	public String getWersja() {
		return wersja;
	}

	public String getWaluta() {
		return waluta;
	}

	public BigDecimal getCena() {
		return cena;
	}

	public Date getData() {
		return data;
	}

	public String getKrajPochodzenia() {
		return krajPochodzenia;
	}

	public Status getStatus() {
		return status;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public void setWersja(String wersja) {
		this.wersja = wersja;
	}

	public void setWaluta(String waluta) {
		this.waluta = waluta;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setKrajPochodzenia(String krajPochodzenia) {
		this.krajPochodzenia = krajPochodzenia;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cena == null) ? 0 : cena.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((krajPochodzenia == null) ? 0 : krajPochodzenia.hashCode());
		result = prime * result + ((marka == null) ? 0 : marka.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + (int) (vin ^ (vin >>> 32));
		result = prime * result + ((waluta == null) ? 0 : waluta.hashCode());
		result = prime * result + ((wersja == null) ? 0 : wersja.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Samochod other = (Samochod) obj;
		if (cena == null) {
			if (other.cena != null)
				return false;
		} else if (!cena.equals(other.cena))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (krajPochodzenia == null) {
			if (other.krajPochodzenia != null)
				return false;
		} else if (!krajPochodzenia.equals(other.krajPochodzenia))
			return false;
		if (marka == null) {
			if (other.marka != null)
				return false;
		} else if (!marka.equals(other.marka))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (status != other.status)
			return false;
		if (vin != other.vin)
			return false;
		if (waluta == null) {
			if (other.waluta != null)
				return false;
		} else if (!waluta.equals(other.waluta))
			return false;
		if (wersja == null) {
			if (other.wersja != null)
				return false;
		} else if (!wersja.equals(other.wersja))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Moneta [model=" + model + ", marka=" + marka + ", wersja=" + wersja + ", waluta="
				+ waluta + ", cena=" + cena + ", data=" + data + ", krajPochodzenia="
				+ krajPochodzenia + ", status=" + status + "]";
	}

	public Long getVin() {
		return vin;
	}

	public void setVin(Long vin) {
		this.vin = vin;
	}


}
