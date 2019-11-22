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

        assertThat(territoryManager.findAdjacentOf(origin, Direction.NORTH)).isEqualTo(directionalTerritories.get(1));
        assertThat(territoryManager.findAdjacentOf(origin, Direction.SOUTH)).isEqualTo(directionalTerritories.get(2));
        assertThat(territoryManager.findAdjacentOf(origin, Direction.EAST)).isEqualTo(directionalTerritories.get(3));
        assertThat(territoryManager.findAdjacentOf(origin, Direction.WEST)).isEqualTo(directionalTerritories.get(4));
    }

}
