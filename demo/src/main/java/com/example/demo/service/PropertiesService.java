package com.example.demo.service;

import com.example.demo.entity.Properties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class PropertiesService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(rollbackFor = Exception.class)
    public  void addBatch(List<Properties> list) {
        for (Properties t : list) {
            entityManager.persist(t);//insert插入操作
        }
        entityManager.flush();
        entityManager.clear();
    }


}
