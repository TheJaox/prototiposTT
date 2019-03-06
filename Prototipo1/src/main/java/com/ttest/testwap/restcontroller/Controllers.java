package com.ttest.testwap.restcontroller;

import com.reus.testwap.model.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/testservice")
public interface TestServiceInterface {

    @RequestMapping("/usecases", method = [RequestMethod.GET])
    public List<UseCase> retrieveUseCases();

    @RequestMapping("/usecases/{id}", method = [RequestMethod.GET])
    public UseCase retrieveUseCase(String id);

    @RequestMapping("/usecases", method = [RequestMethod.POST])
    public UseCase createUseCase(UseCase useCase);

    @RequestMapping("/usecases", method = [RequestMethod.PUT])
    public UseCase updateUseCase(UseCase useCase);

    @RequestMapping("/usecases", method = [RequestMethod.DELETE])
    public UseCase deleteUseCase(UseCase useCase);

    @RequestMapping("/usecases/{useCaseId}/steps", method = [RequestMethod.GET])
    public List<Step> retrieveUseCaseSteps(String useCaseId);

    @RequestMapping("/usecases/execute/{useCaseId}", method = [RequestMethod.GET])
    public Boolean executeUseCase(String useCaseId);

}
