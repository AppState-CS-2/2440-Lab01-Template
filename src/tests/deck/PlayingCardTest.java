package deck;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the PlayingCard class.
 * 
 * @author Willow Sapphire
 * @version 04/06/2023
 */
public class PlayingCardTest
{
    /**
     * Initial rank for the card created in beforeEach.
     */
    private static final Rank INIT_RANK = Rank.TWO;

    /**
     * Initial suit for the card created in beforeEach.
     */
    private static final Suit INIT_SUIT = Suit.CLUBS;


    private PlayingCard card;

    /**
     * Initializes the card field for testing.
     */
    @BeforeEach
    public void beforeEach()
    {
        card = new PlayingCard(INIT_RANK, INIT_SUIT);
    }

    /**
     * Tests the PlayingCard constructor.
     */
    @Test
    public void testPlayingCard()
    {
        for (Rank r: Rank.values())
        {
            for (Suit s : Suit.values())
            {
                card = new PlayingCard(r, s);
                assertEquals(r, card.getRank(),
                    "Constructor resulted in incorrect rank.");
                assertEquals(s, card.getSuit(),
                    "Constructor resulted in incorrect suit.");
            }
        }
    }

    /**
     * Tests the PlayingCard setRank method.
     */
    @Test
    public void testSetRank()
    {
        for (Rank r: Rank.values())
        {
            card.setRank(r);
            assertEquals(r, card.getRank(),
                "setRank resulted in incorrect rank");
        }
    }

    /**
     * Tests the PlayingCard setSuit method.
     */
    @Test
    public void testSetSuit()
    {
        for (Suit s : Suit.values())
            {
                card.setSuit(s);
                assertEquals(s, card.getSuit(),
                    "setSuit resulted in incorrect suit");
            }
    }

    /**
     * Tests the PlayingCard toString method.
     */
    @Test
    public void testToString()
    {
        for (Rank r: Rank.values())
        {
            for (Suit s : Suit.values())
            {
                card = new PlayingCard(r, s);
                assertEquals(String.format("%s %s",
                    r.toString(), s.toString()), card.toString(),
                    "toString method has invalid output.");
            }
        }
    }
}
