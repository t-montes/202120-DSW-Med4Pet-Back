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

import co.edu.uniandes.dse.med4pet.entities.TarjetaCreditoEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(TarjetaCreditoService.class)
class TarjetaCreditoServiceTest 
{
	@Autowired
	private TarjetaCreditoService tarjetaCreditoService;
	
	@Autowired 
	private TestEntityManager entityManager;
	
	private PodamFactory factory = new PodamFactoryImpl();
	
	private List<TarjetaCreditoEntity> tarjetaCreditoList = new ArrayList<>();
	
	@BeforeEach
	void setUp() throws Exception
	{
		clearData();
		insertData();
	}
	
	private void clearData()
	{
		entityManager.getEntityManager().createQuery("delete from TarjetaCreditoEntity").executeUpdate();
	}
	
	private void insertData()
	{
		for(int i = 0; i < 3; i++)
		{
			TarjetaCreditoEntity tarjetaCreditoEntity = factory.manufacturePojo(TarjetaCreditoEntity.class);
			entityManager.persist(tarjetaCreditoEntity);
			tarjetaCreditoList.add(tarjetaCreditoEntity);
		}
	}

	@Test
	void testGetTarjetasCredito()
	{
		List<TarjetaCreditoEntity> list = tarjetaCreditoService.getTarjetasCredito();
		assertEquals(list.size(), tarjetaCreditoList.size());
	}
	
	@Test
	void testCreatePSE()
	{
		TarjetaCreditoEntity newEntity = factory.manufacturePojo(TarjetaCreditoEntity.class);
		TarjetaCreditoEntity result = tarjetaCreditoService.createTarjetaCredito(newEntity);
		assertNotNull(result);

		TarjetaCreditoEntity entity = entityManager.find(TarjetaCreditoEntity.class, result.getId());

		assertEquals(newEntity.getId(), entity.getId());
		assertEquals(newEntity.getCodigoSeguridad(), entity.getCodigoSeguridad());
		assertEquals(newEntity.getFechaVencimiento(), entity.getFechaVencimiento());
		assertEquals(newEntity.getNumeroTarjeta(), entity.getNumeroTarjeta());
	}
	
	@Test
	void testGetPSE() throws EntityNotFoundException
	{
		TarjetaCreditoEntity tarjetaCreditoEntity = tarjetaCreditoList.get(0);

		TarjetaCreditoEntity resultEntity = tarjetaCreditoService.getTarjetaCredito(tarjetaCreditoEntity.getId());
		assertNotNull(resultEntity);

		assertEquals(tarjetaCreditoEntity.getId(), resultEntity.getId());
		assertEquals(tarjetaCreditoEntity.getCodigoSeguridad(), resultEntity.getCodigoSeguridad());
		assertEquals(tarjetaCreditoEntity.getFechaVencimiento(), resultEntity.getFechaVencimiento());
		assertEquals(tarjetaCreditoEntity.getNumeroTarjeta(), resultEntity.getNumeroTarjeta());
	}
	
	@Test
	void testGetInvalidPse() 
	{
		assertThrows(EntityNotFoundException.class, ()->{
			tarjetaCreditoService.getTarjetaCredito(0L);
		});
	}

}
