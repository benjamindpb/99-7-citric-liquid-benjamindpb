Este README corresponde a la Tarea 2

En esta version de la tarea se implemento el controlador. Esta entidad sirve como intermediario entre los objetos del
modelo y la interfaz grafica de la aplicacion. Es vital para el modelo de MVC que sigue esta tarea.

El controlador se encarga de mantener todos los parametros necesarios para implementar las reglas y el flujo del juego.
Su funcion es muy importante ya que permite que la interfaz grafica no interactue directamente con los objetos del modelo
y vice versa.

En esta tarea era importante el uso de patrones de diseño. En particular en esta version se implemento el patron Null Object
para el panel neutral de forma inconsciente en la tarea 1, tambien se implemento el patron State para las fases del turno
del jugador. Se implemento en parte el patron de diseño Observer en la clase Controller de manera implicita, ya que 
en verdad la nueva version de Obvserver de Java > 8 utiliza Handlers que son como los nuevos observers del modelo.

Esta version no esta 100% terminada y faltan algunas modificaciones pero si es robusta en cuanto al modelo. Aun falta agregar
mas Handlers (observers) que notifican el cambio de propiedades de objetos del modelo. Pero eso lo hare para la proxima entrega
para poder implementar la interfaz grafica y terminar el proyecto.

PD1: este nuevo modelo tiene pequeñas modificaciones ya que la primera version se implemento de una muy buena manera. Y por otro
lado obviamente mantiene todas las caracteristicas y requisitos necesarios de la Tarea 1.
