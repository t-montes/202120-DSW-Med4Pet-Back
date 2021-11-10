package co.edu.uniandes.dse.med4pet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import co.edu.uniandes.dse.med4pet.entities.TarjetaCreditoEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.ErrorMessage;
import co.edu.uniandes.dse.med4pet.repositories.TarjetaCreditoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TarjetaCreditoService 
{
	@Autowired
	TarjetaCreditoRepository tarjetaCreditoRepository;
	
	// Obtener todas las instancias
	@Transactional
	public List<TarjetaCreditoEntity> getTarjetasCredito()
	{
		return tarjetaCreditoRepository.findAll();
	}
	
	// Obtener una instancia
	@Transactional
	public TarjetaCreditoEntity getTarjetaCredito(Long tarjetaCreditoId) throws EntityNotFoundException
	{
		log.info("Inicia el proceso de consultar la tarjeta con el id = {0}", tarjetaCreditoId);
		Optional<TarjetaCreditoEntity> tarjetaCreditoEntity = tarjetaCreditoRepository.findById(tarjetaCreditoId);
		if (tarjetaCreditoEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.TARJETA_NOT_FOUND);
		log.info("Termina el proceso de consultar la tarjeta con el id = {0}", tarjetaCreditoId);
		return tarjetaCreditoEntity.get();
	}
	
	// Crear una instancia
	@Transactional
	public TarjetaCreditoEntity createTarjetaCredito(TarjetaCreditoEntity tarjetaCredito)
	{
		return tarjetaCreditoRepository.save(tarjetaCredito);
	}
}
