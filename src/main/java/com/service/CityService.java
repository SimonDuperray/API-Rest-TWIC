package com.service;

import java.sql.SQLException;
import java.util.Optional;
import com.dto.CityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.model.City;
import com.repository.VilleRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CityService {
	@Autowired
	private VilleRepository villeRepository;

	public Optional<City> getCity(final String codeCommune) {
		return villeRepository.findById(codeCommune);
	}

	public Object getCities(final String codePostal, int page, int size) {
		if(codePostal!=null) {
			if(size!=-1 && page!=-1) {
				return villeRepository.findByCodePostalOrderByNomCommune(
						codePostal,
						PageRequest.of(page-1, size)
				);
			} else if(size!=-1 && page==-1) {
				return villeRepository.findByCodePostalOrderByNomCommune(
						codePostal,
						PageRequest.of(0, size)
				);
			} else {
				return villeRepository.findByCodePostalOrderByNomCommune(codePostal);
			}
		} else {
			if(size!=-1 && page!=-1){
				return villeRepository.findByOrderByNomCommune(
						PageRequest.of(page-1, size)
				);
			} else if(size!=-1 && page==-1) {
				return villeRepository.findByOrderByNomCommune(
						PageRequest.of(0, size)
				);
			} else {
				return villeRepository.findByOrderByNomCommune();
			}
		}
	}

	public void createCity(CityDTO villeDTO) throws SQLException {
		if(villeRepository.existsById(villeDTO.getCodeCommune())) {
			throw new SQLException("This city already exists!");
		}
		villeRepository.save(villeDTO.villeToEnt());
	}

	public void deleteCity(final String id) throws ObjectNotFoundException {
		if(!villeRepository.existsById(id)) {
			throw new ObjectNotFoundException("This city does not exist. So it cannot be deleted");
		}
		villeRepository.deleteById(id);
	}

	public void modifyCity(final CityDTO villeDTO) throws ObjectNotFoundException {
		if(!villeRepository.existsById(villeDTO.getCodeCommune())) {
			throw new ObjectNotFoundException("This city does not exist. So it cannot be modified!");
		}
		villeRepository.save(villeDTO.villeToEnt());
	}
}