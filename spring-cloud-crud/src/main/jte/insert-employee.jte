@param uk.gov.hmrc.entity.Employee employee
@param String success = null

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HR Schema - Add Employee</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f4f9; }
        .container { max-width: 500px; background: white; padding: 25px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; color: #333; }
        input { width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        button { width: 100%; padding: 10px; background-color: #005a9c; color: white; border: none; border-radius: 4px; font-size: 16px; cursor: pointer; }
        button:hover { background-color: #004477; }
        .alert { padding: 12px; background-color: #d4edda; color: #155724; border-radius: 4px; margin-bottom: 15px; }
    </style>
</head>
<body>

<div class="container">
    <h2>Add New HR Employee</h2>

    @if(success != null)
        <div class="alert">Employee successfully recorded in HR system!</div>
    @endif

    <form action="/web/employees/new" method="POST">
        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" value="${employee.getFirstName()}" />
        </div>

        <div class="form-group">
            <label for="lastName">Last Name *:</label>
            <input type="text" id="lastName" name="lastName" value="${employee.getLastName()}" required />
        </div>

        <div class="form-group">
            <label for="email">Email *:</label>
            <input type="email" id="email" name="email" value="${employee.getEmail()}" required />
        </div>

        <div class="form-group">
            <label for="jobId">Job ID *:</label>
            <input type="text" id="jobId" name="jobId" value="${employee.getJobId()}" placeholder="e.g. IT_PROG" required />
        </div>

        <div class="form-group">
            <label for="hireDate">Hire Date *:</label>
            <input type="date" id="hireDate" name="hireDate" value="${employee.getHireDate() != null ? employee.getHireDate().toString() : ""}" required />
        </div>

        <div class="form-group">
            <label for="salary">Salary:</label>
            <input type="number" id="salary" name="salary" step="0.01" value="${employee.getSalary() != null ? employee.getSalary().toString() : ""}" />
        </div>

        <button type="submit">Submit Record</button>
    </form>
</div>

</body>
</html>
