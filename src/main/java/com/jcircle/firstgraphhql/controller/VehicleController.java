package com.jcircle.firstgraphhql.controller;

import com.jcircle.firstgraphhql.service.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/vehicles")
@RestController
@CrossOrigin
public class VehicleController {

    @Autowired
    GraphQLService graphQLService;

    @PostMapping
    public ResponseEntity<Object> getAllVehicles(@RequestBody String query) {

        ExecutionResult executionResult =  graphQLService.getGraphQL().execute(query);
        return new ResponseEntity<Object>(executionResult, HttpStatus.OK);

    }

}
