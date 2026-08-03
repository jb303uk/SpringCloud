@import uk.gov.hmrc.entity.Catches
@import java.util.List

@param uk.gov.hmrc.entity.Catches Catches
@param String success = null

@param List<Catches> catches

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HR Schema - Add Catches</title>
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
    <h2>Throw a Pokeball <a href="links">(Links)</a></h2>
    

    <form action="/" method="POST">

        <button type="submit">Throw</button>
    </form>
    </div>
    @if(catches.isEmpty())
        <div class="no-data">Nothing found yet</div>
    @else
        <table>
            <thead>
                <tr>
                    <th>Catch ID</th>
                </tr>
            </thead>
            <tbody>
                @for(Catches ch : catches)
                    <tr>
                        <td>${ch.getCatchId()}</td>
                    </tr>
                @endfor
            </tbody>
        </table>
    @endif
</body>
</html>
