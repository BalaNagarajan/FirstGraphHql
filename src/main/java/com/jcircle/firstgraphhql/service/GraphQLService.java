package com.jcircle.firstgraphhql.service;

import com.jcircle.firstgraphhql.model.Vehicle;
import com.jcircle.firstgraphhql.repository.VehicleRepository;
import com.jcircle.firstgraphhql.service.datafetcher.AllVehiclesDataFetcher;
import com.jcircle.firstgraphhql.service.datafetcher.VehicleDataFetcher;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

@Service
public class GraphQLService {

    @Value("classpath:vehicle.graphql")
    Resource resource;

    private GraphQL graphQL;
    @Autowired
    private AllVehiclesDataFetcher allVehiclesDataFetcher;
    @Autowired
    private VehicleDataFetcher vehicleDataFetcher;
    @Autowired
    VehicleRepository vehicleRepository;

     //Load the graphql file during the application startup
     @PostConstruct
     public void loadSchema() throws IOException {
          //load vehicle datas into HSQL();
           loadVehicleDatasIntoHSQL();
          //get the schema file
          File schemaFileObj = resource.getFile();
          //parse the schema file
         TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFileObj);
         RuntimeWiring wiring = buildRuntimeWiring();
         GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry,wiring);
         this.graphQL = GraphQL.newGraphQL(schema).build();

     }

     private RuntimeWiring buildRuntimeWiring() {
         return RuntimeWiring.newRuntimeWiring().type("Query",typeRuntimeWiring -> {
             return typeRuntimeWiring.dataFetcher("allVehicles",allVehiclesDataFetcher)
                              .dataFetcher("vehicle",vehicleDataFetcher); } ).build();

     }

     public GraphQL getGraphQL() {
         return this.graphQL;
     }

     private void loadVehicleDatasIntoHSQL() {


         Stream.of(
               new Vehicle("1234","Honda","02/02/1998"),
                 new Vehicle("4567","Toyota","03/01/2013"),
                 new Vehicle("8976","Cadillac","03/04/2001")
         ).forEach(vehicle -> {
             vehicleRepository.save(vehicle);
         });

     }

}
