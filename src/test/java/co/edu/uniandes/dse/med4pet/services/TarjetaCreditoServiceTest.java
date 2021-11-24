package co.edu.uniandes.dse.med4pet.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
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
import co.edu.uniandes.dse.med4pet.entities.TarjetaCreditoEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
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
	void testCreateTarjetaCredito() throws IllegalOperationException, EntityNotFoundException
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
	public void testCreateTarjetaCreditoWithNoValidNumeroTarjeta()
	{
		assertThrows(IllegalOperationException.class, () -> {
			TarjetaCreditoEntity newEntity = factory.manufacturePojo(TarjetaCreditoEntity.class);
			newEntity.setNumeroTarjeta(null);
			newEntity.setFechaVencimiento(Calendar.getInstance().getTime());
			newEntity.setCodigoSeguridad("aaa");
			tarjetaCreditoService.createTarjetaCredito(newEntity);
		});
	}
	
	public void testCreateTarjetaCreditoWithNoValidCodigoSeguridad()
	{
		assertThrows(IllegalOperationException.class, () -> {
			TarjetaCreditoEntity newEntity = factory.manufacturePojo(TarjetaCreditoEntity.class);
			newEntity.setNumeroTarjeta("asjfakjsdfh");
			newEntity.setFechaVencimiento(Calendar.getInstance().getTime());
			newEntity.setCodigoSeguridad(null);
			tarjetaCreditoService.createTarjetaCredito(newEntity);
		});
	}
	
	@Test
	void testGetTarjetaCredito() throws EntityNotFoundException
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
	void testGetInvalidTarjetaCredito() 
	{
		assertThrows(EntityNotFoundException.class, ()->{
			tarjetaCreditoService.getTarjetaCredito(0L);
		});
	}

}
