//package com.mxgraph.examples.swing;
//javac -cp jgraphx.jar Port.java
//java -cp jgraphx.jar:. Port

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

import java.util.concurrent.ThreadLocalRandom;
import java.util.Collections;
import java.util.ArrayList;

//public class Port extends JFrame implements CArbolBinarioDeBusqueda
public class JFArbolBinB extends CArbolBinB
{
	/**
	 *
	 */
	private static final long serialVersionUID = -464235672367772404L;
	mxGraph graph;
	static int jfsizehor = 1400;
	static int jfsizever = 750;
	Map<String, Object> style;
	Object vAB;
	Object vCA;
	mxCell dIsplay;
	Object parent;
	mxGraphComponent graphComponent;
	int temp = 0;

	ArrayList<CDatos> inorden_lst = new ArrayList<>();
	int inorden_count = 0;

	ArrayList<CDatos> posorden_lst = new ArrayList<>();
	int posorden_count = 0;

	ArrayList<CDatos> preorden_lst = new ArrayList<>();
	int preorden_count = 0;

	String cursor = "";

	final int PORT_DIAMETER = 10;

	final int PORT_RADIUS = PORT_DIAMETER/2 ;

	public JFArbolBinB()
	{
		super("Laboratorio 3 ESTDA11");

	  graph = new mxGraph() {

			// Ports are not used as terminals for edges, they are
			// only used to compute the graphical connection point
			public boolean isPort(Object cell)
			{
				mxGeometry geo = getCellGeometry(cell);

				return (geo != null) ? geo.isRelative() : false;
			}

			// Implements a tooltip that shows the actual
			// source and target of an edge
			public String getToolTipForCell(Object cell)
			{
				if (model.isEdge(cell))
				{
					return convertValueToString(model.getTerminal(cell, true)) + " -> " +
						convertValueToString(model.getTerminal(cell, false));
				}

				return super.getToolTipForCell(cell);
			}

			// Removes the folding icon and disables any folding
			public boolean isCellFoldable(Object cell, boolean collapse)
			{
				return false;
			}
		};
		//The default values for all switches are designed to meet
		//the requirements of general diagram drawing applications.
		//A very typical set of settings to avoid edges that are not connected:
		graph.setAllowDanglingEdges(false);
		graph.setDisconnectOnMove(false);
		graph.setCellsEditable(false);

		// Sets the default edge style
		style = graph.getStylesheet().getDefaultEdgeStyle();
		style.put(mxConstants.STYLE_EDGE, mxEdgeStyle.ElbowConnector);

		parent = graph.getDefaultParent();

		//initial_graph();
		vAB = graph.insertVertex(parent, "vAB", "vAB", 20, 400, 80, 30);
		mxCell cInorden = (mxCell) graph.insertVertex(vAB, null, "INORDEN", 0, 0, 80, 20, "");
		mxCell cposorden = (mxCell) graph.insertVertex(vAB, null, "POSTORDEN", 80, 0, 80, 20, "");
		mxCell cpreorden = (mxCell) graph.insertVertex(vAB, null, "PREORDEN", 160, 0, 80, 20, "");
		mxCell csiguiente = (mxCell) graph.insertVertex(vAB, null, "SIGUIENTE", 0, 20, 240, 20, "");
		mxCell creset = (mxCell) graph.insertVertex(vAB, null, "RESET", 0, 40, 240, 20, "");
		//Object[] roots = graph.getChildCells(graph.getDefaultParent(), true, false);
		//graph.groupCells(vAB, 1, new Object[]{cdato.v});

		vCA = graph.insertVertex(parent, "vCA", "vCA", 20, 500, 60, 30);
		dIsplay = (mxCell) graph.insertVertex(vCA, null, "", 0, 0, 60, 20, "");
		mxCell cDig1 = (mxCell) graph.insertVertex(vCA, null, "_1", 0, 20, 20, 20, "");
		mxCell cDig2 = (mxCell) graph.insertVertex(vCA, null, "_2", 20, 20, 20, 20, "");
		mxCell cDig3 = (mxCell) graph.insertVertex(vCA, null, "_3", 40, 20, 20, 20, "");
		mxCell cDig4 = (mxCell) graph.insertVertex(vCA, null, "_4", 0, 40, 20, 20, "");
		mxCell cDig5 = (mxCell) graph.insertVertex(vCA, null, "_5", 20, 40, 20, 20, "");
		mxCell cDig6 = (mxCell) graph.insertVertex(vCA, null, "_6", 40, 40, 20, 20, "");
		mxCell cDig7 = (mxCell) graph.insertVertex(vCA, null, "_7", 0, 60, 20, 20, "");
		mxCell cDig8 = (mxCell) graph.insertVertex(vCA, null, "_8", 20, 60, 20, 20, "");
		mxCell cDig9 = (mxCell) graph.insertVertex(vCA, null, "_9", 40, 60, 20, 20, "");
		mxCell cDel0 = (mxCell) graph.insertVertex(vCA, null, "_<", 0, 80, 20, 20, "");
		mxCell cDig0 = (mxCell) graph.insertVertex(vCA, null, "_0", 20, 80, 20, 20, "");
		mxCell cAdd0 = (mxCell) graph.insertVertex(vCA, null, "_>", 40, 80, 20, 20, "");


		graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
		graphComponent.setToolTips(true);

		graphComponent.getGraphControl().addMouseListener(new MouseAdapter()
		{
			/*
			public void mouseReleased(MouseEvent e)
			{
				Object cell = graphComponent.getCellAt(e.getX(), e.getY());

				if (cell != null)
				{
					System.out.println("Release cell="+graph.getLabel(cell));
					//visitarInorden();

				}
			}*/

			public void mouseClicked(MouseEvent e)
			{
				Object cell = graphComponent.getCellAt(e.getX(), e.getY());

				if (cell != null) if(graph.getLabel(cell) != null)
				{
					if(	graph.getLabel(cell) == "INORDEN" ||
							graph.getLabel(cell) == "POSTORDEN" ||
							graph.getLabel(cell) == "PREORDEN" ||
							graph.getLabel(cell) == "SIGUIENTE" ||
							graph.getLabel(cell) == "RESET" ||
							graph.getLabel(cell) == "vCA" ||
							graph.getLabel(cell).matches("^_.*") ||
							graph.getLabel(cell) == "vAB")
					{
						putMessage(1000);
						System.out.println("Command Click cell="+graph.getLabel(cell));
						if(graph.getLabel(cell).matches("^_.*")){
							if( graph.getLabel(cell).contains("1") ){
								String label = (String)dIsplay.getValue();
								dIsplay.setValue(label + "1");
							}

							if( graph.getLabel(cell).contains("2") ){
								String label = (String)dIsplay.getValue();
								dIsplay.setValue(label + "2");
							}

							if( graph.getLabel(cell).contains("3") ){
								String label = (String)dIsplay.getValue();
								dIsplay.setValue(label + "3");
							}

							if( graph.getLabel(cell).contains("4") ){
								String label = (String)dIsplay.getValue();
								dIsplay.setValue(label + "4");
							}

							if( graph.getLabel(cell).contains("5") ){
								String label = (String)dIsplay.getValue();
								dIsplay.setValue(label + "5");
							}

							if( graph.getLabel(cell).contains("6") ){
								String label = (String)dIsplay.getValue();
								dIsplay.setValue(label + "6");
							}

							if( graph.getLabel(cell).contains("7") ){
								String label = (String)dIsplay.getValue();
								dIsplay.setValue(label + "7");
							}

							if( graph.getLabel(cell).contains("8") ){
								String label = (String)dIsplay.getValue();
								dIsplay.setValue(label + "8");
							}

							if( graph.getLabel(cell).contains("9") ){
								String label = (String)dIsplay.getValue();
								dIsplay.setValue(label + "9");
							}

							if( graph.getLabel(cell).contains("0") ){
								String label = (String)dIsplay.getValue();
								dIsplay.setValue(label + "0");
							}

							if( graph.getLabel(cell).contains("<") ){
								//System.out.println("!!!!Hola="+graph.getLabel(cell));
								String label = (String)dIsplay.getValue();
								if (label != null && label.length() > 1)
									label=label.substring(0,label.length()-1);
								//System.out.println("!!!!Hola="+label);
								dIsplay.setValue(label);
							}

							if( graph.getLabel(cell).contains(">") ){
								String label = (String)dIsplay.getValue();
								if (label != null && label.length() > 1)
									insertar(new CDatos(label, 50));
							}

							graph.refresh();
							//	System.out.println("!!!!Hola="+graph.getLabel(cell));
							//if( graph.getLabel(cell).contains("1") )
								//System.out.println("!!!!Hola="+graph.getLabel(cell));
						}else if(graph.getLabel(cell) == "RESET"){
							inorden_lst.clear();
							inorden_count = 0;

							posorden_lst.clear();
							posorden_count = 0;

							preorden_lst.clear();
							preorden_count = 0;

							String cursor = "";
						}else if(graph.getLabel(cell) == "PREORDEN"){
							visitarPreorden();
							imprimir();
						}else if(graph.getLabel(cell) == "POSTORDEN"){
							visitarPosorden();
							imprimir();
						}else if(graph.getLabel(cell) == "INORDEN"){
							visitarInorden();
							imprimir();
						}else if(graph.getLabel(cell) == "SIGUIENTE"){
							imprimir();
						}
					}else{
						System.out.println("Click cell="+graph.getLabel(cell));
						CDatos cdato = new CDatos(graph.getLabel(cell));
						cdato = (CDatos)buscar(cdato);
						pintar(cdato);
					}
				}
			}
		});
	}

