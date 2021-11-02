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

import co.edu.uniandes.dse.med4pet.entities.ContactoEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(ContactoService.class)
class ContactoServiceTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private ContactoService contactoService;

	private PodamFactory factory = new PodamFactoryImpl();
	
	private List<ContactoEntity> contactoList = new ArrayList<>();
	
	@BeforeEach
	void setUp() throws Exception {
		clearData();
		insertData();
	}
	
	private void clearData() {
        entityManager.getEntityManager().createQuery("delete from ContactoEntity").executeUpdate();
	}
	
	private void insertData() {
        for (int i = 0; i < 3; i++) {
                ContactoEntity contactoEntity = factory.manufacturePojo(ContactoEntity.class);
                entityManager.persist(contactoEntity);
                contactoList.add(contactoEntity);
        }
	}
	@Test
	void testGetContactos() {
		List<ContactoEntity> list = contactoService.getContactos();
        assertEquals(list.size(), contactoList.size());
        for(int i = 0; i < list.size(); i++) {
        	assertEquals(list.get(i).getId(), contactoList.get(i).getId());
        	assertEquals(list.get(i).getTelefono(), contactoList.get(i).getTelefono());
        }
	}
	@Test
	void testGetContacto() throws EntityNotFoundException{
		ContactoEntity contactoEntity = contactoList.get(0);
		ContactoEntity resultEntity = contactoService.getContacto(contactoEntity.getId());
		assertNotNull(resultEntity);
		
		assertEquals(contactoEntity.getId(), resultEntity.getId());
		assertEquals(contactoEntity.getCorreo(), resultEntity.getCorreo());
		assertEquals(contactoEntity.getTelefono(), resultEntity.getTelefono());

	}
	
	@Test
	void testGetInvalidContacto() {
		assertThrows(EntityNotFoundException.class, ()->{
			contactoService.getContacto(0L);
		});
	}

}
