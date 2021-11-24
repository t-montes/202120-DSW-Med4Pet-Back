package co.edu.uniandes.dse.med4pet.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.dse.med4pet.dto.PSEDTO;
import co.edu.uniandes.dse.med4pet.dto.TarjetaCreditoDTO;
import co.edu.uniandes.dse.med4pet.entities.PSEEntity;
import co.edu.uniandes.dse.med4pet.entities.TarjetaCreditoEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.med4pet.services.TarjetaCreditoService;

@RestController
@RequestMapping("/tarjetasCredito")
public class TarjetaCreditoController{
	
	@Autowired
	private TarjetaCreditoService tarjetaCreditoService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<TarjetaCreditoDTO> findAll()
	{
		List<TarjetaCreditoEntity> tarjetasCredito = tarjetaCreditoService.getTarjetasCredito();
		return modelMapper.map(tarjetasCredito, new TypeToken<List<TarjetaCreditoDTO>>(){}.getType());
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public TarjetaCreditoDTO findOne(@PathVariable("id") Long id) throws EntityNotFoundException
	{
		TarjetaCreditoEntity tarjetaEntity = tarjetaCreditoService.getTarjetaCredito(id);
		return modelMapper.map(tarjetaEntity, TarjetaCreditoDTO.class);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public TarjetaCreditoDTO create(@RequestBody TarjetaCreditoDTO tarjetaCreditoDTO) throws IllegalOperationException
	{
		TarjetaCreditoEntity tarjetaCreditoEntity = tarjetaCreditoService.createTarjetaCredito(modelMapper.map(tarjetaCreditoDTO, TarjetaCreditoEntity.class));
		return modelMapper.map(tarjetaCreditoEntity, TarjetaCreditoDTO.class);
	}
}
