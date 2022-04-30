package com.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.City;

@Repository
public interface VilleRepository extends JpaRepository<City, String> {
   List<City> findByCodePostalOrderByNomCommune(String codePostal);
   Page<City> findByCodePostalOrderByNomCommune(String codePostal, Pageable pageable);
   List<City> findByOrderByNomCommune();
   Page<City> findByOrderByNomCommune(Pageable pageable);
}