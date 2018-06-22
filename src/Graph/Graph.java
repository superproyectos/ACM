package Graph;

import java.util.ArrayList;
import java.util.List;

public class Graph
{
    List<Node> nodes;
    List<Side>sides;
    int[][] sideMatrix;
    public Graph(int n)
    {
        nodes=new ArrayList<>();
        sides=new ArrayList<>();
        sideMatrix =new int[n][n];
    }
    public void addNode(int node)
    {
        if(!isCountained(node))
            nodes.add(new Node(node));
    }
    public void addSide(int a,int b,int peso)
    {
        if(!isRepeatSide(a, b))
        {
            sides.add(new Side(a, b, peso));
            sideMatrix[a-1][b-1]=sideMatrix[b-1][a-1]=peso;
        }
    }
    public void addSide(int a,int b)
    {
        sides.add(new Side(a,b,1));
    }
    public void addNodeAndSide(int a,int b,int peso)
    {
        addNode(a);
        addNode(b);
        addSide(a,b,peso);
    }
    public void addNodeAndSide(int a,int b)
    {
        addNodeAndSide(a,b,1);
    }
    private boolean isCountained(int a)
    {
        for(Node p:nodes)
            if(p.value ==a)
                return true;
        return false;
    }
    private boolean isRepeatSide(int a,int b)
    {
        for(Side p:sides)
            if(p.equals(a,b))
                return true;
        return false;
    }
    private void uncheck()
    {
        for (Node p:nodes)
        {
            p.check = false;
            p.weight = -1;
            p.ref=null;
        }
    }
    private int getIndex(int a)
    {
        for(int i=0;i<nodes.size();i++)
            if(nodes.get(i).value==a)
                return i;
        return -1;
    }
    private Node getNodeFinded(int a)
    {
        int i=getIndex(a);
        return i!=-1?nodes.get(i):null;
    }
    private Node getMinVertexOpen()
    {
        Node min=null;
        for(Node a:nodes)
            if(!a.check&&a.weight!=-1)
                min=(min==null||min.weight>a.weight?a:min);
        return min;
    }
    private List<Side> getNext(int v)
    {
        List<Side> son=new ArrayList<>();
        for(Side a:sides)
            if(a.isContained(v))
                son.add(a);
        return son;
    }
    public int runReferences(Node a)
    {
        if(a.ref!=null)
        {
            Side p=a.ref;
            a.ref=null;
            if(p.a!=a.value)
                return runReferences(getNodeFinded(p.a))+a.weight;
            else
                return runReferences(getNodeFinded(p.b))+a.weight;
        }
        return 0;
    }
    public int minWay(int a,int b)
    {
        uncheck();
        Node node=getNodeFinded(a);

        node.weight=0;
        Node n;
        while ((n=getMinVertexOpen())!=null)
        {
            n.check=true;
            List<Side>next=getNext(n.value);
            while (next.size()>0)
            {
                Side mn=next.get(0);
                Node m=mn.a==n.value?getNodeFinded(mn.b):getNodeFinded(mn.a);
                if(n.weight+mn.weight<=m.weight||m.weight==-1)
                {
                    m.weight=n.weight+mn.weight;
                    m.ref=mn;
                }
                next.remove(0);
            }
        }
        n=getNodeFinded(b);
        return n==null?0:n.weight;
    }
    public void showSides()
    {
        for(int i=0;i<sideMatrix.length;i++)
        {
            for (int j = 0; j < sideMatrix.length; j++)
                System.out.print(sideMatrix[i][j] + " ");
            System.out.println();
        }
    }
}
