package edu.miu.security.repository;

import edu.miu.security.entity.OffensiveWordsAudit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface OffensiveWordsAuditRepository extends CrudRepository<OffensiveWordsAudit, Integer> {

    Set<OffensiveWordsAudit> findByCreatedAtLessThanEqualAndUser_Id(LocalDateTime last30Minutes, int userId);
}

