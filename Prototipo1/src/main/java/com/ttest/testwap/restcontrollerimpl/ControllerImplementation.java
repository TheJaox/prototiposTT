package com.ttest.testwap.restcontrollerimpl;

import com.ttest.test.Test;
import com.ttest.testwap.model.Step;
import com.ttest.testwap.repository.UseCaseRepository;
import com.ttest.testwap.model.UseCase;
import com.ttest.testwap.restcontroller.TestServiceInterface;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Level;
//import kotlin.concurrent.thread

@RestController
public class TestService implements TestServiceInterface {
    
    @Autowired
    private UseCaseRepository useCaseRepository;

    @Override
    public List<UseCase> retrieveUseCases() {
        return useCaseRepository.findAll();
    }

    @Override
    public UseCase retrieveUseCase(@PathVariable String id) {
        return useCaseRepository.findById(id).get();
    }

    @Override
    public UseCase createUseCase(@RequestBody UseCase useCase) {
        return useCaseRepository.save(useCase);
    }

    @Override
    public UseCase updateUseCase(@RequestBody UseCase useCase) {
        return useCaseRepository.save(useCase);
    }

    @Override
    public UseCase deleteUseCase(@RequestBody UseCase useCase) {
//        useCaseRepository.delete(useCase);
        return useCase
    }

    @Override
    public List<Step> retrieveUseCaseSteps(@PathVariable String useCaseId) {
        UseCase useCase = new UseCase(useCaseId, "", mutableListOf());
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("name", "steps");
        Example<UseCase> example = Example.of(useCase, matcher);
        return useCaseRepository.findOne(example).get().steps;
    }

    @Override
    public Boolean executeUseCase(@PathVariable String useCaseId) {
        UseCase useCase = useCaseRepository.findById(useCaseId).get();
        Boolean result = Boolean.FALSE;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //String driverPath = "D:\\Programs\\geckodriver-v0.21.0-win64\\geckodriver.exe";
                //String firefoxPath = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
                //System.setProperty("webdriver.firefox.bin", firefoxPath);
                String driverPath = "/opt/geckodriver-v0.21.0-linux64/geckodriver";
                String firefoxPath = "/usr/bin/firefox";
                System.setProperty("webdriver.firefox.bin", firefoxPath);
                System.setProperty("webdriver.gecko.driver", driverPath);
                FirefoxOptions options = new FirefoxOptions();
                options.setLogLevel(FirefoxDriverLogLevel.fromLevel(Level.OFF));
                WebDriver driver = FirefoxDriver(options);
                Test test = new Test(driver, useCaseId, useCase.steps);
                test.build();
                result = test.run();
                driver.close(); 
            }
        });
        thread.start();
        return result;
    }


}