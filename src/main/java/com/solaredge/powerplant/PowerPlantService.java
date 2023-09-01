package com.solaredge.powerplant;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.solaredge.exception.NotFoundException;

@Service
public class PowerPlantService {
	private final PowerPlantDao powerPlantDao;

	public PowerPlantService(PowerPlantDao powerPlantDAO) {
		this.powerPlantDao = powerPlantDAO;
	}

	public List<PowerPlant> readList() {
		return powerPlantDao.getAll();
	}

	public PowerPlant readById(UUID id) {
		return powerPlantDao.get(id)
				.orElseThrow(() -> new NotFoundException(String.format("Power plant with id %s not found", id)));
	}

	public void create(PowerPlant powerPlant) {
		int result = powerPlantDao.insert(powerPlant);

		if (result != 1) {
			throw new IllegalStateException("Something went wrong on inserting new power plant");
		}
	}

	/*
	 * Field *name* is only updatable field
	 */
	public void update(String value) {
		int result = powerPlantDao.update(value);

		if (result != 1) {
			throw new IllegalStateException("Something went wrong when updating a power plant");
		}
	}

	public void delete(UUID id) {
		Optional<PowerPlant> powerPlant = powerPlantDao.get(id);

		powerPlant.ifPresentOrElse(item -> {
			int result = powerPlantDao.delete(id);

			if (result != 1) {
				throw new IllegalStateException("Could not delete a power plant");
			}
		}, () -> {
			throw new NotFoundException(String.format("Power plant with id %s not found", id));
		});
	}
}
