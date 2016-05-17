/////////////////////////////////////////////////////////////////

// Clase derivada de la clase abstracta CArbolBinB. Redefine los

// m�todos: comparar, procesar y visitarInorden.

//

public class CArbolBinarioDeBusqueda extends CArbolBinB
//public interface CArbolBinarioDeBusqueda extends CArbolBinB
//public class CArbolBinarioDeBusqueda implements CArbolBinB
{
  // Permite comparar dos nodos del �rbol por el atributo

  // nombre.

  public int comparar(Object obj1, Object obj2)
  {
    String str1 = new String(((CDatos)obj1).obtenerNombre());
    String str2 = new String(((CDatos)obj2).obtenerNombre());
    return str1.compareTo(str2);
  }

  // Permite mostrar los datos del nodo visitado.

  public void procesar(Object obj)
  {
    String nombre = new String(((CDatos)obj).obtenerNombre());
    double nota = ((CDatos)obj).obtenerNota();
    System.out.println(nombre + "  " + nota);
  }

  // Visitar los nodos del �rbol.

  public void visitarInorden()
  {
    // Si el segundo argumento es true, la visita comienza

    // en la ra�z independientemente del primer argumento.

    inorden(null, true);
  }
}
/////////////////////////////////////////////////////////////////
