public class KnightBoard{
  public int[][] board;
  public KnightBoard(int startingRow, int startingCol){
    board = new int[startingRow][startingCol];}
  public String toString(){
    String output = "";
    if(board.length * board.length < 10){
      for(int i = 0; i < board.length; i++){
        for(int j = 0; j < board.length; j++){
          output += board[i][j];
        }
        output += "\n";}
      return output;}
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board.length; j++){
        if(board[i][j] < 10){
          output += " " + board[i][j];}
        else{
          output += board[i][j];}
      }
    output += "\n";}
    return output;}
  
