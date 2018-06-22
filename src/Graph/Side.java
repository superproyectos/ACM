package Graph;

public class Side
{
    int a,b, weight;
    public Side(int a,int b,int weight)
    {
        this.a=a;
        this.b=b;
        this.weight = weight;
    }
    public boolean equals(int p,int q)
    {
        return (p==a&&q==b)||(p==a&&q==b);
    }


    public boolean isContained(int n)
    {
        return (n==a||n==b);
    }
}
