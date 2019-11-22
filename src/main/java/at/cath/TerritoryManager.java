package at.cath;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum TerritoryManager {

    INSTANCE;

    @Getter
    private final Map<TerritoryCoordinate, Territory> territories = new HashMap<>();

    public TerritoryManager getInstance() {
        return INSTANCE;
    }

    public Territory findByCoordinate(TerritoryCoordinate coordinate) {
        return territories.get(coordinate);
    }

    public Territory findNorthOf(Territory territory) {
        TerritoryCoordinate current = territory.getTerritoryCoordinate();
        return territories.get(new TerritoryCoordinate(current.getChunkX(), current.getChunkZ() - 1));
    }

    public Territory findSouthOf(Territory territory) {
        TerritoryCoordinate current = territory.getTerritoryCoordinate();
        return territories.get(new TerritoryCoordinate(current.getChunkX(), current.getChunkZ() + 1));
    }

    public Territory findEastOf(Territory territory) {
        TerritoryCoordinate current = territory.getTerritoryCoordinate();
        return territories.get(new TerritoryCoordinate(current.getChunkX() + 1, current.getChunkZ()));
    }

    public Territory findWestOf(Territory territory) {
        TerritoryCoordinate current = territory.getTerritoryCoordinate();
        return territories.get(new TerritoryCoordinate(current.getChunkX() - 1, current.getChunkZ()));
    }


    public void add(TerritoryCoordinate coordinate, Territory territory) {
        territories.put(coordinate, territory);
    }


}
