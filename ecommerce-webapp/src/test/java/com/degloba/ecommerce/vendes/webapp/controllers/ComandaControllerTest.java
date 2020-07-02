package com.degloba.ecommerce.vendes.webapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.degloba.ecommerce.vendes.application.services.IComandesService;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.Comanda;

@WebMvcTest(controllers = ComandaController.class)
@ActiveProfiles("test")
public class ComandaControllerTest {

	 @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private IComandesService comandaService;

	    @Autowired
	    private ObjectMapper objectMapper;

	    private List<Comanda> comandaList;

	    @BeforeEach
	    void setUp() {
	        this.comandaList = new ArrayList<>();
	        this.comandaList.add(new Comanda(1L, "user1@gmail.com", "pwd1","User1"));
	        this.comandaList.add(new Comanda(2L, "user2@gmail.com", "pwd2","User2"));
	        this.comandaList.add(new Comanda(3L, "user3@gmail.com", "pwd3","User3"));

	        objectMapper.registerModule(new ProblemModule());
	        objectMapper.registerModule(new ConstraintViolationProblemModule());
	    }

	    @Test
	    void hauriaCercarTotesComandes() throws Exception {
	        given(comandaService.ObtenirTotesComandes()).willReturn(this.comandaList);

	        this.mockMvc.perform(get("/api/comandes"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.size()", is(comandaList.size())));
	    }

	    @Test
	    void hauriaTrobarComandaPerId() throws Exception {
	        Long comandaId = 1L;
	        Comanda comanda = new Comanda(comandaId, "newuser1@gmail.com", "pwd", "Name");
	        given(comandaService.obtenirComandaPerId(comandaId)).willReturn(Optional.of(comanda));

	        this.mockMvc.perform(get("/api/comandes/{id}", comandaId))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.email", is(comanda.getEmail())))
	                .andExpect(jsonPath("$.password", is(comanda.getPassword())))
	                .andExpect(jsonPath("$.name", is(comanda.getName())))
	        ;
	    }

	    @Test
	    void hauriaRetornar404QuanEsBuscaComandaNoExistent() throws Exception {
	        Long comandaId = 1L;
	        given(comandaService.obtenirComandaPerId(comandaId)).willReturn(Optional.empty());

	        this.mockMvc.perform(get("/api/comandes/{id}", comandaId))
	                .andExpect(status().isNotFound());

	    }

	    @Test
	    void hauriaCrearNovaComanda() throws Exception {
	        given(comandaService.creaComanda(any(Comanda.class))).willAnswer((invocation) -> invocation.getArgument(0));

	        Comanda comanda = new Comanda(null, "newuser1@gmail.com", "pwd", "Name");
	        this.mockMvc.perform(post("/api/comandes")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(comanda)))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.email", is(comanda.getEmail())))
	                .andExpect(jsonPath("$.password", is(comanda.getPassword())))
	                .andExpect(jsonPath("$.name", is(comanda.getName())))
	                ;

	    }

	    @Test
	    void shouldReturn400WhenCreateNewUserWithoutEmail() throws Exception {
	    	Comanda comanda = new Comanda(null, null, "pwd", "Name");

	        this.mockMvc.perform(post("/api/comandes")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(comanda)))
	                .andExpect(status().isBadRequest())
	                .andExpect(header().string("Content-Type", is("application/problem+json")))
	                .andExpect(jsonPath("$.type", is("https://zalando.github.io/problem/constraint-violation")))
	                .andExpect(jsonPath("$.title", is("Constraint Violation")))
	                .andExpect(jsonPath("$.status", is(400)))
	                .andExpect(jsonPath("$.violations", hasSize(1)))
	                .andExpect(jsonPath("$.violations[0].field", is("email")))
	                .andExpect(jsonPath("$.violations[0].message", is("Email should not be empty")))
	                .andReturn()
	        ;
	    }

	    @Test
	    void hauriaModificarComanda() throws Exception {
	        Long comandaId = 1L;
	        Comanda comanda = new Comanda(comandaId, "user1@gmail.com", "pwd", "Name");
	        given(comandaService.obtenirComandaPerId(comandaId)).willReturn(Optional.of(comanda));
	        given(comandaService.modificarComanda(any(Comanda.class))).willAnswer((invocation) -> invocation.getArgument(0));

	        this.mockMvc.perform(put("/api/comandes/{id}", comanda.getId())
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(comanda)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.email", is(comanda.getEmail())))
	                .andExpect(jsonPath("$.password", is(comanda.getPassword())))
	                .andExpect(jsonPath("$.name", is(comanda.getName())));

	    }

	    @Test
	    void hauriaRetornar404QuanEsModificaComandaNoExistent() throws Exception {
	        Long comandaId = 1L;
	        given(comandaService.obtenirComandaPerId(comandaId)).willReturn(Optional.empty());
	        Comanda comanda = new Comanda(comandaId, "user1@gmail.com", "pwd", "Name");

	        this.mockMvc.perform(put("/api/comandes/{id}", comandaId)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(comanda)))
	                .andExpect(status().isNotFound());

	    }

	    @Test
	    void hauriaEsborrarComanda() throws Exception {
	        Long comandaId = 1L;
	        Comanda comanda = new Comanda(comandaId, "user1@gmail.com", "pwd", "Name");
	        given(comandaService.obtenirComandaPerId(comandaId)).willReturn(Optional.of(comanda));
	        doNothing().when(comandaService).esborrarComandaPerId(comanda.getId());

	        this.mockMvc.perform(delete("/api/comandes/{id}", comanda.getId()))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.email", is(comanda.getEmail())))
	                .andExpect(jsonPath("$.password", is(comanda.getPassword())))
	                .andExpect(jsonPath("$.name", is(comanda.getName())));

	    }

	    @Test
	    void hauriaRetornar404QuanEsborremComandaNoExistent() throws Exception {
	        Long comandaId = 1L;
	        given(comandaService.obtenirComandaPerId(comandaId)).willReturn(Optional.empty());

	        this.mockMvc.perform(delete("/api/comandes/{id}", comandaId))
	                .andExpect(status().isNotFound());

	    }

}