package com.example.week8.mappers;

import org.springframework.stereotype.Repository;
import com.example.week8.models.Order;

import java.util.List;
import java.util.Map;


@Repository
public interface OrderMapper {

    void insert(Order order);
    void delete(Long id);
    void update(Order order);
    List<Map<String, Object>> query(Map<String, Object> condition);
}
