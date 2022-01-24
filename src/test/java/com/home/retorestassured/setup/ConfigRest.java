package com.home.retorestassured.setup;

import com.home.retorestassured.stepdefinition.RegistrationStepDefinition;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import static com.home.retorestassured.utils.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static io.cucumber.messages.internal.com.google.common.base.StandardSystemProperty.USER_DIR;

public class ConfigRest {

    private static final String BASE_URL = "https://reqres.in/";
    private static final String BASE_PATH = "api/";
    protected static final String RESOURCE_REGISTER = "/register";
    protected static final String RESOURCE_USERS = "/users/";

    protected static final Logger LOGGER = Logger.getLogger(RegistrationStepDefinition.class);
    protected static Response response;

    protected void generalSetUp(){
        configurationForRestAssured();
        setUplog4j();
    }

    private void configurationForRestAssured(){
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = BASE_PATH;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    private void setUplog4j(){
        PropertyConfigurator.configure(USER_DIR.value() + LOG4J_PROPERTIES_FILE_PATH.getValue());
    }

}
