# BANKING API REST ON JAVA 

##Descripción

Este proyecto es una aplicación de demostración desarrollada con Spring Boot. La aplicación incluye características como seguridad, envío de correos electrónicos, gestión de caché, documentación de API con OpenAPI y más.
Tecnologías utilizadas

    Java 21
    Spring Boot 3.2.4
    MySQL
    Hibernate
    JSON Web Tokens (JWT)
    Caffeine Cache
    Spring Mail
    OpenAPI (Springdoc)
    Spring Security

Ejecución de la aplicación

    Asegúrese de tener Java y Maven instalados en su sistema.
    Clone este repositorio en su máquina local.
    Asegúrese de que MySQL esté instalado y en ejecución, y cree una base de datos con el nombre bakingapp.
    Modifique el archivo application.properties ubicado en src/main/resources con la configuración de su base de datos y correo electrónico.
    Ejecute el siguiente comando en el directorio raíz del proyecto para compilar y ejecutar la aplicación:

bash

mvn spring-boot:run

    La aplicación estará disponible en http://localhost:8180.

Puertos de conexión utilizados

    La aplicación se ejecuta en el puerto 8180.
    La base de datos MySQL utiliza el puerto 3306.
    El servicio de correo electrónico utiliza el puerto 587.

