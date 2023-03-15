def bfsOfGraph(V, adj):
 
    bfs_traversal = []
    vis = [False]*V
    for i in range(V):
 
        # To check if already visited
        if (vis[i] == False):
            q = []
            vis[i] = True
            q.append(i)
 
            # BFS starting from ith node
            while (len(q) > 0):
                g_node = q.pop(0)
 
                bfs_traversal.append(g_node)
                for it in adj[g_node]:
                    if (vis[it] == False):
                        vis[it] = True
                        q.append(it)
 
    return bfs_traversal