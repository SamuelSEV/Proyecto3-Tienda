# Proyecto final: Tienda 
Este trabajo práctico ha sido realizado para el módulo de programación del primer curso de desarrollo de aplicaciones web.


Es un proyecto maven.


## Utilidad:
Este programa tiene el fin de guardar una lista de compras o factura de compras.  
Al ejecutar mostrará una serie de productos disponibles, de los cuales el cliente deberá elegir el deseado y la cantidad que desee. 

Una vez elegidos todos los productos, se mostrará el precio total por producto.  
Seguidamente nos dará la opción de visualizar la factura a través de una base de datos, donde se almacenan los datos de las facturas (persona, producto, cantidad, precio e ID).



## Requisitos:
Qué necesitamos antes de empezar:

Instalaciones:
```
Sqlite3 

MySql

JavaFx (Opcional)
```
Sobre cómo instalar, os dejamos unos manuales:

- https://www.digitalocean.com/community/tutorials/como-instalar-mysql-en-ubuntu-18-04-es


- https://www.ochobitshacenunbyte.com/2019/10/01/instalacion-y-uso-basico-de-sqlite-en-ubuntu-18-04/




## Antes de ejecutar
*IMPORTANTE*


En el fichero Beans.xml habra que elegir que base de datos queremos comentando una u otra.




 
## Ejecución del proyecto
Para ejecutar, nos vamos al raíz del proyecto maven (donde visualizamos el pom.xml y la carpeta src) y ejecutamos los comandos siguientes:
```
$  mvn compile
$  mvn dependency:copy-dependencies package
$  java -cp target/nombre-archivo.jar:target/dependency/*:. tienda
```
Para ejecutar el proyecto con JavaFx 
```
$  mvn compile
$  mvn dependency:copy-dependencies package
$  java -cp target/nombre-archivo.jar:target/dependency/*:. javafx
```



Nos saldrá en ambos casos un mensaje para conectarnos a la base de datos mysql o sqlite y otro mensaje para elegir entre frutería o informática.



## Contenido del proyecto:

tienda.java     --> Es la clase principal, cuyo funcionamiento se explica más adelante.

Compra.java     --> Crea un objeto compra, que contiene la clase Articulo, y la clase Persona implementadas.

Articulo.java   --> Objeto articulo, que contiene el nombre y el precio del articulo.

Person.java     --> Objeto persona, que contiene el nombre de una persona.

DAOCompra.java  --> Interface que se usará para hacer implementaciones de base de datos.

JDBCCompra.java --> Contiene métodos para conectarse a la bd, para grabar, y para consultar la base de datos.

productos.json  --> Aquí se guarda el catálogo de compras, en formato json.

javafx.java     --> Clase principal con JavaFx.





## ¿Cómo funciona?
En un principio, el programa tienda.java coge informacion del fichero json, para asi poseer un catalogo de productos permitidos, que contienen nombre y precio. Una vez obtenido el catalogo, el programa hace una nueva compra(ahi es donde entran las clases Articulo,Compra, y Person), y empieza a pedir datos por teclado, para definir dicha compra(nombre de la persona, que articulo se quiere comprar, etc..).  

El programa es capaz de que una persona, pueda tener uno o mas articulos, y tambien puede tener una o mas compras(que estan distribuidos por ID) por lo que, se pueden repetir nombres en las personas.Cada vez que se hace una entrada, al finalizar, el programa guarda la informacion en la base de datos del fichero compra.db(aqui es donde se empieza a usar El fichero DAOCompra, y su implementación). 

Una vez que ya se acaban con las entradas, existe la posibilidad de consultar los datos. Al ir por ese camino, se muestra un listado completo, mostrando todos los datos que hay guardados en la base de datos, y despues de eso, te ofrece 2 posibilidades: Consultar por nombre y consultar por ID de compra. Finalmente, cuando ya no se quiere consultar mas, finaliza el programa.

## Cambios anteriores redactados:
Los cambios para la v0.2: 
- Se le ha añadido fecha y hora a la factura de la compra, tanto a la clase Compra como a la compra guardada en la base de datos compra.db
- Pequeñas mejoras de código.



## Cambios en progreso fecha 2020 :
* Parametrización de datos de bases de datos en fichero Beans.xml, carpeta Resources
* Arreglar fallos de orden de comandos, concretamente en el archivo JDBCCompra.java, donde los ResultSet de dos métodos no estaban correctos
* Utilización de más de una base de datos
* Utilización de Spring Framework
* Añadido reglas de sintaxis en las órdenes condicionales (IgnoreCase) para una mejor interacción con el usuario
* Maven
* Implementar scripts de creación de base de datos en DAO.
* Añadido programa en JavaFx
* Añadido colores a la salida estándar
* Añadido otra temática sobre la tienda (ficheros json, modificaciones código) tanto java como javaFx.
* Reparado y añadido que compueba si existe el id (solo funcional en tienda no en javafx).



## Autores:
* **Robert Marius Puiu** - *Initial work* - *Enlazamiento entre código, mejoras y agregado de código adicional.*
* **Guillermo Pérez Aragón** - *Initial work* - *Aportación de clases, DAOs, fechas y mejoras de código*
* **Francisco Javier Bernal Ramírez** 
* **Samuel Rivera Peñalosa** 

&copy;
