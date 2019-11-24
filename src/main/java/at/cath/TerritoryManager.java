package at.cath;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public enum TerritoryManager {

    INSTANCE;

    @Getter
    private final Map<TerritoryCoordinate, Territory> territories = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(TerritoryManager.class);

    public static TerritoryManager getInstance() {
        return INSTANCE;
    }

    public Territory findByCoordinate(TerritoryCoordinate coordinate) {
        return territories.get(coordinate);
    }

    public Territory findAdjacentOf(Territory territory, Direction direction) {
        TerritoryCoordinate current = territory.getTerritoryCoordinate();
        TerritoryCoordinate destination = current;

        switch (direction) {
            case NORTH:
                destination = new TerritoryCoordinate(current.getStartChunkX(), current.getStartChunkZ() - 32);
                break;
            case SOUTH:
                destination = new TerritoryCoordinate(current.getStartChunkX(), current.getStartChunkZ() + 32);
                break;
            case EAST:
                destination = new TerritoryCoordinate(current.getStartChunkX() + 32, current.getStartChunkZ());
                break;
            case WEST:
                destination = new TerritoryCoordinate(current.getStartChunkX() - 32, current.getStartChunkZ());
                break;
        }
        return territories.get(destination);
    }

    public int distanceInTerritories(Territory territory1, Territory territory2) {
        TerritoryCoordinate territoryCoordinate1 = territory1.getTerritoryCoordinate();
        TerritoryCoordinate territoryCoordinate2 = territory2.getTerritoryCoordinate();

        int territoryDifferenceX = Math.abs(territoryCoordinate1.getStartChunkX() / 32 - territoryCoordinate2.getStartChunkX() / 32);
        int territoryDifferenceZ = Math.abs(territoryCoordinate1.getStartChunkZ() / 32 - territoryCoordinate2.getStartChunkZ() / 32);

        return territoryDifferenceX + territoryDifferenceZ;
    }

    public Territory fromChunkCoords(int x, int z) {
        int startChunkX = (x / 32) * 32;
        int startChunkZ = (z / 32) * 32;

        logger.debug("({}|{}), within territory starting at: ({}/{})", x, z, startChunkX, startChunkZ);

        TerritoryCoordinate territoryCoordinate = new TerritoryCoordinate(startChunkX, startChunkZ);

        return Optional.ofNullable(territories.get(territoryCoordinate))
                .orElseGet(() -> {
                    Territory territory = new Territory(Kingdom.defaultKingdom(), territoryCoordinate);
                    territories.put(territoryCoordinate, territory);
                    return territory;
                });
    }

    public List<Territory> getinRangeOf(Territory territory) {
        //To be expanded
        return search(null, territory, Direction.NORTH);
    }

    private List<Territory> search(List<Territory> territoriesFound, Territory latest, Direction direction) {
        if (territoriesFound == null)
            territoriesFound = new ArrayList<>();

        if (findAdjacentOf(latest, direction) != null) {
            territoriesFound.add(findAdjacentOf(latest, direction));
            search(territoriesFound, territoriesFound.get(territoriesFound.size() - 1), direction);
        }
        return territoriesFound;
    }

    public void add(TerritoryCoordinate coordinate, Territory territory) {
        territories.put(coordinate, territory);
    }
}