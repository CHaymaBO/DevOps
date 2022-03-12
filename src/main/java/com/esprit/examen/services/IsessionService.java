package com.esprit.examen.services;

import com.esprit.examen.entities.Session;
import com.esprit.examen.repositories.SessionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class IsessionService implements SessionService{
    SessionRepository sessionRepository;

    @Override
    public void saveorupadte(Session session) {
        sessionRepository.save(session);
    }

    @Override
    public Optional<Session> getById(int id) {

        return sessionRepository.findById( id);

    }

    @Override
    public void delete(int id) {
        sessionRepository.deleteById(id);
    }

    @Override
    public List<Session> consulte() {
        return sessionRepository.findAll();
    }


}
