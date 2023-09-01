package com.solaredge.powerplant;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/power-plant")
public class PowerPlantController {

	private final PowerPlantService powerPlantService;

	public PowerPlantController(PowerPlantService powerPlantService) {
		this.powerPlantService = powerPlantService;
	}

	@GetMapping
	public List<PowerPlant> readPowerPlantList() {
		return powerPlantService.readList();
	}

	@GetMapping("{id}")
	public PowerPlant readPowerPlantById(@PathVariable("id") UUID id) {
		return powerPlantService.readById(id);
	}

	@PostMapping
	public void createPowerPlant(@RequestBody PowerPlant powerPlant) {
		powerPlantService.create(powerPlant);
	}

	@PutMapping
	public void updatePowerPlant(@RequestBody String value) {
		powerPlantService.update(value);
	}

	@DeleteMapping
	public void deletePowerPlant(@PathVariable("id") UUID id) {
		powerPlantService.delete(id);
	}
}
