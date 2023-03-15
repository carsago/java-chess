package techcourse.fp.chess.domain;

import java.util.ArrayList;
import java.util.List;
import techcourse.fp.movingStrategy.DownStrategy;
import techcourse.fp.movingStrategy.LeftDownStrategy;
import techcourse.fp.movingStrategy.LeftUpStrategy;
import techcourse.fp.movingStrategy.MovingStrategies;
import techcourse.fp.movingStrategy.MovingStrategy;
import techcourse.fp.movingStrategy.RightDownStrategy;
import techcourse.fp.movingStrategy.RightUpStrategy;
import techcourse.fp.movingStrategy.UpStrategy;

public class Pawn extends Piece {

    public static final int INITIAL_WHITE_RANK = 2;
    public static final int INITIAL_BLACK_RANK = 7;

    private Pawn(final Color color, final MovingStrategies strategies) {
        super(color, strategies);
    }

    public static Pawn createByColor(final Color color) {
        if (color == Color.BLACK) {
            return new Pawn(color, initBlackPawnStrategies());
        }

        if (color == Color.WHITE) {
            return new Pawn(color, initWhitePawnStrategies());
        }

        throw new AssertionError();
    }

    private static MovingStrategies initBlackPawnStrategies() {
        final List<MovingStrategy> movingStrategies = List.of(new DownStrategy(), new LeftDownStrategy(),
                new RightDownStrategy());
        return new MovingStrategies(movingStrategies);
    }

    private static MovingStrategies initWhitePawnStrategies() {
        final List<MovingStrategy> movingStrategies = List.of(new UpStrategy(), new LeftUpStrategy(),
                new RightUpStrategy());
        return new MovingStrategies(movingStrategies);
    }


    @Override
    public List<Position> findPath(final Position source, final Position target, final Color targetColor) {
        final MovingStrategy movingStrategy = strategies.findStrategy(source, target);

        if (isAttack(source, target, targetColor)) {
            return List.of(movingStrategy.move(source));
        }

        if (isOneStepMove(source, target, targetColor)) {
            return List.of(movingStrategy.move(source));
        }

        if (isTwoStepMove(source, target, targetColor)) {
            return getTwoStepPath(source, movingStrategy);
        }

        throw new IllegalArgumentException("폰이 해당 지점으로 이동할 수 없습니다.");
    }

    private boolean isAttack(final Position source, final Position target, final Color targetColor) {
        return source.isOnDiagonal(target) && targetColor.isOpponent(this.color);
    }

    private boolean isOneStepMove(final Position source, final Position target, final Color targetColor) {
        return source.isUpDown(target) && targetColor.isEmpty();
    }

    private boolean isTwoStepMove(final Position source, final Position target, final Color targetColor) {
        return isMovableTwoStep(source) && source.isUpDownTwo(target) && targetColor.isEmpty();
    }

    private List<Position> getTwoStepPath(final Position source, final MovingStrategy movingStrategy) {
        List<Position> path = new ArrayList<>();
        Position currentPosition = source;

        for (int i = 0; i < 2; i++) {
            currentPosition = movingStrategy.move(currentPosition);
            path.add(currentPosition);
        }

        return path;
    }

    private boolean isMovableTwoStep(final Position source) {
        if (color.isWhite()) {
            return source.getRankOrder() == INITIAL_WHITE_RANK;
        }

        if (color.isBlack()) {
            return source.getRankOrder() == INITIAL_BLACK_RANK;
        }

        return false;
    }
}
