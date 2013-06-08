/*
 * Created on Jul 14, 2004
 */
package tinyos.dlrc.views.cgraph.layout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.graph.DirectedGraph;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.EdgeList;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.draw2d.graph.NodeList;


/**
 * Creates dummy edges between nodes, to be used with NodeJoiningDirectedGraphLayout
 * @author Phil Zoio
 */
@Deprecated
public class ClusterEdgeCreator
{

	//sets up maximum depth of recursion to set up initial cluster list
	private static final int INITIAL_RECURSION_DEPTH = 3;

	NodeList nodeList;
	EdgeList edgeList;
	DirectedGraph graph;

	List edgesAdded;
	List encountered = new ArrayList();
	List clusters = new ArrayList();

	Cluster currentCluster = null;

	/**
	 * @param graph
	 */
	public ClusterEdgeCreator()
	{
		super();
	}

	public void visit(DirectedGraph graph)
	{

		try
		{

			this.graph = graph;
			this.nodeList = graph.nodes;
			this.edgeList = graph.edges;
			edgesAdded = new ArrayList();

			//iterate through all of the nodes in the node list
			for (Iterator iter = nodeList.iterator(); iter.hasNext();)
			{
				Node node = (Node) iter.next();

				//check whether we have already come across this node
				if (!encountered.contains(node))
				{
					//create a new cluster for this node
					currentCluster = new Cluster();
					clusters.add(currentCluster);
					encountered.add(node);
					currentCluster.set.add(node);

					//System.out.println("Adding to NEW cluster: " + node + ", cluster: " + currentCluster);
					// recursively add any other nodes reachable from it
					int depth = INITIAL_RECURSION_DEPTH;
					recursivelyAddToCluster(node, depth);
				}
				else
				{
					//System.out.println("Already encountered: " + node);
				}
			}

			//System.out.println("Clusters: ");
			for (Iterator iter = clusters.iterator(); iter.hasNext();)
			{
				Cluster cluster = (Cluster) iter.next();
				//System.out.println(cluster);

			}

			coalesceRemainingClusters();

			//System.out.println("");
			joinClusters();

		}
		catch (RuntimeException e)
		{
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * If recursion fails to join all the remaining
	 */
	private void coalesceRemainingClusters()
	{
	}

	/**
	 * Joins the clusters together
	 */
	private void joinClusters()
	{
		if (clusters.size() > 1)
		{

			Node sourceNode = null;
			Node targetNode = null;

			//add an edge from each successive cluster to next
			for (Iterator iter = clusters.iterator(); iter.hasNext();)
			{
				Cluster cluster = (Cluster) iter.next();
				if (sourceNode != null)
				{
					//use first node in set as target node
					targetNode = (Node) cluster.set.get(0);
					newDummyEdge(sourceNode, targetNode);
				}

				//set up source node for the next iteration using last node in
				// set
				sourceNode = (Node) cluster.set.get(cluster.set.size() - 1);

			}

		}
	}

	private void recursivelyAddToCluster(Node node, int depth)
	{

		if (depth > 3)
			return;
		else
		{
			depth++;
			EdgeList incoming = node.incoming;
			for (Iterator iter = incoming.iterator(); iter.hasNext();)
			{
				Edge edge = (Edge) iter.next();
				Node incomingNode = edge.source;

				if (!encountered.contains(incomingNode))
				{
					encountered.add(incomingNode);
					currentCluster.set.add(incomingNode);
					//System.out.println("Adding to current cluster: " + incomingNode + ", cluster: " + currentCluster);
					recursivelyAddToCluster(incomingNode, depth);
				}
				else
				{
					//System.out.println("Already encountered: " + incomingNode);
				}
			}

			EdgeList outgoing = node.outgoing;
			for (Iterator iter = outgoing.iterator(); iter.hasNext();)
			{
				Edge edge = (Edge) iter.next();
				Node outgoingNode = edge.target;

				if (!encountered.contains(outgoingNode))
				{
					encountered.add(outgoingNode);
					currentCluster.set.add(outgoingNode);
					//System.out.println("Adding to current cluster: " + outgoingNode + ", cluster: " + currentCluster);
					recursivelyAddToCluster(outgoingNode, depth);
				}
				else
				{
					//System.out.println("Already encountered: " + outgoingNode);
				}
			}
		}

	}

	/**
	 * creates a new dummy edge to be used in the graph
	 */
	private Edge newDummyEdge(Node sourceNode, Node targetNode)
	{
		boolean addedEdge;
		DummyEdgePart edgePart = new DummyEdgePart();
		Edge edge = new Edge(edgePart, sourceNode, targetNode);
		edge.weight = 2;

		//add the new edge to the edge list
		edgeList.add(edge);

		targetNode = sourceNode;
		addedEdge = true;
		return edge;
	}

	/**
	 * Very thin wrapper around List
	 */
	private class Cluster
	{

		List set = new ArrayList();

		public String toString()
		{
			return set.toString();
		}
	}

}