package edu.miu.security.service;

import edu.miu.security.entity.OffensiveWordsAudit;

import java.util.Set;

public interface OffensiveWordsAuditService {

    void save(OffensiveWordsAudit offensiveWordsAudit);

    Set<OffensiveWordsAudit> findByLast30Mins(int userId);
}
