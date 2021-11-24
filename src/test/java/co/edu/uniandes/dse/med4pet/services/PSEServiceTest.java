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

import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.med4pet.entities.PSEEntity;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(PSEService.class)
class PSEServiceTest
{

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

	@Test
	void testGetPSEs()
	{
		List<PSEEntity> list = pseService.getPSEs();
		assertEquals(list.size(), pseList.size());
	}

	@Test
	void testCreatePSE() throws EntityNotFoundException, IllegalOperationException
	{
		PSEEntity newEntity = factory.manufacturePojo(PSEEntity.class);
		PSEEntity result = pseService.createPSE(newEntity);
		assertNotNull(result);

		PSEEntity entity = entityManager.find(PSEEntity.class, result.getId());

		assertEquals(newEntity.getId(), entity.getId());
		assertEquals(newEntity.getBanco(), entity.getBanco());
		assertEquals(newEntity.getNumeroTarjeta(), entity.getNumeroTarjeta());
	}

	/**
	 * Crea un PSE con un banco inválido
	 */
	@Test
	public void testCreatePSEWithNoValidBanco()
	{
		assertThrows(IllegalOperationException.class, () -> {
			PSEEntity newEntity = factory.manufacturePojo(PSEEntity.class);
			newEntity.setBanco(null);
			newEntity.setNumeroTarjeta("1111");
			pseService.createPSE(newEntity);
		});
	}
	
	/**
	 * Crea un PSE con un número de tarjeta inválido
	 */
	@Test
	public void testCreatePSEWithNoValidNumeroTarjeta()
	{
		assertThrows(IllegalOperationException.class, () -> {
			PSEEntity newEntity = factory.manufacturePojo(PSEEntity.class);
			newEntity.setBanco("Bancolombia");
			newEntity.setNumeroTarjeta(null);
			pseService.createPSE(newEntity);
		});
	}


	@Test
	void testGetPSE() throws EntityNotFoundException
	{
		PSEEntity pseEntity = pseList.get(0);

		PSEEntity resultEntity = pseService.getPSE(pseEntity.getId());
		assertNotNull(resultEntity);

		assertEquals(pseEntity.getId(), resultEntity.getId());
		assertEquals(pseEntity.getBanco(), resultEntity.getBanco());
		assertEquals(pseEntity.getNumeroTarjeta(), resultEntity.getNumeroTarjeta());
	}

	@Test
	void testGetInvalidPse() 
	{
		assertThrows(EntityNotFoundException.class, ()->{
			pseService.getPSE(0L);
		});
	}
}
