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
            case NORTH_EAST:
                destination = new TerritoryCoordinate(current.getStartChunkX() + 32, current.getStartChunkZ() - 32);
                break;
            case NORTH_WEST:
                destination = new TerritoryCoordinate(current.getStartChunkX() - 32, current.getStartChunkZ() - 32);
                break;
            case SOUTH_EAST:
                destination = new TerritoryCoordinate(current.getStartChunkX() + 32, current.getStartChunkZ() + 32);
                break;
            case SOUTH_WEST:
                destination = new TerritoryCoordinate(current.getStartChunkX() - 32, current.getStartChunkZ() + 32);
                break;
        }
        return Optional.ofNullable(territories.get(destination));
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

        for (Direction direction : Direction.values()) {
            Optional<Territory> next = findAdjacentOf(origin, direction);

            if (next.map(Territory::getKingdom).filter(kingdom -> kingdom.equals(origin.getKingdom())).isPresent()) {

                Territory toBeAdded = next.get();

                if (!territories.contains(toBeAdded)) {
                    territories.add(toBeAdded);
                    search(territories.get(territories.size() - 1), territories);
                }
            }
        }
        return territories;
    }

    public boolean isFriendly(Kingdom k1, Kingdom k2) {
        return k1.equals(k2) || k1.getAlliance().equals(k2.getAlliance());
    }

    public void updateAllegiance(Territory origin) {
        for (int i = 0; i < Direction.values().length; i++) {
            Optional<Territory> next = findAdjacentOf(origin, Direction.values()[i]);
            if (next.isPresent()) {
                Territory neighbour = next.get();
                byte b = origin.getAllegiance();
                if (isFriendly(neighbour.getKingdom(), origin.getKingdom())) {
                    origin.setAllegiance((byte) (b | (1 << i)));
                    if (i + 4 < 8)
                        neighbour.setAllegiance((byte) (b | (1 << (i + 4))));
                    else
                        neighbour.setAllegiance((byte) (b | (1 << (i - 4))));
                } else {
                    origin.setAllegiance((byte) (b & ~(1 << (i))));
                    if (i + 4 < 8)
                        neighbour.setAllegiance((byte) (b & ~(1 << (i + 4))));
                    else
                        neighbour.setAllegiance((byte) (b & ~(1 << (i - 4))));
                }
            }
        }
    }

    public void add(TerritoryCoordinate coordinate, Territory territory) {
        territories.put(coordinate, territory);
        //updateAllegiance(territory);
    }

    public void remove(TerritoryCoordinate coordinate) {
        territories.remove(coordinate);
    }
}