package maps;

public class ChessboardState {
    // The state of a game of chessis determined by what piece is present on each square, as
    //illustrated in Figure 13.2 on the following page. Each square may be empty, or have
    //one of six classes of pieces; each piece may be black or white. Thus|Tg(l + 6x2)1 =4
    //bitssuffice persquare, which meansthat a total of 64 X 4 = 256 bits can represent the
    //state of the chessboard. (The actual state of the game isslightly more complex, asit
    //needs to capture which side is to move, castling rights, en passant, etc., but we will
    //use the simpler model for this question.)
    //Chess playing computers need to store sets of states, e.g., to determine if a partic¬
    //ular state has been evaluated before, or is known to be a winning state. To reduce
    //storage, it is natural to apply a hash function to the 256 bits of state, and ignore col¬
    //lisions. The hash code can be computed by a conventional hash function for strings.
    //However, since the computer repeatedly explores nearby states, it is advantageous
    //to consider hash functions that can be efficiently computed based on incremental
    //changes to the board.
    //Design a hash function for chess game states. Your function should take a state and
    //the hash code for that state, and a move, and efficiently compute the hash code for
    //the updated state.


    // Solution:
    //  Straight forward solution would be to create polynomial hash function
    //  SUMi(c[i] * p^i)  i=0..63
    //    where ci = value of the cell with
    //               fourteen states |color| x (|figure| + 1)
    //               + 1 represents empty cell
    //          p  = |color| x (|figure| + 1) = 13, base of the function
    //
    // Trickier and faster solution would be to generate
    //    64 * 13 random codes for each state of all cells
    //    represent state as XOR of all cell states
    //
    //    when moving a figure XOR remove figure from the STATE
    //                         XOR add empty state to the cell
    //                         XOR remove target cell state
    //                         XOR add figure to target cell
}
