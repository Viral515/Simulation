package ru.egar.springcourses;

import ru.egar.springcourses.entity.Entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

    public static Coordinates SearchFirstStepToTarget(Map map, Entity currentEntity, Entity entityType) {
        int mapSize= map.GetMapSize();
        Queue<GraphNode> queue = new LinkedList<>();
        ArrayList<GraphNode> allNodes = new ArrayList<>();
        queue.add(new GraphNode(currentEntity.getCoordinates(), currentEntity));

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                allNodes.add(new GraphNode(new Coordinates(j,i), map.GetTile(new Coordinates(j, i))));
            }
        }

        do {
            GraphNode node = queue.remove();
            node.SetIsVisited(true);
            if (node.GetMapTile() != null) {
                if (node.GetMapTile().getClass().equals(entityType.getClass())) {
                    while(node.parent.mapTile != currentEntity) {
                        node = node.parent;
                    }
                    return node.GetCoordinates();
                }
            }
            CheckNodeExist(mapSize, queue, allNodes, node, new Coordinates( node.GetCoordinates().getX(), node.GetCoordinates().getY() - 1), entityType);
            CheckNodeExist(mapSize, queue, allNodes, node, new Coordinates( node.GetCoordinates().getX() + 1, node.GetCoordinates().getY()), entityType);
            CheckNodeExist(mapSize, queue, allNodes, node, new Coordinates( node.GetCoordinates().getX(), node.GetCoordinates().getY() + 1), entityType);
            CheckNodeExist(mapSize, queue, allNodes, node, new Coordinates( node.GetCoordinates().getX() - 1, node.GetCoordinates().getY()), entityType);
        } while (!queue.isEmpty());

        return null;
    }

    private static void CheckNodeExist(int mapSize, Queue<GraphNode> queue, ArrayList<GraphNode> allNodes,
                                       GraphNode parent, Coordinates coordinates, Entity entityType) {
        int x = coordinates.getX();
        int y = coordinates.getY();
        if (0 <= x && x < mapSize && 0 <= y && y < mapSize
                && !allNodes.get(y * mapSize + x).IsVisited()
                && (allNodes.get(y * mapSize + x).mapTile == null
                || allNodes.get(y * mapSize + x).mapTile.getClass().equals(entityType.getClass()))){

            allNodes.get(y * mapSize + x).SetParent(parent);
            allNodes.get(y * mapSize + x).SetIsVisited(true);
            queue.add(allNodes.get(y * mapSize + x));
        }
    }

    static class GraphNode{
        private final Entity mapTile;
        private final Coordinates coordinates;
        private GraphNode parent;
        private Boolean isVisited;
        public GraphNode(Coordinates coordinates, Entity mapTile) {
            this.coordinates = coordinates;
            this.mapTile = mapTile;
            this.isVisited = false;
        }

        public Entity GetMapTile() {
            return mapTile;
        }

        public void SetParent(GraphNode parent) {
            this.parent = parent;
        }

        public Boolean IsVisited() {
            return isVisited;
        }

        public void SetIsVisited(Boolean isVisited) {
            this.isVisited = isVisited;
        }

        public Coordinates GetCoordinates() {
            return coordinates;
        }
    }
}
