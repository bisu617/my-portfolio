# Tourism Admin Dashboard - JavaFX Implementation

This project includes a comprehensive JavaFX Admin Dashboard for tourism management with modern UI design and responsive layout.

## Features

### 1. Enhanced FXML Layout (admin_dashboard.fxml)
- **Fixed XML Declaration**: Proper XML version declaration positioning
- **JavaFX 17 Compatibility**: Updated from version 23.0.1 to 17 for better compatibility
- **Responsive Design**: ScrollPane implementation for overflow content handling
- **Proper Control IDs**: All controls have appropriate fx:id attributes for controller binding

### 2. Professional CSS Styling (admin_dashboard.css)
- **Modern Color Scheme**: Professional gradient headers and card-based layouts
- **Button Styling**: Primary, secondary, and danger button styles with hover effects
- **Table Styling**: Enhanced table appearance with shadows and proper spacing
- **Form Controls**: Styled text fields, combo boxes, and form elements
- **Chart Styling**: Professional chart appearance with shadows and padding

### 3. Comprehensive Controller (AdminDashboardController.java)
- **Input Validation**: Text field validation for numeric inputs and search functionality
- **Event Handling**: Complete event handlers for all buttons and controls
- **Data Management**: Sample data loading for charts and tables
- **Real-time Updates**: Time label updates and status notifications

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── module-info.java
│   │   └── com/tourism/
│   │       ├── TourismAdminApp.java (Main Application)
│   │       └── controllers/
│   │           └── AdminDashboardController.java
│   └── resources/
│       ├── fxml/
│       │   └── admin_dashboard.fxml
│       └── styles/
│           └── admin_dashboard.css
└── pom.xml (Maven configuration)
```

## Dashboard Sections

### 1. Overview Tab
- **Analytics Cards**: Total bookings, revenue, and active tours
- **Charts**: Booking trends (Area Chart) and tour types distribution (Pie Chart)

### 2. Bookings Management
- **CRUD Operations**: Add, edit, delete bookings
- **Search Functionality**: Real-time search with validation
- **Data Table**: Comprehensive booking information display

### 3. Tours Management
- **Tour Administration**: Complete tour lifecycle management
- **Search and Filter**: Advanced search capabilities
- **Table View**: Detailed tour information display

### 4. Customer Management
- **Customer Database**: Complete customer information management
- **Contact Management**: Email and phone number tracking
- **Registration Tracking**: Customer registration date monitoring

### 5. Settings Tab
- **Application Settings**: Currency, tax rate, and company configuration
- **Database Management**: Connection status and backup functionality

## Technical Specifications

### Requirements Met:
- ✅ XML version declaration fix
- ✅ JavaFX version updated to 17
- ✅ CSS stylesheet integration
- ✅ Enhanced spacing and alignment
- ✅ ScrollPane for responsive overflow handling
- ✅ Complete fx:id attributes for all controls
- ✅ Input validation implementation
- ✅ Proper controller bindings

### UI Enhancements:
- **Responsive Layout**: Adapts to window resizing
- **Professional Styling**: Modern color scheme and typography
- **User Experience**: Intuitive navigation and clear visual hierarchy
- **Data Visualization**: Interactive charts for analytics
- **Status Feedback**: Real-time status updates and notifications

## Running the Application

### Prerequisites:
- Java 17 or higher
- Maven 3.6 or higher

### Commands:
```bash
# Build the project
mvn clean compile

# Run the application
mvn javafx:run

# Package the application
mvn clean package
```

## Styling Classes

The CSS includes the following key style classes:
- `.header-section`: Gradient header styling
- `.analytics-card`: Card-based analytics display
- `.action-button`: Base button styling
- `.primary-button`, `.secondary-button`, `.danger-button`: Button variants
- `.table-view`: Enhanced table appearance
- `.chart`: Professional chart styling
- `.content-vbox`: Proper spacing for content areas

## Future Enhancements

- Database integration for real data management
- User authentication and role-based access
- Report generation and export functionality
- Real-time notifications and alerts
- Multi-language support
- Advanced filtering and sorting capabilities