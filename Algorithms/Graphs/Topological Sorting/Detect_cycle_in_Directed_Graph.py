
t = 0
n = 0
m = 0
a = 0
  
# Stack to store the
# visited vertices in
# the Topological Sort
s = []
  
# Store Topological Order
tsort = []
  
# Adjacency list to store edges
adj = [[] for i in range(100001)]
  
# To ensure visited vertex
visited = [False for i in range(100001)]
  
# Function to perform DFS
def dfs(u):
 
    # Set the vertex as visited
    visited[u] = 1
     
    for it in adj[u]:
 
        # Visit connected vertices
        if (visited[it] == 0):
            dfs(it)
  
    # Push into the stack on
    # complete visit of vertex
    s.append(u)
 
# Function to check and return
# if a cycle exists or not
def check_cycle():
 
    # Stores the position of
    # vertex in topological order
    pos = dict()
     
    ind = 0
     
    # Pop all elements from stack
    while (len(s) != 0):
        pos[s[-1]] = ind
  
        # Push element to get
        # Topological Order
        tsort.append(s[-1])
  
        ind += 1
  
        # Pop from the stack
        s.pop()
     
    for i in range(n):
        for it in adj[i]:
            first = 0 if i not in pos else pos[i]
            second = 0 if it not in pos else pos[it]
 
            # If parent vertex
            # does not appear first
            if (first > second):
  
                # Cycle exists
                return True
  
    # Return false if cycle
    # does not exist
    return False
 
# Function to add edges
# from u to v
def addEdge(u, v):
     
    adj[u].append(v)
 
# Driver Code
if __name__ == "__main__":
     
    n = 4
    m = 5
  
    # Insert edges
    addEdge(0, 1)
    addEdge(0, 2)
    addEdge(1, 2)
    addEdge(2, 0)
    addEdge(2, 3)
     
    for i in range(n):
        if (visited[i] == False):
            dfs(i)
  
    # If cycle exist
    if (check_cycle()):
        print('Yes')
    else:
        print('No')