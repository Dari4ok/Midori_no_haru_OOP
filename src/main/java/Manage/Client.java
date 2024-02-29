package Manage;

import Manage.Interfaces.Model;
import Manage.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client implements Model {
    private int id;
    private String username;

    private String password;
    private int role = Role.User.getId();

    @Override
    public String toString () {
        return "{\n\tID: " + id +
                "\n\tUsername: " + username +
                "\n\tPassword: " + password +
                "\n}";
    }
}
