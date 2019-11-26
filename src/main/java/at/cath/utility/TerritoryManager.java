package at.cath.utility;

import at.cath.Kingdom;
import at.cath.Territory;
import at.cath.TerritoryCoordinate;
import lombok.Getter;

import java.util.*;

public enum TerritoryManager {

    INSTANCE;

    @Getter
    private final Map<TerritoryCoordinate, Territory> territories = new HashMap<>();

    public static TerritoryManager getInstance() {
        return INSTANCE;
    }

    public Territory findByCoordinate(int x, int z) {
        return territories.get(new TerritoryCoordinate(x, z));
    }

    public Optional<Territory> findAdjacentOf(Territory territory, Direction direction) {
        TerritoryCoordinate current = territory.getTerritoryCoordinate();

        return Optional.ofNullable(territories.get(
                new TerritoryCoordinate(current.getX() + direction.getXShift(),
                        current.getZ() + direction.getZShift())));
    }

    public Set<Territory> getInRangeOf(Territory territory) {
        return search(territory, null);
    }

    private Set<Territory> search(Territory origin, HashSet<Territory> territories) {
        if (territories == null)
            territories = new HashSet<>();
        for (Direction direction : Direction.values()) {
            int i = direction.ordinal();
            Optional<Territory> next = findAdjacentOf(origin, Direction.values()[i]);
            if (next.isPresent()) {
                Territory toBeAdded = next.get();
                if ((toBeAdded.getAllegianceBit(i)))
                    if (territories.add(toBeAdded))
                        search(toBeAdded, territories);

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
                if (isFriendly(neighbour.getKingdom(), origin.getKingdom())) {
                    origin.updateAllegiance(i, true, false);
                    neighbour.updateAllegiance(i, true, true);
                } else {
                    origin.updateAllegiance(i, false, false);
                    neighbour.updateAllegiance(i, false, true);
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