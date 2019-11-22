package at.cath;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Territory {

    private Kingdom kingdom;
    private TerritoryCoordinate territoryCoordinate;


}
