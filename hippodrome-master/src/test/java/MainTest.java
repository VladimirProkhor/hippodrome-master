import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;



class MainTest {
    @DisplayName("timeout")
    @Disabled
    @Test
    @Timeout(value = 22)
    void timeout() {
        try {
            Main.main(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}