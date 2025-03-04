package mayfifth99.twitter.user.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private final UserInfo userInfo1 = new UserInfo("test1", "test1");

    private User user1;
    private User user2;


    @BeforeEach
    void init() {
        user1 = new User(1L, userInfo1);
        user2 = new User(2L, userInfo1);
    }

    @Test
    void givenTwoUser_whenEqual_thenReturnFalse() throws Exception {
        //when
        boolean value = user1.equals(user2);

        //then
        assertFalse(value);
    }

    @Test
    void givenTwoSameIdUser_whenEqual_thenReturnTrue() throws Exception {
        //given
        User sameUser = new User(1L, userInfo1);

        //when
        boolean isSame = user1.equals(sameUser);

        //then
        assertTrue(isSame);
    }

    @Test
    void givenTwoUser_whenHashCode_thenNotEqual() throws Exception {
        //when
        int hashCode1 = user1.hashCode();
        int hashCode2 = user2.hashCode();

        //then
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    void givenTwoUser_whenHashCode_thenEqual() throws Exception {
        //given
        User sameUser = new User(1L, userInfo1);

        //when
        int hashCode1 = user1.hashCode();
        int sameUserHashCode = sameUser.hashCode();

        //then
        assertEquals(hashCode1, sameUserHashCode);
    }

    @Test
    void givenTwoUser_whenUser1FollowUser2_thenIncreaseUserCount() throws Exception {
        //when
        user1.follow(user2);

        //then
        assertEquals(user1.getFollowingCount(), 1);
        assertEquals(user1.getFollowerCount(), 0);
        assertEquals(user2.getFollowerCount(), 1);
        assertEquals(user2.getFollowingCount(), 0);
    }
    
    @Test
    void givenUser1FollowUser2_whenUser1UnfollowUser2_thenDecreaseUserCount() throws Exception {
        //given
        user1.follow(user2);

        //when
        user1.unfollow(user2);

        //then
        assertEquals(user1.getFollowingCount(), 0);
        assertEquals(user1.getFollowerCount(), 0);
        assertEquals(user2.getFollowerCount(), 0);
        assertEquals(user2.getFollowingCount(), 0);
    }
    
    @Test
    void givenTwoUser_whenUnfollow_thenDecreaseUserCount() throws Exception {
        //when
        user1.unfollow(user2);

        //then
        assertEquals(user1.getFollowingCount(), 0);
        assertEquals(user1.getFollowerCount(), 0);
        assertEquals(user2.getFollowerCount(), 0);
        assertEquals(user2.getFollowingCount(), 0);
    }
}