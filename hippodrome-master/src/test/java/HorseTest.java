import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;


class HorseTest {
    @DisplayName("getIllegalArgumentException")
    @Test
    void getIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @DisplayName("getIsNullNameHorse")
    @Test
    void getIsNullNameHorse() {
        try {
            new Horse(null, 1, 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @DisplayName(" getIsBlankNameHorse")
    @ParameterizedTest
    @ValueSource(strings = {" ", "/t", "/t/t"
    })
    void getIsBlankNameHorse(String name) {
        try {
            new Horse(name, 1, 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @DisplayName("getSpeedIllegalArgumentException")
    @Test
    void getSpeedIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1, 1));
    }

    @DisplayName("getSpeedCountNegative")
    @ParameterizedTest
    @ValueSource(doubles = {-1})
    void getSpeedCountNegative(double speed) {
        try {
            new Horse("name", speed, 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    void getDistanceIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1, -1));
    }

    @DisplayName("getDistanceNegative")
    @ParameterizedTest
    @ValueSource(doubles = -1)
    void getDistanceNegative(double distance) {
        try {
            new Horse("name", 1, distance);
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

    private Horse horseThreePar;
    private Horse horseTwoPar;
    private final String name = "Pegas";
    private final double speed = 4.0;
    private final double distance = 10.0;

    @BeforeEach
    void init() {
        horseThreePar = new Horse(name, speed, distance);
        horseTwoPar = new Horse(name, speed);
    }

    @DisplayName("getName")
    @Test
    void getName() {
        assertEquals(name, horseThreePar.getName());
    }

    @DisplayName("getSpeed")
    @Test
    void getSpeed() {
        assertEquals(speed, horseThreePar.getSpeed());

    }

    @DisplayName("getDistance")
    @Test
    void getDistance() {
        assertEquals(distance, horseThreePar.getDistance());
        assertEquals(0, horseTwoPar.getDistance());
    }

    @DisplayName("moveStaticMethod")
    @Test
    void moveStaticMethod() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseThreePar.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @DisplayName("moveHorse")
    @ParameterizedTest
    @ValueSource(doubles = {1.0, 5.0, 4.4})
    void move(double result) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(result);
            horseThreePar.move();
            assertEquals(distance + speed * result, horseThreePar.getDistance());
        }
    }
}