package com.esprit.examen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.examen.entities.Cours;
import com.esprit.examen.repositories.CoursRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Service
public class CoursService implements ICoursService {
	private static final Logger l = LogManager.getLogger(CoursService.class); //journalisation
	@Autowired
	CoursRepository coursRepository;
	@Override
	public Long addCours(Cours cours) {
		l.info("Cours : "+cours.getDescription()); //les messages journalisation
		coursRepository.save(cours);
		return cours.getId();
	}

	@Override
	public Long modifierCours(Cours cours) {
		coursRepository.save(cours);
		return cours.getId();
		}

	@Override
	public void supprimerCours(Long coursId) {
		coursRepository.deleteById(coursId);
		
	}

	@Override
	public List<Cours> getCours() {
		
		List<Cours> cours =   coursRepository.findAll();
		return cours;
	}
	
	@Override
	public void affecterCoursASession(Long coursId, Long sessionId)
	{
		/*todo*/
        
	}

}
