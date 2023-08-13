package org.example;

import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class App extends Application
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
    }

    @Override
    public void start(Stage stage)  {
        stage.setTitle("CryptoCurrency Prices");

        GridPane grid = createGrid();

        Map<String,Label> cryptoLabels = createCryptoPriceLabels();

        double width = 300;
        double height = 250;

        StackPane root = new StackPane();

        Rectangle background = createBackgroundRectangle(width, height);
        root.getChildren().add(background);
        root.getChildren().add(grid);

        stage.setScene(newScene(root,width,height));
        stage.show();

    }

    private Map<String,Label> createCryptoPriceLabels() {
    }

    private GridPane createGrid() {
    }


    public static class PriceContainer {


        private Lock lockObject = new ReentrantLock();
        private double bitcoinPrice;
        private double ethereumPrice;
        private double litecoinPrice;
        private double bitcoinCashPrice;
        private double ripplesPrice;


        public Lock getLockObject() {
            return lockObject;
        }
        public double getBitcoinPrice() {
            return bitcoinPrice;
        }

        public void setBitcoinPrice(double bitcoinPrice) {
            this.bitcoinPrice = bitcoinPrice;
        }

        public double getEthereumPrice() {
            return ethereumPrice;
        }

        public void setEthereumPrice(double ethereumPrice) {
            this.ethereumPrice = ethereumPrice;
        }

        public double getLitecoinPrice() {
            return litecoinPrice;
        }

        public void setLitecoinPrice(double litecoinPrice) {
            this.litecoinPrice = litecoinPrice;
        }

        public double getBitcoinCashPrice() {
            return bitcoinCashPrice;
        }

        public void setBitcoinCashPrice(double bitcoinCashPrice) {
            this.bitcoinCashPrice = bitcoinCashPrice;
        }

        public double getRipplesPrice() {
            return ripplesPrice;
        }

        public void setRipplesPrice(double ripplesPrice) {
            this.ripplesPrice = ripplesPrice;
        }
    }


    public static class PriceUpdater extends Thread {
        private PriceContainer priceContainer;
        private Random random = new Random();

        public PriceUpdater(PriceContainer priceContainer) {
            this.priceContainer = priceContainer;
        }

        public void run() {
            while(true){

                priceContainer.getLockObject().lock();
              try{
                  try {
                      Thread.sleep(1000);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
                  priceContainer.getLockObject().lock();
                  priceContainer.setBitcoinPrice(random.nextDouble());
                  priceContainer.setEthereumPrice(random.nextDouble());
                  priceContainer.setLitecoinPrice(random.nextDouble());
                  priceContainer.setBitcoinCashPrice(random.nextDouble());
                  priceContainer.setRipplesPrice(random.nextDouble());
                  priceContainer.getLockObject().unlock();
              } finally {
                  priceContainer.getLockObject().unlock();
              }

              try {
                  Thread.sleep(2000);
              } catch (InterruptedException e) {
                  //ToDo
              }
            }
        }
    }
}
