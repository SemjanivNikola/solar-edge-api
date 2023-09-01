package com.solaredge.powerplant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PowerPlantDao {
	List<PowerPlant> getAll();
	Optional<PowerPlant> get(UUID id); 
	int insert(PowerPlant powerPlant);
	int update(String value);
	int delete(UUID id);
}
