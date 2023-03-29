package techcourse.fp.chess.domain.piece.pawn;

import java.util.Collections;
import java.util.List;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.movingStrategy.MovingStrategy;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.Piece;
import techcourse.fp.chess.domain.piece.PieceType;

public abstract class Pawn extends Piece {

    private final MovingStrategy movingStrategy;

    public Pawn(final Color color, final PieceType pieceType,
                final MovingStrategy movingStrategy) {
        super(color, pieceType);
        this.movingStrategy = movingStrategy;
    }

    @Override
    public List<Position> findPath(final Position source, final Position target, final Piece targetPiece) {
        if (isAttack(source, target, targetPiece) || isOneStepMove(source, target, targetPiece)) {
            return Collections.emptyList();
        }

        if (isTwoStepMove(source, target, targetPiece)) {
            return movingStrategy.createPath(source, target);
        }

        throw new IllegalArgumentException("폰이 해당 지점으로 이동할 수 없습니다.");
    }

    private boolean isAttack(final Position source, final Position target, final Piece targetPiece) {
        return source.isOnDiagonal(target) && isOpponent(targetPiece);
    }

    private boolean isOpponent(Piece otherPiece) {
        return color.isOpponent(otherPiece.getColor());
    }

    private boolean isOneStepMove(final Position source, final Position target, final Piece targetPiece) {
        return source.isUpDown(target) && targetPiece.isEmpty();
    }

    private boolean isTwoStepMove(final Position source, final Position target, final Piece targetPiece) {
        return isStartPosition(source) && isTwoDistance(source, target, targetPiece);
    }

    private boolean isTwoDistance(final Position source, final Position target, final Piece targetPiece) {
        return isStartPosition(source) && source.isUpDownTwo(target) && targetPiece.isEmpty();
    }

    protected abstract boolean isStartPosition(final Position source);

    @Override
    public boolean isKing() {
        return false;
    }
}
