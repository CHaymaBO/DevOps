package com.esprit.examen.services;


import com.esprit.examen.entities.Session;
import java.util.List;
import java.util.Optional;

public interface SessionService {

	public void saveorupadte(Session session);
	public Optional<Session> getById(int id);
	public void delete(int id);
	public List<Session> consulte();

}
