package co.edu.uniandes.dse.med4pet.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.med4pet.entities.CitaEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(CitaService.class)
class CitaServiceTest {

	@Autowired
	private CitaService citaService;

	@Autowired
	private TestEntityManager entityManager;
	
	private PodamFactory factory = new PodamFactoryImpl();
	
	private List<CitaEntity> citaList = new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception {
		clearData();
		insertData();
	}

	/**
     * Borra los datos de las tablas de CitaEntity.
     */
    private void clearData() {
            entityManager.getEntityManager().createQuery("delete from CitaEntity").executeUpdate();
    }
    
    /**
     * Agrega 3 entidades CitaEntity aleatorias a la lista de citas.
     */
    private void insertData() {
    	for (int i=0 ; i < 3 ; i++) {
    		CitaEntity citaEntity = factory.manufacturePojo(CitaEntity.class);
    		entityManager.persist(citaEntity);
    		citaList.add(citaEntity);
    	}
    }
	
    /**
     * Prueba que al obtener las citas, estas son las mismas.
     */
	@Test
	void testGetCitas() {
		List<CitaEntity> list = citaService.getCitas();
		assertEquals(list.size(), citaList.size());
		
		CitaEntity cita1 = citaList.get(0);
        CitaEntity cita2 = entityManager.find(CitaEntity.class, cita1.getId());
        
        assertEquals(cita1.getFecha(), cita2.getFecha());
	}

	@Test
	void testGetCita() throws EntityNotFoundException {
		CitaEntity cita = citaList.get(0);
		CitaEntity result = citaService.getCita(cita.getId());
		assertNotNull(result);
		assertEquals(cita.getFecha(), result.getFecha());
	}
	
	@Test
	void testGetInvalidCita() {
		assertThrows(EntityNotFoundException.class, ()->{
			citaService.getCita(0L);
		});
	}
	
	@Test
	void testCreateCita() throws IllegalOperationException {
		/*
		CitaEntity newEntity = factory.manufacturePojo(CitaEntity.class);
		CitaEntity result = citaService.createCita(newEntity);
		assertNotNull(result);
		*/
		//Duración que no es un número entero de horas
		assertThrows(IllegalOperationException.class, () -> {
			CitaEntity citaTest = factory.manufacturePojo(CitaEntity.class);
			citaTest.setFecha(LocalDateTime.of(2021, 12, 17, 10, 0, 0));
			citaTest.setDuracion(59);
			citaService.createCita(citaTest);
		},
		"La duración de la cita debe ser un múltiplo de 60");
		
		//Hora inicia antes de las 6am
		assertThrows(IllegalOperationException.class, () -> {
			CitaEntity citaTest = factory.manufacturePojo(CitaEntity.class);
			citaTest.setFecha(LocalDateTime.of(2021, 12, 17, 5, 0, 0));
			citaTest.setDuracion(60);
			citaService.createCita(citaTest);
		},
		"La hora de inicio de la cita debe estar entre las 6am y las 8pm");

		//Hora inicia después de las 8pm
		assertThrows(IllegalOperationException.class, () -> {
			CitaEntity citaTest = factory.manufacturePojo(CitaEntity.class);
			citaTest.setFecha(LocalDateTime.of(2022, 12, 17, 21, 0, 0));
			citaTest.setDuracion(60);
			citaService.createCita(citaTest);
		},
		"La hora de inicio de la cita debe estar entre las 6am y las 8pm");
		
		//Hora finaliza después de las 9pm
		assertThrows(IllegalOperationException.class, () -> {
			CitaEntity citaTest = factory.manufacturePojo(CitaEntity.class);
			citaTest.setFecha(LocalDateTime.of(2022, 12, 17, 20, 0, 0));
			citaTest.setDuracion(120);
			citaService.createCita(citaTest);
		},
		"La hora de finalización de la cita debe ser máximo a las 9pm");

		//Fecha antes de la actual
		assertThrows(IllegalOperationException.class, () -> {
			CitaEntity citaTest = factory.manufacturePojo(CitaEntity.class);
			citaTest.setFecha(LocalDateTime.of(2021, 5, 16, 10, 0, 0));
			citaTest.setDuracion(60);
			citaService.createCita(citaTest);
		},
		"La fecha de la cita a crear debe ser futura");
		
		//Fecha es un domingo
		assertThrows(IllegalOperationException.class, () -> {
			CitaEntity citaTest = factory.manufacturePojo(CitaEntity.class);
			citaTest.setFecha(LocalDateTime.of(2021, 12, 12, 10, 0, 0));
			citaTest.setDuracion(60);
			citaService.createCita(citaTest);
		},
		"La fecha de la cita no puede ser un domingo");
		
		assertDoesNotThrow(() -> {
			CitaEntity citaTest = factory.manufacturePojo(CitaEntity.class);
			citaTest.setFecha(LocalDateTime.of(2021, 12, 13, 10, 0, 0));
			citaTest.setDuracion(60);
			citaService.createCita(citaTest);
		});
		
		/*
		CitaEntity entity = entityManager.find(CitaEntity.class, result.getId());
		assertEquals(newEntity.getId(), entity.getId());
		assertEquals(newEntity.getFecha(), entity.getFecha());
		assertEquals(newEntity.getCosto(), entity.getCosto());
		assertEquals(newEntity.getDuracion(), entity.getDuracion());
		assertEquals(newEntity.getDisponible(), entity.getDisponible());
		assertEquals(newEntity.getEstado(), entity.getEstado());
		*/
	}
}
