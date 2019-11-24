package at.cath;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class Kingdom {

    @Getter
    private String name;

    public static Kingdom defaultKingdom() {
        return new Kingdom("Wilderness");
    }

}
