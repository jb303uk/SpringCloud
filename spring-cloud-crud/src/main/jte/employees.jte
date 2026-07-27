@import uk.gov.hmrc.entity.Employee
@import java.util.List

@param List<Employee> employees

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Oracle HR - Employees Table</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #cccccc; padding: 10px; text-align: left; }
        th { background-color: #eaeff2; color: #333333; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .no-data { text-align: center; color: #777777; font-style: italic; padding: 20px; }
    </style>
</head>
<body>

    <h2>Oracle HR Schema: Employees Record Management</h2>

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
