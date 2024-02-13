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
    private String film_name;
    private String director;
    private String genre;
    private String my_list;

    @Override
    public String toString() {
        return id + ". " + "Film: " + film_name + " Director: " + director + "Genre: " + genre + "Add smth: " + my_list;
    }
}
