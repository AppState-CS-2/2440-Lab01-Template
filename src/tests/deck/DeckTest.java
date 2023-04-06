package deck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Deck class.
 * 
 * @author Willow Sapphire
 * @version 04/06/2023
 */
public class DeckTest
{
    /**
     * The number of cards that should be in the deck.
     */
    public static final int NUM_CARDS = 52;

    /**
     * The seed for the random number generator.
     */
    public static final int SEED = 12414543;
    
    /**
     * The deck object used for testing.
     */
    private Deck deck;

    /**
     * Initializes the deck field for testing.
     */
    @BeforeEach
    public void beforeEach()
    {
        deck = new Deck(SEED);
    }

    /**
     * Tests the deck's one-arg constructor.
     */
    @Test
    public void testOneArgConstructor()
    {
        deck = new Deck();
        testInitialCardOrder(deck);
    }

    /**
     * Tests the deck's no-arg constructor.
     */
    @Test
    public void testNoArgConstructor()
    {
        deck = new Deck();
        testInitialCardOrder(deck);
    }

    /**
     * Tests the deck's shuffle method.
     */
    @Test
    public void testShuffle()
    {
        Deck deck2 = new Deck(SEED);
        deck.shuffleDeck();
        deck2.shuffleDeck();
        // Test that shuffling with the same seed are consistent.
        for (int i = 0; i < NUM_CARDS; i++)
        {
            assertEquals(deck2.getCard(i).getRank(),
                deck.getCard(i).getRank(),
                "Suffle method is not consistent when given the same seed\n"
                    + "Did you make sure to set the seed in the one-arg constructor?");
            assertEquals(deck2.getCard(i).getSuit(),
                deck.getCard(i).getSuit(),
                "Suffle method is not consistent when given the same seed\n"
                    + "Did you make sure to set the seed in the one-arg constructor?");
        }
        // tests that shuffles are random
        int numTrials = 1000000;
        int minDiff = Integer.MAX_VALUE;
        int maxDiff = Integer.MIN_VALUE;
        long totalDiff = 0;
        Deck a = new Deck();
        Deck b;
        for (int i = 0; i < numTrials; i++) {
            b = new Deck();
            b.shuffleDeck();
            int diff = numDifPosition(a, b);
            if (diff < minDiff) minDiff = diff;
            if (diff > maxDiff) maxDiff = diff;
            totalDiff += diff;
            a = b;
        }
        int avgDiff = (int) (totalDiff / numTrials);
        int lowerBound = 40;
        assertTrue(avgDiff >= lowerBound);
    }

    /**
     * Helper method to test the initial order of cards.
     * 
     * @param deck the deck to test
     */
    private void testInitialCardOrder(Deck deck)
    {
        int deckIndex = 0;
        PlayingCard card;
        for (Rank rank : Rank.values())
        {
            for (Suit suit : Suit.values())
            {
                card = deck.getCard(deckIndex++);
                assertEquals(card.getRank(), rank,
                    String.format("Rank of card at index %d incorrect\n"
                        + "Could be an issue in a constructor or getRank.",
                        deckIndex));
                assertEquals(card.getSuit(), suit,
                    String.format("Suit of card at index %d incorrect\n"
                        + "Could be an issue in a constructor or getSuit.",
                        deckIndex));
            }
        }
    }

    /**
     * Helper method that calculates how many cards
     * are in different positions between two decks.
     * 
     * @param a the first deck
     * @param b the second deck
     * @return the number of cards in different positions between the decks.
     */
    private int numDifPosition(Deck a, Deck b)
    {
        int diffPosition = 0;
        for (int i = 0; i < 52; i++)
        {
            PlayingCard ai = a.getCard(i);
            PlayingCard bi = b.getCard(i);
            if (ai.getRank() != bi.getRank() || ai.getSuit() != bi.getSuit())
            {
                diffPosition++;
            }
        }
        return diffPosition;
    }
}
