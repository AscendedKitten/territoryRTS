package at.cath;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum TerritoryManager {

    INSTANCE;

    @Getter
    private final Map<TerritoryCoordinate, Territory> territories = new HashMap<>();

    public Territory findByCoordinate(TerritoryCoordinate coordinate) {
        return territories.get(coordinate);
    }

    public Territory findAdjacentOf(Territory territory, Direction direction) {
        TerritoryCoordinate current = territory.getTerritoryCoordinate();
        TerritoryCoordinate destination = current;

        switch (direction) {
            case NORTH:
                destination = new TerritoryCoordinate(current.getChunkX(), current.getChunkZ() - 1);
                break;
            case SOUTH:
                destination = new TerritoryCoordinate(current.getChunkX(), current.getChunkZ() + 1);
                break;
            case EAST:
                destination = new TerritoryCoordinate(current.getChunkX() + 1, current.getChunkZ());
                break;
            case WEST:
                destination = new TerritoryCoordinate(current.getChunkX() - 1, current.getChunkZ());
                break;
        }
        return territories.get(destination);
    }


    public void add(TerritoryCoordinate coordinate, Territory territory) {
        territories.put(coordinate, territory);
    }


}
