package APIAutTests;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApiAutTests {

    @Test
    @Description("Testa a requisição GET no endpoint de comentários")
    public void testGetComments() {

        //Realizar uma requisição no endpoint https://jsonplaceholder.typicode.com/comments
        // pesquisando pelo atributo name: alias odio sit
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/comments?name=alias%20odio%20sit");

        // Validações
        assertEquals(200, response.getStatusCode());  //Validar o statuscode = 200 e o email do objeto retornado
        assertNotNull(response.jsonPath().getString("email"));
    }

    @Test
    @Description("Testa a requisição POST no endpoint de usuários")
    public void testPostUser() { //  Realizar uma requisição do tipo POST

        String requestBody = "{ \"name\": \"John Doe\", \"email\": \"john@example.com\" }";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody) //  Realizar um post no endpoint https://jsonplaceholder.typicode.com/users enviando os valores no body
                .post("https://jsonplaceholder.typicode.com/users");

        // Validações
        assertEquals(201, response.getStatusCode()); //  Validar o statuscode = 201 e o id retornado
        assertNotNull(response.jsonPath().getString("id"));
    }

    @Test
    @Description("Testa a requisição PUT no endpoint de usuários")
    public void testPutUser() { //  Realizar uma requisição do tipo PUT

        String requestBody = "{ \"email\": \"newemail@example.com\", \"lat\": \"-34.397\", \"lng\": \"150.644\" }";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .put("https://jsonplaceholder.typicode.com/users/5"); //Realizar um put no endpoint https://jsonplaceholder.typicode.com/user - Alterando os valores dos campos: email, lat e lng do usuário com id = 5
        // Validações
        assertEquals(200, response.getStatusCode()); //  Validar o statuscode = 200 e os dados alterados
        assertEquals("newemail@example.com", response.jsonPath().getString("email"));
    }
}
