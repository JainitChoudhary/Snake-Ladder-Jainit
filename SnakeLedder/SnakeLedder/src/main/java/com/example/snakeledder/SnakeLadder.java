package com.example.snakeledder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {

     public static final int tileSize=40, width=10, height=10;

     public static final int buttonLine = height*tileSize+50, infoLine= buttonLine-30;

     private static Dice dice= new Dice();
     private Player playerOne, playerTwo;

     private boolean gameStarted= false, playerOneTurn =false, playerTwoTurn =false;

   //Root
    private Pane createContent(){
        Pane root= new Pane();
        root.setPrefSize(width*tileSize, height*tileSize+100);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                Tile tile=new Tile(tileSize);

                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
            
        }
        //Image
        Image img =new Image("C:\\SnakeLedder\\SnakeLedder\\src\\main\\snake and ledder.jpg");
        ImageView board= new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);


        //buttons

        Button playerOneButton= new Button(" Player One");
        Button playerTwoButton= new Button(" Player Two");
        Button startButton = new Button("Start");

        playerOneButton.setTranslateY((buttonLine));
        playerOneButton.setTranslateX(20);
        playerOneButton.setDisable(true);
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setDisable(true);
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(170);


        //Info Display

        Label playerOnelabel= new Label("");
        Label playerTwolabel= new Label("");
        Label diceLabel= new Label("Start The Game");

        playerOnelabel.setTranslateY(infoLine);
        playerOnelabel.setTranslateX(20);
        playerTwolabel.setTranslateY(infoLine);
        playerTwolabel.setTranslateX(300);
        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(160);

        playerOne =new Player(tileSize, Color.BLACK, "Jainit" );
        playerTwo =new Player(tileSize-5,Color.WHITE, "Akhilesh");



     // Player Action

        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerOneTurn){
                        int diceValue= dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value :"+diceValue);
                        playerOne.movePlayer(diceValue);

                        //winning condition
                        if(playerOne.isWinner()){
                            diceLabel.setText("Winner is "+ playerOne.getName());
                            playerOneTurn= false;
                            playerOneButton.setDisable(true);
                            playerOnelabel.setText("");


                            playerTwoTurn = true;
                            playerTwoButton.setDisable(true);
                            playerTwolabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart ");
                            gameStarted= false;

                        }
                        else{
                            playerOneTurn= false;
                            playerOneButton.setDisable(true);
                            playerOnelabel.setText("");


                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false);
                            playerTwolabel.setText("Your Turn "+ playerTwo.getName());
                        }






                    }
                }

            }
        });
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn){
                        int diceValue= dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value :"+diceValue);
                        playerTwo.movePlayer(diceValue);

                        //winning condition
                        if(playerTwo.isWinner()){
                            diceLabel.setText("Winner is "+ playerTwo.getName());
                            playerOneTurn= false;
                            playerOneButton.setDisable(true);
                            playerOnelabel.setText("");


                            playerTwoTurn = true;
                            playerTwoButton.setDisable(true);
                            playerTwolabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart ");

                        }
                        else {
                            playerOneTurn= true;
                            playerOneButton.setDisable(false);
                            playerOnelabel.setText("Your Turn "+ playerOne.getName());


                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwolabel.setText("");
                        }






                    }
                }

            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted= true;

                diceLabel.setText("Game Started");
                startButton.setDisable(true);
                playerOneTurn= true;
                playerOnelabel.setText("Your Turn "+playerOne.getName());
                playerOneButton.setDisable(false);
                playerOne.startingPosition();

                playerTwoTurn=false;
                playerTwolabel.setText("");
                playerTwoButton.setDisable(true);
                playerTwo.startingPosition();


            }
        });



        root.getChildren().addAll(board,playerOneButton,playerTwoButton,startButton,
                playerOnelabel,playerTwolabel,diceLabel,
                playerOne.getCoin(), playerTwo.getCoin()
                );




        return root;
    }





    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createContent());
        stage.setTitle(" Snake & Ladder !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}