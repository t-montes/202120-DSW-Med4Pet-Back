package co.edu.uniandes.dse.med4pet.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.med4pet.entities.RegistroMedicoEntity;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(RegistroMedicoService.class)

class RegistroMedicoServiceTest 
{
	@Autowired

	private RegistroMedicoService registroMedicoService;

	@Autowired

	private TestEntityManager entityManager;
	
	private PodamFactory factory = new PodamFactoryImpl();

    private List<RegistroMedicoEntity> registroMedicoList = new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception {
		clearData();
        insertData();
	}
	
	private void clearData() {
        entityManager.getEntityManager().createQuery("delete from RegistroMedicoEntity").executeUpdate();
}
	private void insertData() {
        for (int i = 0; i < 3; i++) {
                RegistroMedicoEntity registroMedicoEntity = factory.manufacturePojo(RegistroMedicoEntity.class);
                entityManager.persist(registroMedicoEntity);
                registroMedicoList.add(registroMedicoEntity);
        }
}

	@Test
	void testGetRegistrosMedicos() 
	{
        List<RegistroMedicoEntity> list = registroMedicoService.getRegistrosMedicos();
        assertEquals(list.size(), registroMedicoList.size());
        RegistroMedicoEntity registro1  = list.get(0);
        RegistroMedicoEntity registro2 = entityManager.find(RegistroMedicoEntity.class, registro1.getId());
        assertEquals(registro1.getIdentificacion(),registro2.getIdentificacion());
        
}
}
