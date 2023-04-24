# pruebaITERIAM

## Descripción.

Una prueba de concepto sobre una calculadora por api, su funcionamiento se basa en operaciones que se realiza por llamadas a una API. Esta calculadora solo dispone de una suma y una resta pero estará preparado para poder aumentar de tamaño y admitir más operadores.

## Versiones

- Java: 17
- Springboot: 3.0.5
- Maven: 3.6.2
- Junit: >5.0.3

## Librería Tracer

Para implementar la librería tracer seguiremos los siguientes pasos:
1. En la carpeta del proyecto, creamos la carpeta lib.
2. En esa carpeta añadimos los ficheros .jar que hay dentro:
- tracer-1.0.0.jar
- tracer-1.0.0-javadoc.jar
- tracer-1.0.0-sources.jar
3. En la carpeta del proyecto ejecutamos *mvn install:install-file -Dfile="lib/tracer-1.0.0.jar" -DgroupId=io.corp.calculator -DartifactId=tracer -Dversion="1.0.0" -Dpackaging=jar -DcreateChecksum=true*.
4. Ahora, en la carpeta local del repositorio maven (en m2) tendremos una carpeta llamada *io/corp/calculator/tracer/1.0.0*
5. Creamos ese mismo camino desde nuestra carpeta lib *lib/io/corp/calculator/tracer/1.0.0*
6. Copiaremos el archivo *maven-metadata-local.xml* que encontraréis en *.m2/io/corp/calculator/tracer* y se guarda en el mismo camino en lib, con el siguiente nombre: *maven-metadata-local.xml*.
7. Copiaremos todos los archivos de *.m2/io/corp/calculator/tracer/1.0.0* en *lib/io/corp/calculator/tracer/1.0.0*
8. Se edita el POM y añadimos el siguiente repositorio:
```
<repositories>
    <repository>
        <id>localRepositoryId</id>
        <name>localRepository</name>
        <url>file://${project.basedir}/lib</url>
    </repository>
</repositories>
```
9. Se añade la dependencia:
```
<dependency>
    <groupId>io.corp.calculator</groupId>
    <artifactId>tracer</artifactId>
    <version>1.0.0</version>
</dependency>
```
10. Por último, Crearemos un Bean dentro del proyecto para poder usarlo:
```
@Configuration
public class TracerConfiguration {
  @Bean
  public TracerImpl getTracerImpl() {
     return new TracerImpl();
  }
}
```
## Inicializar la App

En la carpeta root del proyecto, se ejecuta *mvn clean install*.

Posteriormente *mvn spring-boot:run*.
Esto iniciará el proyecto y lo alojará en localhost:8080.

Con esto, se puede acceder a la calculadora de la siguiente forma:

1. calculator/calculate: realiza una operación.
- Para poder acceder correctamente a este endpoint se puede ejecutar el siguiente comando como ejemplo: 
```
curl -X 'GET' \
  'http://localhost:8080/calculator/calculate?operand1=1&operand2=2&operator=sumar' \
  -H 'accept: application/json'
```
2. calculator/getOperators: devuelve una lista con los operadores disponibles.

## Decisiones tomadas

Se ha realizado una separación por paquetes para tener mejor cada clase de manera que esté asociada a su función, por ejemplo, sumar y restar están un paquete llamado operaciones, y la factoría que los gestiona en un paquete llamado factorías.

Actualmente hay 2 controladores, uno para poder gestionar la llamada de cálculo y la lista de operaciones, y otra para poder manejar las excepciones que puedan saltar en el proceso.

La factoría está dispuesta de manera, que si queremos añadir un nuevo operador, como 'multiplicar', lo único que tendremos que hacer es crear una nueva clase dentro del paquete operaciones que se asemeje a lo que ya está construido y con eso será suficiente para tener el nuevo operador funcional dentro del endpoint.
