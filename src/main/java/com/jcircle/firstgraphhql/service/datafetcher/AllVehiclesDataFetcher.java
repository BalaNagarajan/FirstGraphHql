package com.jcircle.firstgraphhql.service.datafetcher;

import com.jcircle.firstgraphhql.model.Vehicle;
import com.jcircle.firstgraphhql.repository.VehicleRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public class AllVehiclesDataFetcher implements DataFetcher<List<Vehicle>> {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> get(DataFetchingEnvironment dataFetchingEnvironment) {

        return vehicleRepository.findAll();
    }
}
