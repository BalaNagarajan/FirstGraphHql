package com.jcircle.firstgraphhql.service.datafetcher;

import com.jcircle.firstgraphhql.model.Vehicle;
import com.jcircle.firstgraphhql.repository.VehicleRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.DatabaseMetaData;

@Component
public class VehicleDataFetcher implements DataFetcher<Vehicle> {

    @Autowired
    VehicleRepository vehicleRepository;


    @Override
    public Vehicle get(DataFetchingEnvironment dataFetchingEnvironment) {
        String vinNo = dataFetchingEnvironment.getArgument("no");
       return vehicleRepository.getOne(vinNo);



    }
}
