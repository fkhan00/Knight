import java.util.*;
public class KnightBoard{
  public class Outgoing implements Comparable<Outgoing>{
    public int posR;
    public int posC;
    public int possib;
    public Outgoing(int x, int y, int value){
      possib = value;
      posR= x;
      posC = y;}
    public int getPossib(){
      return possib;}
    public void edit(int value){
      possib = value;}
    public void update(int increment){
      possib += increment;}
    public int compareTo(Outgoing other){
      return getPossib() - other.getPossib();}}
  // Outgoing class works
  public Outgoing[][] options;
  // board is where the knight will be moving through
  public int[][] board;
  //incR and incC are knight's moveset
  public static int incR[] = { 1, 1, 2, 2, -1, -1, -2, -2};
  public static int incC[] = { 2, -2, 1, -1, 2, -2, 1, -1};

  public KnightBoard(int startingRow, int startingCol){
    options = new Outgoing[startingRow][startingCol];
    fillBoard();
    board = new int[startingRow][startingCol];}
  // constructor works
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

  public boolean valid(int r, int c){
    // if within board and knight has not been here
    return r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] == 0;}
  // valid works
  public String toString(){
    String output = "";
    if(board.length -1 * board[0].length < 10){
      for(int i = 0; i < board.length; i++){
        for(int j = 0; j < board[0].length - 1; j++){
            output += board[i][j] + "  ";}
        output += board[i][board[0].length - 1];
        output += "\n";}}
    else{
      for(int i = 0; i < board.length; i++){
        for(int j = 0; j < board[0].length - 1; j++){
            output += " " + board[i][j] + " ";}
        output += " " + board[i][board[0].length];
        output += "\n";}}
    return output;}
    // toString works
  public Outgoing[] arrange(int row, int col){
    ArrayList<Outgoing> data = new ArrayList<Outgoing>();
    // take the coordinates from your moveset
    // and add it to Outgoing[] data
    for(int i = 0; i < incR.length; i++){
      if(valid(row + incR[i], col + incC[i])){
        data.add(options[row + incR[i]][col + incC[i]]);}}
    Collections.sort(data);
    // sort data using compareTo; sorts by least number of outgoing moves to most
    Outgoing[] output = new Outgoing[data.size()];
    for(int i = 0; i < data.size(); i++){
      output[i] = data.get(i);}
    return output;}

    public String display(Outgoing[][] ary){
      String output = "";
      for(int i = 0; i < options.length; i++){
        for(int j = 0; j < options[0].length; j++){
          output += ary[i][j].getPossib() + " ";}
        output += "\n";}
      return output;
      }
  // arrange works
  public String print(Outgoing[] ary){
    String output = "";
    for(int i = 0; i < ary.length; i++){
      output += ary[i].posR + " " + ary[i].posC + " " + ary[i].getPossib() + "\n";}
    return output;}

  public boolean solve(int row, int col){
    // checks if given empty board
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[0].length; j++){
        if(board[i][j] != 0){
          throw new IllegalStateException("please give an empty board");}
        if(row < 0 || col < 0){
          throw new IllegalArgumentException("please enter non negative parameters");}}}
    // calls on helper
    board[row][col] = 1;
    return solveH(row, col, 2, new Outgoing[8]);}
    //possiblites is the problem
  public boolean solveH(int row, int col, int num, Outgoing[] possibilites){
    if(num == board.length * board[0].length){
      return true;}
    possibilites = arrange(row, col);
    for(int i = 0; i < possibilites.length; i++){
      if(valid(possibilites[i].posR, possibilites[i].posC)){
        options[possibilites[i].posR][possibilites[i].posC].update(-1);}}
    for(int i = 0; i < possibilites.length; i++){
        if(valid(possibilites[i].posR, possibilites[i].posC)){
          board[possibilites[i].posR][possibilites[i].posC] = num;
          if(solveH(possibilites[i].posR, possibilites[i].posC, num + 1, possibilites)){
            return true;}
          else{
            board[possibilites[i].posR][possibilites[i].posC] = 0;}}}
    for(int j = 0; j < possibilites.length; j++){
      if(valid(possibilites[j].posR, possibilites[j].posC)){
        options[possibilites[j].posR][possibilites[j].posC].update(1);}}
    return false;}

    public static int counter = 0;
    public int countSolutions(int r, int c){
      for(int i = 0; i < board.length; i++){
        for(int j = 0; j < board[0].length; j++){
          if(board[i][j] != 0){
            throw new IllegalStateException("please give an empty board");}
          if(r < 0 || c < 0){
            throw new IllegalArgumentException("please enter non negative parameters");}}}
      counter = 0;
      countSolutionsH(new Outgoing[8], r, c, 1);
       return counter;}


    public void countSolutionsH(Outgoing[] moveset, int r, int c, int num){
      // mark knight position
      if(num == board.length * board[0].length){
        // if knight tour add to counter
        counter ++;
        board[r][c] = 0;
        for(int i = 0; i < moveset.length; i++){
          if(valid(moveset[i].posR, moveset[i].posC)){
            options[moveset[i].posR][moveset[i].posC].update(1);}}
        return;}
      board[r][c] = num;
      moveset = arrange(r, c);
      for(int i = 0; i < moveset.length; i++){
        if(valid(moveset[i].posR, moveset[i].posC)){
          options[moveset[i].posR][moveset[i].posC].update(-1);}}

      for(int i = 0; i < moveset.length; i++){
        //  iterate through moveset
        // and try going through there
        if(valid(moveset[i].posR, moveset[i].posC)){
          countSolutionsH(moveset, moveset[i].posR, moveset[i].posC, num + 1);}}
      board[r][c] = 0;
        for(int i = 0; i < moveset.length; i++){
          if(valid(moveset[i].posR, moveset[i].posC)){
            options[moveset[i].posR][moveset[i].posC].update(1);
          }}}
}
