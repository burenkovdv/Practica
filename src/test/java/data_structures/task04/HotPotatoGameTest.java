package data_structures.task04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("HotPotatoGame Tests")
class HotPotatoGameTest {

    @Test
    @DisplayName("Конструктор: null или пустой список участников")
    void constructorShouldThrowForNullOrEmptyList() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new HotPotatoGame(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> new HotPotatoGame(Collections.emptyList()))
        );
    }

    @Test
    @DisplayName("play: count <= 0")
    void playShouldThrowForNonPositiveCount() {
        List<String> parts = Arrays.asList("A", "B", "C");
        HotPotatoGame game = new HotPotatoGame(parts);
        assertThrows(IllegalArgumentException.class, () -> game.play(0));
        assertThrows(IllegalArgumentException.class, () -> game.play(-3));
    }

    @Test
    @DisplayName("Один участник всегда побеждает сразу")
    void singleParticipantAlwaysWins() {
        List<String> parts = Collections.singletonList("Solo");
        HotPotatoGame game = new HotPotatoGame(parts);
        assertEquals("Solo", game.play(5));
    }

    @Test
    @DisplayName("Классический пример игры")
    void classicExample() {
        List<String> parts = Arrays.asList("A", "B", "C", "D", "E");
        HotPotatoGame game = new HotPotatoGame(parts);
        // Если count=3, выбывают: C, F, ... последний — B
        assertEquals("B", game.play(3));
    }

    @Test
    @DisplayName("play с разным шагом")
    void differentCounts() {
        List<String> parts = Arrays.asList("1", "2", "3", "4");
        HotPotatoGame game1 = new HotPotatoGame(parts);
        assertEquals("1", game1.play(1), "count=1: каждый шаг выбывает сразу первый");

        HotPotatoGame game2 = new HotPotatoGame(parts);
        assertEquals("1", game2.play(2), "count=2: вариант классики");

        HotPotatoGame game3 = new HotPotatoGame(parts);
        assertEquals("4", game3.play(4), "count=4");
    }
}
