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

import co.edu.uniandes.dse.med4pet.entities.CitaEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
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
}
