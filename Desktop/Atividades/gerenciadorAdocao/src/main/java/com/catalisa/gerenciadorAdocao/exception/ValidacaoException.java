package com.catalisa.gerenciadorAdocao.exception;

import com.catalisa.gerenciadorAdocao.model.AnimalModel;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ValidacaoException extends RuntimeException {

    private Set<ConstraintViolation<AnimalModel>> violations;

    public ValidacaoException(Set<ConstraintViolation<AnimalModel>> violations) {
        this.violations = violations;
    }

    public Set<ConstraintViolation<AnimalModel>> getViolations() {
        return violations;
    }
}

