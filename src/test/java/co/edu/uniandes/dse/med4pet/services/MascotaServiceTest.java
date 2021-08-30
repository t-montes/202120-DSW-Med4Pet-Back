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

import co.edu.uniandes.dse.med4pet.entities.MascotaEntity;
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
	void testGetMascota() {
		  List<MascotaEntity> list = mascotaService.getMascotas();
          assertEquals(list.size(), mascotaList.size());
          
          MascotaEntity mascota1 = mascotaList.get(0);
          MascotaEntity mascota2 = entityManager.find(MascotaEntity.class, mascota1.getId());
          
          assertEquals(mascota1.getNombre(), mascota2.getNombre());
          assertEquals(mascota1.getRaza(), mascota2.getRaza());
          assertEquals(mascota1.getUbicacion(), mascota2.getUbicacion());
          assertEquals(mascota1.getEdad(), mascota2.getEdad());
	}

}