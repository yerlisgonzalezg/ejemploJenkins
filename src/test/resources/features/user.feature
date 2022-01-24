Feature: Gestión de usuarios
  Yo como usuario administrador
  necesito gestionar la información de los usuarios
  para los procesos requeridos de consulta,creación, actualización y eliminación.

  Background: Acceso al aplicativo
    Given el usuario tiene acceso al aplicativo

  Scenario Outline: Consulta de usuario por id
    When consulta la información de un usuario con id 2
    Then obtendrá un código de respuesta exitoso
    And obtendrá en el campo "email" el valor "<email>"
    And obtendrá en el campo "first_name" el valor "<first_name>"
    And obtendrá en el campo "last_name" el valor "<last_name>"
    Examples:
      |         email           | first_name | last_name |
      | janet.weaver@reqres.in  |    Janet   |  Weaver   |

  Scenario: Cosulta de usuario por id no existente
    When consulta la información de un usuario con id 23
    Then obtendrá una respuesta de recurso no encontrado
    And no encontrará información del usuario


