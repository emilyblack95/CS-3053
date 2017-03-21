package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/** 
 * Controller for Assignment 4 - HCI
 * 3/9/17
 * @author Emily Black
 * This program serves to test Fitt's Law
 */
public class Controller implements Initializable
{
	@FXML
	private StackPane stackPane;
	@FXML
	private Label backDrop;
	@FXML
	private Text clicksLeft;
	@FXML
	private Button targetButton;
	@FXML
	private Button startButton;
	@FXML
	private Text percentage;
	@FXML
	private NumberAxis xAxis;
	@FXML
    private NumberAxis yAxis;
	@FXML
	private LineChart<Number, Number> chart;
	private XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
	
	private long start;
	private long end;
	private double[] times = new double[10]; //holds values of time it took to click target each round
	private int[] success = new int[10]; //10 = sucessfully clicked target this round, 0 = target not clicked this round
	private double[] distance = new double[10]; //holds distances to target each round
	private Point2D[] points = new Point2D[11]; //holds points for calculating distances
	private double widthOfTarget;
	private int numOfClicks = 10;
	
	@FXML
	private void startTimer(ActionEvent e)
	{
		start = System.currentTimeMillis();
		points[numOfClicks] = new Point2D(startButton.getLayoutX(),startButton.getLayoutY());
		startButton.setVisible(false);
		stackPane.getChildren().remove(startButton);
	}
	
	@FXML
	private void registerDataForBackground(MouseEvent e)
	{
		numOfClicks--;
		
		points[numOfClicks] = new Point2D(e.getSceneX(),e.getSceneY());
		double x1 = points[numOfClicks+1].getX();
		double y1 = points[numOfClicks+1].getY();
		double x2 = points[numOfClicks].getX();
		double y2 = points[numOfClicks].getY();
		distance[numOfClicks] = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
		
		//Register time
		end = System.currentTimeMillis();
		times[numOfClicks] = ((end-start)/1000.0);
		
		//Register success
		success[numOfClicks] = 0;
		
		clicksLeft.setText("Clicks left: " + numOfClicks);
		
		//Move button
		targetButton.setTranslateX(Math.random() * 150.0);
		targetButton.setTranslateY(Math.random() * 150.0);
		
		start = System.currentTimeMillis();
		
		if(numOfClicks == 0)
		{
			calculateGraph();
		}
	}
	
	@FXML
	private void registerDataForButton(ActionEvent e)
	{
		numOfClicks--;
		
		points[numOfClicks] = new Point2D(targetButton.getLayoutX() + targetButton.getTranslateX(),targetButton.getLayoutY()+targetButton.getTranslateY());
		double x1 = points[numOfClicks+1].getX();
		double y1 = points[numOfClicks+1].getY();
		double x2 = points[numOfClicks].getX();
		double y2 = points[numOfClicks].getY();
		distance[numOfClicks] = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
		
		//Register time
		end = System.currentTimeMillis();
		times[numOfClicks] = ((end-start)/1000.0);
		
		//Register success
		success[numOfClicks] = 10;
		
		clicksLeft.setText("Clicks left: " + numOfClicks);
		
		//Move button
		targetButton.setTranslateX(Math.random() * 150.0);
		targetButton.setTranslateY(Math.random() * 150.0);
		
		start = System.currentTimeMillis();
		
		if(numOfClicks == 0)
		{
			calculateGraph();
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	private void calculateGraph()
	{
		widthOfTarget = targetButton.getWidth();
		for(int i = 0; i < 10; i++)
		{
			series.getData().add(new XYChart.Data<Number, Number>(Math.log((distance[i]/widthOfTarget)+1),times[i]));
		}
		
		//Calculate success %
		int total = 0;
		for(int i = 0; i < 10; i++)
		{
			total += success[i];
		}
		
		targetButton.setVisible(false);
		stackPane.getChildren().remove(targetButton);
		backDrop.setVisible(false);
		stackPane.getChildren().remove(backDrop);
		series.setName("Data - 10 Clicks");
		chart.getData().addAll(series);
		chart.setTitle("Results");
		chart.setCreateSymbols(false);
		chart.setVisible(true);
		
		clicksLeft.setVisible(false);
		stackPane.getChildren().remove(clicksLeft);
		percentage.setVisible(true);
		percentage.setText("Percentage of Successful Clicks: " + total + "%" + '\n' + "Percentage of Failed Clicks: " + (100-total) + "%");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}
