# Estándar de nombres

> ### Índice:
>
> > #### 1.  [`Introducción y justificación`](#Introducción y justificación).
> >
> > #### 2.  [`Entidades`](#Entidades).
> >
> > #### 3. [`Atributos`](#Atributos).
> >
> > #### 4. [`Tipos de datos a usar para atributos`](#Tipos de datos a usar para atributos).
> >
> > #### 5. [`Llaves primarias`](#Llaves primarias).
> >
> > #### 6. [`Llaves foraneas`](#Llaves foráneas).
> > 
> > #### 7. [`Índices`](#Índices).



<a name= "Introducción y justificación"></a>

> ### - Introducción y justificación
>
> ​	El uso de un estándar a la hora de programar en bases de datos es muy importante ya que este permite tener orden e uniformidad a lo largo del código, a continuación se describirá una propuesta de estándar a usar en MySQL 8 el cual tiene beneficios para una programación más ligera, rápida y con una sencilla comprensión de términos. Además es un estándar sencillo de adaptar a otras posibles aplicaciones fuera de MySQL 8.
>
> El estándar siguiente utilizará el idioma inglés ya que este es el más conocido de mundialmente, además vale mencionar que el estándar no reconocerá ni utilizará signos especiales o acentos como 'ñ' o las tildes del español u otros símbolos propios de otro idioma que no sea inglés. Adicionalmente se utilizará el guión bajo para separar palabras de una manera más sencilla, y la primer palabra se escribirá en letras en mayúscula, esto para acortar las búsquedas de nombres y facilidad a la hora de diferenciar los tipos, vale recalcar que la primer palabra de cada nombre esta reservada para el tipo de variable dentro de la base de datos. Se podría decir que es un estándar tipado.



<a name= "Entidades"></a>



> ### - Entidades
>
> > ##### - Descripción del estándar:
> >
> > Deben cumplir con las siguientes características:
> >
> > 		1. Los nombres serán en **inglés**.
> > 		2. Serán con la primer palabra en mayúscula.
> > 		3. Los espacios se representarán con el guión bajo. 
>
> >##### - Ejemplos:
> >
> >​	`SPECIAL_discount_items` , `COMMON_discount_items` o `SPECIAL_items`
> >
> >En este caso el tipo es SPECIAL y COMMON diferenciando la clase de cada uno y dando una noción más clara de con que atributos y tablas se esta trabajando.
>
> > ##### -Excepciones:
> >
> > ​	Una excepción sucederá en caso de que se ingrese un nombre con la primer palabra en mayúscula y las iniciales de las siguientes en mayúscula también:
> >
> >​	` SPECIAL_Discount_Items` , `COMMON_Discount_Items` o `SPECIAL_Items`



<a name= "Atributos"></a>



> ### - Atributos
>
> > ##### - Descripción del estándar:
> >
> > ​	Deben cumplir con las siguientes características:
> >
> > 	1. Estos serán escritos en inglés.
> > 	2. Toda la palabra será escrita en mayúscula.
> > 	3. En el caso de llevar espacios serán representados con guión bajo.
>
> > ##### - Ejemplos:
> >
> > `CLIENT_ID`, `ITEMS` o `PETS_OWNED`
>
> > ##### - Excepciones:
> >
> > No habrán excepciones.




<a name= "Tipos de datos a usar para atributos"></a>
> ### - Tipos de datos a usar para atributos
>
> > ​	Deben cumplir con las siguientes características:
> >
> > 	1. Estos serán escritos en inglés.
> > 	2. Será agregado al final del nombre.
> > 	3. Se escribirá en mayúscula.
> >    
>
> > ##### - Ejemplos:
> >
> >  `CLIENT_ID_NUMERIC`, `ITEMS_JSON` o `NAME_STRING`
>
> > ##### - Excepciones:
> >
> >  `CLIENT_ID_Numeric`, `ITEMS_Json` o `NAME_String`




<a name= "Llaves primarias"></a>
> ### - Llaves primarias
>
> > ##### - Descripción del estándar:
> >
> > ​	Deben cumplir con las siguientes características:
> >
> > 	1. Estos serán escritos en inglés.
> > 	2. Todo el nombre será escrito en mayúscula.
> > 	3. En el caso de llevar espacios serán representados con guión bajo.
> > 	4. Se escribirá PRIMK antes de cada llave primaria .
> >     
>
> > ##### - Ejemplos:
> >
> > `PRIMK_ID`, `PRIMK_AGE` o `PRIMK_LOCATION`
>
> > ##### - Excepciones:
> >
> > No habrán excepciones.





<a name= "Llaves foráneas"></a>
>### - Llaves foráneas
>
>> ##### - Descripción del estándar:
>>
> > ​	Deben cumplir con las siguientes características:
> >
> > 	1. Estos serán escritos en inglés.
> > 	2. Todo el nombre será escrito en minúscula.
> > 	3. En el caso de llevar espacios serán representados con guión bajo.
> > 	4. Se escribirá FORK antes de cada llave foránea .
> >  
>
>> ##### - Ejemplos:
>>
>> `FORK_department` o `FORK_location`
>
>> ##### - Excepciones:
>>
>> `FORK_Department` o `FORK_Location`





<a name= "Índices"></a>
> ### - Índices
>
> > ##### - Descripción del estándar:
> >
> > ​	Deben cumplir con las siguientes características:
> >
> > 	1. Estos serán escritos en inglés.
> > 	2. Todo el nombre será escrito en minúscula.
> > 	3. En el caso de llevar espacios serán representados con guión bajo.
> > 	4. Se escribirá INDX antes de cada Índice .
> >    
>
> > ##### - Ejemplos:
> >
> > `INDX_repetitions` o `INDX_cycles`
>
> > ##### - Excepciones:
> >
> >  `INDX_Repetitions` o `INDX_Cycles`
