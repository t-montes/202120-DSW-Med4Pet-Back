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
import co.edu.uniandes.dse.med4pet.entities.VeterinarioEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
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
    private List<VeterinarioEntity> vetList = new ArrayList<VeterinarioEntity>();
    
    
	@BeforeEach
	void setUp() throws Exception {
		clearData();
        insertData();
	}
	
	private void clearData() {
        entityManager.getEntityManager().createQuery("delete from RegistroMedicoEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from VeterinarioEntity").executeUpdate();
}
	private void insertData() {
        for (int i = 0; i < 3; i++) {
                RegistroMedicoEntity registroMedicoEntity = factory.manufacturePojo(RegistroMedicoEntity.class);
                entityManager.persist(registroMedicoEntity);
                registroMedicoList.add(registroMedicoEntity);
        }
        for (int i = 0; i < 3; i++) {
            VeterinarioEntity vete = factory.manufacturePojo(VeterinarioEntity.class);
            entityManager.persist(vete);
            vetList.add(vete);
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
	@Test
	void TestGetRegistroMedico() throws EntityNotFoundException
	{
		RegistroMedicoEntity registro = registroMedicoList.get(0);
		RegistroMedicoEntity resultEntity = registroMedicoService.getRegistroMedico(registro.getId());
		assertNotNull(resultEntity);
		assertEquals(registro.getId(),resultEntity.getId());
		assertEquals(registro.getFechaExpedicion(),resultEntity.getFechaExpedicion());
		assertEquals(registro.getIdentificacion(),resultEntity.getIdentificacion());
		assertEquals(registro.getImagen(),resultEntity.getImagen());
	}
	@Test 
	void TestCreateRegistroMedico() throws IllegalOperationException
	{
		RegistroMedicoEntity registro = factory.manufacturePojo(RegistroMedicoEntity.class);
		registro.setVeterinario(vetList.get(0));
		RegistroMedicoEntity resultEntity = registroMedicoService.createRegistroMedico(registro);
		assertNotNull(resultEntity);
		
		RegistroMedicoEntity result = entityManager.find(RegistroMedicoEntity.class, resultEntity.getId());
		
		assertEquals(registro.getId(),result.getId());
		assertEquals(registro.getFechaExpedicion(),result.getFechaExpedicion());
		assertEquals(registro.getIdentificacion(),result.getIdentificacion());
		assertEquals(registro.getImagen(),result.getImagen());
	}
	@Test
	void TestCreateRegistroMedicoInvalidVeterinario()
	{
		assertThrows(IllegalOperationException.class, () -> {
			RegistroMedicoEntity newEntity = factory.manufacturePojo(RegistroMedicoEntity.class);
			VeterinarioEntity veterinario = new VeterinarioEntity();
			veterinario.setId(0L);
			newEntity.setVeterinario(veterinario);
			registroMedicoService.createRegistroMedico(newEntity);
		});
	}
}
