package com.example.demo.repository;

import com.example.demo.entity.Properties;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface PropertiesRepository  extends CrudRepository<Properties, Long>, JpaSpecificationExecutor<Properties> {
}
