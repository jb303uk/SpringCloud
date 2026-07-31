@import uk.gov.hmrc.entity.Employee
@import java.util.List

@param uk.gov.hmrc.entity.Employee employee
@param String success = null

@param List<Employee> employees

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
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #cccccc; padding: 10px; text-align: left; }
        th { background-color: #eaeff2; color: #333333; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .no-data { text-align: center; color: #777777; font-style: italic; padding: 20px; }
        
    </style>
</head>
<body>

<div class="container">
    <h2>Add New HR Employee <a href="links">Links</a></h2>

    @if(success != null)
        <div class="alert">Employee successfully recorded in HR system!</div>
    @endif
        
    <form action="/" method="POST">
        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" value="James" />
        </div>

        <div class="form-group">
            <label for="lastName">Last Name *:</label>
            <input type="text" id="lastName" name="lastName" value="Beardsley" required />
        </div>

        <div class="form-group">
            <label for="email">Email *:</label>
            <input type="email" id="email" name="email" value="JBEARDSLEY@HMRC" required />
        </div>

        <div class="form-group">
            <label for="jobId">Job ID *:</label>
            <input type="text" id="jobId" name="jobId" value="IT_PROG" placeholder="e.g. IT_PROG" required />
        </div>

        <div class="form-group">
            <label for="hireDate">Hire Date *:</label>
            <input type="date" id="hireDate" name="hireDate" value="${employee.getHireDate() != null ? employee.getHireDate().toString() : ""}" required />
        </div>

        <div class="form-group">
            <label for="salary">Salary:</label>
            <input type="number" id="salary" name="salary" step="0.01" value="${employee.getSalary() != null ? employee.getSalary().toString() : "50"}" />
        </div>

        <button type="submit">Submit Record</button>
    </form>
    </div>
    @if(employees.isEmpty())
        <div class="no-data">No employee data returned from the Oracle HR schema.</div>
    @else
        <table>
            <thead>
                <tr>
                    <th>Employee ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email Address</th>
                    <th>Hire Date</th>
                    <th>Salary</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                @for(Employee emp : employees)
                    <tr>
                        <td>${emp.getEmployeeId()}</td>
                        <td>${emp.getFirstName()}</td>
                        <td>${emp.getLastName()}</td>
                        <td>${emp.getEmail()}</td>
                        <td>${emp.getHireDate().toString()}</td>
                        <td>
                            @if(emp.getSalary() != null)
                                $${String.format("%,.2f", emp.getSalary())}
                            @else
                                N/A
                            @endif
                        </td>
                    </tr>
                @endfor
            </tbody>
        </table>
    @endif
</body>
</html>
