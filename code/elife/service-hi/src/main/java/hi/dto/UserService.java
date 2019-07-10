package hi.dto;

import hi.entity.User;

public interface UserService {
    public User create(String username, String password);
}
