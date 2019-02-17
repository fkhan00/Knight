public class KnightBoard{
  private class Outgoing{
    public int posR;
    public int posC;
    public int possib;
    public Outgoing(int x, int y, int value){
      value = possib;
      posR= x;
      posC = y;}
    public void edit(int value){
      possib = value;}
    public void update(){
      possib --;}}

  public Outgoing[][] options;
  public int[][] board;
  public static final int incR[] = { 2, 1, -1, -2, -2, -1,  1,  2 , 2 };
  public static final int incC[] = { 1, 2,  2,  1, -1, -2, -2, -1, 1 };

  public void fillBoard(){
    for(int i = 0; i < options.length; i++){
      for(int j = 0; j < options[0].length; j++){
        options[i][j] = new Outgoing(i, j, 8);
        if(i == 0 ||j == 0 || i == options.length - 1 || j == options[0].length - 1){
          options[i][j] = new Outgoing(i, j, 4);}
        if(i == 1 ||j == 1 || i == options.length - 2 || j == options[0].length - 2){
          options[i][j] = new Outgoing(i, j, 6);}
        if(i * j == 0 && (i + j == options.length - 1 || i + j == 0 || i + j == options[0].length)){
          options[i][j] = new Outgoing(i, j, 2);}
        if((i == options.length - 2 && j == 0) || (i == 1 && j == 0) || (i == 0 && j == 1) ||
         (i == 0 && j == options[0].length - 2) || (i == options.length - 1 && j == 1) || (i == options.length - 1 && j == 1)
        || (i == options.length - 2 && j == options[0].length - 1) || (i == options.length - 1 && j == options[0].length - 2)){
          options[i][j] = new Outgoing(i, j, 3);}
        }}
    options[1][1].edit(4);
    options[1][options[0].length - 2].edit(4);
    options[options.length - 2][1].edit(4);
    options[options.length - 2][options[0].length - 2].edit(4);}

  public KnightBoard(int startingRow, int startingCol){
    options = new Outgoing[startingRow][startingCol];
    fillBoard();
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
    for(int i = 0; i < incR.length; i++){
      if(r + incR[i] > 0 && c + incC[i] > 0 && r + incR[i] < board.length && c + incC[i] < board.length){
        options[r + incR[i]][c + incC[i]].update();}}
    if(filled(board)){
      counter ++;
      return;}
    for(int i = 0; i < incR.length; i++){
      if(valid(r + incR[i], c + incC[i])){
        countSolutionsH(r + incR[i], c + incC[i]);}}
    board[r][c] = 0;
  }
}
