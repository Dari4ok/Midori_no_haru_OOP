package Manage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Role {
    Admin(1),
    User(2);

    private int id;

    public static Role fromID(int id) {
        Role role = null;
        for (Role r:values()) {
            if (r.getId() == id) {
                role = r;
                break;
            }
        }
        return role;
    }
}
