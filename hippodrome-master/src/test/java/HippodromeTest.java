import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

@DisplayName("getNullIllegalArgumentException")
    @Test
    void getNullIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }
@DisplayName("isNullCollectionHorse")
    @Test
    void isNullCollectionHorse() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }
@DisplayName("getIsEmptyIllegalArgumentException")
    @Test
    void getIsEmptyIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));
    }
@DisplayName("isEmptyCollectionHorse")
    @Test
    void isEmptyCollectionHorse() {
        try {
            new Hippodrome(Collections.emptyList());
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }
    @DisplayName("getHorse")
    @Test
    void getHorse(){
        List<Horse>horseList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horseList.add(new Horse(""+i,i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(horseList,hippodrome.getHorses());
    }
    @DisplayName("moveHippodrome")
    @Test
    void move(){
        List<Horse>horseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse mock =  Mockito.mock(Horse.class);
            horseList.add(mock);
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();
        for (Horse horse: horseList) {
            verify(horse).move();
        }
    }
    @DisplayName("getWinner")
    @Test
    void getWinner(){
        Horse horse = new Horse("Буцефал", 2.4,3);
        Horse horse1 = new Horse("Туз Пик", 2.5,4.7777);
        Horse horse2 = new Horse("Зефир", 2.6,5.5);
        Horse horse3 = new Horse("Пожар", 2.7,1.22);
        Horse horse4 = new Horse("Лобстер", 2.8,3.2);
        Hippodrome hippodrome = new Hippodrome(List.of(horse,horse1,horse2,horse3,horse4));
        assertSame(horse2,hippodrome.getWinner());
    }
}
