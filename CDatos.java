import java.util.Map;

import javax.swing.JFrame;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxEdgeStyle;
import com.mxgraph.view.mxGraph;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CDatos
{
  // Atributos
  String nombre;
  double nota;
  mxCell v;
  mxCell izq;
  mxCell der;
  int nivel=0;

  // M�todos
  public CDatos() {}        // constructor sin par�metros

  public CDatos(String nom) // constructor con par�metros
  {
    nombre = nom;
  }

  public CDatos(String nom, double n) // constructor con par�metros
  {
    nombre = nom;
    nota = n;
  }

  public CDatos(String nom, double n, mxCell v) // constructor con par�metros
  {
    nombre = nom;
    nota = n;
    v = v;
  }

  public void asignarNombre(String nom)
  {
    nombre = nom;
  }

  public String obtenerNombre()
  {
    return nombre;
  }

  public void asignarNota(double n)
  {
    nota = n;
  }

  public double obtenerNota()
  {
    return nota;
  }
}
