<?php
include 'connect.php';
?>
<!DOCTYPE html>
<html>
<head>
    <title>Home Appliance Store</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>


    <main>
        <h1>Welcome to the Home Appliance Store</h1>
        <p>Our collection of appliances:</p>
        <ul>
        <?php
        $sql = "SELECT name, type, price FROM home_appliances";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
            while ($row = $result->fetch_assoc()) {
                echo "<li>" . $row["name"] . " (" . $row["type"] . ") - $" . $row["price"] . "</li>";
            }
        } else {
            echo "No appliances available.";
        }
        ?>
        </ul>
    </main>

</body>
</html>
