package com.sas.thecookgram.steps.config;

import com.sas.thecookgram.TheCookGramCucumberApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(loader = SpringBootContextLoader.class, classes = TheCookGramCucumberApplication.class)
public class CucumberSpringBootConfiguration {

}
