// Java program to implement
// the above approach
import java.util.*;
 
class GFG{
     
static int t, n, m, a;
 
// Stack to store the
// visited vertices in
// the Topological Sort
static Stack<Integer> s;
 
// Store Topological Order
static ArrayList<Integer> tsort;
 
// Adjacency list to store edges
static ArrayList<ArrayList<Integer>> adj;
 
// To ensure visited vertex
static int[] visited = new int[(int)1e5 + 1];
 
// Function to perform DFS
static void dfs(int u)
{
     
    // Set the vertex as visited
    visited[u] = 1;
 
    for(Integer it : adj.get(u))
    {
         
        // Visit connected vertices
        if (visited[it] == 0)
            dfs(it);
    }
     
    // Push into the stack on
    // complete visit of vertex
    s.push(u);
}
 
// Function to check and return
// if a cycle exists or not
static boolean check_cycle()
{
     
    // Stores the position of
    // vertex in topological order
    Map<Integer, Integer> pos = new HashMap<>();
     
    int ind = 0;
     
    // Pop all elements from stack
    while (!s.isEmpty())
    {
        pos.put(s.peek(), ind);
         
        // Push element to get
        // Topological Order
        tsort.add(s.peek());
 
        ind += 1;
         
        // Pop from the stack
        s.pop();
    }
 
    for(int i = 0; i < n; i++)
    {
        for(Integer it : adj.get(i))
        {
             
            // If parent vertex
            // does not appear first
            if (pos.get(i) > pos.get(it))
            {
                 
                // Cycle exists
                return true;
            }
        }
    }
 
    // Return false if cycle
    // does not exist
    return false;
}
 
// Function to add edges
// from u to v
static void addEdge(int u, int v)
{
    adj.get(u).add(v);
}
 
// Driver code   
public static void main (String[] args)
{
    n = 4; m = 5;
     
    s = new Stack<>();
    adj = new ArrayList<>();
    tsort = new ArrayList<>();
     
    for(int i = 0; i < 4; i++)
        adj.add(new ArrayList<>());
         
    // Insert edges
    addEdge(0, 1);
    addEdge(0, 2);
    addEdge(1, 2);
    addEdge(2, 0);
    addEdge(2, 3);
     
    for(int i = 0; i < n; i++)
    {
        if (visited[i] == 0)
        {
            dfs(i);
        }
    }
     
    // If cycle exist
    if (check_cycle())
       System.out.println("Yes");
    else
       System.out.println("No");
}
}
 