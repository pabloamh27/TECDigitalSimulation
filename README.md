Mi-TEC-Digital
==============
[![Java CI with Maven de pana](https://github.com/Litecore50/mi-tec-digital/actions/workflows/maven.yml/badge.svg)](https://github.com/Litecore50/mi-tec-digital/actions/workflows/maven.yml)
==============
Last Release: 4.0
==============
Nombre: Pablo Munoz Hidalgo
==============
Carne: 2020031899
==============
Aplicación Demo para gestionar Estudiantes, Profesores y Cursos.

## Revisión de tarea 1

Nota: 9.5. El código se ve bien y todo corre.

#### Observaciones
1. No está listando los estudiantes por numero de carné de forma ascendente
2. En `EstudianteService` hizo uso del método `this.getAll()` para obtener un Estudiante en particular. Pudo haber utilizado `this.getById()` para no tener que recorrerlos todos.
3. En `EstudianteServiceTest` para las pruebas unitarias intente tomar un enfoque de: "lo que pasó antes" y "lo que va a pasar". Es que por ejemplo probar que ud hizo una actualización y luego verificar que la cantidad de estudiantes sigue siendo 3 es poco útil. Hubiera podido obtener de la "base de datos" actual por medio del carné (2), luego hacer la actualización (2) y, como método de verificación comparar que del paso (1) con el del paso (2). Intente un enfoque similar para las pruebas de borrado.


## Revisión de tarea 2

Nota: 10

