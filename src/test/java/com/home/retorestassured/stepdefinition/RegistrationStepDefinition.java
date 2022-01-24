package com.home.retorestassured.stepdefinition;

import com.google.gson.Gson;
import com.home.retorestassured.model.User;
import com.home.retorestassured.setup.ConfigRest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class RegistrationStepDefinition extends ConfigRest {


    @Given("el usuario tiene acceso al aplicativo")
    public void elUsuarioEstaEnLaPagina() {
        try {
            generalSetUp();
        } catch (Exception e){
            LOGGER.error("FALLO: No se pudo establecer conexión con la página");
            Assertions.fail(e.getMessage());
        }
    }
    
    @When("el usuario realiza una petición de registro con el correo de usuario {string} y la contraseña {string}")
    public void elUsuarioRealizaUnaPeticionDeRegistro(String email, String password) {
        try {
            User user = new User(email,password);
            response = given().body(new Gson().toJson(user))
                    .post(RESOURCE_REGISTER);
        } catch (Exception e){
            LOGGER.error("FALLO: No se realizó la petición");
            Assertions.fail(e.getMessage());
        }

    }

    @Then("obtendrá un código de respuesta exitoso")
    public void elUsuarioVeraUnCodigoDeRespuestaExitoso() {
        try {
            response.then()
                    .statusCode(HttpStatus.SC_OK);
        } catch (Exception e){
            LOGGER.error("FALLO, no se obtuvo el código de respuesta esperado");
            Assertions.fail(e.getMessage());
        }
    }

    @Then("se le asignará al usuario un identificador y un token de respuesta")
    public void seLeAsignaraAlUsuarioUnIdentificadorYUnTokenDeRespuesta() {
        try {
            response.then()
                    .body("id",notNullValue())
                    .body("token", notNullValue());
        } catch (Exception e){
            LOGGER.error("FALLO, No se obtuvieron resultados esperados en el body");
            Assertions.fail(e.getMessage());
        }
    }

}
