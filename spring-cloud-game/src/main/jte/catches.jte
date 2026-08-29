@import uk.co.jb303.entity.Catches
@import java.util.List
@import org.springframework.data.domain.Window
@import uk.co.jb303.entity.CatchSummary
@import uk.co.jb303.service.SpriteService

@param uk.co.jb303.entity.Catches Catches
@param String success = null
@param String USERUUID

@param List<Catches> catches
@param Window<CatchSummary> allCatches
@param String sortAll
@param String dirAll
@param int sizeAll
@param Boolean hasNext
@param Long nextLastCatchId

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- <meta name="color-scheme" content="light dark"> -->
    <title>🥚</title>
    <style>
    body { font-family: Arial, sans-serif; margin: 10px; background-color: #f4f4f9; }
    .container { max-width: 500px; background: white; padding: 10px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
    .form-group { margin-bottom: 15px; }
    label { display: block; margin-bottom: 5px; font-weight: bold; color: #333; }
    input { width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
    button { width: 100%; padding: 1px; background-color: #005a9c; color: white; border: none; border-radius: 4px; font-size: 40px; cursor: pointer; }
    button:hover { background-color: #004477; }
    .alert { padding: 12px; background-color: #d4edda; color: #155724; border-radius: 4px; margin-bottom: 15px; }
    table { width: 100%; border-collapse: collapse; margin-top: 15px; }
    th, td { border: 1px solid #cccccc; padding: 5px; }
    th { background-color: #eaeff2; color: #333333; }
    tr:nth-child(even) { background-color: #f9f9f9; }
    .no-data { text-align: center; color: #777777; font-style: italic; padding: 20px; }
    .pagination { display: flex; gap: 10px; list-style: none; }
    .disabled { color: #ccc; pointer-events: none; }
    </style>
</head>
<body>
<div class="container">
    <form action="/" method="POST">
        <button type="submit">🥚</button>
        <input type="hidden" id="USERUUID" name="USERUUID" value="${USERUUID}"> 
    </form>
    </div>
    <table>
        <thead>
            <tr>
                <th><a href="?sortAll=isShiny&dirAll=${dirAll.equals("asc") ? "asc" : "desc"}">Icon</a></th>
                <th><a href="?sortAll=catchId&dirAll=${dirAll.equals("asc") ? "desc" : "asc"}">Count</a></th>
                <th><a href="?sortAll=speciesId&dirAll=${dirAll.equals("asc") ? "desc" : "asc"}">#</a></th>
                <th><a href="?sortAll=pokemonName&dirAll=${dirAll.equals("asc") ? "desc" : "asc"}">Name</a></th>
                <th><a href="?sortAll=ivsPercent&dirAll=${dirAll.equals("asc") ? "asc" : "desc"}">IVs %</a></th>
            </tr>
        </thead>
        <tbody>
            @for(CatchSummary catchRecord : allCatches.getContent())
                <tr>
                    <td><img class="sprite" 
                         src="${SpriteService.getSpriteDataUri(catchRecord.getSpeciesId(), catchRecord.getIsShiny())}" 
                         alt="${catchRecord.getPokemonName()}" 
                         loading="lazy" />${catchRecord.getShinyIcon()}</td>
                    <td>${catchRecord.getCatchId()}</td>
                    <td>${catchRecord.getSpeciesId()}</td>
                    <td>${catchRecord.getPokemonName()}</td>
                    <td>${catchRecord.getIVsPercent()}</td>
                </tr>
            @endfor
        </tbody>
    </table>

    <nav>
        <ul class="pagination">
                <li>
                    <a href="?sizeAll=${sizeAll}&sortAll=${sortAll}&dirAll=${dirAll}">« Previous</a>
                </li>
                @if(hasNext && nextLastCatchId != null)
                <li>
                    <a href="?lastCatchId=${nextLastCatchId}&sizeAll=${sizeAll}&sortAll=${sortAll}&dirAll=${dirAll}">Next »</a>
                </li>
                @endif
        </ul>
    </nav>
</body>
</html>
