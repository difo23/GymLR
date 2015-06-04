# GymLR Objetivo:

EL proyecto GymLR desarrollado para Android forma un conjunto de pruebas y modificaciones realizadas en la aplicación
original Futbol creada en el transcurso de las clases de SmartPhone tema OS Android. El objetivo de este proyecto es totalmente 
didactico, presentando conceptos como el Model-View-Presenter (MVP), utilizacion de herramientas como son Retrofit,
Picasso y Dagger entre otras libres y herramientas tocadas durante las clases.

<h4>Descripcion de funcionalidades APP Futbol:</h4>

La funcion del proyecto Futbol es algo simple, pero con una gran muestra de conceptos con herramientas que son muy 
utiles en el desarrollo de aplicaciones android. El objetivo del APP es tomar los datos relacionados con los partidos de futbol realizados en Espana y mostrar los marcadores correspondientes. La fuente de los datos sera la pagina web <a href="http://www.marcadoresonline.com/futbol/espa%C3%B1a/primeradivision" > Marcadores.com </a> (La utilizacion de  estos datos es totalmente educativa y no prentende uso comercial). Para la toma de los datos y injeccion de los mismo se utilizan herramientas como Retrofit y Dagger. Para manejar los iconos que representan los Escudos de los equipos se implementa Picasso estas imagenes necesitan ser tratadas para usarla en nuestra aplicacion. La aplicacion original solo muestra los resultados para Espana y la liga 1. Su unica funcionalidad es mostrar estos datos en forma de una lista de marcados y la bandera del pais al que pertenecen la siguiente imagen muestra como se ve la aplicacion en el emulador Genymotion.

<img src="https://github.com/difo23/GymLR/blob/master/app/src/main/res/drawable/original.png?raw=true">

<h4>Descripcion de funcionalidades APP GymRL y modificaciones realizadas a APP Futbol:</h4>

El APP GymRL es una replicacion paso a paso del APP Futbol desarrollado en clases, para su desarrollo fue necesario instalar un entorno de desarrollo para android desde 0, el IDE selecionado para este trabajo fue Android Studio y para pruebas se usaron el Emulador Genymotion con Custom Phone version 5.10 API 22 y un celular Alcatel One Touch Android 4.1.2 (API 16), a la hora de instalar el APP en el telefono fue necesario modificar build.gradle  para que acepte esta version tan basa del API:

<code>
defaultConfig {
        applicationId "com.smartphone.lizandro.gymlr"
        minSdkVersion 16
        targetSdkVersion 22
        versionCode 1
        versionName "1.0"
    }
</code>

Cuando obtuvimos un ambiente de pruebas funcional se empezo a analizar el desarrollo del APP Futbol  como interactua entre sus clases y activitys. EL flujo de datos y la aplicacion de las herramientas MVP,Retrofit,Picasso y Dagger. Dentro del la carpeta com.smartphone.lizandro.gymlr.mvp se encuentra los las clases relacionadas con el el view que son (IDashboardView no es mas que una interface con metodos de carga y refresh de la IU y InjectedActivity), el presenter DashboardPresenter.java una clase en esta capeta con los metodos relacionados con MarcadoresInterface y el modelo partido Partido.java los metodos claves de estre presenter son:

<code>
public void loadPartidos(int id) {
        loadPartidoLiga(id);
    }
</code>

Su funcion es apartir de un id liga obtenido de la pagina  <a href="http://www.marcadoresonline.com/futbol/espa%C3%B1a/primeradivision" > Marcadores.com </a> pasar a cargar los patidos realizados en esa liga.

