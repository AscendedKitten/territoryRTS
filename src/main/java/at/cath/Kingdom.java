package at.cath;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class Kingdom {

    @Getter
    private String name;

    @Getter
    private Alliance alliance;

    public static Kingdom defaultKingdom() {
        return new Kingdom("Wilderness", null);
    }

}
