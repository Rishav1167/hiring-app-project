package com.candidateonboardingsystem.domain.enums;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public enum CandidateStatus {
    APPLIED,
    INTERVIEWED,
    OFFERED,
    REJECTED;

    private static final Map<CandidateStatus, Set<CandidateStatus>> validTransitions = Map.of(
            APPLIED, EnumSet.of(INTERVIEWED, REJECTED),
            INTERVIEWED, EnumSet.of(OFFERED, REJECTED),
            OFFERED, EnumSet.noneOf(CandidateStatus.class),
            REJECTED, EnumSet.noneOf(CandidateStatus.class)
    );

    public boolean canTransitionTo(CandidateStatus nextStatus) {
        return validTransitions
                .getOrDefault(this, EnumSet.noneOf(CandidateStatus.class))
                .contains(nextStatus);
    }
}
