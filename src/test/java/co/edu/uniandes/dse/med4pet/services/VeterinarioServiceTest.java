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

import co.edu.uniandes.dse.med4pet.entities.VeterinarioEntity;
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

}