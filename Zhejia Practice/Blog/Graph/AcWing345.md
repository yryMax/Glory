#### Cow Relays

------

###### Problem Analysis

The State of the problem is donated by both number of edges has been traveled $n$, and the current vertex of the traversal $v_i$, which could be represented by $S[n, \ v_i] \ (1 \ \le \ n \ \le \ N)$. The *State Transformation Equation* is
$$
S[n \ + \ 1, \ v_i] \ = \ \min_j\{S[n, \ v_j] \ + \ \textrm{edge}[v_i, \ v_j]\} \ (\{v_i, \ v_j\} \ \in \ E)
$$
The final answer is $S[N, \ E]$. Below will illustrate the connection of this problem with *Bellman-Ford Algorithm*.

For the graph $G \ = \ (V, \ E)$ with $N$ vertexes and $M$ edges, the core process of *Bellman-Ford Algorithm* is that in each iteration of *traversing all edges*, relaxing each edge $(x, \ y)$ in $G$, if the condition $\textrm{distance}[y] \ > \ \textrm{distance}[x] \ + \ \textrm{edge}[x, \ y]$ holds. The times of edge traversal has maximum $N - 1$ times. Consider the $n$ th edge traversal, it could assert that the vertext $v_i$ whose value of $\textrm{distance}[v_i]$ is updated in the current traversal has the optimal value under the circumstances that $v_i$ could be reached by source point $s$ by travelling **maximum** $n$ edges. 

Compared to the problem, there is both **similarity** and **differences** with this problem and *Bellman-Ford Algorithm*. For the state of the problem, it could represented by two variables: the number edges travelled from the source point $n$, the current vertex $v_i$. However, there is **distinction** between this problem and *Bellman-Ford Algorithm*: for this problem, $S[n, \ v_i]$ donates the minimum value that travels from $s$ to $v_i$ by visiting ***exactly*** $n$ edges. However, what *Bellman-Ford Algorithm* returns is minimum value that travels from $s$ to $v_i$ by travelling ***maximum*** n edges. 

------

###### Implementation Explanation

> Implementatation of *traversing* all the edges

In this problem, the number of edges is far less than the number of vertexes, therefore, insteading of building a graph by *Adjacent List* and traversing each node, we could directly traverse the *edge* list. 



> Realising the logic of traversing of ***exactly*** $n$ edges. 

Two arrays `distance` and `previous` are needed to stores the value of travelling ***exactly*** $n$ edges. This is because the graph given in the problem is *undirected* graph and we have to relax the edge $(v_i, \ v_j)$ for **both** directions: from $v_i$ to $v_j$ (the $n$ th edge travelled to reach $v_j$ is $(v_i, \ v_j)$) and from $v_j$ to $v_i$ (the $n$ th edge travelled to reach $v_i$ is $(v_i, \ v_j)$). However, if only one array `distance` is declared to represented the shortest length of travelling $t$ edges where $1 \ \le \ t \ \le \ T$. Suppose we have already finished calculating the shortest length of travelling $n  \ - \ 1$ edges and now we going to traverse the edge $(v_i, \ v_j)$ for the $n$ th time. Suppose we first relaxed the edge from the direction of $v_i$ to $v_j$ and `distance[j]` is updated after the traversal. Then we are unable to update relaxing the edge from the direction of $v_j$ to $v_i$ because now `distance[j]` stores the value of the *shortest length* travelling from source point $s$ to current point $v_j$ by travelling ***exactly*** $n$ edges. Therefore, if use `distance[j]` to update `distance[i]` illustrated the *shortest length* travelling from source point $s$ to current point $v_j$ by travelling ***exactly*** $n \ + \ 1$ edges, which is not correct. Therefore, array `previous` is established to store the *shortest length* travelling from source point $s$ to current point $v_j$ by travelling ***exactly*** $n \ - \ 1$ edges if we are currently calculating the *shortest length* travelling from source point $s$ to current point $v_j$ by travelling ***exactly*** $n$ edges.

```java
Arrays.fill(previous, LIMIT);
Arrays.fill(distances, LIMIT);
distances[S] = 0;
previous[S] = 0;

for (int pass = 1; pass <= N; pass++) {
  	System.arraycopy(distances, 0, previous, 0, verMax + 1); 
  	Arrays.fill(distances, Long.MAX_VALUE);
  	for (int e = 1; e <= T; e++) {
    		distances[edges[e][1]] = Math.min(distances[edges[e][1]], previous[edges[e][0]] + edges[e][2]);
    		distances[edges[e][0]] = Math.min(distances[edges[e][0]], previous[edges[e][1]] + edges[e][2]);
  	}
}
```

Time Complexity: $O(NT)$.

------

###### Optimisation 

We could use additional **data structure** and **strategy** to optimise the *State Transformation*. The Idea is based on the *Matrix Multiplication* topic discussed when calculating *All Pair Shortest Path Problem*.

