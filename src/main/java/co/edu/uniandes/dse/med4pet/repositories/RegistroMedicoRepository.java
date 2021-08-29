package co.edu.uniandes.dse.med4pet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.med4pet.entities.RegistroMedicoEntity;
@Repository
public interface RegistroMedicoRepository extends JpaRepository<RegistroMedicoEntity,Long> 
{

}
