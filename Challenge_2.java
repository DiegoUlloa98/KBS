package Challenge_2;   //Sirve para indicarle a JADE que cree la carpeta en donde se guardaran los archivos compilados (.class).

import jade.core.Agent;   //Sirve para indicarle a JADE que importe el archivo 'Agent.java' que se encuentra en la carpeta 'core' y a su vez, la carpeta 'core' se encuentra dentro de la carpeta 'jade'.
import jade.core.behaviours.Behaviour;   //Sirve para indicarle a JADE que importe el archivo 'Behaviour.java' que se encuentra en la carpeta 'behaviours' y a su vez, la carpeta 'behaviours' se encuentra dentro de la carpeta 'core', y esta, en la carpeta 'jade'.

import net.sf.clipsrules.jni.*;   //Sirve para indicarle a JADE que importe todos los archivos que esten dentro de la ruta de directorios net.sf.clipsrules.jni. 

public class Challenge_2 extends Agent   //Esto indica el inicio de la clase Challenge_2 que extiende de la clase Agent, que es el agente que estamos programando.
{
  Environment clips;   //Esto crea una variable del tipo 'Environment' que es el entorno de CLIPS y cuyo nombre de la variable es 'clips'.

  protected void setup()   //Esta linea crea e inicia un metodo llamado 'setup' cuyo metodo sirve para inicializar el agente.
  {
      try  
      {   //Esta es un declaracion try-catch que sirve para intentar todo un bloque de sentencias y se especifica una respuesta si se produce una excepcion.
        clips = new Environment();   //Esta linea almacena una referencia del objeto 'clips' que es la variable de tipo entorno de CLIPS.
      } catch (Exception e){}   //Esta termina la declaracion try-catch.
     
    addBehaviour(new TellBehaviour());   //Esta linea nos permite crear un nuevo comportamiento en nuestro agente invocando la funcion 'addbehaviour' donde le especificamos una funcion del tipo 'TellBehaviour'.
    addBehaviour(new AskBehaviour());   //Esta linea nos permite crear un nuevo comportamiento en nuestro agente invocando la funcion 'addbehaviour' donde le especificamos una funcion del tipo 'AskBehaviour'.
  } 

  private class TellBehaviour extends Behaviour   //Esta linea inicia la clase TellBehaviour que extiende de la clase Behaviour.
  {

    boolean tellDone = false; //Esta crea una variable tipo booleana (de decision) que se inicializa a falso y que nos va a permitir saber en que momento termina el comportamiento del agente.
    public void action()   //Esto inicia el metodo 'action' que sirve para indicarle al agente todas las acciones que debe de llevar a cabo.
    {
      try
      {    
        // // Market
        // clips.load("C:/jade/clips_jni_640/CLIPSJNI/clips/market/templates.clp");   //Esto carga el archivo 'templates' de tipo 'clp' que es el formato que utiliza CLIPS utilizando la funcion 'load'.
        // clips.load("C:/jade/clips_jni_640/CLIPSJNI/clips/market/rules.clp");   //Esto carga el archivo 'rules' de tipo 'clp' que es el formato que utiliza CLIPS utilizando la funcion 'load'.
        // clips.load("C:/jade/clips_jni_640/CLIPSJNI/clips/market/facts.clp");   //Esto carga el archivo 'facts' de tipo 'clp' que es el formato que utiliza CLIPS utilizando la funcion 'load'.

        // // Person
        // clips.load("C:/jade/clips_jni_640/CLIPSJNI/clips/persons/load-persons.clp");   //Esto carga el archivo 'load-persons' de tipo 'clp' que es el formato que utiliza CLIPS utilizando la funcion 'load'.
        // clips.load("C:/jade/clips_jni_640/CLIPSJNI/clips/persons/load-persons-rules.clp");   //Esto carga el archivo 'load-persons-rules' de tipo 'clp' que es el formato que utiliza CLIPS utilizando la funcion 'load'.

        // // Products
        // clips.load("C:/jade/clips_jni_640/CLIPSJNI/clips/prodcust/load-prod-cust.clp");   //Esto carga el archivo 'load-prod-cust' de tipo 'clp' que es el formato que utiliza CLIPS utilizando la funcion 'load'.
        // clips.load("C:/jade/clips_jni_640/CLIPSJNI/clips/prodcust/load-prodcust-rules.clp");   //Esto carga el archivo 'load-prodcust-rules' de tipo 'clp' que es el formato que utiliza CLIPS utilizando la funcion 'load'.
        

        clips.build("(deftemplate person (slot name ) (slot gender) (slot age (type INTEGER)) (slot partner))");   //Esto construye la plantilla que nos permite declarar la estructura 'person' que contiene los campos 'name', 'gender', 'age' y 'partner', utilizando la funcion 'build'. Esto es una variante del hecho de cargar un archivo .clp.
        clips.build("(deffacts partnership (person  (name Fred)  (gender  male)   (age 26)  (partner Susan)) (person  (name Susan) (gender female)  (age 24) (partner Fred))	(person  (name Andy)  (gender male)    (age 25)   (partner Sara)) (person  (name Alice) (gender female)  (age 23)   (partner Bob)))");   //Esto nos permite crear los hechos o 'facts' de CLIPS, que en nuestro caso el hecho es 'partnership' que significa la asociacion que hay entre dos personas. Se declaran 4 personas que contienen sus respectivos campos anteriormente mencionados.
        clips.build("(defrule my-rule1 ?p <- (person (gender female)(name ?x)(age ?a))(test(> ?a 20))=> (printout t ?p ?x \" tiene: \" ?a crlf))");   //Esta linea nos permite crear una regla llamada 'my-rule1'. Las reglas sirven para hacer un razonamiento sobre la bases de conocimiento que se pueden construir. En nuestro caso, la reglas nos dice que nos busque a las personas que son mujeres y que pueden tener cualquier nombre y que tengan una edad mayor a 20 años, por lo cual nos imprimira un resultado de 2 mujeres que cumplen con dichas condiciones.
       
        clips.eval("(reset)");   //Nos sirve para hacer un 'reset' un reinicio a CLIPS. Esto se hace con la funcion 'eval'.
      
      }catch (Exception e){}   //Termina la sentencia try-catch.

      tellDone = true;   //Esto cambia la variable booleana 'tellDone' a verdadera, lo que significa que el agente ya realizo todas sus actividades.
    } 
    
