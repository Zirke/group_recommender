package testRoot;

import destination.Destination;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestinationTest {

    @Test
    void fillActivitiesTest() {
        Destination izmir = new Destination("Izmir");
        izmir.fillActivities();

        Destination kualalumpur = new Destination("Kuala Lumpur");
        kualalumpur.fillActivities();

        Destination istanbul = new Destination("Istanbul");
        istanbul.fillActivities();

        assertEquals(9, izmir.getActivities().size());
        assertEquals("Pirlanta Beach", izmir.getActivities().get(0).getName());
        assertEquals("Beach", izmir.getActivities().get(0).getType());
        assertEquals("Kemeralti Market", izmir.getActivities().get(izmir.getActivities().size() - 1).getName());
        assertTrue(izmir.getActivities().get(0).getTypeSpecific() instanceof Integer);
        assertEquals(23,izmir.getActivities().get(0).getTypeSpecific());

        assertEquals(6, kualalumpur.getActivities().size());
        assertEquals("Islamic Arts Museum", kualalumpur.getActivities().get(0).getName());
        assertEquals("Museum", kualalumpur.getActivities().get(0).getType());
        assertEquals("Kuala Lumpur Tower", kualalumpur.getActivities().get(kualalumpur.getActivities().size() - 1).getName());

        assertEquals(6, istanbul.getActivities().size());
        assertEquals("Hagia Sophia", istanbul.getActivities().get(0).getName());
        assertEquals("Museum", istanbul.getActivities().get(0).getType());
        assertEquals("Sultan Ahmed Mosque", istanbul.getActivities().get(istanbul.getActivities().size() - 1).getName());
    }

}