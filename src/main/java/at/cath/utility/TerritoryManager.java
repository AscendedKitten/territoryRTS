package at.cath.utility;

import at.cath.Alliance;
import at.cath.Kingdom;
import at.cath.Territory;
import at.cath.TerritoryCoordinate;
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

    public Optional<Territory> findAdjacentOf(Territory territory, Direction direction) {
        TerritoryCoordinate current = territory.getTerritoryCoordinate();

        return Optional.ofNullable(territories.get(
                new TerritoryCoordinate(current.getStartChunkX() + direction.getXShift(),
                        current.getStartChunkZ() + direction.getZShift())));
    }
    
    //rework
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

    public List<Territory> getInRangeOf(Territory territory) {
        return search(territory, null);
    }

    private List<Territory> search(Territory origin, List<Territory> territories) {
        if (territories == null)
            territories = new ArrayList<>();
        for (int i = 0; i < Direction.values().length; i++) {
            Optional<Territory> next = findAdjacentOf(origin, Direction.values()[i]);
            if (next.isPresent()) {
                Territory toBeAdded = next.get();
                if ((toBeAdded.getAllegiance() & (1 << (i + 4) % 8)) != 0) {
                    if (!territories.contains(toBeAdded)) {
                        territories.add(toBeAdded);
                        search(territories.get(territories.size() - 1), territories);
                    }
                }
            }
        }
        return territories;
    }

    public boolean isFriendly(Kingdom k1, Kingdom k2) {
        return k1.equals(k2) || k1.getAlliance().equals(k2.getAlliance());
    }

    public void updateAllegiance(Territory origin) {
        for (Direction direction : Direction.values()) {
            int i = direction.ordinal();
            Optional<Territory> next = findAdjacentOf(origin, Direction.values()[i]);
            if (next.isPresent()) {
                Territory neighbour = next.get();
                byte bOrigin = origin.getAllegiance();
                byte bNeighbor = neighbour.getAllegiance();
                if (isFriendly(neighbour.getKingdom(), origin.getKingdom())) {
                    origin.setAllegiance((byte) (bOrigin | (1 << i)));
                    neighbour.setAllegiance((byte) (bNeighbor | (1 << (i + 4) % 8)));
                } else {
                    origin.setAllegiance((byte) (bOrigin & ~(1 << (i))));
                    neighbour.setAllegiance((byte) (bNeighbor & ~(1 << (i + 4) % 8)));
                }
            }
        }
    }

    public void add(TerritoryCoordinate coordinate, Territory territory) {
        territories.put(coordinate, territory);
        updateAllegiance(territory);
    }

    public void remove(TerritoryCoordinate coordinate) {
        territories.remove(coordinate);
    }
}