	public void pintar(CDatos cdato)
	{
		try
		{
			graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "#FF0000", new Object[]{cdato.v});
		} finally {
			 graph.refresh();
			 putMessage(50);
			 graphComponent.refresh();
			 //graph.refresh();
			 //graph.getModel().endUpdate();
		}
	}

	public void pintar(CDatos cdato, String color)
	{
		try
		{
			graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, color, new Object[]{cdato.v});
		} finally {
			 graph.refresh();
			 putMessage(10);
			 graphComponent.refresh();
			 //graph.refresh();
			 //graph.getModel().endUpdate();
		}
	}

	public void insertar(CDatos cdato){
		try
		{
		 	//cdato.v = (mxCell) graph.insertVertex(parent, null, "root", 20, 20, 40, 30, "ROUNDED;strokeColor=red;fillColor=green");
			cdato.v = (mxCell) graph.insertVertex(parent, null, cdato.nombre, 0, 0, 40, 20, "");
			//temp = temp + 150;
			cdato.v.setConnectable(false);

			//graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "#FF0000", new Object[]{cdato.v});
			//mxGeometry geo = graph.getModel().getGeometry(cdato.v);
			// The size of the rectangle when the minus sign is clicked
			//geo.setAlternateBounds(new mxRectangle(20, 20, 100, 50));

			mxGeometry geo1 = new mxGeometry(0, 1, PORT_DIAMETER, PORT_DIAMETER);
			// Because the origin is at upper left corner, need to translate to
			// position the center of port correctly
			geo1.setOffset(new mxPoint(-PORT_RADIUS, -PORT_RADIUS));
			geo1.setRelative(true);

			cdato.izq = new mxCell(null, geo1,
					"shape=ellipse;perimter=ellipsePerimeter");
			cdato.izq.setVertex(true);

			mxGeometry geo2 = new mxGeometry(1.0, 1, PORT_DIAMETER,
					PORT_DIAMETER);
			geo2.setOffset(new mxPoint(-PORT_RADIUS, -PORT_RADIUS));
			geo2.setRelative(true);

			cdato.der = new mxCell(null, geo2,
					"shape=ellipse;perimter=ellipsePerimeter");
			cdato.der.setVertex(true);

			graph.addCell(cdato.izq, cdato.v);
			graph.addCell(cdato.der, cdato.v);

			super.insertar(cdato);

			//mxCell cInorden = (mxCell) graph.insertVertex(parent, null, cdato.nombre, 0, 0, 40, 20, "");
			//Object[] roots = graph.getChildCells(graph.getDefaultParent(), true, false);
			//graph.groupCells(vAB, 1, new Object[]{cdato.v});

			//Object v2 = graph.insertVertex(cdato.v, null, "World!", 240, 150, 80, 30);
			//graph.insertEdge(parent, null, "Edge", cdato.port2, v2);
		}
		finally
		{
			graph.getModel().endUpdate();
		}
	}

	public int comparar(Object obj1, Object obj2)
	{
		String str1 = new String(((CDatos)obj1).obtenerNombre());
		String str2 = new String(((CDatos)obj2).obtenerNombre());
		try{
			//int istr1 = Integer.parseInt(str1);
			//int istr2 = Integer.parseInt(str2);
			Integer objeto1 = new Integer(str1);
			Integer objeto2 = new Integer(str2);
      return objeto1.compareTo(objeto2);
		}catch(NumberFormatException er){
			return str1.compareTo(str2);
		}
	}

	// Permite mostrar los datos del nodo visitado.

	public void procesarIn(Object obj)
	{
		String nombre = new String(((CDatos)obj).obtenerNombre());
		double nota = ((CDatos)obj).obtenerNota();
		System.out.println(nombre + "  " + nota);
		inorden_lst.add(((CDatos)obj));
		//pintar(((CDatos)obj));

		//graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "#FF0000", new Object[]{((CDatos)obj).v});
		/*
		try
		{
			graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "#FF0000", new Object[]{});
		} finally {
			 graph.refresh();
			 putMessage(1000);
			 //graph.refresh();
			 //graph.getModel().endUpdate();
		}*/
	}

	public void procesarPos(Object obj)
	{
		String nombre = new String(((CDatos)obj).obtenerNombre());
		double nota = ((CDatos)obj).obtenerNota();
		System.out.println(nombre + "  " + nota);
		posorden_lst.add(((CDatos)obj));
	}

	public void procesarPre(Object obj)
	{
		String nombre = new String(((CDatos)obj).obtenerNombre());
		double nota = ((CDatos)obj).obtenerNota();
		System.out.println(nombre + "  " + nota);
		preorden_lst.add(((CDatos)obj));
	}

	// Visitar los nodos del �rbol.

	public void visitarInorden()
	{
		// Si el segundo argumento es true, la visita comienza

		// en la ra�z independientemente del primer argumento.
		cursor = "INORDEN";
		inorden(null, true);
		//posorden(null,true);
	}

	public void visitarPosorden()
	{
		// Si el segundo argumento es true, la visita comienza

		// en la ra�z independientemente del primer argumento.
		cursor = "POSTORDEN";
		posorden(null,true);
	}

	public void visitarPreorden()
	{
		// Si el segundo argumento es true, la visita comienza

		// en la ra�z independientemente del primer argumento.
		cursor = "PREORDEN";
		preorden(null,true);
	}

	public void imprimir()
	{
		/*
		for(CDatos v : inorden_lst){
			System.out.println("Imprimiendo: "+v.obtenerNombre());
			///String nombre = new String(v.obtenerNombre());
			pintar(v);
			graph.getModel().endUpdate();
			graphComponent.refresh();
		}*/
		if( cursor.equals("INORDEN") && inorden_lst.get(inorden_count)!=null ){
			pintar(inorden_lst.get(inorden_count),"#000080");
			inorden_count++;
		}

		if( cursor.equals("POSTORDEN") && posorden_lst.get(posorden_count)!=null ){
			pintar(posorden_lst.get(posorden_count),"#008000");
			posorden_count++;
		}

		if( cursor.equals("PREORDEN") && preorden_lst.get(preorden_count)!=null ){
			pintar(preorden_lst.get(preorden_count),"#FF00FF");
			preorden_count++;
		}

	}

	//Representacion grafica de edge.
  public void representarEgde(Object obj1, Object obj2, String str)
	{
		int exphor = 50;
    int expver = 40;
		mxGeometry geo = graph.getModel().getGeometry(((CDatos)obj1).v);

		double xc = geo.getCenterX();
    double yc = geo.getCenterY();
    double w = geo.getWidth();
    double h = geo.getHeight();

		System.out.println( xc + " - " + yc + " - " + w + " - " + h + "nivel:" + (((CDatos)obj1).nivel) );

		try
		{
    if(str.compareTo("raiz")==0){
			System.out.println(" RAIZ " + ((CDatos)obj2).nombre);
			graph.moveCells(new Object[]{((CDatos)obj2).v}, jfsizehor/(((CDatos)obj2).nivel+2), (jfsizever/30)-10 );
			((CDatos)obj2).nivel++;
		}else if(str.compareTo("izquierdo")==0){
			((CDatos)obj2).nivel = ((CDatos)obj1).nivel + 1;
			System.out.println(((CDatos)obj1).nombre + " -Izq> " + ((CDatos)obj2).nombre);
			graph.insertEdge(parent, null, "  I", ((CDatos)obj1).der, ((CDatos)obj2).v);
			if(((CDatos)obj2).nivel == 2 || ((CDatos)obj2).nivel == 3 || ((CDatos)obj2).nivel == 4)
				graph.moveCells(new Object[]{((CDatos)obj2).v}, xc-40+(jfsizehor/((int) Math.pow(2, ((CDatos)obj2).nivel)) ), yc+expver);
			else
				graph.moveCells(new Object[]{((CDatos)obj2).v}, xc+exphor-20, yc+expver);
		}else{
			((CDatos)obj2).nivel = ((CDatos)obj1).nivel + 1;
			System.out.println( ((CDatos)obj1).nombre  + " -Der> " + ((CDatos)obj2).nombre);
			graph.insertEdge(parent, null, "  D" , ((CDatos)obj1).izq, ((CDatos)obj2).v);
			if( ((CDatos)obj2).nivel == 2 || ((CDatos)obj2).nivel == 3 || ((CDatos)obj2).nivel == 4)
				graph.moveCells(new Object[]{((CDatos)obj2).v}, xc-(jfsizehor/((int) Math.pow(2, ((CDatos)obj2).nivel)) ), yc+expver);
			else
				graph.moveCells(new Object[]{((CDatos)obj2).v}, xc-20-exphor, yc+expver);
		}
		//graph.insertEdge(parent, null, "Edge", cdato.port2, v2);
		//graph.getModel().endUpdate();
		}
		finally
		{
			 putMessage(50);
			 graph.refresh();
			//graph.getModel().endUpdate();
		}
	}

	public synchronized void putMessage(int time){
		try
{
    Thread.sleep(time);
}
catch(InterruptedException e)
{
     // this part is executed when an exception (in this example InterruptedException) occurs
}
}

	public static void main(String[] args)
	{
		int cod;

		JFArbolBinB jfarbolbb = new JFArbolBinB();

    jfarbolbb.insertar(new CDatos("4999", 50));
		jfarbolbb.insertar(new CDatos("1000", 50));
		jfarbolbb.insertar(new CDatos("9000", 50));

		for(int i=0;i<=40;i++){
			int rand = ThreadLocalRandom.current().nextInt(1,9999);
			jfarbolbb.insertar(new CDatos(String.valueOf(rand), rand));
		}
		jfarbolbb.insertar(new CDatos("ZZZZ", 50));
		jfarbolbb.insertar(new CDatos("AAAA", 50));
		
		//jfarbolbb.visitarInorden();
		/*
		int[] data = {20,13,9,6,10,18,14,19,32,26,24,29,36,34,40};
		for(int i = 0; i < data.length; i++) {
						jfarbolbb.insertar(new CDatos(""+data[i], 50));
        }
     */


		jfarbolbb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfarbolbb.setSize(jfsizehor, jfsizever);
		jfarbolbb.setVisible(true);
	}

}
