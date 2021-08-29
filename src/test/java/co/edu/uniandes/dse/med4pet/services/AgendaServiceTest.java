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
		
		for (int i = 0; i < list.size(); i++) {
			//Verifica que cada elemento tenga el mismo ID
			assertEquals(list.get(i).getId(), agendaList.get(i).getId());
			//Verifica que tenga algún elemento idéntico
			assertEquals(list.get(i).getNumeroCitasPendientes(), agendaList.get(i).getNumeroCitasPendientes());
		}
	}

}
