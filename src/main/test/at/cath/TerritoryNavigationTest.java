package at.cath;

import at.cath.utility.Direction;
import at.cath.utility.TerritoryManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TerritoryNavigationTest {

    private final TerritoryManager territoryManager = TerritoryManager.getInstance();
    private final List<Territory> cardinalTerritories = DomainFixtures.getDirectionalTerritories();

    @BeforeEach
    void setup() {
        cardinalTerritories.forEach(territory -> territoryManager.add(territory.getTerritoryCoordinate(), territory));
    }


    @Test
    void ensureAdjacentRetrieval() {
        Territory origin = cardinalTerritories.get(0);

        assertThat(territoryManager.findAdjacentOf(origin, Direction.NORTH).get()).isEqualTo(cardinalTerritories.get(1));
        assertThat(territoryManager.findAdjacentOf(origin, Direction.SOUTH).get()).isEqualTo(cardinalTerritories.get(2));
        assertThat(territoryManager.findAdjacentOf(origin, Direction.EAST).get()).isEqualTo(cardinalTerritories.get(3));
        assertThat(territoryManager.findAdjacentOf(origin, Direction.WEST).get()).isEqualTo(cardinalTerritories.get(4));
    }

    //abandon
    @Test
    void ensureTerritoryBounds() {
        //Testing for (0,0)
        assertThat(territoryManager.fromChunkCoords(1, 1)).isIn(cardinalTerritories);
        assertThat(territoryManager.fromChunkCoords(16, 16)).isIn(cardinalTerritories);

        //Testing for (32, 0)
        assertThat(territoryManager.fromChunkCoords(35, 31)).isIn(cardinalTerritories);

        //New Creation (32/32)
        assertThat(territoryManager.fromChunkCoords(32, 32)).isNotIn(cardinalTerritories);
    }

    @Test
    void ensureTerritoryDistance() {
        Territory from = cardinalTerritories.get(0);
        Territory to = new Territory(Kingdom.defaultKingdom(), new TerritoryCoordinate(96, 96));

        assertThat(territoryManager.distanceInTerritories(from, to)).isEqualTo(6);
        assertThat(from.distanceTo(to)).isEqualTo(6);

        assertThat(territoryManager.distanceInTerritories(from, cardinalTerritories.get(2))).isEqualTo(1);
    }

    @Test
    void ensureFindSurroundings() {
        territoryManager.remove(cardinalTerritories.get(0).getTerritoryCoordinate());
        assertThat(territoryManager.getInRangeOf(cardinalTerritories.get(1)).size()).isEqualTo(4);

        territoryManager.add(cardinalTerritories.get(0).getTerritoryCoordinate(), cardinalTerritories.get(0));
        assertThat(territoryManager.getInRangeOf(cardinalTerritories.get(0)).size()).isEqualTo(5);
    }

    @Test
    void ensureAllegianceShift() {
        for (Territory t : territoryManager.getTerritories().values())
            System.out.println(String.format("%8s", Integer.toBinaryString(t.getAllegiance() & 0xFF)).replace(' ', '0') + " | " + t.getKingdom().getName());
    }
}