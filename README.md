# pruebaITERIAM

## Description

Una prueba muy simple sobre una calculadora por api, su funcionamiento se basa en operaciones sencillas que se realiza por llamadas a una API. Esta calculadora solo dispone de una suma y una resta.

## Versions

- Java: 17
- Springboot: 3.0.5
- Maven: 3.6.2
- Junit: >5.0.3

## Tracer library

Para implementar la librería tracer seguiremos los siguientes pasos:
1. En la carpeta del proyecto, creamos la carpeta lib.
2. En esa carpeta añadimos los ficheros .jar que hay dentro:
- tracer-1.0.0.jar
- tracer-1.0.0-javadoc.jar
- tracer-1.0.0-sources.jar
3. En la carpeta del proyecto ejecutamos *mvn install:install-file -Dfile="lib/tracer-1.0.0.jar" -DgroupId=io.corp.calculator -DartifactId=tracer -Dversion="1.0.0" -Dpackaging=jar -DcreateChecksum=true*.
4. Ahora, en la carpeta local del repositorio maven (en m2) tendremos una carpeta llamada *io/corp/calculator/tracer/1.0.0*
5. Creamos ese mismo camino desde nuestra carpeta lib *lib/io/corp/calculator/tracer/1.0.0*
6. Copiad el archivo *maven-metadata-local.xml* que encontraréis en *.m2/io/corp/calculator/tracer* y se guarda en el mismo camino en lib, con el siguiente nombre: *maven-metadata-local.xml*.
7. Copiad todos los archivos de *.m2/io/corp/calculator/tracer/1.0.0* en *lib/io/corp/calculator/tracer/1.0.0*
8. Editad el POM y añadid el siguiente repositorio:
```
<repositories>
    <repository>
        <id>localRepositoryId</id>
        <name>localRepository</name>
        <url>file://${project.basedir}/lib</url>
    </repository>
</repositories>
```
9. Añadid la dependencia:
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
## Start App

En la carpeta root del proyecto, ejecutad *mvn clean install*.

Posteriormente ejecutad *mvn spring-boot:run*.
Esto iniciará un proyecto y lo alojará en localhost:8080.

Con esto, podemos acceder a la calculadora de la siguiente forma:

1. calculator/{operand1}/{operand2}/{operator} -> como ejemplo podemos probar: *localhost:8080/calculator/19.5/265/+*
2. calculator/{operation} -> como ejemplo podemos probar: *localhost:8080/calculator/40.6+26+35-50*

## Decisions I made

He realizado dos llamadas sobretodo porque con la primera veía que era demasiado simple, y al intentar hacer la segunda con recursividad, no acababa contento. Al final me he decantado por la solución actual para la segunda porque creo que es una solución bastante limpia.

Aunque nunca había creado un ExceptionHandler, he preferido aventurarme a ponerlo aunque no haya hecho seguramente una buena aplicación de la misma, porque si no tenía que devolver un String para poder cubrir, en caso de un error, el mensaje para devolverlo, y no me parecía correcto ya que quería devolver si o sí un tipo "double".

No he creado ningún mensaje JSON para las respuestas porque creo que es una aplicación muy sencilla como para necesitar devolver un mensaje de esta forma.

A pesar de todo ello, la aplicación es muy limitada, en la llamada donde pasamos un string completo, no se tiene en cuenta la prioridad de las operaciones, y al no haber hecho ninguna diferencia entre un '-' por negativo (-15,6 por ejemplo) y un '-' por la operación 'resta' al intentar sumar o restar números negativos da error.

### Changes

La aplicación realizada es muy simple, si tuvieramos ahora mismo que introducir nuevas funcionalidades como multiplicar o dividir, sería sencillo por la parte de la resolución simple, por la parte de resolución de múltiples operaciones sería más sencillo quitarlo que introducirle la lógica necesaria para que funcionara correctamente.

Si nos centramos en la parte de operaciones sencillas, sería tan simple como añadir un caso más al switch, aunque eso supondría modificar el código, por poco que se modificara.

Si usaramos la acción de realizar una operación, la pusieramos en una interfaz y hacemos un patrón factoría, sería muy fácil desacoplar el apartado de operar dentro del servicio y sería más fácil ir creando clases que se fueran necesitando por cada operando que se quisiera añadir. El principal problema que tendría esta acción, es la complejidad añadida a una aplicación que solo opera con un tipo primitivo, en este caso 'double', aunque sería una solución mejor porque el esquema principal de la aplicación no tendría que modificarse en ningún caso.

Para hacer una mejor presentación de la aplicación, eliminaría la parte de "calculator/{operation}" y únicamente se mantiene porque si no la aplicación sería demasiado sencilla para lo que yo quería de una prueba técnica, aunque no esté del todo contento con como se ha realizado esa parte.

### SOLID

Esta aplicación no cumple el principio SOLID por varias razones:

- Si añadimos una nueva funcionalidad, debe modificarse la parte de resolver multiples operaciones por completo, lo cual, no cumpliría ni la 'S' ni la 'O'.
- Al no desacoplar la el caso del switch en la realización simple, siempre habrá que modificar esa operación, aunque solo sea añadir casos, por tanto, no se cumpliría la O.