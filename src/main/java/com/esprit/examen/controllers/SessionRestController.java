package com.esprit.examen.controllers;

import com.esprit.examen.entities.Response;
import com.esprit.examen.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.esprit.examen.entities.Session;

import java.util.List;


@RestController
@RequestMapping("/SessionService")
public class SessionRestController {

	@Autowired
	private SessionRepository sessionRepository;

	
	@PostMapping("/ajouterSession")
	@ResponseBody
	public Response ajouterSession(@RequestBody Session session) {

			session = sessionRepository.save(session);
			if (session == null)
				return new Response("session not inserted", Boolean.FALSE);
			else
				return new Response(session.getId() + " inserted", Boolean.TRUE);

	}

	@GetMapping("/session/{id}")
	public Session getSession(@PathVariable("id") int id) throws Exception{
		return sessionRepository.findById(id).orElseThrow(() -> new Exception());
	}
	@PutMapping("/updateSession/{id}")
	public Response updateSession(@RequestBody Session session, @PathVariable Long id){
		sessionRepository.save(session);

		return new Response(session.getId()+" updated",Boolean.TRUE);
	}

	@GetMapping("/getSessions")
	public Response getAllSession() {
		List<Session> sessions = sessionRepository.findAll();
		return new Response("record counts : " + sessions.size(), Boolean.TRUE);
	}

	@DeleteMapping("/deleteSession/{id}")
	public Response deleteSession(@PathVariable("id") Long id){

		if(sessionRepository.existsById(Math.toIntExact(id))) {
			sessionRepository.deleteById(Math.toIntExact(id));
			return new Response("deleted ", Boolean.TRUE);
		}
		else{
			return new Response("not deleted : object not found", Boolean.FALSE);

		}
	}

	// TDD (Test Driven Developement)
}

