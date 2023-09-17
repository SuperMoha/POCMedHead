package mshopital.dao;

import mshopital.model.Hopital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HopitalRepository extends JpaRepository<Hopital, Integer> {

}
