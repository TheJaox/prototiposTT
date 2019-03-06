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



}

/*
    override fun updateUseCase(@RequestBody useCase: UseCase): UseCase {
        return useCaseRepository.save(useCase)
    }

    override fun deleteUseCase(@RequestBody useCase: UseCase): UseCase {
//        useCaseRepository.delete(useCase)
        return useCase
    }

    override fun retrieveUseCaseSteps(@PathVariable useCaseId: String): List<Step> {
        val useCase = UseCase(useCaseId, "", mutableListOf())
        val matcher = ExampleMatcher.matching().withIgnorePaths("name", "steps")
        val example : Example<UseCase> = Example.of(useCase, matcher)
        return useCaseRepository.findOne(example).get().steps
    }
    */

/*
    override fun executeUseCase(@PathVariable useCaseId: String): Boolean {
        val useCase = useCaseRepository.findById(useCaseId).get()
        var result: Boolean = false
        val t = thread {
            // val driverPath = "D:\\Programs\\geckodriver-v0.21.0-win64\\geckodriver.exe"
            val driverPath = "/opt/geckodriver-v0.21.0-linux64/geckodriver"
            //System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe")
            System.setProperty("webdriver.firefox.bin", "/usr/bin/firefox")
            System.setProperty("webdriver.gecko.driver", driverPath)
            val options = FirefoxOptions()
            options.setLogLevel(FirefoxDriverLogLevel.fromLevel(Level.OFF))
            val driver: WebDriver = FirefoxDriver(options)
            val test = Test(driver, useCaseId, useCase.steps)
            test.build()
            result = test.run()
            driver.close()
        }
        t.join()
        return result
    }
    */