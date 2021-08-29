package co.edu.uniandes.dse.med4pet.repositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniandes.dse.med4pet.entities.TarjetaCreditoEntity;

@Repository
public interface TarjetaCreditoRepository extends JpaRepository<TarjetaCreditoEntity, Long>
{

}
