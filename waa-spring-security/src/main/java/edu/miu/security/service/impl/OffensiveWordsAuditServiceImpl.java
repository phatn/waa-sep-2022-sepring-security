package edu.miu.security.service.impl;

import edu.miu.security.entity.OffensiveWordsAudit;
import edu.miu.security.repository.OffensiveWordsAuditRepository;
import edu.miu.security.service.OffensiveWordsAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OffensiveWordsAuditServiceImpl implements OffensiveWordsAuditService {

    private final OffensiveWordsAuditRepository offensiveWordsAuditRepository;


    @Override
    @Transactional
    public void save(OffensiveWordsAudit offensiveWordsAudit) {
        offensiveWordsAuditRepository.save(offensiveWordsAudit);
    }

    @Override
    public Set<OffensiveWordsAudit> findByLast30Mins(int userId) {
        return offensiveWordsAuditRepository
                .findByCreatedAtLessThanEqualAndUser_Id(LocalDateTime.now().plusMinutes(30), userId);
    }
}
