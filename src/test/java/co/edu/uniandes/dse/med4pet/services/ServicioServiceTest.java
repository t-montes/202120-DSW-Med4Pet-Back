package co.edu.uniandes.dse.med4pet.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.med4pet.entities.ServicioEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(ServicioService.class)

class ServicioServiceTest {
	@Autowired
	private ServicioService servicioService;
	
	@Autowired
	private TestEntityManager entityManager;
	
	private PodamFactory factory = new PodamFactoryImpl();
	private List<ServicioEntity> servicioList = new ArrayList<>();
	
	private void insertData() {
        for (int i = 0; i < 3; i++) {
        	ServicioEntity servicioEntity = factory.manufacturePojo(ServicioEntity.class);
                entityManager.persist(servicioEntity);
                servicioList.add(servicioEntity);
        }
	}

	 private void clearData() {
         entityManager.getEntityManager().createQuery("delete from ServicioEntity").executeUpdate();
	 }

	@BeforeEach
	void setUp() throws Exception {
		 clearData();
         insertData();
	}

	@Test
	void testGetServicios() {
		  List<ServicioEntity> list = servicioService.getServicios();
          assertEquals(list.size(), servicioList.size());
          
          ServicioEntity servicio1 = servicioList.get(0);
          ServicioEntity servicio2 = entityManager.find(ServicioEntity.class, servicio1.getId());
          
          assertEquals(servicio1.getNombre(), servicio2.getNombre());
	}

	@Test
	void testGetServicio() throws EntityNotFoundException {
		ServicioEntity entity = servicioList.get(0);
		ServicioEntity resultEntity = servicioService.getServicio(entity.getId());
		assertNotNull(resultEntity);
		assertEquals(entity.getId(), resultEntity.getId());
		assertEquals(entity.getNombre(), resultEntity.getNombre());
		assertEquals(entity.getPrestadores(), resultEntity.getPrestadores());
		assertEquals(entity.getCitas(), resultEntity.getCitas());
	}

}