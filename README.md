<!-- 1.0.3-b1 -->
![](img/97%25CitricLiquid.png)

<p align="center">
  <img width="200" height="200" 
        src="https://stickershop.line-scdn.net/stickershop/v1/product/11281886/LINEStorePC/main.png;compress=true">
</p>

Este proyecto consiste en crear un clon (simplificado) del juego **100% Orange Juice** desarrollado por [Orange_Juice](http://dadai.moo.jp) y distribuido por [Fruitbat Factory](https://fruitbatfactory.com). A grandes rasgos este es un juego de tablero por turnos de 4 jugadores donde cada uno maneja un personaje distinto con características propias. El objetivo del juego es cumplir una cierta cantidad de requisitos para llegar al nivel más alto antes que los otros jugadores.

Usa una version de Java 13 o mayor, además se usa el testing framework de [JUnit 5.1](https://junit.org/junit5/), la librería [@API Guardian](https://github.com/apiguardian-team/apiguardian) para anotar tipos públicos, métodos, constructores y campos dentro de un marco o aplicación, y [OpenJFX](https://openjfx.io/) que es una plataforma de aplicación para sistemas de escritorio, móviles y embebidos integrados en Java.

# Lógica del juego

Esta primera version del proyecto contiene la lógica y el funcionamiento de los **Paneles** y de las **Unidades**. En ambos casos se crea una **interfaz** para favorecer el mantenimiento y la extensión de futuras versiones del proyecto, y una **clase abstracta** que implementa la interfaz correspondiente en la cual están presentes las funciones genéricas que comparten las subclases que extiendan esta clase. Para cada tipo de panel (**Home**, **Bonus**, **Drop**, **Boss**, **Encounter**, **Draw** y **Neutral**) se crea su respectiva clase, que extiende a **AbstractPanel**, por otro lado, para las unidades se crean al igual que en el caso de los paneles, clases para cada tipo de unidad (**Player**, **Wild** y **Boss**) que extienden a la clase **AbstractUnit**.

# Activación de Paneles
En el juego los paneles son activados por la unidad **Player** al caer en uno, y cada panel tiene una reacción distinta cuando un player cae en el. Para implementar estas distintas reacciones la clase **Player** implementa el siguiente método:

```
public void activatePanel(IPanel panel){
    panel.activatePanelEffectBy(this);
}
```
En el cual se puede ver que se implementa el patron de diseño de ***Double Dispatch***, para así no tener que preguntar que tipo de panel activa el player, sino que es el panel que activa su efecto por si solo. Con este patron evitamos el uso de **instanceof** y se aplica una buena metodología de diseño.

> La lógica de la activación de paneles se puede observar en el archivo AbstractPanelTest.java

# Batallas

Esta version permite las batallas entre todas las unidades. A grandes rasgos las batallas se realizan de la siguiente forma:
1. La unidad que activa la batalla **ataca**
2. La unidad que es atacada elige si **defender** o **evadir**
3. Si la unidad que recibe el ataque queda vida, entonces **contraataca** (volviendo a los pasos 1-2)
4. Se termina la batalla

Existen diversas formas en que una batalla puede ser originada, que no explicaré ya que me importa mas que quede clara la implementación de las batallas.

Cuando una unidad derrota a otra, es decir hace que sus puntos de vida lleguen a 0, aumenta la cantidad de victorias y la cantidad de estrellas, mientras que la unidad vencida pierde cierta cantidad de estrellas. Cabe destacar que este aumento de victorias y aumento/disminución de estrellas depende directamente de que unidades son las que estaban en combate. Es por esto que también se implementa el patron de diseño ***Double Dispatch*** en el combate para evitar preguntar que unidad esta siendo atacada y si es derrotada, sea este patrón el que se encargue de la distribucion de estrellas y victorias.

Concretamente podemos ver esta implementación en que cada unidad implementa el método:
```
public void attack(IUnit unit){
  int dmg = this.setDmg();
  unit.receiveXAttack(this, dmg); // donde la X puede ser "Player", "Wild" o "Boss"
}
```
El método ``setDmg()`` retorna el ataque de la unidad, además existen otros métodos para defender o evadir dependiendo de lo que quiere hacer la unidad, al igual que uno que verifica si la unidad sigue en combate o no. Estos métodos son los más importantes que se usan en una batalla.

> La lógica de las batallas se puede observar en el archivo CombatTest.java
