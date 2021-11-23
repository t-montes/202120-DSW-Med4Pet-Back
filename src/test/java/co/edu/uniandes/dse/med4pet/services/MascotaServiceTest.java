package co.edu.uniandes.dse.med4pet.services;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
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

import co.edu.uniandes.dse.med4pet.entities.MascotaEntity;
import co.edu.uniandes.dse.med4pet.entities.TarjetaCreditoEntity;
import co.edu.uniandes.dse.med4pet.entities.TipoMascota;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(MascotaService.class)

class MascotaServiceTest {
	@Autowired
	private MascotaService mascotaService;
	
	@Autowired
	private TestEntityManager entityManager;
	
	private PodamFactory factory = new PodamFactoryImpl();
	private List<MascotaEntity> mascotaList = new ArrayList<>();
	
	private void insertData() {
        for (int i = 0; i < 3; i++) {
        	MascotaEntity mascotaEntity = factory.manufacturePojo(MascotaEntity.class);
                entityManager.persist(mascotaEntity);
                mascotaList.add(mascotaEntity);
        }
	}

	 private void clearData() {
         entityManager.getEntityManager().createQuery("delete from MascotaEntity").executeUpdate();
	 }

	@BeforeEach
	void setUp() throws Exception {
		 clearData();
         insertData();
	}

	@Test
	void testGetMascotas() {
		  List<MascotaEntity> list = mascotaService.getMascotas();
          assertEquals(list.size(), mascotaList.size());
          
          MascotaEntity mascota1 = mascotaList.get(0);
          MascotaEntity mascota2 = entityManager.find(MascotaEntity.class, mascota1.getId());
          
          assertEquals(mascota1.getNombre(), mascota2.getNombre());
          assertEquals(mascota1.getRaza(), mascota2.getRaza());
          assertEquals(mascota1.getUbicacion(), mascota2.getUbicacion());
          assertEquals(mascota1.getFechaNacimiento(), mascota2.getFechaNacimiento());
	}
	@Test
	void testGetMascota() throws EntityNotFoundException {
		MascotaEntity entity = mascotaList.get(0);
		MascotaEntity resultEntity = mascotaService.getMascota(entity.getId());
		assertNotNull(resultEntity);
		assertEquals(entity.getId(), resultEntity.getId());
		assertEquals(entity.getNombre(), resultEntity.getNombre());
		assertEquals(entity.getTipoMascota(), resultEntity.getTipoMascota());
		assertEquals(entity.getRaza(), resultEntity.getRaza());
		assertEquals(entity.getUbicacion(), resultEntity.getUbicacion());
		assertEquals(entity.getFechaNacimiento(), resultEntity.getFechaNacimiento());
		assertEquals(entity.getEdad(), resultEntity.getEdad());
		assertEquals(entity.getDuenio(), resultEntity.getDuenio());
		assertEquals(entity.getCitasAtendidas(), resultEntity.getCitasAtendidas());
	}
	@Test
	void testGetInvalidMascota() {
		assertThrows(EntityNotFoundException.class, ()->{
			mascotaService.getMascota(0L);
		});
	}
	
	@Test
	void testCreateMascota()
	{
		MascotaEntity newEntity = factory.manufacturePojo(MascotaEntity.class);
		MascotaEntity result = mascotaService.createMascota(newEntity);
		assertNotNull(result);

		MascotaEntity entity = entityManager.find(MascotaEntity.class, result.getId());

		assertEquals(newEntity.getId(), entity.getId());
		assertEquals(newEntity.getNombre(), entity.getNombre());
		assertEquals(newEntity.getTipoMascota(), entity.getTipoMascota());
		assertEquals(newEntity.getRaza(), entity.getRaza());
		assertEquals(newEntity.getUbicacion(), entity.getUbicacion());
		assertEquals(newEntity.getFechaNacimiento(), entity.getFechaNacimiento());
		assertEquals(newEntity.getEdad(), entity.getEdad());
	}
	
	


}