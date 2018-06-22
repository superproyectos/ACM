import Graph.Graph;

public class Main
{
    public static void main(String[] args)
    {
        Graph graph=new Graph(5);
        graph.addNodeAndSide(1,2,1);
        graph.addNodeAndSide(5,2,5);
        graph.addNodeAndSide(3,5,1);
        graph.addNodeAndSide(1,4,2);
        graph.addNodeAndSide(4,3,1);
        graph.addNodeAndSide(1,3,5);
        System.out.println(graph.minWay(1,3));
        System.out.println(graph.minWay(1,5));
        graph.showSides();
    }
}
