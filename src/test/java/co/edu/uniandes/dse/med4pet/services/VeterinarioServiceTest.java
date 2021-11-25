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
import co.edu.uniandes.dse.med4pet.entities.RegistroMedicoEntity;
import co.edu.uniandes.dse.med4pet.entities.VeterinarioEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(VeterinarioService.class)
class VeterinarioServiceTest {
	
	@Autowired
	private VeterinarioService veterinarioService;

	@Autowired
	private TestEntityManager entityManager;

	private PodamFactory factory = new PodamFactoryImpl();
	
	private List<VeterinarioEntity> veterinarioList = new ArrayList<>();

	

	@BeforeEach
	void setUp() throws Exception {
		clearData();
		insertData();
	}

	private void clearData() {
        entityManager.getEntityManager().createQuery("delete from VeterinarioEntity").executeUpdate();
	}
	
	private void insertData() {
        for (int i = 0; i < 3; i++) {
                VeterinarioEntity veterinarioEntity = factory.manufacturePojo(VeterinarioEntity.class);
                entityManager.persist(veterinarioEntity);
                veterinarioList.add(veterinarioEntity);
        }   
	}
	@Test
	void testGetVeterinarios() {
		List<VeterinarioEntity> list = veterinarioService.getVeterinarios();
        assertEquals(list.size(), veterinarioList.size());
        for(int i = 0; i < list.size(); i++) {
        	assertEquals(list.get(i).getId(), veterinarioList.get(i).getId());
        	assertEquals(list.get(i).getNombre(), veterinarioList.get(i).getNombre());
        }
	}
	
	@Test
	void testGetVeterinario() throws EntityNotFoundException {
		VeterinarioEntity veterinarioEntity = veterinarioList.get(0);
		
		VeterinarioEntity resultEntity = veterinarioService.getVeterinario(veterinarioEntity.getId());
		assertNotNull(resultEntity);
		
		assertEquals(veterinarioEntity.getId(), resultEntity.getId());
		assertEquals(veterinarioEntity.getNombre(), resultEntity.getNombre());
		assertEquals(veterinarioEntity.getEspecialidad(), resultEntity.getEspecialidad());
		assertEquals(veterinarioEntity.getExperienciaPrevia(), resultEntity.getExperienciaPrevia());
		assertEquals(veterinarioEntity.getCertificadoEntrenamiento(), resultEntity.getCertificadoEntrenamiento());
		assertEquals(veterinarioEntity.getCalificacion(), resultEntity.getCalificacion());
		
	}
	
	@Test
	void testGetInvalidVeterinario() {
		assertThrows(EntityNotFoundException.class, ()->{
			veterinarioService.getVeterinario(0L);
		});
	}
	
	@Test
	void testCreateVeterinario() throws IllegalOperationException
	{
		VeterinarioEntity newEntity = factory.manufacturePojo(VeterinarioEntity.class);
		RegistroMedicoEntity re = factory.manufacturePojo(RegistroMedicoEntity.class);
		ContactoEntity co = factory.manufacturePojo(ContactoEntity.class);
		newEntity.setContacto(co);
		newEntity.setRegistroMedico(re);
		VeterinarioEntity result = veterinarioService.createVeterinario(newEntity);
		assertNotNull(result);
		
		VeterinarioEntity entity = entityManager.find(VeterinarioEntity.class, result.getId());
		
		assertEquals(newEntity.getId(), entity.getId());
		assertEquals(newEntity.getCalificacion(), entity.getCalificacion());
		assertEquals(newEntity.getNombre(), entity.getNombre());
		assertEquals(newEntity.getEspecialidad(), entity.getEspecialidad());
		assertEquals(newEntity.getExperienciaPrevia(), entity.getExperienciaPrevia());
		assertEquals(newEntity.getCertificadoEntrenamiento(), entity.getCertificadoEntrenamiento());
	}
	
	@Test
	void testCreateVeterinarioWithWrongCalification() {
		VeterinarioEntity newEntity = factory.manufacturePojo(VeterinarioEntity.class);
		assertThrows(IllegalOperationException.class, ()-> {
			newEntity.setCalificacion(6.0);
			veterinarioService.createVeterinario(newEntity);
		});
		assertThrows(IllegalOperationException.class, ()-> {
			newEntity.setCalificacion(-1.0);
			veterinarioService.createVeterinario(newEntity);
		});
	}
	
	@Test 
	void testCreateVeterinarioWithWrongNombre() {
		VeterinarioEntity newEntity = factory.manufacturePojo(VeterinarioEntity.class);
		assertThrows(IllegalOperationException.class, ()->{
			newEntity.setNombre("");
			veterinarioService.createVeterinario(newEntity);
		});
	}
	
	@Test 
	void testCreateVeterinarioWithWrongExperiencia() {
		VeterinarioEntity newEntity = factory.manufacturePojo(VeterinarioEntity.class);
		assertThrows(IllegalOperationException.class, ()->{
			newEntity.setExperienciaPrevia("");
			veterinarioService.createVeterinario(newEntity);
		});
	}
	
	@Test 
	void testCreateVeterinarioWithWrongEspecialidad() {
		VeterinarioEntity newEntity = factory.manufacturePojo(VeterinarioEntity.class);
		assertThrows(IllegalOperationException.class, ()->{
			newEntity.setEspecialidad("");
			veterinarioService.createVeterinario(newEntity);
		});
	}
}
