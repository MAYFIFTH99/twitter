package mayfifth99.twitter.user.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import mayfifth99.twitter.user.application.interfaces.UserRepository;
import mayfifth99.twitter.user.domain.User;

public class FakeUserRepository implements UserRepository {

    private final Map<Long, User> store = new HashMap<>();
    @Override
    public User save(User user) {
        if(user.getId() != null){
            store.put(user.getId(), user);
            return user;
        }
        Long id = store.size() + 1L;
        User newUser = new User(id, user.getUserInfo());
        store.put(id, newUser);
        return newUser;
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(store.get(userId));
    }
}
