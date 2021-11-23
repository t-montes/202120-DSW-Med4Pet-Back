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
import co.edu.uniandes.dse.med4pet.entities.ClienteEntity;
import co.edu.uniandes.dse.med4pet.entities.VeterinarioEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
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
    private List<VeterinarioEntity> veterinariosList = new ArrayList<VeterinarioEntity>();
    private List<ClienteEntity> clientesList = new ArrayList<ClienteEntity>();

	@BeforeEach
	void setUp() throws Exception 
	{
		clearData();
        insertData();
	}
	
	private void clearData() 
	{
        entityManager.getEntityManager().createQuery("delete from CalificacionEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from VeterinarioEntity");
        entityManager.getEntityManager().createQuery("delete from ClienteEntity");
}
	private void insertData()
	{
        for (int i = 0; i < 3; i++) {
                CalificacionEntity calificacionEntity = factory.manufacturePojo(CalificacionEntity.class);
                entityManager.persist(calificacionEntity);
                calificacionList.add(calificacionEntity);
        }
        for (int i = 0; i < 3; i++) {
            VeterinarioEntity veterinarioEntity = factory.manufacturePojo(VeterinarioEntity.class);
            entityManager.persist(veterinarioEntity);
            veterinariosList.add(veterinarioEntity);
    }
        for (int i = 0; i < 3; i++) {
            ClienteEntity clienteEntity = factory.manufacturePojo(ClienteEntity.class);
            entityManager.persist(clienteEntity);
            clientesList.add(clienteEntity);
    }
        
}
	
	@Test
		void TestGetCalificaciones()
		{
			List<CalificacionEntity> List = calificacionService.getCalificaciones(); 
			assertEquals(List.size(),calificacionList.size());
			CalificacionEntity calificacion1 = calificacionList.get(0);
			CalificacionEntity calificacion2 = entityManager.find(CalificacionEntity.class, calificacion1.getId());
			assertEquals(calificacion1.getCreador(),calificacion2.getCreador());
			
		}
	@Test 
	void TestGetCalificacion() throws EntityNotFoundException
	{
		CalificacionEntity calificacionEntity = calificacionList.get(0);
		CalificacionEntity resultEntity = calificacionService.getCalificacion(calificacionEntity.getId());
		assertNotNull(resultEntity);
		assertEquals(calificacionEntity.getId(),resultEntity.getId());
		assertEquals(calificacionEntity.getCreador(),resultEntity.getCreador());
		assertEquals(calificacionEntity.getFecha(),resultEntity.getFecha());
		assertEquals(calificacionEntity.getPuntaje(),resultEntity.getPuntaje());
		assertEquals(calificacionEntity.getDescripcion(),resultEntity.getDescripcion());
	}
	@Test 
	void TestCreateCalificacionCliente() throws IllegalOperationException
	{
		CalificacionEntity calificacionEntity = factory.manufacturePojo(CalificacionEntity.class);
		calificacionEntity.setClienteCalificado(clientesList.get(0));
		calificacionEntity.setPuntaje(4.0);
		CalificacionEntity resultEntity = calificacionService.createCalificacion(calificacionEntity);
		assertNotNull(resultEntity);

		CalificacionEntity result = entityManager.find(CalificacionEntity.class,resultEntity.getId());
		
		assertEquals(calificacionEntity.getId(),result.getId());
		assertEquals(calificacionEntity.getCreador(),result.getCreador());
		assertEquals(calificacionEntity.getFecha(),result.getFecha());
		assertEquals(calificacionEntity.getPuntaje(),result.getPuntaje());
		assertEquals(calificacionEntity.getDescripcion(),result.getDescripcion());
	}
	@Test 
	void TestCreateCalificacionVeterinario() throws IllegalOperationException
	{
		CalificacionEntity calificacionEntity = factory.manufacturePojo(CalificacionEntity.class);
		calificacionEntity.setVeterinarioCalificado(veterinariosList.get(0));
		calificacionEntity.setPuntaje(4.0);
		CalificacionEntity resultEntity = calificacionService.createCalificacion(calificacionEntity);
		assertNotNull(resultEntity);

		CalificacionEntity result = entityManager.find(CalificacionEntity.class,resultEntity.getId());
		
		assertEquals(calificacionEntity.getId(),result.getId());
		assertEquals(calificacionEntity.getCreador(),result.getCreador());
		assertEquals(calificacionEntity.getFecha(),result.getFecha());
		assertEquals(calificacionEntity.getPuntaje(),result.getPuntaje());
		assertEquals(calificacionEntity.getDescripcion(),result.getDescripcion());
	}
	@Test
	void TestCreateCalificacionPuntajeMayor()
	{
		assertThrows(IllegalOperationException.class, () ->{
			CalificacionEntity calificacionEntity = factory.manufacturePojo(CalificacionEntity.class);
			calificacionEntity.setVeterinarioCalificado(veterinariosList.get(0));
			calificacionEntity.setPuntaje(6.3);
			calificacionService.createCalificacion(calificacionEntity);
		});
	}
	@Test
	void TestCreateCalificacionPuntajeMenor()
	{
		assertThrows(IllegalOperationException.class, () ->{
			CalificacionEntity calificacionEntity = factory.manufacturePojo(CalificacionEntity.class);
			calificacionEntity.setVeterinarioCalificado(veterinariosList.get(0));
			calificacionEntity.setPuntaje(-0.3);
			calificacionService.createCalificacion(calificacionEntity);
		});
	}
}


