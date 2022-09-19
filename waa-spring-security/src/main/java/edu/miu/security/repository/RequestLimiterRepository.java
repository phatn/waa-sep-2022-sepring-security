package edu.miu.security.repository;

import edu.miu.security.entity.RequestLimiter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLimiterRepository extends CrudRepository<RequestLimiter, Integer> {

    RequestLimiter findFirstByOrderByCreatedAtDesc();
}
