package at.cath;

import java.util.List;

public class DomainFixtures {

    private static final Alliance DEFAULT_ALLIANCE = new Alliance();

    public static List<Territory> getDirectionalTerritories() {

        TerritoryCoordinate coordOrigin = new TerritoryCoordinate(0, 0);
        TerritoryCoordinate coordNorth = new TerritoryCoordinate(0, -1);
        TerritoryCoordinate coordSouth = new TerritoryCoordinate(0, 1);
        TerritoryCoordinate coordEast = new TerritoryCoordinate(1, 0);
        TerritoryCoordinate coordWest = new TerritoryCoordinate(-1, 0);

        Territory terrOrigin = new Territory(new Kingdom("origin", DEFAULT_ALLIANCE), coordOrigin);
        Territory terrNorth = new Territory(new Kingdom("north", DEFAULT_ALLIANCE), coordNorth);
        Territory terrSouth = new Territory(new Kingdom("south", DEFAULT_ALLIANCE), coordSouth);
        Territory terrEast = new Territory(new Kingdom("east", DEFAULT_ALLIANCE), coordEast);
        Territory terrWest = new Territory(new Kingdom("west", DEFAULT_ALLIANCE), coordWest);

        return List.of(terrOrigin, terrNorth, terrSouth, terrEast, terrWest);
    }

}