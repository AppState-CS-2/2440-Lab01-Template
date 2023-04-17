package deck;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import util.ClassInspections;

/**
 * Unit tests for the Suit enum.
 * 
 * @author Willow Sapphire
 * @version 04/06/2023
 */

public class SuitTest
{
    /**
     * Expected values in the Suit enum.
     */
    private static final String[] EXPECTED_STRINGS = {
        "CLUBS", "DIAMONDS", "HEARTS", "SPADES"
    };

    /**
     * Checks that the enum is public.
     */
    @Test
    public void testEnumIsPublic()
    {
        ClassInspections.checkClassModifier("deck.Suit", true, false, false);
    }

    /**
     * Tests the enum values and order.
     */
    @Test
    public void testSuits()
    {
        Suit[] suits = Suit.values();
        for (int i = 0; i < EXPECTED_STRINGS.length; i++)
        {
            assertEquals(EXPECTED_STRINGS[i], suits[i].toString());
        }
    }
}