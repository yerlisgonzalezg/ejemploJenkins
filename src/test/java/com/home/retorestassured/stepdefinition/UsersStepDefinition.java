package com.home.retorestassured.stepdefinition;
import com.home.retorestassured.setup.ConfigRest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class UsersStepDefinition extends ConfigRest {

    @When("consulta la información de un usuario con id {int}")
    public void consultaLaInformacionDeUnUsuarioConId(Integer id) {
        try {
            response = given()
                    .get(RESOURCE_USERS + id);
        } catch (Exception e){
            LOGGER.error(String.format("FALLO: No fue posible realizar la petición", id));
            Assertions.fail(e.getMessage());
        }
    }

    @Then("obtendrá en el campo {string} el valor {string}")
    public void obtendraEnElCampoElValor(String campo, String valor) {
        try {
            response.then()
                    .body("data.".concat(campo),containsString(valor));
        } catch (Exception e){
            LOGGER.error("FALLO: No se ha obtenido información esperada en el body");
            Assertions.fail(e.getMessage());
        }
    }

    @Then("obtendrá una respuesta de recurso no encontrado")
    public void obtendraUnaRespuestaDeRecursoNoEncontrado() {
        try {
            response.then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        } catch (Exception e){
            LOGGER.error("FALLO: No se ha obtenido el código de respuesta esperado");
            Assertions.fail(e.getMessage());
        }

    }

    @Then("no encontrará información del usuario")
    public void noEncontraraInformacionDelUsuario() {

        if (response.asString().equals("{}")){
            LOGGER.info("OK");
        } else {
            Assertions.fail("Falló en la validación, se encontraron resultados en el bodyZ");
        }
    }

}