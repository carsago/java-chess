package techcourse.fp.chess;

import java.util.Scanner;
import techcourse.fp.chess.controller.ChessController;
import techcourse.fp.chess.dao.MysqlChessGameDao;
import techcourse.fp.chess.dao.MysqlPieceDao;
import techcourse.fp.chess.service.ChessGameService;
import techcourse.fp.chess.view.InputView;
import techcourse.fp.chess.view.OutputView;


//TODO: DTO 및 Controller 개선
public final class MainApp {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final ChessController chessController = new ChessController(new InputView(scanner), new OutputView(),
                    new ChessGameService(new MysqlChessGameDao(), new MysqlPieceDao()));
            chessController.run();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
