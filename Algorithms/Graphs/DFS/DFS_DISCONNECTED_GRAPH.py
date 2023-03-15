'''Python3 program to print DFS traversal for complete graph'''
from collections import defaultdict
  
class Graph:
    # Constructor
    def __init__(self):
        self.graph = defaultdict(list)

    def addEdge(self, u, v):
        self.graph[u].append(v)
 
    def DFSUtil(self, v, visited):
        visited.add(v)
        print(v, end=" ")
 
    # recur for all the vertices adjacent to this vertex
        for neighbour in self.graph[v]:
            if neighbour not in visited:
                self.DFSUtil(neighbour, visited)
    # The function to do DFS traversal. It uses recursive DFSUtil
 
    def DFS(self):
        # create a set to store all visited vertices
        visited = set()
    # call the recursive helper function to print DFS traversal starting from all
    # vertices one by one
        for vertex in self.graph:
            if vertex not in visited:
                self.DFSUtil(vertex, visited)
# Driver's code
# create a graph given in the above diagram
 
 
if __name__ == "__main__":
    print("Following is Depth First Traversal \n")
    g = Graph()
    g.addEdge(0, 1)
    g.addEdge(0, 2)
    g.addEdge(1, 2)
    g.addEdge(2, 0)
    g.addEdge(2, 3)
    g.addEdge(3, 3)
 
    # Function call
    g.DFS()