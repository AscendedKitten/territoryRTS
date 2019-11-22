package at.cath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TerritoryNavigationTest {

    private final TerritoryManager territoryManager = TerritoryManager.INSTANCE;
    private final List<Territory> directionalTerritories = DomainFixtures.getDirectionalTerritories();

    @BeforeEach
    void setup() {
        directionalTerritories.forEach(territory -> territoryManager.add(territory.getTerritoryCoordinate(), territory));
    }

    @Test
    void ensureAdjacentRetrieval() {
        Territory origin = directionalTerritories.get(0);

        assertThat(territoryManager.findNorthOf(origin)).isEqualTo(directionalTerritories.get(1));
        assertThat(territoryManager.findSouthOf(origin)).isEqualTo(directionalTerritories.get(2));
        assertThat(territoryManager.findEastOf(origin)).isEqualTo(directionalTerritories.get(3));
        assertThat(territoryManager.findWestOf(origin)).isEqualTo(directionalTerritories.get(4));
    }

}
