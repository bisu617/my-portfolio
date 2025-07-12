package com.tourism.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.ResourceBundle;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Controller class for the Tourism Admin Dashboard
 * Handles all user interactions and data management for the admin interface
 */
public class AdminDashboardController implements Initializable {

    // Main UI Components
    @FXML private ScrollPane mainScrollPane;
    @FXML private TabPane mainTabPane;
    
    // Analytics Labels
    @FXML private Label totalBookingsLabel;
    @FXML private Label revenueLabel;
    @FXML private Label activeToursLabel;
    
    // Charts
    @FXML private AreaChart<String, Number> bookingTrendsChart;
    @FXML private CategoryAxis bookingTrendsXAxis;
    @FXML private NumberAxis bookingTrendsYAxis;
    @FXML private PieChart tourTypeChart;
    
    // Booking Management
    @FXML private Button addBookingBtn;
    @FXML private Button editBookingBtn;
    @FXML private Button deleteBookingBtn;
    @FXML private TextField bookingSearchField;
    @FXML private Button searchBookingBtn;
    @FXML private TableView<?> bookingsTable;
    @FXML private TableColumn<?, ?> bookingIdColumn;
    @FXML private TableColumn<?, ?> customerNameColumn;
    @FXML private TableColumn<?, ?> tourNameColumn;
    @FXML private TableColumn<?, ?> bookingDateColumn;
    @FXML private TableColumn<?, ?> statusColumn;
    @FXML private TableColumn<?, ?> totalAmountColumn;
    
    // Tour Management
    @FXML private Button addTourBtn;
    @FXML private Button editTourBtn;
    @FXML private Button deleteTourBtn;
    @FXML private TextField tourSearchField;
    @FXML private Button searchTourBtn;
    @FXML private TableView<?> toursTable;
    @FXML private TableColumn<?, ?> tourIdColumn;
    @FXML private TableColumn<?, ?> tourTitleColumn;
    @FXML private TableColumn<?, ?> destinationColumn;
    @FXML private TableColumn<?, ?> durationColumn;
    @FXML private TableColumn<?, ?> priceColumn;
    @FXML private TableColumn<?, ?> availabilityColumn;
    
    // Customer Management
    @FXML private Button addCustomerBtn;
    @FXML private Button editCustomerBtn;
    @FXML private Button deleteCustomerBtn;
    @FXML private TextField customerSearchField;
    @FXML private Button searchCustomerBtn;
    @FXML private TableView<?> customersTable;
    @FXML private TableColumn<?, ?> customerIdColumn;
    @FXML private TableColumn<?, ?> firstNameColumn;
    @FXML private TableColumn<?, ?> lastNameColumn;
    @FXML private TableColumn<?, ?> emailColumn;
    @FXML private TableColumn<?, ?> phoneColumn;
    @FXML private TableColumn<?, ?> registrationDateColumn;
    
    // Settings
    @FXML private ComboBox<String> currencyComboBox;
    @FXML private TextField taxRateField;
    @FXML private TextField companyNameField;
    @FXML private Button saveSettingsBtn;
    @FXML private Button resetSettingsBtn;
    @FXML private Label dbStatusLabel;
    @FXML private Button testConnectionBtn;
    @FXML private Button backupDatabaseBtn;
    
