package com.example.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.TruckDevice;
import com.example.repos.TruckDeviceRepository;
import com.exmaple.misc.ConnectMSSQLServer;

@Service
public class GPSDataService {

	@Autowired
	TruckDeviceRepository truckRepo;
	public boolean updateTruckLocations() {
		ConnectMSSQLServer connServer = new ConnectMSSQLServer();
		try {
			Connection conn = connServer.dbConnect();
		      Statement statement = conn.createStatement();
		         String queryString = "select VehicleID,max(StartTime) as MaxStartTime FROM dbo.VehicleEvent where EventTypeID=1 GROUP BY VehicleID";
		         System.out.println(queryString);
		         ResultSet rs = statement.executeQuery(queryString);
		         HashMap <String,String> vehicleTime = new HashMap<String,String>();
		         
		         while (rs.next()) {
		        	vehicleTime.put( rs.getString("VehicleID") , rs.getString("MaxStartTime"));
		            System.out.println(rs.getString(1));
		         }
		         Iterator<HashMap.Entry<String,String>> it = vehicleTime.entrySet().iterator();
		         while(it.hasNext()) {
		        	 HashMap.Entry<String, String> pair = it.next();
		        	 queryString = "SELECT VehicleID,Latitude,Longitude,StartTime,Location from dbo.VehicleEvent where EventTypeID = 1 AND VehicleID= " +pair.getKey()+ " AND StartTime = '" + pair.getValue()+"'";
		        	 System.out.println(queryString);
		        	 ResultSet rs2 = statement.executeQuery(queryString);
		        	 while(rs2.next()) {
		        		 String latitude = rs2.getString("Latitude");
		        		 String longitude = rs2.getString("Longitude");
		        		 String vehicleId = rs2.getString("VehicleID");
		        		 
		 
		        		 //String lastUpdate = rs2.getString("StartTime");
		        		 //String address = rs2.getString("Location");
		        		 
		        		 TruckDevice td = truckRepo.findByVehicleId(Long.parseLong(vehicleId));
		        		 if(td == null ) {
		        			 //The truck does not exist, create it
		        			 String type = new String();
		        			 //Truck with even no is snow
		        			 if(Long.parseLong(vehicleId) %2 ==0) type = "snow";
		        			 else type="garbage";
		        			 td = new TruckDevice(Long.parseLong(vehicleId),type,latitude,longitude);
		        		 }
		        		 td.setLat(latitude);
		        		 td.setLng(longitude);
		        		 truckRepo.save(td);
		        		 
		        	 }
		         }
		         
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
