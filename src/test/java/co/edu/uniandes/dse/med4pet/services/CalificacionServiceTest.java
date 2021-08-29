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

import co.edu.uniandes.dse.med4pet.entities.CalificacionEntity;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(CalificacionService.class)

class CalificacionServiceTest {
	
	@Autowired

	private CalificacionService calificacionService;

	@Autowired

	private TestEntityManager entityManager;
	
	private PodamFactory factory = new PodamFactoryImpl();

    private List<CalificacionEntity> calificacionList = new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception 
	{
		clearData();
        insertData();
	}
	
	private void clearData() 
	{
        entityManager.getEntityManager().createQuery("delete from CalificacionEntity").executeUpdate();
}
	private void insertData()
	{
        for (int i = 0; i < 3; i++) {
                CalificacionEntity calificacionEntity = factory.manufacturePojo(CalificacionEntity.class);
                entityManager.persist(calificacionEntity);
                calificacionList.add(calificacionEntity);
        }
}
	
	@Test
		void getCalificacionesTest()
		{
			List<CalificacionEntity> List = calificacionService.getCalificaciones(); 
			assertEquals(List.size(),calificacionList.size());
			CalificacionEntity calificacion1 = calificacionList.get(0);
			CalificacionEntity calificacion2 = entityManager.find(CalificacionEntity.class, calificacion1.getId());
			assertEquals(calificacion1.getCreador(),calificacion2.getCreador());
			
		}
}


