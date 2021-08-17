package bst;

public class AreNodesTotalyOrdered {
    //  Write a program which takes two nodes in a BST and a third node, the "middle"
    // node, and determines if one of the two nodes is a proper ancestor and the other a
    // proper descendant of the middle. (A proper ancestor of a node is an ancestor that
    // is not equal to the node; a proper descendant is defined similarly.) For example, in
    // Figure 15.1 on Page 255, if the middle is Node /, your function should return true if
    // the two nodes are {A,K] or j/,M|. It should return false if the two nodes are [1,P| or
    // {],K}. You can assume that all keys are unique. Nodes do not have pointers to their
    // parents
    //
    // Examples:
    //              10
    //      5             20
    // -4     8        14    21
    //-10 0  7  9    11  17     24
    //                     19
    //                    16
    // mid=17  14 24  false
    // mid=17  14 16
}
