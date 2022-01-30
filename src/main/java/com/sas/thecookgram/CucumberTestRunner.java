package com.sas.thecookgram;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/features", glue = {"com.sas.thecookgram.steps"})
public class CucumberTestRunner {
    public static void main(String[] args) {
        io.cucumber.core.cli.Main.main(args);
    }
}

