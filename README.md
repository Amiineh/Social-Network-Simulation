# Social-Network-Simulation

This was a project defined for Engineering Probability and Statistics course.

I modeled a social network by a directed graph with weighted edges and vertices (each weight between 0, 1). Edge's weights represented the probability of a person looking at the other person's news feed via a poisson process and the vertex weight representing the probability of a person's decision weather or not to spread the news via a bernouli distribution.
The model has four news subjects and tracks all of them simultaneously. (Note that the weights are 4D vectors, each representing one news subject)
The model supports different categories of people and is capable of tracing news subjects through each of those categories.

Test cases are provided in this repository and instruction to use the program is described in the UI.

Formats specified for inputs:

Seeds.txt -> representing the initial informed vertices

format: "%d %d\t" vertex index - news index

Edges.txt -> represnting edge's weights

format: "%d %d %f\n" source vertex index - destination vertex index - poisson procces parameter

Nodes.txt -> representing vertex's weights

format: "%d %f %f %f %f\n" vertex index - probability of spreading first-to-fourth news

