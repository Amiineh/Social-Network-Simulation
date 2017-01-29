# Social-Network-Simulation

This was a project defined for Engineering Probability and Statistics course.
<br />
## Project description
I modeled a social network by a directed graph with weighted edges and vertices (each weight between 0, 1). Edge's weights represented the probability of a person looking at the other person's news feed via a poisson process and the vertex weight representing the probability of a person's decision weather or not to spread the news via a bernouli distribution.
The model has four news subjects and tracks all of them simultaneously. (Note that the weights are 4D vectors, each representing one news subject)
The model supports different categories of people and is capable of tracing news subjects through each of those categories.

## Input Formats
Test cases are provided in this repository and instruction to use the program is described in the UI.

-><img src="https://cloud.githubusercontent.com/assets/19167068/22405381/0905632c-e657-11e6-864d-021320987b77.jpg" width="600"><-


In the text editor you should copy the address of the folder containing three files: <br />
**Seeds.txt**: representing the initial informed vertices
format: "vertex_index &nbsp; &nbsp; &nbsp; news_index" <br />
Example:
```
1 2
```

**Edges.txt**: represnting edge's weights
format: "source_vertex_index &nbsp; &nbsp; &nbsp; destination_vertex_index &nbsp; &nbsp; &nbsp;  poisson_procces_parameter"<br />
 Example:
```
5 6 0.12
```

**Nodes.txt**: representing vertex's weights
format: "vertex_index &nbsp; &nbsp; &nbsp; vertex_category &nbsp; &nbsp; &nbsp; probability_of_spreading_first_to_fourth_news"<br />
 Example:
 ```
 0 student 0.88 0 0 0
```
<br />
## Final Result
![alt tag](https://cloud.githubusercontent.com/assets/19167068/22405385/0c2e644a-e657-11e6-9298-b9c6c6850af2.jpg)
