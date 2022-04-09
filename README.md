# Libreria, WEBFLUX CRUD para recursos universitarios o informativos - CRUD en general

<p align="center">
<img src="https://www.sofka.com.co/wp-content/uploads/2021/02/sofkau-logo-horizontal.png">
</p>
<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white">
</p>
<p align="center">
  <img src="https://img.shields.io/github/v/release/JoseNSoler/PracticaMVC?style=flat-square"
</p>   

## Video explicatorio
--


## Descripcion General

Sistema para el manejo de libreria usando Java Webflux - reactive mongo repository y spring boot - Netty HTTP server, alojando base de datos en nube Mongo atlas

Requerimientos


- Consultar disponibilidad de un recurso indicando en un mensaje si esta disponible o no. en caso de no estar disponible presentar la fecha del préstamo actual del ultimo ejemplar.
- Prestar un recurso, se debe comprobar si esta prestado o no, indicarlo mediante un mensaje. Si se encuentra disponible debemos marcarlo como prestado y registrar la fecha del préstamo (no es necesario llevar el historia de prestamos).
- Recomendar un listado de recursos al usuario a partir del tipo de recurso, del área temática o de los dos. Los recursos están clasificados por tipo de recurso (libros, revistas, fichas, etc) pero también por área temática (ciencias, naturaleza, historia, etc).
- Devolver un recurso que se encontraba prestado, obviamente si un recurso no se encuentra en préstamo no podrá ser devuelto. Indicar el resultado con un mensaje.
- Servicios para hacer CRUD de los recursos. 

## Estructura Recurso a prestar
```json
{
    "id": "624f65711af87a429f70d647",
    "title": "Gulf of Mexico a focus on community recovery and new response technology : oversight field hearing before the Committee on Natural Resources, U.S. House of Representatives, One Hundred Twelfth Congress, first session, Monday, April 18, 2011, in Houma, Louisiana",
    "subject": [
        {
            "name": "BP Deepwater Horizon Explosion and Oil Spill, 2010"
        },
        {
            "name": "Oil spills--Economic aspects--Gulf States"
        },
        {
            "name": "Oil spills--Prevention--Technological innovations"
        }
    ],
    "type": "text",
    "format": [
        "Language material",
        "Electronic resource"
    ],
    "description": [
        "Title from title screen (viewed on Oct. 31, 2011).",
        "Paper version available for sale by the Supt. of Docs., U.S. G.P.O.",
        "\"Serial no. 112-25.\""
    ],
    "available": true,
    "borrowTime": "jue., 7 abr. 2022 17:30:28 -0500"
}
```