The data structure utilised is **Adjacency Matrix**. Define $A^{n}$ stores the shortest path of all pairs $(v_i, \ v_j)$ after traversing exactly $n$ edges. This data structure **supports** a binary operator $\oplus$ similar to matrix multiplication. The operator calculates the *State Transformation Formula* for all pairs $(v_i, \ v_j)$:
$$
A^{n+1}(v_i, \ v_j) \ = \ \min_{v_k}\{A^n(v_i, \ v_k) \ + \ A^1(v_k, \ v_j)\}
$$
This formula represents the original *State Transformation Formula* in the *Matrix* language. Furthermore, if the state transformation is viewed from another perspective: instead of finding a substructure (subproblem) based on *the number of edges traversed*, we could find the substructure via finding a intermediate point $v_k$ along the path from $v_i$ to $v_j$. The optimal length of the path from $v_i$ to $v_j$ in $n$ edges could be decomposed into the optimal length from $v_i$ to $v_k$ in $\lambda$ edges and the optimal length from $v_k$ to $v_j$ in $n \ - \ \lambda$ edges. For all $\lambda \ = \ 1, \ 2, \ ..., \ n \ - \ 1$.
$$
S[n][v_i][v_j] \ = \ \min_{v_k}\{\min_\lambda \{S[\lambda][v_i][v_k] \ + \ S[n \ - \ \lambda][v_k][v_j]\}\} \newline \lambda \ = \ 1, \ 2, \ ..., \ n \ - \ 1
$$
Translated to the *Matrix Language*
$$
A^n(v_i, \ v_j) \ = \ \min_{v_k}\{\min_{\lambda}\{A^{\lambda}(v_i, \ v_k) \ + \ A^{n-\lambda}(v_k, \ v_j)\}\}
$$
It could asserted that operation $\oplus$ satisfied ***Associative Rule***, which is equivalent to $(A \  \oplus \ A) \ \oplus \ A \ = \ A \ \oplus \ (A \ \oplus \ A)$. The proof is 
$$
\begin{align*}
(A \  \oplus \ A) \ \oplus \ A\ (v_i, \ v_j)\ & = \ \min_{v_{k_1}}\{\min_{v_{k_2}}\{A(v_i, \ v_{k_2}) \ + \ A(v_{k_2}, \ v_{k_1})\} \ + \ A(v_{k_1}, \ v_j)\} \\
& = \ \min_{v_{k_1}}\{\min_{v_{k_2}}\{A(v_i, \ v_{k_2}) \ + \ A(v_{k_2}, \ v_{k_1}) \ + \ A(v_{k_1}, \ v_j)\} \} \\
& = \ \min_{v_{k_2}}\{A(v_i, \ v_{k_2}) \ + \ \min_{v_{k_1}}\{A(v_{k_2}, \ v_{k_1}) \ + \ A(v_{k_1}, \ v_j)\} \} \\
& = \ A \ \oplus \ (A \ \oplus \ A)\  (v_i, \ v_j)
 \end{align*}
$$
 Therefore, because $\oplus$ satisfies ***Associative Rules***, ***Binary Lifting*** technique could be utilised to calculuate $A^n$
$$
A^n \ = \ \begin{cases} 
A^{\frac{n}{2}} \ \oplus \ A^{\frac{n}{2}} \ \ \ \ \  & n \ \equiv \ 0 \ (\mathrm{mod} \  2) \\ \\
A \ \oplus \ (A^{\frac{n-1}{2}} \ \oplus \ A^{\frac{n-1}{2}}) \ \ \ \ \ & n \ \equiv \ 1 \ (\mathrm{mod} \  2)
\end{cases}
$$
The implimentation is 

```java
private static void matrixPower() {
      while (N > 0) {
          if ((N & 1) == 1)
            		ansMap = matrixMultiplication(ansMap, adjacencyMap);
          adjacencyMap = matrixMultiplication(adjacencyMap, adjacencyMap);
          N >>= 1;
      }
}
```

The answer is $A^n(S, \ E)$ and the time complexity is $O(T^3\log(N))$.

------

###### Final Remark

There is a **key difference** between the intrinsic meaning of matrix in the context of *All Pair Shortest Path Problem* and this problem. 

- In *All Pair Shortest Path Problem*, matrix $A^n \ (v_i, \ v_j)$ represents the shortest distance from $v_i$ to $v_j$ traversing ***maximum*** $n$ edges.
- In this problem, matrix $A^n \ (v_i, \ v_j)$ represents the shortest distance from $v_i$ to $v_j$ traversing ***exactly*** $n$ edges.

The key reason that causes this difference is ***not*** because *the State Transformation Formula*, but because of ***the base matrix*** $A$. There are ***different initialisation*** of elements $A \ (v_i, \ v_i) \ (v_i \ \in \ V)$. 

- In *All Pair Shortest Path Problem*, element $A \ (v_i, \ v_i)$ is always initialised to 0, whenever there are **self loop** or not. Therefore, during *the State Transformation Process*, for the distance from $v_i$ to $v_j$, if passing another vertex $v_k \ (v_k \ \neq \ v_i)$ will generate a path with length **less optimal** than the length produced by staying at the current vertex $v_i$, the operation $\oplus$ allows the value that is generated by staying at the current vertex $v_i$ to be assigned to the updated value of $(v_i, \ v_j)$.
- In this problem, elements $A \ (v_i, \ v_i)$ is initialised to length of ***self loop*** if there is a self loop that starts and ends at the same vertex or `LIMIT` if there is no such a self loop exists. Therefore, during *the State Transformation Process*, the behaviour of *staying at the same vertex* is disallowed if there is *no self loop*, because the value $(v_i, \ v_i)$ that is equal to `LIMIT` will be eliminates during the $\min$ operation. 
