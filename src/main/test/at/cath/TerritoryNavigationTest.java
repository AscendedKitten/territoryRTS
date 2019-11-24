package at.cath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TerritoryNavigationTest {

    private final TerritoryManager territoryManager = TerritoryManager.getInstance();
    private final List<Territory> directionalTerritories = DomainFixtures.getDirectionalTerritories();

    @BeforeEach
    void setup() {
        directionalTerritories.forEach(territory -> territoryManager.add(territory.getTerritoryCoordinate(), territory));
    }


    @Test
    void ensureAdjacentRetrieval() {
        Territory origin = directionalTerritories.get(0);

        assertThat(territoryManager.findAdjacentOf(origin, Direction.NORTH)).isEqualTo(directionalTerritories.get(1));
        assertThat(territoryManager.findAdjacentOf(origin, Direction.SOUTH)).isEqualTo(directionalTerritories.get(2));
        assertThat(territoryManager.findAdjacentOf(origin, Direction.EAST)).isEqualTo(directionalTerritories.get(3));
        assertThat(territoryManager.findAdjacentOf(origin, Direction.WEST)).isEqualTo(directionalTerritories.get(4));
    }

    @Test
    void ensureTerritoryBounds() {
        //Testing for (0,0)
        assertThat(territoryManager.fromChunkCoords(1, 1)).isIn(directionalTerritories);
        assertThat(territoryManager.fromChunkCoords(16, 16)).isIn(directionalTerritories);

        //Testing for (32, 0)
        assertThat(territoryManager.fromChunkCoords(35, 31)).isIn(directionalTerritories);

        //New Creation (32/32)
        assertThat(territoryManager.fromChunkCoords(32, 32)).isNotIn(directionalTerritories);
    }

    @Test
    void ensureTerritoryDistance() {
        Territory from = directionalTerritories.get(0);
        Territory to = new Territory(Kingdom.defaultKingdom(), new TerritoryCoordinate(96, 96));

        assertThat(territoryManager.distanceInTerritories(from, to)).isEqualTo(6);
        assertThat(from.distanceTo(to)).isEqualTo(6);

        assertThat(territoryManager.distanceInTerritories(from, directionalTerritories.get(2))).isEqualTo(1);
    }

    @Test
    void ensureFindSurroundings() {
        assertThat(territoryManager.getinRangeOf(directionalTerritories.get(0)))
                .isEqualTo(List.of(directionalTerritories.get(1), directionalTerritories.get(2), directionalTerritories.get(3), directionalTerritories.get(4)));
    }
}