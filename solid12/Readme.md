1- Necesidad: Desarrollar una api rest en Spring boot, para gestionar  informacion de ciudadanos.
- La informacion que se debe manejar del ciudadano es: numero de documento, tipo de documento (CC, CI, PP) y nombre.
- Se debe manejar operaciones de: crear un nuevo ciudadano, actualizar informacion, eliminar y listar todos los ciudadanos.
- Debe soportar bases de datos: H2, MYSQL, POSTGRESQL, REDIS Y DYNAMO.

2- Arquitectura de la aplicacion (capas):

**Package Domain:** 
  - Contiene las clases: Citizen y Document, que representan el modelo del negocio. 
  - Contiene la Interfaz ICitizenManagement: encargada de  comunicar la capa externa (adaptadores), con la capa de aplicacion(Clase CitizenzService). 
  
**Package Service:**
- Contiene la clase: CitizenService, que hace de puente entre el controlador y los adaptadores.

**Package Controller:**
- Contiene la clase CitizenController: la cual es el punto de entrada a la aplciacion. Contiene todos los endpoints y operaciones http que soporta la aplicacion, como son:
    - Crear un nuevo ciudadano (post).
    - Listar todos los ciudadanos (get).
    - Actualizar informacion de un ciudadano (put).
    - Eliminar un ciudadano buscandolo por su id (delete).
- Contiene la clase Response: la cual es una clase para manejar las respuestas que da el Controlador al exterior, ya sean exitosas o fallidas.
- Contiene el package exception_handler: en el cual se centraliza el manejo de errores.

**Package Adapters**
- Contiene los adaptadores que se conectan a las diferentes bases de datos.
   -  dbrelation: adaptadores para bases de datos relacionales como son: H2, MYSQL, POSTGRESQL. Se conectan por JPA Y JDBC.
   -  dbnotrelation: adaptadores para bases de datos no relacionales como son: REDIS Y DYNAMO.

**Resources**
- Contiene los archivos de configuracion para las diferentes bases de datos
   - application.properties: donde se configura el adaptador y configuracion de base de datos a usar.
   - application-<data_base>.properties: contiene la configuracion de conexion para la base de datos especifica.