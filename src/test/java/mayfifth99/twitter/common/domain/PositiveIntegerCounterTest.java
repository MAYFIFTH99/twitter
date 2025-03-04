package mayfifth99.twitter.common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PositiveIntegerCounterTest {

    @Test
    void givenCreated_whenIncrease_thenCountIsOne() throws Exception {
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        //when
        counter.increase();

        //then
        assertEquals(counter.getCount(), 1);
    }

    @Test
    void givenCreatedAndIncreased_whenDecrease_thenCountIsZero() throws Exception {
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        //when
        counter.increase();
        counter.decrease();

        //then
        assertEquals(counter.getCount(), 0);
    }

    @Test
    void givenCreatedZero_whenDecrease_thenCountIsZero() throws Exception {
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        //when
        counter.decrease();

        //then
        assertEquals(counter.getCount(), 0);
    }
}