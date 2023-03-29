package techcourse.fp.chess.domain.piece;

import java.util.List;
import techcourse.fp.chess.domain.Position;

public final class EmptyPiece extends Piece {

    public EmptyPiece(final Color color, final PieceType pieceType) {
        super(color, pieceType);
    }

    public static EmptyPiece create() {
        return new EmptyPiece(Color.EMPTY, PieceType.EMPTY);
    }

    @Override
    public List<Position> findPath(final Position source, final Position target, final Piece targetPiece) {
        throw new IllegalStateException("시작점에 기물이 존재하지 않습니다.");
    }
}
