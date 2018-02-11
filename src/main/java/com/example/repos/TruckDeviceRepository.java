package com.example.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.PersonalDevice;
import com.example.models.Student;
import com.example.models.TruckDevice;

public interface TruckDeviceRepository extends JpaRepository<TruckDevice, Long> {

	public TruckDevice findById(long id);
	public TruckDevice findByVehicleId(long vehicleId);
	public List<TruckDevice> findByTypeAndBusyAndFcmtokenNotNull(String type,boolean busy);
}
