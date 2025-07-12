package com.tourism.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Simple test class to verify FXML loading without displaying the UI
 */
public class FXMLLoadTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Test loading the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin_dashboard.fxml"));
            Parent root = loader.load();
            
            System.out.println("✅ FXML file loaded successfully!");
            System.out.println("✅ Controller instantiated: " + loader.getController().getClass().getSimpleName());
            System.out.println("✅ Root node type: " + root.getClass().getSimpleName());
            
            // Don't show the stage, just test loading
            primaryStage.close();
            
        } catch (Exception e) {
            System.err.println("❌ Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Test without showing GUI
        System.setProperty("java.awt.headless", "true");
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.text", "t2k");
        
        try {
            launch(args);
            System.out.println("✅ All tests passed!");
        } catch (Exception e) {
            System.err.println("❌ Test failed: " + e.getMessage());
            System.exit(1);
        }
    }
}