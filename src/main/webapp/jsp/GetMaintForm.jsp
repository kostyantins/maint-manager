<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Maint Form</title>
</head>
<body align="center">
<form action="maint" method="post">
    Maint identifier : <input type="text" name="maintIdentifier"><br>
    Capability id : <input type="number" name="capabilityId"><br>
    Created data : <input type="text" name="createdData"><br>
    Due data : <input type="text" name="dueData"><br>
    Solve priority id : <input type="number" name="solvePriorityId"><br>
    Estimate : <input type="number" name="estimate"><br>
    Client : <input type="text" name="client"><br>
    <input type="submit" value="Add maint">
</form>
</body>
</html>