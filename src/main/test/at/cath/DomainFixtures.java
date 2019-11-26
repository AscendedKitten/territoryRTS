package at.cath;

import at.cath.utility.Direction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DomainFixtures {

    private static final Alliance DEFAULT_ALLIANCE = new Alliance();

    public static List<Territory> getCardinalTerritories() {

        return Stream.concat(Arrays.stream(Direction.values()).map(direction -> new Territory(new Kingdom(direction.name(), DEFAULT_ALLIANCE),
                new TerritoryCoordinate(direction.getXShift(), direction.getZShift()))), Stream.of(new Territory(new Kingdom("ORIGIN", DEFAULT_ALLIANCE),
                new TerritoryCoordinate(0, 0)))).collect(Collectors.toList());

    }

}