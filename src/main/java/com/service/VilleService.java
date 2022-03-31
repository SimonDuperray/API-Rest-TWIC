package com.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Ville;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;

    public Optional<Ville> getVille(final String codeCommune) {
        return villeRepository.findById(codeCommune);
    }
    
    public List<Ville> getVilles(final String codePostal) {
    	if (codePostal == null) {
    		return villeRepository.findAll();
    	} else {
    		return villeRepository.findByCodePostal(codePostal);
    	}
    }

    public void createVille(Ville ville) throws SQLException {
    	if (villeRepository.existsById(ville.getCodeCommune())) {
    		throw new SQLException("Primary key already exists!");
    	}
		villeRepository.save(ville);
    }

    public void replaceVille(final Ville ville) throws ObjectNotFoundException {
    	if (!villeRepository.existsById(ville.getCodeCommune())) {
    		throw new ObjectNotFoundException("Record does not exists!");
    	}
		villeRepository.save(ville);
    }
    
    public void deleteVille(final String id) throws ObjectNotFoundException {
    	if (!villeRepository.existsById(id)) {
    		throw new ObjectNotFoundException("Record does not exists!");
    	}
		villeRepository.deleteById(id);
    }
}