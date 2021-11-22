package co.edu.uniandes.dse.med4pet.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.med4pet.entities.AgendaEntity;
import co.edu.uniandes.dse.med4pet.entities.CitaEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(AgendaService.class)
class AgendaServiceTest {

	@Autowired
	private AgendaService agendaService;

	@Autowired
	private TestEntityManager entityManager;
	
	private PodamFactory factory = new PodamFactoryImpl();
	
	private List<AgendaEntity> agendaList = new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception {
		clearData();
		insertData();
	}

	/**
     * Borra los datos de las tablas de AgendaEntity.
     */
    private void clearData() {
            entityManager.getEntityManager().createQuery("delete from AgendaEntity").executeUpdate();
    }
    
    /**
     * Agrega 3 entidades AgendaEntity aleatorias a la lista de agendas.
     */
    private void insertData() {
    	for (int i=0 ; i < 3 ; i++) {
    		AgendaEntity agendaEntity = factory.manufacturePojo(AgendaEntity.class);
    		entityManager.persist(agendaEntity);
    		agendaList.add(agendaEntity);
    	}
    }
	
    /**
     * Prueba que al obtener las agendas, estas son iguales.
     */
	@Test
	void testGetAgendas() {
		List<AgendaEntity> list = agendaService.getAgendas();
		assertEquals(list.size(), agendaList.size());
		
        AgendaEntity agenda1 = agendaList.get(0);
        AgendaEntity agenda2 = entityManager.find(AgendaEntity.class, agenda1.getId());
        
        assertEquals(agenda1.getNumeroCitasCanceladas(), agenda2.getNumeroCitasCanceladas());
        assertEquals(agenda1.getNumeroCitasPendientes(), agenda2.getNumeroCitasPendientes());
        assertEquals(agenda1.getNumeroCitasRealizadas(), agenda2.getNumeroCitasRealizadas());
	}

	@Test
	void testGetAgenda() throws EntityNotFoundException{
		AgendaEntity agenda = agendaList.get(0);
		AgendaEntity result = agendaService.getAgenda(agenda.getId());
		assertNotNull(result);
		assertEquals(agenda.getNumeroCitasPendientes(), result.getNumeroCitasPendientes());
	}
	
	@Test
	void testGetInvalidAgenda() {
		assertThrows(EntityNotFoundException.class, ()->{
			agendaService.getAgenda(0L);
		});
	}
	
	@Test
	void testCreateAgenda() throws IllegalOperationException {
		AgendaEntity newEntity = factory.manufacturePojo(AgendaEntity.class);
		newEntity.setNumeroCitasCanceladas(0);
		newEntity.setNumeroCitasPendientes(0);
		newEntity.setNumeroCitasRealizadas(0);
		ArrayList<CitaEntity> citas = new ArrayList<CitaEntity>(); 
		newEntity.setCitas(citas);
		AgendaEntity result = agendaService.createAgenda(newEntity);
		assertNotNull(result);
		
		AgendaEntity entity = entityManager.find(AgendaEntity.class, result.getId());
		
		assertEquals(newEntity.getNumeroCitasCanceladas(), entity.getNumeroCitasCanceladas());
		assertEquals(newEntity.getNumeroCitasPendientes(), entity.getNumeroCitasPendientes());
		assertEquals(newEntity.getNumeroCitasRealizadas(), entity.getNumeroCitasRealizadas());
	}
	
	@Test
	void testCreateAgendaConNumCanceladasInvalido() {
		//Número de citas canceladas diferente de 0
		assertThrows(IllegalOperationException.class, () -> {
			AgendaEntity agendaTest = factory.manufacturePojo(AgendaEntity.class);
			agendaTest.setNumeroCitasCanceladas(1);
			agendaTest.setNumeroCitasPendientes(0);
			agendaTest.setNumeroCitasRealizadas(0);
			ArrayList<CitaEntity> citas = new ArrayList<CitaEntity>(); 
			agendaTest.setCitas(citas);
			agendaService.createAgenda(agendaTest);
		},
		"La agenda ya está inicializada; la agenda a crear debe ser nula");
	}

	@Test
	void testCreateAgendaConNumPendientesInvalido() {
		//Número de citas pendientes diferente de 0
		assertThrows(IllegalOperationException.class, () -> {
			AgendaEntity agendaTest = factory.manufacturePojo(AgendaEntity.class);
			agendaTest.setNumeroCitasCanceladas(0);
			agendaTest.setNumeroCitasPendientes(1);
			agendaTest.setNumeroCitasRealizadas(0);
			ArrayList<CitaEntity> citas = new ArrayList<CitaEntity>(); 
			agendaTest.setCitas(citas);
			agendaService.createAgenda(agendaTest);
		},
		"La agenda ya está inicializada; la agenda a crear debe ser nula");
	}

	@Test
	void testCreateAgendaConNumRealizadasInvalido() {
		//Número de citas realizadas diferente de 0
		assertThrows(IllegalOperationException.class, () -> {
			AgendaEntity agendaTest = factory.manufacturePojo(AgendaEntity.class);
			agendaTest.setNumeroCitasCanceladas(0);
			agendaTest.setNumeroCitasPendientes(0);
			agendaTest.setNumeroCitasRealizadas(1);
			ArrayList<CitaEntity> citas = new ArrayList<CitaEntity>(); 
			agendaTest.setCitas(citas);
			agendaService.createAgenda(agendaTest);
		},
		"La agenda ya está inicializada; la agenda a crear debe ser nula");
	}
	
	@Test
	void testCreateAgendaConCitasNoVacia() {
		//Lista de citas nu vacía
		assertThrows(IllegalOperationException.class, () -> {
			AgendaEntity agendaTest = factory.manufacturePojo(AgendaEntity.class);
			agendaTest.setNumeroCitasCanceladas(0);
			agendaTest.setNumeroCitasPendientes(0);
			agendaTest.setNumeroCitasRealizadas(0);
			ArrayList<CitaEntity> citas = new ArrayList<CitaEntity>(); 
			citas.add(factory.manufacturePojo(CitaEntity.class));
			agendaTest.setCitas(citas);
			agendaService.createAgenda(agendaTest);
		},
		"La agenda ya está inicializada; la agenda a crear debe ser nula");
	}
}
