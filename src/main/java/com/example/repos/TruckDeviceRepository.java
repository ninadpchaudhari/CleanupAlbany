package com.example.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.PersonalDevice;
import com.example.models.Student;
import com.example.models.TruckDevice;

public interface TruckDeviceRepository extends JpaRepository<TruckDevice, Long> {

	public TruckDevice findById(long id);
	public TruckDevice findByVehicleId(long vehicleId);
}
