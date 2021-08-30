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

import co.edu.uniandes.dse.med4pet.entities.EmpresaConvenioEntity;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(EmpresaConvenioService.class)

class EmpresaConvenioServiceTest {
	@Autowired
	private EmpresaConvenioService empresaConvenioService;
	
	@Autowired
	private TestEntityManager entityManager;
	
	private PodamFactory factory = new PodamFactoryImpl();
	
	private List<EmpresaConvenioEntity> empresaConvenioList = new ArrayList<>();
	
	private void insertData() {
        for (int i = 0; i < 3; i++) {
        	EmpresaConvenioEntity empresaConvenioEntity = factory.manufacturePojo(EmpresaConvenioEntity.class);
                entityManager.persist(empresaConvenioEntity);
                empresaConvenioList.add(empresaConvenioEntity);
        }
	}

	 private void clearData() {
         entityManager.getEntityManager().createQuery("delete from EmpresaConvenioEntity").executeUpdate();
	 }

	@BeforeEach
	void setUp() throws Exception {
		 clearData();
         insertData();
	}

	@Test
	void testGetEmpresaConvenio() {
		  List<EmpresaConvenioEntity> list = empresaConvenioService.getEmpresasConvenio();
          assertEquals(list.size(), empresaConvenioList.size());
          
          EmpresaConvenioEntity empresaConvenio1 = empresaConvenioList.get(0);
          EmpresaConvenioEntity empresaConvenio2 = entityManager.find(EmpresaConvenioEntity.class, empresaConvenio1.getId());
          
          assertEquals(empresaConvenio1.getNombre(), empresaConvenio2.getNombre());
          assertEquals(empresaConvenio1.getNit(), empresaConvenio2.getNit());
          assertEquals(empresaConvenio1.getServicio(), empresaConvenio2.getServicio());
	}

}
