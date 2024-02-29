package Manage;

import Manage.Interfaces.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Admin implements Model {
    private int id;
    private String username;
    private String password;
    private int role = Role.Admin.getId();
}