    // Status Bar
    @FXML private Label statusLabel;
    @FXML private Label userLabel;
    @FXML private Label timeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupComponents();
        setupValidation();
        setupEventHandlers();
        loadInitialData();
        updateTimeLabel();
    }
    
    /**
     * Initialize all UI components and their default states
     */
    private void setupComponents() {
        // Setup currency combo box
        if (currencyComboBox != null) {
            currencyComboBox.setValue("USD");
        }
        
        // Setup initial chart data
        setupCharts();
        
        // Setup table column sizing
        setupTableColumns();
    }
    
    /**
     * Setup input validation for text fields
     */
    private void setupValidation() {
        // Tax rate field validation (numeric only)
        if (taxRateField != null) {
            taxRateField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    taxRateField.setText(oldValue);
                }
            });
        }
        
        // Search field validation and auto-search
        if (bookingSearchField != null) {
            bookingSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
                // Trigger search after 3 characters
                if (newValue.length() >= 3) {
                    performBookingSearch(newValue);
                }
            });
        }
        
        if (tourSearchField != null) {
            tourSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() >= 3) {
                    performTourSearch(newValue);
                }
            });
        }
        
        if (customerSearchField != null) {
            customerSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.length() >= 3) {
                    performCustomerSearch(newValue);
                }
            });
        }
    }
    
    /**
     * Setup event handlers for buttons and other controls
     */
    private void setupEventHandlers() {
        // Booking management handlers
        if (addBookingBtn != null) {
            addBookingBtn.setOnAction(e -> handleAddBooking());
        }
        if (editBookingBtn != null) {
            editBookingBtn.setOnAction(e -> handleEditBooking());
        }
        if (deleteBookingBtn != null) {
            deleteBookingBtn.setOnAction(e -> handleDeleteBooking());
        }
        
        // Tour management handlers
        if (addTourBtn != null) {
            addTourBtn.setOnAction(e -> handleAddTour());
        }
        if (editTourBtn != null) {
            editTourBtn.setOnAction(e -> handleEditTour());
        }
        if (deleteTourBtn != null) {
            deleteTourBtn.setOnAction(e -> handleDeleteTour());
        }
        
        // Customer management handlers
        if (addCustomerBtn != null) {
            addCustomerBtn.setOnAction(e -> handleAddCustomer());
        }
        if (editCustomerBtn != null) {
            editCustomerBtn.setOnAction(e -> handleEditCustomer());
        }
        if (deleteCustomerBtn != null) {
            deleteCustomerBtn.setOnAction(e -> handleDeleteCustomer());
        }
        
        // Settings handlers
        if (saveSettingsBtn != null) {
            saveSettingsBtn.setOnAction(e -> handleSaveSettings());
        }
        if (resetSettingsBtn != null) {
            resetSettingsBtn.setOnAction(e -> handleResetSettings());
        }
        if (testConnectionBtn != null) {
            testConnectionBtn.setOnAction(e -> handleTestConnection());
        }
        if (backupDatabaseBtn != null) {
            backupDatabaseBtn.setOnAction(e -> handleBackupDatabase());
        }
    }
    
    /**
     * Setup charts with sample data
     */
    private void setupCharts() {
        if (bookingTrendsChart != null) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Bookings");
            series.getData().add(new XYChart.Data<>("Jan", 65));
            series.getData().add(new XYChart.Data<>("Feb", 85));
            series.getData().add(new XYChart.Data<>("Mar", 120));
            series.getData().add(new XYChart.Data<>("Apr", 98));
            series.getData().add(new XYChart.Data<>("May", 145));
            bookingTrendsChart.getData().add(series);
        }
        
        if (tourTypeChart != null) {
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Adventure Tours", 35),
                new PieChart.Data("Cultural Tours", 25),
                new PieChart.Data("Nature Tours", 20),
                new PieChart.Data("City Tours", 15),
                new PieChart.Data("Food Tours", 5)
            );
            tourTypeChart.setData(pieChartData);
        }
    }
    
    /**
     * Setup table column properties
     */
    private void setupTableColumns() {
        // Booking table columns setup would go here
        // Tour table columns setup would go here
        // Customer table columns setup would go here
    }
    
    /**
     * Load initial data for all tables and charts
     */
    private void loadInitialData() {
        updateStatusLabel("Loading initial data...");
        // Load sample data or connect to database
        updateStatusLabel("Ready");
    }
    
    /**
     * Update the time label with current time
     */
    private void updateTimeLabel() {
        if (timeLabel != null) {
            LocalDateTime now = LocalDateTime.now();
            timeLabel.setText(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
    }
    
    /**
     * Update status label
     */
    private void updateStatusLabel(String message) {
        if (statusLabel != null) {
            statusLabel.setText(message);
        }
    }
    
    // Event handler methods
    private void handleAddBooking() {
        updateStatusLabel("Adding new booking...");
        // Implementation for adding booking
    }
    
    private void handleEditBooking() {
        updateStatusLabel("Editing booking...");
        // Implementation for editing booking
    }
    
    private void handleDeleteBooking() {
        updateStatusLabel("Deleting booking...");
        // Implementation for deleting booking
    }
    
    private void handleAddTour() {
        updateStatusLabel("Adding new tour...");
        // Implementation for adding tour
    }
    
    private void handleEditTour() {
        updateStatusLabel("Editing tour...");
        // Implementation for editing tour
    }
    
    private void handleDeleteTour() {
        updateStatusLabel("Deleting tour...");
        // Implementation for deleting tour
    }
    
    private void handleAddCustomer() {
        updateStatusLabel("Adding new customer...");
        // Implementation for adding customer
    }
    
    private void handleEditCustomer() {
        updateStatusLabel("Editing customer...");
        // Implementation for editing customer
    }
    
    private void handleDeleteCustomer() {
        updateStatusLabel("Deleting customer...");
        // Implementation for deleting customer
    }
    
    private void handleSaveSettings() {
        updateStatusLabel("Saving settings...");
        // Implementation for saving settings
    }
    
    private void handleResetSettings() {
        updateStatusLabel("Resetting settings...");
        // Implementation for resetting settings
    }
    
    private void handleTestConnection() {
        updateStatusLabel("Testing database connection...");
        // Implementation for testing connection
    }
    
    private void handleBackupDatabase() {
        updateStatusLabel("Creating database backup...");
        // Implementation for database backup
    }
    
    // Search methods
    private void performBookingSearch(String searchTerm) {
        // Implementation for booking search
    }
    
    private void performTourSearch(String searchTerm) {
        // Implementation for tour search
    }
    
    private void performCustomerSearch(String searchTerm) {
        // Implementation for customer search
    }
}