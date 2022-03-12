package com.esprit.examen;

import com.esprit.examen.entities.Response;
import com.esprit.examen.entities.Session;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableAspectJAutoProxy
public class SessionTests {
    @Autowired
    private MockMvc mockMvc;
    private static final Logger l = LogManager.getLogger(SessionTests.class);

    ObjectMapper om = new ObjectMapper(); // convertir les objets en JSON

    @Test
    public void ajouterSessionTest() throws Exception {
        // création de l'objet
        Session session = new Session();
        session.setDescription("workshop devops");
        session.setDateDebut("12-04-2022");
        session.setDateFin("12-04-2022");
        session.setDuree(1);
        l.info("ajout de session : "+ session);

        // pour transformer l'ojet session en format json
        String objectinjson = om.writeValueAsString(session);

        // envoyer une requête d'ajout ( requête http)
        MvcResult queryresponse =
                mockMvc.perform(post("/SessionService/ajouterSession").
                                content(objectinjson)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)).
                        andExpect(status().isOk()).andReturn();

        // récupérer le contenu de la réponse sous forme chaine de caractères
        String resultContent = queryresponse.getResponse().getContentAsString();

        // transformer la réponse en format json en un objet de la classe Response
        Response response = om.readValue(resultContent, Response.class);

        Assert.assertTrue(response.isStatus() == Boolean.TRUE);

    }

    @Test
    public void deleteSessionTest() throws Exception {

        Session session = new Session();
        session.setId(2);
        l.info("id de session a suprimer : "+ session);

        // envoyer une requête d'ajout ( requête http)
        MvcResult queryresponse =
                mockMvc.perform(delete("/SessionService/deleteSession/" + session.getId())
                                .contentType(MediaType.APPLICATION_JSON_VALUE)).
                        andExpect(status().isOk()).andReturn();

        // récupérer le contenu de la réponse sous forme chaine de caractères
        String resultContent = queryresponse.getResponse().getContentAsString();

        // transformer la réponse en format json en un objet de la classe Response
        Response response = om.readValue(resultContent, Response.class);

        Assert.assertTrue(response.isStatus() == Boolean.FALSE);
    }

    @Test
    public void updateSessionTest() throws Exception{
        Session session = new Session();
        session.setId(3);
        session.setDescription("atelier number");
        session.setDuree(1);
        l.info("id de session a suprimer : "+ session);

        // pour transformer l'ojet employee en format json
        String jsonRequest = om.writeValueAsString(session);

        // envoyer une requête d'ajout ( requête http)
        MvcResult queryresponse = mockMvc.perform(put("/SessionService/updateSession/"+session.getId()).content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

        // récupérer le contenu de la réponse sous forme chaine de caractères
        String resultContent = queryresponse.getResponse().getContentAsString();

        // transformer la réponse en format json en un objet de la classe Response
        Response response = om.readValue(resultContent, Response.class);

        Assert.assertTrue(response.isStatus() == Boolean.TRUE);
    }

    @Test
    public void getAllSessionTest() throws Exception {
        MvcResult result = mockMvc
                .perform(get("/SessionService/getSessions")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        Response response = om.readValue(resultContent, Response.class);
        Assert.assertTrue(response.isStatus() == Boolean.TRUE);

    }

}

