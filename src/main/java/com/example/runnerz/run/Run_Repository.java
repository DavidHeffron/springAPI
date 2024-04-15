package com.example.runnerz.run;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface Run_Repository extends ListCrudRepository<Run, Integer> {

    List<Run> findAllByLocation(String location);

}
