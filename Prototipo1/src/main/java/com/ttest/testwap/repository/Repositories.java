package com.reus.testwap.repository;

import com.reus.testwap.model.Step;
import com.reus.testwap.model.UseCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UseCaseRepository extends JpaRepository<UseCase, String>;

public interface UseCaseStepRepository extends JpaRepository<Step, Integer>;
