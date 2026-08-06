@import org.springframework.data.domain.Page
@import uk.gov.hmrc.entity.CatchSummary

@param Page<CatchSummary> allCatches
@param String sortAll
@param String dirAll
@param String sortHigh
@param String dirHigh

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catches</title>
    <style>
        body { font-family: sans-serif; margin: 20px; }
        table { width: 100%; border-collapse: collapse; margin-bottom: 15px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f4f4f4; }
        .pagination { margin-bottom: 40px; }
        .pagination a { padding: 8px 12px; border: 1px solid #ddd; text-decoration: none; margin-right: 5px; }
        .active { background-color: #007bff; color: white; border-color: #007bff; }
    </style>
</head>
<body>

    <h1>HR Department Dashboard</h1>

    <!-- SECTION 1: ALL EMPLOYEES -->
    <h2>All Employees</h2>
    <table>
        <thead>
            <tr>
                <th><a href="?sortAll=id&dirAll=${dirAll.equals("asc") ? "desc" : "asc"}&pageHigh=zzz&sortHigh=${sortHigh}&dirHigh=${dirHigh}">ID</a></th>
                <th><a href="?sortAll=firstName&dirAll=${dirAll.equals("asc") ? "desc" : "asc"}&pageHigh=xxx&sortHigh=${sortHigh}&dirHigh=${dirHigh}">Name</a></th>
                <th><a href="?sortAll=lastName&dirAll=${dirAll.equals("asc") ? "desc" : "asc"}&pageHigh=yyy&sortHigh=${sortHigh}&dirHigh=${dirHigh}">Is Shiny</a></th>
                <th><a href="?sortAll=lastName&dirAll=${dirAll.equals("asc") ? "desc" : "asc"}&pageHigh=yyy&sortHigh=${sortHigh}&dirHigh=${dirHigh}">IVs</a></th>
                <th>Salary</th>
            </tr>
        </thead>
        <tbody>
            @for(CatchSummary emp : allCatches.getContent())
                <tr>
                    <td>${emp.getCatchId()}</td>
                    <td>${emp.getPokemonName()}</td>
                    <td>${emp.getIsShiny()}</td>
                    <td>${emp.getIVsAttack()} / {emp.getIVsdefense()} / {emp.getIVsstamina()}</td>
                </tr>
            @endfor
        </tbody>
    </table>

    <div class="pagination">
        @for(int i = 0; i < allCatches.getTotalPages(); i++)
            <a href="?pageAll=${i}&sortAll=${sortAll}&dirAll=${dirAll}&pageHigh=aaa&sortHigh=${sortHigh}&dirHigh=${dirHigh}" 
               class="${i == allCatches.getNumber() ? "active" : ""}">
               ${i + 1}
            </a>
        @endfor
    </div>

</body>
</html>
