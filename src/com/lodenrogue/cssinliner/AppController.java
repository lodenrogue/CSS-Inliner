package com.lodenrogue.cssinliner;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

public class AppController implements Initializable {
	@FXML
	private Label cssFileLbl;
	@FXML
	private Button chooseCssBtn;
	@FXML
	private Label htmlFileLbl;
	@FXML
	private Button chooseHtmlBtn;
	@FXML
	private Button inlineBtn;
	@FXML
	private TextArea outputArea;

	private File cssFile;
	private File htmlFile;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() -> cssFileLbl.setText(""));
		Platform.runLater(() -> htmlFileLbl.setText(""));
	}

	public void onButtonPressed(ActionEvent e) {
		if (e.getSource().equals(chooseCssBtn)) {
			cssFile = chooseCss();
			if (cssFile != null) {
				cssFileLbl.setText(cssFile.getName());
			}
		}
		else if (e.getSource().equals(chooseHtmlBtn)) {
			htmlFile = chooseHtml();
			if (htmlFile != null) {
				htmlFileLbl.setText(htmlFile.getName());
			}
		}
		else if (e.getSource().equals(inlineBtn)) {
			if (cssFile != null && htmlFile != null) {
				try {
					String output = CssInliner.inlineCss(cssFile, htmlFile);
					Platform.runLater(() -> outputArea.setText(output));
				}
				catch (IOException e1) {
					Platform.runLater(() -> outputArea.setText("Something went wrong. Check that the CSS and HTML files are valid"));
				}
			}
			else {
				Platform.runLater(() -> outputArea.setText("Missing file"));
			}
		}
	}

	private File chooseCss() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose a CSS file");
		return fileChooser.showOpenDialog(App.getStage());
	}

	private File chooseHtml() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose a HTML file");
		return fileChooser.showOpenDialog(App.getStage());
	}

}