    public boolean done()   //Esta inicia el metodo booleano 'done' cuyo metodo sirve para indicar que el compartimiento del agente ya ha concluido.
    {
      if (tellDone)   //Esta es una sentencia 'if' que sirve para decirnos que si la variable 'tellDone' es verdadera, entonces retorna 'true', que significa que el agente ha concluido su comportamiento y en el caso contrario, retorna 'false' y significa que el agente aun NO ha concluido su comportamiento.
        return true;  
      else
	      return false;
    }
  }   //Aqui termina la clase 'TellBehaviour'.


  private class AskBehaviour extends Behaviour   //Esta linea indica el inicio de la clase 'AskBehaviour' que extiende de la clase 'Behaviour'.
  {
    boolean askDone = false;   //Esta crea una variable tipo booleana (de decision) que se inicializa a falso y que nos va a permitir saber en que momento termina el comportamiento del agente.

    public void action()   //Esto inicia el metodo 'action' que sirve para indicarle al agente todas las acciones que debe de llevar a cabo.
    {
        try
        {   //Esta es un declaracion try-catch que sirve para intentar todo un bloque de sentencias y se especifica una respuesta si se produce una excepcion.
          clips.eval("(reset)");   //Nos sirve para hacer un 'reset' un reinicio a CLIPS. Esto se hace con la funcion 'eval'.
          clips.eval("(facts)");   //Esta linea nos sirve para que se imprima la lista de hechos que se encuentran en CLIPS.
          clips.eval("(rules)");   //Esta linea nos sirve para que se imprima la lista de reglas que se encuentran en CLIPS.

          clips.run();   //Esta linea nos permite ejecutar CLIPS, es decir, que se impriman todos los hechos y reglas.
        }catch(Exception e){}   //Termina la sentencia try-catch.

       askDone = true;   //Esto cambia la variable booleana 'askDone' a verdadera, lo que significa que el agente ya realizo todas sus actividades.
    } 
    
    public boolean done()   //Esta inicia el metodo booleano 'done' cuyo metodo sirve para indicar que el compartimiento del agente ya ha concluido.
    {
      if (askDone)   //Esta es una sentencia 'if' que sirve para decirnos que si la variable 'askDone' es verdadera, entonces retorna 'true', que significa que el agente ha concluido su comportamiento y en el caso contrario, retorna 'false' y significa que el agente aun NO ha concluido su comportamiento.
        return true;
      else
	      return false;
    }
   
    public int onEnd()   //Esta linea inicia el metodo 'onEnd' que nos sirve para destruir la clase comportamiento del agente.
    { 
      myAgent.doDelete();   //Esta linea sirve para destruir al agente con el metodo 'doDelete'.
      return super.onEnd();   //Esta nos permite devolver el resultado de que el agente y su comportamiento han sido destruidos.
    } 
  }   //Aqui termina la clase 'AskBehaviour'.
}   //Aqui termina la clase 'Challenge_2', es decir, el agente.


/*
  Hecho por: Diego Ulloa.
  Fecha: 08/03/2022.
*/
