package co.edu.uniandes.dse.med4pet.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.med4pet.entities.PSEEntity;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(PSEService.class)
class PSEServiceTest {

	@Autowired
	private PSEService pseService;
	
	@Autowired 
	private TestEntityManager entityManager;
	
	private PodamFactory factory = new PodamFactoryImpl();
	
	private List<PSEEntity> pseList = new ArrayList<>();
	
	@BeforeEach
	void setUp() throws Exception
	{
		clearData();
		insertData();
	}

	@Test
	void testGetPSEs()
	{
		List<PSEEntity> list = pseService.getPSEs();
		assertEquals(list.size(), pseList.size());
	}
	
	private void clearData()
	{
		entityManager.getEntityManager().createQuery("delete from PSEEntity").executeUpdate();
	}
	
	private void insertData()
	{
		for(int i = 0; i < 3; i++)
		{
			PSEEntity pseEntity = factory.manufacturePojo(PSEEntity.class);
			entityManager.persist(pseEntity);
			pseList.add(pseEntity);
		}
	}

}
