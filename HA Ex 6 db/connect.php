<?php
$servername = "localhost";
$username = "root";  // Your DB username
$password = "";      // Your DB password
$database = "home_appliance"; // Database for home appliance theme

// Create connection
$conn = new mysqli($servername, $username, $password, $database);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
echo "Connected successfully";
?>
