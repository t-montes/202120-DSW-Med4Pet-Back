package co.edu.uniandes.dse.med4pet.services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.med4pet.entities.TarjetaCreditoEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.repositories.TarjetaCreditoRepository;

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
		Optional<TarjetaCreditoEntity> tarjetaCreditoEntity = tarjetaCreditoRepository.findById(tarjetaCreditoId);
		if (tarjetaCreditoEntity.isEmpty())
			throw new EntityNotFoundException("Tarjeta credito not found");
		return tarjetaCreditoEntity.get();
	}
	
	// Crear una instancia
	@Transactional
	public TarjetaCreditoEntity createTarjetaCredito(TarjetaCreditoEntity tarjetaCredito)
	{
		return tarjetaCreditoRepository.save(tarjetaCredito);
	}
}