<code>
private void loadPartidoLiga(int ligaId) {
        restClient.getPartidos(ligaId)
</code>

El model esta formado por las clases java Equipo.java y Partido.java siguiendo el patron MVP estos son los modelos de datos relacionados con los datos del Partido y datos del Equipo.

Otro punto al resaltar que puede causar confusion al analizar la aplicacion es el uso de modelos en la carpeta rest/model/ que poseen las clases Participantes.java y Partido. java que son generadas por las heramientes implementadas para consumir los datos de la pagina web. La clase RestClient es el nucleo de la aplicacion y es donde se indica la URL de la pagina web que servira de fuente de datos, los metodos claves son:

<code>
public RestClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.marcadoresonline.com")
                .build();
        marcadoresInterface = restAdapter.create(MarcadoresInterface.class);
    }
</code>

<code>
public  MarcadoresInterface getInstance() {
        if (restClient == null)
            restClient = new RestClient();
        return marcadoresInterface;
    }
<code>

Para modificar la aplicacion a que presente mas resultados y distintas ligas se ajusto APP para que comparta el ID de ligas distintas y al presionar un boton se carguen los datos de esa nueva liga, demanera que la cantidad de datos obtenidos de la pagina web para los juegos de futbol, mostrando la potencia de las herramientas al cambiar. La aplicacion GymRL se presenta de la siguiente manera aparte de mostrar los datos para varias ligas de Espana al precionar el boton puede cambiar los datos de los partidos por pais y liga, las siguientes imagenes muestran los resultados optenidos para Portugal, Francia y algunos partidos internacionales:

<h3>Portugal</h3>:
<img src="https://github.com/difo23/GymLR/blob/master/app/src/main/res/drawable/portugal_gymlr.png?raw=true">

<h3>Francia</h3>
<img src="https://github.com/difo23/GymLR/blob/master/app/src/main/res/drawable/gymlr_france.png?raw=true">

<h3>Internacional</h3>
<img src="https://github.com/difo23/GymLR/blob/master/app/src/main/res/drawable/international.png?raw=true">

<h4>Informacion y consulta sobre MVP, la cual fue necesaria para entender el proyecto Futbol:</h4>
<ul>
<li><a href="http://magenic.com/Blog/Post/6/An-MVP-Pattern-for-Android">MVP Magenic</a></li>
<li><a href="http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter"> Wikipedia MVP </a></li>
<li><a href="http://droidumm.blogspot.com.es/2011/11/concept-model-view-present-mvp-pattern.html"> MVP Droidumm</a></li>
</ul>

<img src="http://upload.wikimedia.org/wikipedia/commons/d/dc/Model_View_Presenter_GUI_Design_Pattern.png">

<h4>Informacion y consulta sobre Retrofit, la cual fue necesaria para entender el proyecto Futbol:</h4>
<ul>
<li><a href="http://androidrx.blogspot.com.es/">REST in android with retrofit and rxjava</a></li>
<li><a href="http://square.github.io/retrofit/"> A type-safe REST client for Android and Java </a></li>
<li><a href="https://github.com/square/retrofit"> Retrofit GitHub</a></li>
</ul>

<h4>Informacion y consulta sobre Dagger, la cual fue necesaria para entender el proyecto Futbol:</h4>
<ul>
<li><a href="http://square.github.io/dagger/">Dagger square</a></li>
<li><a href="http://xurxodeveloper.blogspot.com.es/2014/11/inyeccion-de-dependencias-en-android-con-dagger.html"> Inyección de dependencias en Android con Dagger </a></li>
<li><a href="http://es.slideshare.net/PedroVicenteGmezSnch/dependency-injection-on-android-spanish"> Dagger Docs</a></li>
</ul>

<h4>Informacion y consulta sobre Picasso, la cual fue necesaria para entender el proyecto Futbol:</h4>
<ul>
<li><a href="https://github.com/square/picasso">Picasso GitHub</a></li>
<li><a href="http://square.github.io/picasso/"> Picasso Square </a></li>
<li><a href="http://desarrollador-android.com/librerias/square/picasso/"> Ejemplos Picasso</a></li>
</ul>

<img src="http://square.github.io/picasso/static/sample.png">





