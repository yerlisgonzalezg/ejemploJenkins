Feature: Registro de usuario
  Yo como usuario
  necesito realizar el proceso de registro
  para poder acceder al sistema

  Scenario: Registro exitoso
    Given el usuario tiene acceso al aplicativo
    When el usuario realiza una petición de registro con el correo de usuario "eve.holt@reqres.in" y la contraseña "pistol"
    Then obtendrá un código de respuesta exitoso
    And se le asignará al usuario un identificador y un token de respuesta

