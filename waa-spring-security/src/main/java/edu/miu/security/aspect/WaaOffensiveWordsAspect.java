package edu.miu.security.aspect;

import edu.miu.security.entity.OffensiveWordsAudit;
import edu.miu.security.entity.RequestLimiter;
import edu.miu.security.entity.Review;
import edu.miu.security.entity.User;
import edu.miu.security.model.UserDetailsAdapter;
import edu.miu.security.repository.RequestLimiterRepository;
import edu.miu.security.repository.UserRepository;
import edu.miu.security.service.OffensiveWordsAuditService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Component
@RequiredArgsConstructor
public class WaaOffensiveWordsAspect {

    private static final Set<String> OFFENSIVE_WORDS = Stream.of("spring", "reactjs").collect(Collectors.toSet());

    private final OffensiveWordsAuditService offensiveWordsAuditService;

    private final RequestLimiterRepository requestLimiterRepository;
    private final UserRepository userRepository;

    @Pointcut("@annotation(edu.miu.security.annotation.InspectOffense)")
    public void inspectOffenseAnnotation() {

    }

    @Around("inspectOffenseAnnotation()")
    public Object maskOutOffensiveWords(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        Set<Review> reviews = (Set<Review>) args[0];
        Set<String> offensiveWords = new HashSet<>();
        for(Review review : reviews) {
           OFFENSIVE_WORDS.forEach(w -> {
               String content = review.getContent();
               if(content.contains(w)) {
                   offensiveWords.add(w);
               }
               String maskedData = content.replaceAll(w, maskOutData(w));
               review.setContent(maskedData);
           });
        }
        if(!offensiveWords.isEmpty()) {



            UserDetailsAdapter userDetailsAdapter =  (UserDetailsAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByEmail(userDetailsAdapter.getUsername())
                    .orElseThrow(() -> new RuntimeException("Cannot find user: " + userDetailsAdapter.getUsername()));

            RequestLimiter requestLimiter = requestLimiterRepository.findFirstByOrderByCreatedAtDesc();
            if(requestLimiter != null && requestLimiter.getCreatedAt() != null &&
                    requestLimiter.getCreatedAt().plusMinutes(15).isAfter(LocalDateTime.now())) {
                long time = ChronoUnit.MINUTES.between(LocalDateTime.now(), requestLimiter.getCreatedAt().plusMinutes(15));
                throw new RuntimeException("Max Bad Words Requests Limit has been Reached. You need wait for " + time + " minutes.");
            }

            Set<OffensiveWordsAudit> offensiveWordsAudits = offensiveWordsAuditService.findByLast30Mins(user.getId());
            if(offensiveWordsAudits.size() >= 5) {
                requestLimiterRepository.save(RequestLimiter.builder()
                        .createdAt(LocalDateTime.now())
                        .user(user)
                        .build());
            }

            OffensiveWordsAudit offensiveWordsAudit = OffensiveWordsAudit.builder()
                    .createdAt(LocalDateTime.now())
                    .offensiveWords(offensiveWords)
                    .user(user)
                    .build();
            offensiveWordsAuditService.save(offensiveWordsAudit);
        }
        Object result = proceedingJoinPoint.proceed();
        return result;
    }

    private String maskOutData(String word) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < word.length(); i++) {
            sb.append("*");
        }
        return sb.toString();
    }

}
