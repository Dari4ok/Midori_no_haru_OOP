package Manage;

import Manage.Interfaces.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Model {
    private int id;
    private String username;
    private String password;
    private int role = Role.User.getId();

    public User(int id, String username, String password, Role role_id) {
    }

}
