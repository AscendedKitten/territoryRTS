package at.cath;

import at.cath.utility.Direction;
import at.cath.utility.TerritoryManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TerritoryNavigationTest {

    private static final TerritoryManager territoryManager = TerritoryManager.getInstance();
    private static final List<Territory> cardinalTerritories = DomainFixtures.getCardinalTerritories();
    private static final Logger logger = LoggerFactory.getLogger(TerritoryNavigationTest.class);

    @BeforeAll
    static void setup() {
        cardinalTerritories.forEach(territory -> territoryManager.add(territory.getTerritoryCoordinate(), territory));
    }

    @Test
    void ensureAdjacentRetrieval() {
        for (Direction direction : Direction.values()) {
            Territory origin = cardinalTerritories.get(cardinalTerritories.size() - 1);
            Territory neighbour = territoryManager.findAdjacentOf(origin, direction).get();
            logger.debug("Neighbor of {} is {}", origin.getKingdom().getName(), neighbour.getKingdom().getName());

            assertThat(neighbour).isIn(cardinalTerritories);

        }
    }

    @Test
    void ensureFindSurroundings() {
        territoryManager.remove(cardinalTerritories.get(0).getTerritoryCoordinate());
        assertThat(territoryManager.getInRangeOf(cardinalTerritories.get(1)).size()).isEqualTo(8);
        territoryManager.add(cardinalTerritories.get(0).getTerritoryCoordinate(), cardinalTerritories.get(0));
        assertThat(territoryManager.getInRangeOf(cardinalTerritories.get(0)).size()).isEqualTo(9);
    }

    @Test
    void ensureAllegianceShift() {
        for (Territory t : territoryManager.getTerritories().values())
            System.out.println(printByte(t));
    }

    private String printByte(Territory territory) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 8; i++)
            s.append(territory.getAllegianceBit(i) ? 1 : 0);
        return s.toString();
    }

}