package techcourse.fp.movingStrategy;

import static org.assertj.core.api.Assertions.assertThat;
import static techcourse.fp.chess.domain.PositionFixtures.A1;
import static techcourse.fp.chess.domain.PositionFixtures.A3;
import static techcourse.fp.chess.domain.PositionFixtures.B1;
import static techcourse.fp.chess.domain.PositionFixtures.B2;
import static techcourse.fp.chess.domain.PositionFixtures.B8;
import static techcourse.fp.chess.domain.PositionFixtures.C1;
import static techcourse.fp.chess.domain.PositionFixtures.C2;
import static techcourse.fp.chess.domain.PositionFixtures.D4;
import static techcourse.fp.chess.domain.PositionFixtures.H8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LeftDownStrategyTest {
    private final MovingStrategy leftDown = new LeftDownStrategy();

    @Nested
    public class MovableTest {

        @DisplayName("대각선 좌하향으로 이동하면 true를 반환한다.")
        @Test
        void success_movable() {
            assertThat(leftDown.movable(B2, A1)).isTrue();
        }

        @DisplayName("대각선 좌상향으로 이동하면 false를 반환한다.")
        @Test
        void fail_by_left_down() {
            assertThat(leftDown.movable(B2, A3)).isFalse();
        }

        @DisplayName("대각선 우상향으로 이동하면 false를 반환한다.")
        @Test
        void fail_by_right_up() {
            assertThat(leftDown.movable(B2, D4)).isFalse();
        }

        @DisplayName("대각선 우하향으로 이동하면 false를 반환한다.")
        @Test
        void fail_by_right_down() {
            assertThat(leftDown.movable(B2, C1)).isFalse();
        }

        @DisplayName("File이 같으면 false를 반환한다.")
        @Test
        void fail_by_same_file() {
            assertThat(leftDown.movable(B8, B1)).isFalse();
        }

        @DisplayName("Rank가 같으면 false를 반환한다.")
        @Test
        void fail_by_same_rank() {
            assertThat(leftDown.movable(B8, H8)).isFalse();
        }
    }

    @Nested
    public class MoveTest {

        @DisplayName("move시 좌하향으로 한 칸 움직인다.")
        @Test
        void move() {
            assertThat(leftDown.move(C2)).isEqualTo(B1);
        }
    }
}
