package com.exmaple.misc;

import org.joda.time.DateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

public class GoogleDistanceApi {
	
		//DistanceMatrixApiRequest req = DistanceMatrixApi.getDistanceMatrix(context,new String[] {"",""}, new String[] {"",""});
		public static DistanceMatrix estimateRoute() {
			GeoApiContext context = new GeoApiContext.Builder()
				    .apiKey("AIzaSyDXxCOHRlN_oeRyLNl78i51IoanE_mF5Gk")
				    .build();
			try {
		        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
		        
		            req.departureTime(DateTime.now());
		        
		        
		            DirectionsApi.RouteRestriction routeRestriction = DirectionsApi.RouteRestriction.TOLLS;
		        
		        DistanceMatrix trix = req.origins(new LatLng(Double.parseDouble("42.82653400"),Double.parseDouble("-73.92746900")))
		                .destinations(new LatLng(Double.parseDouble("42.8148897"),Double.parseDouble("-73.950099")))
		                .mode(TravelMode.DRIVING)
		                .avoid(routeRestriction)
		                .await();
		       System.out.println(trix.toString());
		        return trix;

		    } catch (ApiException e) {
		        System.out.println(e.getMessage());
		    } catch (Exception e) {
		        System.out.println(e.getMessage());
		    }
		    return null;
		
		}

		/*
		 * 
		 
		GeocodingResult[] results =  GeocodingApi.geocode(context,
		    "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		System.out.println(gson.toJson(results[0].addressComponents));
		*/
}
