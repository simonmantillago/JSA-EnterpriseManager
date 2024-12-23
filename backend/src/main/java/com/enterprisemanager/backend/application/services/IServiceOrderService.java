package com.enterprisemanager.backend.application.services;

import java.util.List;
import java.util.Optional;

import com.enterprisemanager.backend.domain.entities.ServiceOrder;

public interface IServiceOrderService {
    ServiceOrder save(ServiceOrder serviceOrder);
    Optional<ServiceOrder> findById(Long id);
    List<ServiceOrder> findAll();
    List<ServiceOrder> findAllByEmployeeId(String id);
    List<ServiceOrder> findAllByCustomerId(String id);
    Optional<ServiceOrder> update(Long id, ServiceOrder serviceOrder);
    Optional<ServiceOrder> delete(Long id);
}
