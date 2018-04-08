package pl.sternik.pb.weekend.repositories.springdata;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.sternik.pb.weekend.entities.Samochod;

@Repository
public interface SamochodRepository extends JpaRepository<Samochod, Long>{

	Samochod findByVin(Long id);
	//List<Samochod> findCrashed();
}
