public class KnightBoard{
  public class Outgoing{
    public int m;
    public int n;
    public int[] possib;
    public Outgoing(int r, int c){
      moves = new int[r][c];}

    public void update(int r, int c){
      for(int i = 0; i < possib.length; i++){
        possib --;}}
    
  }
  public int[][] board;
  public static final int incR[] = { 2, 1, -1, -2, -2, -1,  1,  2 , 2 };
  public static final int incC[] = { 1, 2,  2,  1, -1, -2, -2, -1, 1 };

  public KnightBoard(int startingRow, int startingCol){
    counter = 0;
    board = new int[startingRow][startingCol];}

  public String toString(){
    String output = "";
    if(board.length * board.length < 10){
      for(int i = 0; i < board.length; i++){
        for(int j = 0; j < board[0].length; j++){
          output += board[i][j];
        }
        output += "\n";}
      return output;}
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[0].length; j++){
        if(board[i][j] < 10){
          output += " " + board[i][j];}
        else{
          output += board[i][j];}
      }
    output += "\n";}
    return output;}

  public boolean solve(int startingRow, int startingCol){
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board.length; j++){
        if(board[i][j] != 0){
          throw new IllegalStateException("please give an empty board");
        }
        if(startingRow < 0 || startingCol < 0){
          throw new IllegalArgumentException("please enter non negative parameters");}}}
    return solveH( startingRow, startingCol, 0, 0);}

  public boolean valid(int r, int c){
    return r >= 0 && c >= 0 && r < board.length && c < board.length && board[r][c] == 0;}

  public boolean filled(int[][] ary){
    for(int i = 0; i < ary.length; i++){
      for(int j = 0; j < ary.length; j++){
        if(ary[i][j] == 0){
          return false;}}}
    return true;}

  public int counter;
  public int solutions;
  public boolean solveH( int r, int c, int incR, int incC){
    if(filled(board)){
      return true;}
    if(! valid (r, c)){
      counter ++;
      if(counter == 8){
        board[r - incR][c - incC] = 0;}
      return false;}
    board[r][c] = 1;
     return solveH( r + 1, c + 2, 1, 2) || solveH( r + 2, c + 1, 2, 1) || solveH( r + 2, c - 1, 2, -1) ||
     solveH( r - 1, c + 2, -1, 2) ||
     solveH( r - 2, c + 1, -2, 1) ||  solveH( r - 1, c - 2, -1, -2) || solveH( r - 2, c - 1, -2, -1) ||
      solveH( r  + 1, c - 2,1, -2);}

  public int countSolutions(int r, int c){
    countSolutionsH(r, c);
     return counter;}

  public void countSolutionsH(int r, int c){
    board[r][c] = 1;
    if(filled(board)){
      counter ++;
      return;}
    for(int i = 0; i < incR.length; i++){
      if(valid(r + incR[i], c + incC[i])){
        countSolutionsH(r + incR[i], c + incC[i]);}}
    board[r][c] = 0;
  }
}
