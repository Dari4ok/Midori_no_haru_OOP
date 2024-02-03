package general;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class film {
    private int id;
    private String name;
    private String director;

    @Override
    public String toString() {
        return id + ". " + name + "director: " + director;

    }
}
