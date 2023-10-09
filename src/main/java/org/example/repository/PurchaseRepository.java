package org.example.repository;

import org.example.model.PurchaseElement;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<PurchaseElement, Long> {
}
