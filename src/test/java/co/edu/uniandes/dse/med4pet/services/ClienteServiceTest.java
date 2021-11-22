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

import co.edu.uniandes.dse.med4pet.entities.ClienteEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(ClienteService.class)

class ClienteServiceTest {
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private TestEntityManager entityManager;
	
	private PodamFactory factory = new PodamFactoryImpl();
	private List<ClienteEntity> clienteList = new ArrayList<>();
	
	private void insertData() {
        for (int i = 0; i < 3; i++) {
        	ClienteEntity clienteEntity = factory.manufacturePojo(ClienteEntity.class);
                entityManager.persist(clienteEntity);
                clienteList.add(clienteEntity);
        }
	}

	 private void clearData() {
         entityManager.getEntityManager().createQuery("delete from ClienteEntity").executeUpdate();
	 }

	@BeforeEach
	void setUp() throws Exception {
		 clearData();
         insertData();
	}

	@Test
	void testGetClientes() {
		  List<ClienteEntity> list = clienteService.getClientes();
          assertEquals(list.size(), clienteList.size());
          
          ClienteEntity cliente1 = clienteList.get(0);
          ClienteEntity cliente2 = entityManager.find(ClienteEntity.class, cliente1.getId());
          
          assertEquals(cliente1.getNombre(), cliente2.getNombre());
          assertEquals(cliente1.getCalificacion(), cliente2.getCalificacion());
	}
	
	@Test
	void testGetCliente() throws EntityNotFoundException {
		ClienteEntity entity = clienteList.get(0);
		ClienteEntity resultEntity = clienteService.getCliente(entity.getId());
		assertNotNull(resultEntity);
		assertEquals(entity.getId(), resultEntity.getId());
		assertEquals(entity.getNombre(), resultEntity.getNombre());
		assertEquals(entity.getCalificaciones(), resultEntity.getCalificaciones());
	}
	
	@Test
	void testCreateCliente() throws EntityNotFoundException, IllegalOperationException {
		ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
		ClienteEntity result = clienteService.createCliente(newEntity);
		assertNotNull(result);

		ClienteEntity entity = entityManager.find(ClienteEntity.class, result.getId());

		assertEquals(newEntity.getId(), entity.getId());
		assertEquals(newEntity.getNombre(), entity.getNombre());
		assertEquals(newEntity.getCalificacion(), entity.getCalificacion());
	}


}











