package techcourse.fp.chess.domain.movingStrategy;

import java.util.List;
import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.Position;

public abstract class MovingStrategy {
    protected final List<Direction> directions;

    public MovingStrategy(final List<Direction> directions) {
        this.directions = directions;
    }

    protected abstract boolean isReachable(final Direction direction, final int gapOfFileOrder, final int gapOfRankOrder);

    public abstract List<Position> createPath(Position source, Position target);
}
