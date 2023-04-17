package deck;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import util.ClassInspections;

/**
 * Unit tests for the Rank enum.
 * 
 * @author Willow Sapphire
 * @version 04/06/2023
 */
public class RankTest
{
    /**
     * Expected values in the Rank enum.
     */
    private static final String[] EXPECTED_STRINGS = {
        "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN",
        "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING", "ACE"
    };

    /**
     * Checks that the enum is public.
     */
    @Test
    public void testEnumIsPublic()
    {
        ClassInspections.checkClassModifier("deck.Rank", true, false, false);
    }

    /**
     * Tests the enum values and order.
     */
    @Test
    public void testRanks()
    {
        Rank[] ranks = Rank.values();
        for (int i = 0; i < EXPECTED_STRINGS.length; i++)
        {
            assertEquals(EXPECTED_STRINGS[i], ranks[i].toString());
        }
    }
}